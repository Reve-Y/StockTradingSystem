package com.service.interfaces;

import com.domain.User;

public interface UserService {

    public int addUser(User user);

    User login(String telephone, String password);

    int updateUserInfo(User user);
}
