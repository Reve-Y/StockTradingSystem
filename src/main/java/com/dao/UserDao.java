package com.dao;

import com.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {

    /**
     * 增加一条用户信息记录
     * @param user
     * @return
     */
    public int addUser(User user);

    /**
     * 根据telephone、password进行登录校验
     * @param telephone
     * @param password
     * @return
     */
    public User login(@Param("telephone")String telephone,@Param("password")String password);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    int updateUserInfo(User user);

    /**
     * 更新用户的证券账户信息
     * @param telephone
     * @param securities_account_id
     * @return
     */
    int updateUserSecuritiesAccount(@Param("telephone")String telephone, @Param("securities_account_id")String securities_account_id);
}
