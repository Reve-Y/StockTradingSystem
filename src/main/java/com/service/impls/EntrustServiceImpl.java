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

    @Autowired
    private EntrustDao entrustDao;

    @Autowired
    private DataService dataService;

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
        String msg = (flag == 3 ? "委托落库成功":"委托落库失败");
        log.info(msg);

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

        PageHelper.offsetPage(pageNum,5);
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
        PageHelper.offsetPage(pageNum,5);
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
     * 委托撤单 ： 1.删除当前委托记录 2 修改历史委托记录的委托状态
     */
    @Override
    @Transactional
    public int withdrawEntrustByKey(String entrust_key) {
        log.info("开始执行撤单....key为"+entrust_key);
        int flag = 0;

        log.info("删除tCurrentEntrust表中entrust_key为"+entrust_key+"的记录");
        flag += entrustDao.withdrawEntrustByKey(entrust_key);
        if (flag == 1) {
            log.info("....OK");
        } else {
            log.info("...fail");
        }

        log.info("修改tHistoryEntrust表中相应的状态...");
        flag += historyDao.updateStatusToWithdrawByKey(entrust_key);
        if (flag == 2) {
            log.info("....OK，撤单成功");
        } else {
            log.info("...fail，撤单失败");
        }
        return flag;
    }

    /**
     * 委托方向为买入的委托
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
        ca.setFrozen_balance(ce.getAmount_money());
        flag += capitalDao.updateAccountInfo(ca);

        return flag;
    }

    /**
     * 委托方向为卖出的委托
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
}
