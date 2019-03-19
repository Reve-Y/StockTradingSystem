package com.service.impls;

import com.dao.UserDao;
import com.domain.User;
import com.service.interfaces.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private static Logger log= Logger.getLogger(UserServiceImpl.class);

    @Autowired
    public UserDao userDao;

    //  用户注册
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

}
