package com.dao;

import com.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {

    public int addUser(User user);

    public User login(@Param("telephone")String telephone,@Param("password")String password);

    int updateUserInfo(User user);
}
