package com.service.impls;

import com.dao.CapitalDao;
import com.dao.SecuritiesDao;
import com.dao.UserDao;
import com.domain.CapitalAccount;
import com.domain.SecuritiesAccount;
import com.domain.User;
import com.service.interfaces.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService{
    private static Logger log= Logger.getLogger(UserServiceImpl.class);

    @Autowired
    public UserDao userDao;

    @Autowired
    public SecuritiesDao securitiesDao;

    @Autowired
    public CapitalDao capitalDao;

    /**
     * 用户注册
     * @param user
     * @return
     */
    @Override
    public int addUser(User user) {
        log.info("开始注册 ");
        int flag = userDao.addUser(user);
        if(flag == 1)
            log.info("用户"+user.getTelephone()+"注册成功");
        else
            log.info("尝试注册失败");
        return flag;
    }

    /**
     * 校验登录信息，返回查询到的用户信息
     * @param telephone
     * @param password
     * @return
     */
    @Override
    public User login(String telephone, String password) {
        log.info("开始验证登录信息");
        return userDao.login(telephone,password);
    }

    /**
     * 更新用户信息
     * @param user
     * @return 返回被影响的记录数
     */
    @Override
    public int updateUserInfo(User user) {
        log.info("开始更新用户："+user.getTelephone()+" 信息...");
        return userDao.updateUserInfo(user);
    }

    /**
     * 开户：传入的telephone为开户人，sa ca为开户信息
     * @param telephone 开户用户的手机号
     * @param sa 证券账户
     * @param ca 资金账户
     * @return 返回被影响的记录数 返回 3 时表示开户成功
     */
    @Override
    @Transactional
    public int openAccount(String telephone, SecuritiesAccount sa, CapitalAccount ca) {
        int flag = 0;
        log.info("向tuser表中更新securities_account_id信息...");
        flag = userDao.updateUserSecuritiesAccount(telephone,sa.getSecurities_account_id());
        if (flag == 0)
            log.info("向tuser表中更新securities_account_id信息失败");
        else
            log.info("向tuser表中更新securities_account_id信息成功");

        log.info("开始向tSecuritiesAccount表中插入记录...");
        flag += securitiesDao.insertOneRecord(sa);
        if (flag < 2)
            log.info("向tSecuritiesAccount表中插入记录失败...");
        else
            log.info("向tSecuritiesAccount表中插入记录成功...");

        flag += capitalDao.insertOneRecord(ca);
        if (flag < 3)
            log.info("向tCapitalAccount表中插入记录失败...");
        else
            log.info("向tCapitalAccount表中插入记录成功...");

        return flag;
    }

}
