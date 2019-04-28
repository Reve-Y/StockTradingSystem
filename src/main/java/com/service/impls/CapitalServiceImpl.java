package com.service.impls;

import com.dao.CapitalDao;
import com.domain.CapitalAccount;
import com.service.interfaces.CapitalService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CapitalServiceImpl implements CapitalService{
    private static Logger log = Logger.getLogger(CapitalServiceImpl.class);

    @Autowired
    private CapitalDao capitalDao;

    /**
     * 根据证券账户号码获取资金账户信息
     * @param securities_account_id
     * @return
     */
    @Override
    public CapitalAccount getCapitalAccountBySecuritiesId(String securities_account_id) {
        log.info("根据证券账户id"+securities_account_id+"取资金账号id...");
        String account_id = capitalDao.queryAccountIdBySecuritiesId(securities_account_id);
        log.info("取得的资金账号id为"+account_id);

        log.info("获取资金账号"+account_id+"信息");
        CapitalAccount ca = capitalDao.queryAccountInfoById(account_id);

        return ca;
    }

    /**
     * 根据证券账户id信息获取资金账户id
     * @param securities_account_id
     * @return
     */
    @Override
    public String getAccountIdBySid(String securities_account_id) {
        log.info("根据证券账户id"+securities_account_id+"取资金账号id...");
        String account_id = capitalDao.queryAccountIdBySecuritiesId(securities_account_id);
        log.info("取得的资金账号id为"+account_id);
        return account_id;
    }

    /**
     * 根据资金账户获取该账户下的可用余额
     * @param capital_id
     * @return enable_balance 可用余额
     */
    @Override
    public String queryEnableBalanceByCid(String capital_id) {
        return capitalDao.queryEnableBalanceByCid(capital_id);
    }


}
