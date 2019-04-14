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

    @Override
    public CapitalAccount getCapitalAccountBySecuritiesId(String securities_account_id) {
        log.info("根据证券账户id"+securities_account_id+"取资金账号id...");
        String account_id = capitalDao.queryAccountIdBySecuritiesId(securities_account_id);
        log.info("取得的资金账号id为"+account_id);

        log.info("获取资金账号"+account_id+"信息");
        CapitalAccount ca = capitalDao.queryAccountInfoById(account_id);

        return ca;
    }

    @Override
    public String getAccountIdBySid(String securities_account_id) {
        log.info("根据证券账户id"+securities_account_id+"取资金账号id...");
        String account_id = capitalDao.queryAccountIdBySecuritiesId(securities_account_id);
        log.info("取得的资金账号id为"+account_id);
        return account_id;
    }


}
