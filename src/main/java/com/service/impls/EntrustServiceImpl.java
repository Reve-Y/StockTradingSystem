package com.service.impls;

import com.dao.CapitalDao;
import com.dao.EntrustDao;
import com.dao.HistoryDao;
import com.dao.HoldingDao;
import com.domain.CapitalAccount;
import com.domain.CurrentEntrust;
import com.domain.HistoryEntrust;
import com.domain.Holdings;
import com.github.pagehelper.PageHelper;
import com.matching.Matching;
import com.service.interfaces.DataService;
import com.service.interfaces.EntrustService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EntrustServiceImpl implements EntrustService{
    private static Logger log= Logger.getLogger(EntrustServiceImpl.class);

    public static final int NORMAL_ENTRUST = 1 ;
    public static final int WITHDRAW_ENTRUST = 2 ;
    public static final int UPDATE_ENTRUST = 3 ;

    @Autowired
    private EntrustDao entrustDao;

    @Autowired
    private DataService dataService;

//    @Autowired
//    private Matching matching;

    @Autowired
    private HistoryDao historyDao;

    @Autowired
    private HoldingDao holdingDao;

    @Autowired
    private CapitalDao capitalDao;

    /**
     *  普通委托 ： 根据委托方向不同，落库的过程不同
     *      买入时 ： 1.向tCurrentEntrust表中插入记录 2. 插入tHistoryEntrust(全部委托表)中 3. 更改资金情况
     *      卖出时 ： 1.向tCurrentEntrust表中插入记录 2. 插入tHistoryEntrust(全部委托表)中 3. 更改持仓可用数量
     * @param ce 传入的委托信息
     */
    @Override
    @Transactional
    public int normalEntrust(CurrentEntrust ce) {
        int flag = 0;
        log.info("接收到委托信息："+ce.toString());

        if (ce.getEntrust_direction() == 1){
            flag = doBuyEntrust(ce);
        }else if(ce.getEntrust_direction() == 2){
            flag = doSellEntrust(ce);
        }

        if (flag == 3){
            log.info("委托落库成功");
            // 呵呵，扣除手续费哦 （万分之5）
            debuctServiceCharge(ce);

            sendMessageToMatching(ce.getEntrust_key(),NORMAL_ENTRUST);
        }else {
            log.info("委托落库失败");
        }

        return flag;
    }

    /**
     * 获取该证券账户下当前正在执行的委托的数量
     * @param securities_account_id
     * @return
     */
    @Override
    public int countNumberOfEntrustBySid(String securities_account_id) {
        log.info("begin 根据证券账户"+securities_account_id+"获取当前委托笔数");
        int count = entrustDao.queryNumberOfEntrustBySid(securities_account_id);
        log.info(" end  证券账户"+securities_account_id+"下当前有"+count+"笔正在执行的委托");
        return count;
    }

    /**
     * 获取第pageNum页当前正在执行的委托
     * @param securities_account_id
     * @param pageNum 页号
     * @return
     */
    @Override
    public List<CurrentEntrust> queryCurrentEntrustBySid(String securities_account_id, int pageNum) {
        log.info("begin--开始获取第"+pageNum+"页当前委托");

        PageHelper.startPage(pageNum,5);
        List<CurrentEntrust> list = entrustDao.queryCurrentEntrustBySid(securities_account_id);

        log.info("end--第"+pageNum+"页当前委托信息查询结束");
        // 设置stock_name
        for (CurrentEntrust currentEntrust : list){
            currentEntrust.setStock_name(dataService.getStockName(currentEntrust.getStock_code()));
        }
        return list;
    }

    /**
     * 获取第pageNum页历史委托信息
     * @param securities_account_id
     * @param pageNum 页号
     * @return
     */
    @Override
    public List<HistoryEntrust> queryHistoryEntrustBySid(String securities_account_id, int pageNum) {
        PageHelper.startPage(pageNum,5);
        List<HistoryEntrust> list = historyDao.queryHistoryEntrustBySid(securities_account_id);

        // 获取股票名称以及完成委托状态的转换
        for (HistoryEntrust historyEntrust : list){
            historyEntrust.setStock_name(dataService.getStockName(historyEntrust.getStock_code()));
            int status = historyEntrust.getEntrust_status();
            if (status == 0)
                historyEntrust.setStatus("执行中");
            else if (status == 1)
                historyEntrust.setStatus("已成交");
            else if (status == 2)
                historyEntrust.setStatus("已撤单");
            else
                historyEntrust.setStatus("系统废弃");
        }
        return list;
    }

    /**
     * 获取该证券账户下历史委托的总记录数
     * @param securities_account_id
     * @return
     */
    @Override
    public int countNumberOfHistoryEntBySid(String securities_account_id) {
        return historyDao.queryNumberOfHistoryEntBySid(securities_account_id);
    }

    /**
     * 普通委托：委托方向为买入的委托
     * 1.向tCurrentEntrust表中插入记录 2. 插入tHistoryEntrust(全部委托表)中 3. 更改资金情况
     * @param ce
     * @return 受影响的行数，具体来说返回 3 时表示落库正常
     */
    @Transactional
    public int doBuyEntrust(CurrentEntrust ce){
        int flag = 0;

        flag += entrustDao.addOneCurrentEntrust(ce);
        flag += historyDao.addOneHistoryEntrust(ce);

        // 更新资金账户信息
        String account_id = capitalDao.queryAccountIdBySecuritiesId(ce.getSecurities_account_id());
        CapitalAccount ca = capitalDao.queryAccountInfoById(account_id);
        ca.setEnable_balance(ca.getEnable_balance() - ce.getAmount_money());   //  可用余额要减去委托金额
        ca.setFrozen_balance(ca.getFrozen_balance() + ce.getAmount_money());   //  原有的冻结加上新的冻结金额
        flag += capitalDao.updateAccountInfo(ca);

        return flag;
    }

    /**
     * 普通委托：委托方向为卖出的委托
     * 1.向tCurrentEntrust表中插入记录 2. 插入tHistoryEntrust(全部委托表)中 3. 更改持仓可用数量
     * @param ce
     * @return 受影响的行数，具体来说返回 3 时表示落库正常
     */
    @Transactional
    public int doSellEntrust(CurrentEntrust ce){
        int flag = 0;

        flag += entrustDao.addOneCurrentEntrust(ce);
        flag += historyDao.addOneHistoryEntrust(ce);

        // 更新持仓情况
        Holdings holding = holdingDao.queryOneHoldingInfo(ce.getSecurities_account_id(),ce.getStock_code());
        holding.setEnable_amount(holding.getEnable_amount() - ce.getEntrust_amount());
        flag += holdingDao.updateOneHoldingRecord(holding);

        return flag;
    }

    /**
     * 委托撤单 ：根据委托方向进行撤单
     *   委托方向为买入的撤单： 1.删除当前委托记录 2.修改历史委托记录的委托状态 3. 还原资金：恢复冻结、增加可用
     *   委托方向为卖出的撤单： 1.删除当前委托记录 2.修改历史委托记录的委托状态 3. 恢复持仓可用数量
     */
    @Override
    public int withdrawEntrustByKey(String entrust_key) {
        log.info("开始执行撤单....key为"+entrust_key);
        int flag = 0;
        CurrentEntrust ce = entrustDao.getCurrentEntrustDetailByKey(entrust_key);

        if (ce.getEntrust_direction() == 1)
            flag = doWithdrawBuyEntrust(ce);
        else
            flag = doWithdrawSellEntrust(ce);

        if (flag == 3){
            log.info("委托撤单成功");
            debuctServiceCharge(ce);

            sendMessageToMatching(entrust_key,WITHDRAW_ENTRUST);
        }else {
            log.info("委托撤单失败");
        }

        return flag;
    }

    /**
     * 撤销委托方向为卖出的委托时调用
     */
    @Transactional
    public int doWithdrawSellEntrust(CurrentEntrust ce) {
        int flag = 0;
        String entrust_key = ce.getEntrust_key();

        flag += entrustDao.withdrawEntrustByKey(entrust_key);
        flag += historyDao.updateStatusToWithdrawByKey(entrust_key);
        // 更新持仓情况
        Holdings holding = holdingDao.queryOneHoldingInfo(ce.getSecurities_account_id(),ce.getStock_code());
        holding.setEnable_amount(holding.getEnable_amount() + ce.getEntrust_amount());
        flag += holdingDao.updateOneHoldingRecord(holding);

        return flag;
    }

    /**
     * 撤销委托方向为买入的委托时调用
     */
    @Transactional
    public int doWithdrawBuyEntrust(CurrentEntrust ce) {
        int flag = 0;
        String entrust_key = ce.getEntrust_key();

        flag += entrustDao.withdrawEntrustByKey(entrust_key);
        flag += historyDao.updateStatusToWithdrawByKey(entrust_key);
        // 更新资金账户信息
        String account_id = capitalDao.queryAccountIdBySecuritiesId(ce.getSecurities_account_id());
        CapitalAccount ca = capitalDao.queryAccountInfoById(account_id);
        ca.setEnable_balance(ca.getEnable_balance() + ce.getAmount_money());   //  可用余额加上委托金额
        ca.setFrozen_balance(ca.getFrozen_balance() - ce.getAmount_money());   //  释放这笔冻结金额
        flag += capitalDao.updateAccountInfo(ca);

        return flag;
    }

    /**
     * 手续费扣除：该笔委托总额的万分之五 ，更新相应资金账户的总金额和可用余额
     * @param ce
     */
    @Override
    @Transactional
    public void debuctServiceCharge(CurrentEntrust ce) {
        String account_id = capitalDao.queryAccountIdBySecuritiesId(ce.getSecurities_account_id());
        CapitalAccount ca = capitalDao.queryAccountInfoById(account_id);

        float serviceMoney = ce.getAmount_money()/2000;
        ca.setAccount_balance(ca.getAccount_balance() - serviceMoney);
        ca.setEnable_balance(ca.getEnable_balance() - serviceMoney);

        capitalDao.updateAccountInfo(ca);
    }

    /**
     * 向matching发送消息。告诉它该更新委托序列了
     * @param entrust_key 每笔委托的唯一标示
     * @param entrust_type 委托类型 1. 普通买卖委托  2. 委托撤单
     */
    @Override
    public void sendMessageToMatching(String entrust_key,int entrust_type){
        Matching.updateWithdrawalKeyMap(entrust_key,entrust_type);
    }
}
