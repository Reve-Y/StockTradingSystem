package com.entrust;

import com.domain.CapitalAccount;
import com.domain.SecuritiesAccount;
import com.service.impls.UserServiceImpl;
import com.service.interfaces.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class OpenAccountTest {

    @Autowired
    public UserService userService;

    @Test
    public void test1(){
        String telephone = "12323232323";
        SecuritiesAccount sa = new SecuritiesAccount();
//        sa.setSecurities_account_id("A321321321");
        sa.setOpen_date("20190101");
        sa.setSecurities_company_name("Zhongxin");
        sa.setAccount_id("123321123");
        CapitalAccount ca = new CapitalAccount();
        ca.setAccount_id("123321123");
        ca.setBank_card_number("12345678998765");
        ca.setBank_name("China");
        ca.setAccount_balance(0);
        int flag = userService.openAccount(telephone,sa,ca);
        System.out.println(flag);
    }
}
