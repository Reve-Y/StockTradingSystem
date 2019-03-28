package com.service.interfaces;

import com.domain.CapitalAccount;
import com.domain.SecuritiesAccount;
import com.domain.User;

public interface UserService {

    public int addUser(User user);

    User login(String telephone, String password);

    int updateUserInfo(User user);

    int openAccount(String telephone, SecuritiesAccount sa, CapitalAccount ca);
}
