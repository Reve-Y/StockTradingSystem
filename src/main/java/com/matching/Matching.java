package com.matching;

import com.dao.EntrustDao;
import com.domain.CurrentEntrust;
import com.domain.StockInfo;
import com.service.interfaces.DataService;
import com.service.interfaces.DealService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 这个线程正常运行的前提是网络连接正常。否则可能会有某些意外情况。
 */
@Service
public class Matching implements Runnable{
    private static Logger log= Logger.getLogger(Matching.class);

    // 用来存放委托序列,使用有序的、非线程安全的 LinkedHashMap
    //   注意 ： LinkedHashMap 每次访问一个元素（get或put），被访问的元素都被提到最后面去了
    private static Map<String,CurrentEntrust> entrustMap = new LinkedHashMap<>();

    // 为了防止集合的快速失败机制抛出异常，制造了一个存放待更新的entrust_key的map
    // 键为entrust_key,值为更新类型。使用线程安全的ConcurrentHashMap
    // 这样在执行时判断当前entrust_key是否被废弃了,在一轮判断结束以后，更新entrustMap
    private static ConcurrentMap<String,Integer> waitForUpdatedKeyMap = new ConcurrentHashMap();

    @Autowired
    private EntrustDao entrustDao;

    @Autowired
    private DealService dealService;

    @Autowired
    private DataService dataService;

    /**
     * 初始化 entrustMap：读取所有的委托，然后排序，放入entrustMap中。
     */
    public void init(){
        List<CurrentEntrust> entrustList = entrustDao.queryAllCurrentEntrust();
        sort(entrustList);
        for (CurrentEntrust ce : entrustList){
            entrustMap.put(ce.getEntrust_key(),ce);
        }
        log.info("Matching -- 委托序列初始化完成");
    }

    /**
     * 向waitForUpdatedKeyMap里添加一条记录。
     * @param entrust_key
     * @param entrust_type
     */
    public static void updateWithdrawalKeyMap(String entrust_key, int entrust_type){
        waitForUpdatedKeyMap.put(entrust_key,entrust_type);
        log.info("待更新队列已加入记录："+entrust_key);
    }

    /**
     * 更新entrustMap,根据waitForUpdatedKeyMap里的键值对更新
     * 为了防止更新时有新的撤单等操作，更新前先深拷贝waitForUpdatedKeyMap到新的map，遍历时再从原map里逐个移除
     * 更新步骤：如果键值对的值是1（新的记录），则读表并放入entrustMap里
     *          如果键值对的值是2（撤单），则从entrustMap里删除该记录
     *          如果键值对的值是3（更新），则更新记录
     */
    public void refresh(){
        HashMap map = new HashMap();
        map.putAll(waitForUpdatedKeyMap);
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,Integer> entry = (Map.Entry<String, Integer>) iterator.next();
            if (entry.getValue() == 1){
                CurrentEntrust ce = entrustDao.getCurrentEntrustDetailByKey(entry.getKey());
                entrustMap.put(entry.getKey(),ce);
                log.info("entrustMap新增记录"+entry.getKey());
            } else if (entry.getValue() == 2){
                entrustMap.remove(entry.getKey());
                log.info("entrustMap已移除记录："+entry.getKey());
            } else if (entry.getValue() == 3){
                CurrentEntrust ce = entrustDao.getCurrentEntrustDetailByKey(entry.getKey());
                entrustMap.replace(entry.getKey(),ce);
                log.info("entrustMap已更新记录："+entry.getKey());
            }
            waitForUpdatedKeyMap.remove(entry.getKey());  //  更新过后就从map里删除。
        }
//        sort(new ArrayList<>(entrustMap.values()));
    }

    /**
     * 对委托进行排序：价格优先 时间优先。
     * 由于本系统是模拟交易，不是真正的撮合，股票的行情数据实时获取，所以没有排序的必要
     */
    public void sort(List<CurrentEntrust> list){

    }

    /**
     * 执行：判断每笔委托是否满足交易成功的条件。
     */
    public void execute(){
        boolean flag = true;
        int count = 1 ;

        while (flag) {
            log.info("第 "+count+" 次匹配交易规则 ... ");

            Iterator iterator = entrustMap.entrySet().iterator();
            while (iterator.hasNext()){
                try{
                    Map.Entry<String,CurrentEntrust> entry = (Map.Entry<String, CurrentEntrust>) iterator.next();

                    // 判断这个键值对存放的记录是否已经被撤单了
                    if (waitForUpdatedKeyMap.containsKey(entry.getKey())){
                        log.info("记录："+entry.getKey()+"已被撤单或者更新，执行下一条记录");
                        continue;
                    }

                    CurrentEntrust ce = entry.getValue();
                    if (ce.getEntrust_direction() == 1){
                        doBuyEntrust(ce);
                    }else {
                        doSellEntrust(ce);
                    }
                }catch (ConcurrentModificationException e){
                    log.info("捕获到由于快速失败机制导致的异常。进行下一轮匹配");
                    refresh();
                    break;
                }
            }
            ++count;
            delayAndRefresh();
        }
    }

    /**
     * 判断委托方向为买入的委托是否能交易成功，规则参考文档（烂）
     */
    public void doBuyEntrust(CurrentEntrust ce) {
        log.info("获取到委托："+ce.getEntrust_key()+",开始计算");

        StringBuilder stock_code = new StringBuilder();
        if (ce.getStock_code().charAt(0) == '6')
            stock_code.append("sh"+ce.getStock_code());
        else
            stock_code.append("sz"+ce.getStock_code());
        StockInfo stockInfo = dataService.getBasicStockInfo(stock_code.toString());
        if (stockInfo.getBuy_price1() == 0){
            log.info("获取不到股票相关信息，跳过此次执行。");
            return;
        }
        float entrust_price = ce.getEntrust_price();
        int entrust_amount = (int) ce.getEntrust_amount();

        // 买入价 大于 卖五价时
        if (entrust_price >= stockInfo.getSale_price5()){
            // 买入数量 小于 卖五数量 : 按照卖五价全部成交
            if (entrust_amount <= stockInfo.getSale_amount5()){
                dealService.allClinchADeal(ce.getEntrust_key(),stockInfo.getSale_price5());
                updateWithdrawalKeyMap(ce.getEntrust_key(), 2);
                log.info("委托："+ce.getEntrust_key()+"全部成交");

            } else if (entrust_amount > stockInfo.getSale_amount5() && entrust_amount <= calTotalSaleAmount(stockInfo,4)){
                dealService.someClinchADeal(ce.getEntrust_key(),stockInfo.getSale_price5(),stockInfo.getSale_amount5());
                dealService.allClinchADeal(ce.getEntrust_key(),stockInfo.getSale_price4());
                updateWithdrawalKeyMap(ce.getEntrust_key(),2);
                log.info("委托："+ce.getEntrust_key()+"全部成交");

            } else if (entrust_amount > calTotalSaleAmount(stockInfo,4) && entrust_amount <= calTotalSaleAmount(stockInfo,3)){
                dealService.someClinchADeal(ce.getEntrust_key(),stockInfo.getSale_price5(),stockInfo.getSale_amount5());
                dealService.someClinchADeal(ce.getEntrust_key(),stockInfo.getSale_price4(),stockInfo.getSale_amount4());
                dealService.allClinchADeal(ce.getEntrust_key(),stockInfo.getSale_price3());
                updateWithdrawalKeyMap(ce.getEntrust_key(),2);
                log.info("委托："+ce.getEntrust_key()+"全部成交");

            } else if (entrust_amount > calTotalSaleAmount(stockInfo,3) && entrust_amount <= calTotalSaleAmount(stockInfo,2)){
                dealService.someClinchADeal(ce.getEntrust_key(),stockInfo.getSale_price5(),stockInfo.getSale_amount5());
                dealService.someClinchADeal(ce.getEntrust_key(),stockInfo.getSale_price4(),stockInfo.getSale_amount4());
                dealService.someClinchADeal(ce.getEntrust_key(),stockInfo.getSale_price3(),stockInfo.getSale_amount3());
                dealService.allClinchADeal(ce.getEntrust_key(),stockInfo.getSale_price2());
                updateWithdrawalKeyMap(ce.getEntrust_key(),2);
                log.info("委托："+ce.getEntrust_key()+"全部成交");

            } else if (entrust_amount > calTotalSaleAmount(stockInfo,2) && entrust_amount <= calTotalSaleAmount(stockInfo,1)){
                dealService.someClinchADeal(ce.getEntrust_key(),stockInfo.getSale_price5(),stockInfo.getSale_amount5());
                dealService.someClinchADeal(ce.getEntrust_key(),stockInfo.getSale_price4(),stockInfo.getSale_amount4());
                dealService.someClinchADeal(ce.getEntrust_key(),stockInfo.getSale_price3(),stockInfo.getSale_amount3());
                dealService.someClinchADeal(ce.getEntrust_key(),stockInfo.getSale_price2(),stockInfo.getSale_amount2());
                dealService.allClinchADeal(ce.getEntrust_key(),stockInfo.getSale_price1());
                updateWithdrawalKeyMap(ce.getEntrust_key(),2);
                log.info("委托："+ce.getEntrust_key()+"全部成交");

            } else if (entrust_amount > calTotalSaleAmount(stockInfo,1)){
                dealService.someClinchADeal(ce.getEntrust_key(),stockInfo.getSale_price5(),stockInfo.getSale_amount5());
                dealService.someClinchADeal(ce.getEntrust_key(),stockInfo.getSale_price4(),stockInfo.getSale_amount4());
                dealService.someClinchADeal(ce.getEntrust_key(),stockInfo.getSale_price3(),stockInfo.getSale_amount3());
                dealService.someClinchADeal(ce.getEntrust_key(),stockInfo.getSale_price2(),stockInfo.getSale_amount2());
                dealService.someClinchADeal(ce.getEntrust_key(),stockInfo.getSale_price1(),stockInfo.getSale_amount1());
                updateWithdrawalKeyMap(ce.getEntrust_key(),3);
                log.info("委托："+ce.getEntrust_key()+"部分成交");
            }

            // 买一到卖五之间逐个分析数量、价格的话，分支太多了  这里就意思意思了  不细写了
        } else if (entrust_price < stockInfo.getSale_price5() && entrust_price >= stockInfo.getSale_price4()){
            dealService.allClinchADeal(ce.getEntrust_key(),stockInfo.getSale_price4());
            updateWithdrawalKeyMap(ce.getEntrust_key(),2);
        } else if (entrust_price < stockInfo.getSale_price4() && entrust_price >= stockInfo.getSale_price3()){
            dealService.allClinchADeal(ce.getEntrust_key(),stockInfo.getSale_price3());
            updateWithdrawalKeyMap(ce.getEntrust_key(),2);
        } else if (entrust_price < stockInfo.getSale_price3() && entrust_price >= stockInfo.getSale_price2()){
            dealService.allClinchADeal(ce.getEntrust_key(),stockInfo.getSale_price2());
            updateWithdrawalKeyMap(ce.getEntrust_key(),2);
        } else if (entrust_price < stockInfo.getSale_price2() && entrust_price >= stockInfo.getSale_price1()){
            dealService.allClinchADeal(ce.getEntrust_key(),stockInfo.getSale_price1());
            updateWithdrawalKeyMap(ce.getEntrust_key(),2);
        }
    }

    /**
     * 判断委托方向为卖出的委托是否能交易成功
     */
    public void doSellEntrust(CurrentEntrust ce) {
        log.info("获取到委托："+ce.getEntrust_key()+",开始计算");
        StringBuilder stock_code = new StringBuilder();
        if (ce.getStock_code().charAt(0) == '6')
            stock_code.append("sh"+ce.getStock_code());
        else
            stock_code.append("sz"+ce.getStock_code());
        StockInfo stockInfo = dataService.getBasicStockInfo(stock_code.toString());
        if (stockInfo.getBuy_price1() == 0){
            log.info("获取不到股票相关信息，跳过此次执行。");
            return;
        }
        float entrust_price = ce.getEntrust_price();
        int entrust_amount = (int) ce.getEntrust_amount();

        // 如果卖出价小于等于买一价大于买二价：按买一价成交。
        if (entrust_price > stockInfo.getBuy_price2() && entrust_price <= stockInfo.getBuy_price1()){
            if (entrust_amount <= stockInfo.getBuy_amount1()) {
                dealService.sellAll(ce.getEntrust_key(), stockInfo.getBuy_price1()); // 全部成交
                updateWithdrawalKeyMap(ce.getEntrust_key(),2);
            } else {
                dealService.sellSome(ce.getEntrust_key(),stockInfo.getBuy_price1(),stockInfo.getBuy_amount1()); // 部分成交
                updateWithdrawalKeyMap(ce.getEntrust_key(),3);
            }
        } else if (entrust_price > stockInfo.getBuy_price3() && entrust_price <= stockInfo.getBuy_price2()){
            // 卖出价小于等于买二价 大于买三价
             if (entrust_amount <= stockInfo.getBuy_amount2()){
                 // 卖出数量小与买二数量，以买二价全部卖出成交
                 dealService.sellAll(ce.getEntrust_key(),stockInfo.getBuy_price2());
                 updateWithdrawalKeyMap(ce.getEntrust_key(),2);
             } else if (entrust_amount <= calTotalBuyAmount(stockInfo,2) && entrust_amount > stockInfo.getBuy_amount2()){
                 // 卖出数量 小于买一数量与买二数量之和且大于买二数量 : 以买二价、买二数量部分成交 ，剩下的部分以买一价全部成交
                 dealService.sellSome(ce.getEntrust_key(),stockInfo.getBuy_price2(),stockInfo.getBuy_amount2());
                 dealService.sellAll(ce.getEntrust_key(),stockInfo.getBuy_price1());
                 updateWithdrawalKeyMap(ce.getEntrust_key(),2);
             } else {
                 // 大于买一买二数量之和 则按买一买二的价格和数量分别部分成交。
                 dealService.sellSome(ce.getEntrust_key(),stockInfo.getBuy_price2(),stockInfo.getBuy_amount2());
                 dealService.sellSome(ce.getEntrust_key(),stockInfo.getBuy_price1(),stockInfo.getBuy_amount1());
                 updateWithdrawalKeyMap(ce.getEntrust_key(),3);
             }
        } else if (entrust_price > stockInfo.getBuy_price4() && entrust_price <= stockInfo.getBuy_price3()){
            // 卖出价小于等于买三价 大于买四价
            if (entrust_amount <= stockInfo.getBuy_amount3()){
                // 卖出数量小与买三数量，以买三价全部卖出成交
                dealService.sellAll(ce.getEntrust_key(),stockInfo.getBuy_price3());
                updateWithdrawalKeyMap(ce.getEntrust_key(),2);
            } else if (entrust_amount <= stockInfo.getBuy_amount2()+stockInfo.getBuy_amount3() && entrust_amount > stockInfo.getBuy_amount3()){
                // 卖出数量 小于买二数量与买三数量之和且大于买三数量 : 以买三价、买三数量部分成交 ，剩下的部分以买二价全部成交
                dealService.sellSome(ce.getEntrust_key(),stockInfo.getBuy_price3(),stockInfo.getBuy_amount3());
                dealService.sellAll(ce.getEntrust_key(),stockInfo.getBuy_price2());
                updateWithdrawalKeyMap(ce.getEntrust_key(),2);
            } else if (entrust_amount > stockInfo.getBuy_amount2()+stockInfo.getBuy_amount3() && entrust_amount > calTotalBuyAmount(stockInfo,3)) {
                // 卖出数量 大于买二数量与买三数量之和且小于买一、二、三数量和 : 以买三价、买三数量部分成交 ，买二价、买二数量部分成交，剩下以买一价全部成交
                dealService.sellSome(ce.getEntrust_key(),stockInfo.getBuy_price3(),stockInfo.getBuy_amount3());
                dealService.sellSome(ce.getEntrust_key(),stockInfo.getBuy_price2(),stockInfo.getBuy_amount2());
                dealService.sellAll(ce.getEntrust_key(),stockInfo.getBuy_price1());
                updateWithdrawalKeyMap(ce.getEntrust_key(),2);
            }
        } else if (entrust_price > stockInfo.getBuy_price5() && entrust_price <= stockInfo.getBuy_price4()){
            // 卖出价小于等于买四价 大于买五价
            if (entrust_amount <= stockInfo.getBuy_amount4()){
                dealService.sellAll(ce.getEntrust_key(),stockInfo.getBuy_price4());
                updateWithdrawalKeyMap(ce.getEntrust_key(),2);
            }
        } else if (entrust_price <= stockInfo.getBuy_price5()){
            // 卖出价小于买五价 
            if (entrust_amount <= stockInfo.getBuy_amount5()){
                dealService.sellAll(ce.getEntrust_key(),stockInfo.getBuy_price5());
                updateWithdrawalKeyMap(ce.getEntrust_key(),2);
            }
        }
    }

    @Override
    public void run() {
        init();
        execute();
    }

    /**
     * 用于延时 10s 并且在延时期间每隔 1s 检测一次需不需要更新委托序列
     */
    public void delayAndRefresh(){
        for (int i = 0; i < 10; i++){
            if (waitForUpdatedKeyMap.size() > 0)
                refresh();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 计算卖五~卖n数量和 
     */
    public int calTotalSaleAmount(StockInfo stockInfo, int n){
        if (n == 5)
            return stockInfo.getSale_amount5();
        else if (n == 4)
            return stockInfo.getSale_amount4() + calTotalSaleAmount(stockInfo,5);
        else if (n == 3)
            return stockInfo.getSale_amount3() + calTotalSaleAmount(stockInfo,4);
        else if (n == 2)
            return stockInfo.getSale_amount2() + calTotalSaleAmount(stockInfo,3);
        else if (n == 1)
            return stockInfo.getSale_amount1() + calTotalSaleAmount(stockInfo,2);
        return 0;
    }

    /**
     * 计算买一~买n数量和
     */
    public int calTotalBuyAmount(StockInfo stockInfo,int n){
        if (n == 1)
            return stockInfo.getBuy_amount1();
        else if (n == 2)
            return stockInfo.getBuy_amount2() + calTotalBuyAmount(stockInfo,1);
        else if (n == 3)
            return stockInfo.getBuy_amount3() + calTotalBuyAmount(stockInfo,2);
        else if (n == 4)
            return stockInfo.getBuy_amount4() + calTotalBuyAmount(stockInfo,3);
        else if (n == 5)
            return stockInfo.getBuy_amount5() + calTotalBuyAmount(stockInfo,4);
        return 0;
    }
}

