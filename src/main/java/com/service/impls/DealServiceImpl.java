package com.service.impls;

import com.dao.*;
import com.domain.CapitalAccount;
import com.domain.CurrentEntrust;
import com.domain.Holdings;
import com.domain.RealDeal;
import com.github.pagehelper.PageHelper;
import com.service.interfaces.CapitalService;
import com.service.interfaces.DataService;
import com.service.interfaces.DealService;
import com.service.interfaces.EntrustService;
import com.util.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class DealServiceImpl implements DealService {
    private static Logger log= Logger.getLogger(DealServiceImpl.class);

    @Autowired
    private DealDao dealDao;

    @Autowired
    private DataService dataService;

    @Autowired
    private EntrustDao entrustDao;

    @Autowired
    private HoldingDao holdingDao;

    @Autowired
    private CapitalDao capitalDao;

    @Autowired
    private CapitalService capitalService;

    @Autowired
    private HistoryDao historyDao;

    /**
     * 根据证券账号获取pageNum页的成交记录
     * @param securities_account_id
     * @param pageNum
     * @return
     */
    @Override
    public List<RealDeal> queryDealRecordBySid(String securities_account_id, int pageNum) {
        log.info("begin--开始获取第"+pageNum+"页成交记录");

        PageHelper.startPage(pageNum,5);
        List<RealDeal> list = dealDao.queryDealRecordsBySid(securities_account_id);

        log.info("end--第"+pageNum+"页成交记录查询结束");
        // 设置stock_name
        for (RealDeal realDeal : list){
            realDeal.setStock_name(dataService.getStockName(realDeal.getStock_code()));
        }
        return list;
    }

    /**
     * 计算该证券账户下的总成交记录条数
     * @param securities_account_id
     * @return
     */
    @Override
    public int countNumberOfDealsBySid(String securities_account_id) {
        return dealDao.queryNumberOfDealBySid(securities_account_id);
    }

    /**
     * 买入委托，以buy_price的价格全部成交：1. 更新持仓 2. 从tCurrentEntrust删除记录 3. 更新资金 4. 修改历史委托状态 5. 插入成交记录
     * @param entrust_key
     * @param buy_price
     */
    @Override
    @Transactional
    public void allClinchADeal(String entrust_key, float buy_price) {
        CurrentEntrust ce = entrustDao.getCurrentEntrustDetailByKey(entrust_key);
        Holdings holdings = holdingDao.queryOneHoldingInfo(ce.getSecurities_account_id(),ce.getStock_code());
        int flag = 0 ;

        if (holdings == null){
            holdings = new Holdings();
            holdings.setSecurities_account_id(ce.getSecurities_account_id());
            holdings.setStock_code(ce.getStock_code());
            holdings.setStock_name(dataService.getStockName(ce.getStock_code()));
            holdings.setHold_amount(ce.getEntrust_amount());
            holdings.setBuy_price(buy_price);
            holdings.setEnable_amount(ce.getEntrust_amount());
            flag += holdingDao.addOneHoldingRecord(holdings);
        }else {
            float avg_price = (holdings.getBuy_price() * holdings.getHold_amount() + buy_price * ce.getEntrust_amount()) / (holdings.getHold_amount() + ce.getEntrust_amount()) ;
            holdings.setBuy_price(avg_price);
            holdings.setHold_amount(holdings.getHold_amount() + ce.getEntrust_amount());
            holdings.setEnable_amount(holdings.getEnable_amount() + ce.getEntrust_amount());
            flag += holdingDao.updateOneHoldingRecord(holdings);
        }

        flag += entrustDao.withdrawEntrustByKey(ce.getEntrust_key());

        CapitalAccount ca = capitalService.getCapitalAccountBySecuritiesId(ce.getSecurities_account_id());
        ca.setFrozen_balance(ca.getFrozen_balance() - ce.getEntrust_price() * ce.getEntrust_amount());
        ca.setAccount_balance(ca.getAccount_balance() - buy_price * ce.getEntrust_amount());
        ca.setEnable_balance(ca.getEnable_balance() + ((ce.getEntrust_price() - buy_price) * ce.getEntrust_amount()) );
        flag += capitalDao.updateAccountInfo(ca);

        flag += historyDao.updateStatusToDealByKey(ce.getEntrust_key());
        flag += addOneDealRecord(ce,buy_price,ce.getEntrust_amount());

        if (flag == 5)
            log.info("委托："+ce.getEntrust_key()+"已全部成交（买入）");
    }

    /**
     * 买入委托的部分成交： 1. 更新持仓 2. 修改tCurrentEntrust记录 3. 更新资金 4. 增加成交记录
     * @param entrust_key
     * @param buy_price 成交价格
     * @param buy_amount 此次成交的数量
     */
    @Override
    @Transactional
    public void someClinchADeal(String entrust_key, float buy_price, int buy_amount) {
        CurrentEntrust ce = entrustDao.getCurrentEntrustDetailByKey(entrust_key);
        Holdings holdings = holdingDao.queryOneHoldingInfo(ce.getSecurities_account_id(),ce.getStock_code());
        int flag = 0 ;

        if (holdings == null){
            holdings = new Holdings();
            holdings.setSecurities_account_id(ce.getSecurities_account_id());
            holdings.setStock_code(ce.getStock_code());
            holdings.setStock_name(dataService.getStockName(ce.getStock_code()));
            holdings.setHold_amount(buy_amount);
            holdings.setBuy_price(buy_price);
            holdings.setEnable_amount(buy_amount);
            flag += holdingDao.addOneHoldingRecord(holdings);
        }else {
            float avg_price = (holdings.getBuy_price() * holdings.getHold_amount() + buy_price * buy_amount) / (holdings.getHold_amount() + buy_amount) ;
            holdings.setBuy_price(avg_price);
            holdings.setHold_amount(holdings.getHold_amount() + buy_amount);
            holdings.setEnable_amount(holdings.getEnable_amount() + buy_amount);
            flag += holdingDao.updateOneHoldingRecord(holdings);
        }

        ce.setEntrust_amount(ce.getEntrust_amount() - buy_amount);
        ce.setAmount_money(ce.getAmount_money() - ce.getEntrust_price() * buy_amount);
        flag += entrustDao.updateEntrust(ce);

        CapitalAccount ca = capitalService.getCapitalAccountBySecuritiesId(ce.getSecurities_account_id());
        ca.setFrozen_balance(ca.getFrozen_balance() - ce.getEntrust_price() * buy_amount);
        ca.setAccount_balance(ca.getAccount_balance() - buy_price * buy_amount);
        ca.setEnable_balance(ca.getEnable_balance() + ((ce.getEntrust_price() - buy_price) * buy_amount) );
        flag += capitalDao.updateAccountInfo(ca);

        flag += addOneDealRecord(ce,buy_price,buy_amount);
        if (flag == 4)
            log.info("委托："+ce.getEntrust_key()+"以"+buy_price+"的价格成交了 "+buy_amount+"股（买入）");
    }

    /**
     * 插入一条成交记录
     * @param ce
     * @param price 本次成交价格
     * @param amount 本次成交数量
     * @return
     */
    public int addOneDealRecord(CurrentEntrust ce, float price, long amount) {
        RealDeal realDeal = new RealDeal();
        realDeal.setEntrust_key(ce.getEntrust_key());
        realDeal.setDeal_date(DateUtils.getDateAndTime());
        realDeal.setSecurities_account_id(ce.getSecurities_account_id());
        realDeal.setStock_code(ce.getStock_code());
        realDeal.setDeal_direction(ce.getEntrust_direction());
        realDeal.setDeal_amount(amount);
        realDeal.setDeal_price(price);
        realDeal.setDeal_amount_money(price * amount);
        CapitalAccount ca = capitalService.getCapitalAccountBySecuritiesId(ce.getSecurities_account_id());
        realDeal.setDeal_capital_balance(ca.getEnable_balance());
        realDeal.setCapital_account_id(ca.getAccount_id());
        return dealDao.addOneDealRecord(realDeal);
    }


    /**
     * 全部卖出： 1. 删除当前委托记录 2. 修改持仓 3. 修改资金 4. 修改成交记录 5. 修改历史委托状态
     * @param entrust_key
     * @param sale_price
     */
    @Override
    @Transactional
    public void sellAll(String entrust_key, float sale_price) {
        CurrentEntrust ce = entrustDao.getCurrentEntrustDetailByKey(entrust_key);
        Holdings holdings = holdingDao.queryOneHoldingInfo(ce.getSecurities_account_id(),ce.getStock_code());
        int flag = 0 ;

        flag += entrustDao.withdrawEntrustByKey(ce.getEntrust_key());
        if (holdings.getHold_amount() - ce.getEntrust_amount() == 0 && holdings.getEnable_amount() == 0){
            // 相当于持有的全部卖掉了
            flag += holdingDao.deleteOneRecord(holdings.getSecurities_account_id(),holdings.getStock_code());
        } else {
            holdings.setHold_amount(holdings.getHold_amount() - ce.getEntrust_amount());
            flag += holdingDao.updateOneHoldingRecord(holdings);
        }

        CapitalAccount ca = capitalService.getCapitalAccountBySecuritiesId(ce.getSecurities_account_id());
        ca.setAccount_balance(ca.getAccount_balance() + sale_price * ce.getEntrust_amount());
        ca.setEnable_balance(ca.getEnable_balance() + sale_price * ce.getEntrust_amount());
        flag += capitalDao.updateAccountInfo(ca);

        flag += addOneDealRecord(ce,sale_price,ce.getEntrust_amount());
        flag += historyDao.updateStatusToDealByKey(ce.getEntrust_key());

        if (flag == 5)
            log.info("委托："+ce.getEntrust_key()+"以"+sale_price+"的价格全部成交（卖出）");
    }

    /**
     * 部分卖出 ： 1. 修改当前委托记录 2. 修改持仓 3. 修改资金 4. 增加成交记录
     * @param entrust_key
     * @param sale_price
     * @param sale_amount
     */
    @Override
    @Transactional
    public void sellSome(String entrust_key, float sale_price, int sale_amount) {
        CurrentEntrust ce = entrustDao.getCurrentEntrustDetailByKey(entrust_key);
        Holdings holdings = holdingDao.queryOneHoldingInfo(ce.getSecurities_account_id(),ce.getStock_code());
        int flag = 0 ;

        ce.setEntrust_amount(ce.getEntrust_amount() - sale_amount);
        flag += entrustDao.updateEntrust(ce);

        holdings.setHold_amount(holdings.getHold_amount() - sale_amount);
        flag += holdingDao.updateOneHoldingRecord(holdings);

        CapitalAccount ca = capitalService.getCapitalAccountBySecuritiesId(ce.getSecurities_account_id());
        ca.setAccount_balance(ca.getAccount_balance() + sale_price * sale_amount);
        ca.setEnable_balance(ca.getEnable_balance() + sale_price * sale_amount);
        flag += capitalDao.updateAccountInfo(ca);

        flag += addOneDealRecord(ce,sale_price,sale_amount);
        if (flag == 4)
            log.info("委托："+ce.getEntrust_key()+"以"+sale_price+"的价格（卖出）"+sale_amount+" 股");
    }


}
