package com.service.impls;

import com.dao.EntrustDao;
import com.domain.CurrentEntrust;
import com.service.interfaces.EntrustService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EntrustServiceImpl implements EntrustService{
    private static Logger log= Logger.getLogger(EntrustServiceImpl.class);

    @Autowired
    private EntrustDao entrustDao;

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
        flag += entrustDao.addOneHistoryEntrust(ce);
        if (flag == 2)
            log.info("向tHistoryEntrust表中插入委托记录成功,委托已受理");
        else
            log.info("执行委托失败");
        return flag;
    }

    @Override
    public int countNumberOfEntrustBySid(String securities_account_id) {
        log.info("begin 根据证券账户"+securities_account_id+"获取当前委托笔数");
        int count = entrustDao.queryNumberOfEntrustBySid(securities_account_id);
        log.info(" end  证券账户"+securities_account_id+"下当前有"+count+"笔正在执行的委托");
        return count;
    }
}
