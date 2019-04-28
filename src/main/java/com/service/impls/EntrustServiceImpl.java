package com.service.impls;

import com.dao.EntrustDao;
import com.dao.HistoryDao;
import com.domain.CurrentEntrust;
import com.domain.HistoryEntrust;
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

    /**
     *  普通委托 ： 1.向tCurrentEntrust表中插入记录 2. 插入tHistoryEntrust(全部委托表)中
     * @param ce 传入的委托信息
     */
    @Override
    @Transactional
    public int normalEntrust(CurrentEntrust ce) {
        int flag = 0;
        log.info("接收到委托信息："+ce.toString());
        flag = entrustDao.addOneCurrentEntrust(ce);
        if (flag == 1)
            log.info("向tCurrentEntrust表中插入委托记录成功");
        flag += historyDao.addOneHistoryEntrust(ce);
        if (flag == 2)
            log.info("向tHistoryEntrust表中插入委托记录成功,委托已受理");
        else
            log.info("执行委托失败");
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
}
