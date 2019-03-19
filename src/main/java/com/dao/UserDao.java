package com.dao;

import com.domain.People;
import com.domain.User;

import java.util.List;

public interface UserDao {

    public List<People> queryTest() ;

    public int addUser(User user);
}
