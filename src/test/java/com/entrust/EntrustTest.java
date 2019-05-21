package com.entrust;

import com.dao.EntrustDao;
import com.domain.CurrentEntrust;
import com.service.interfaces.EntrustService;
import com.util.DateUtils;
import com.util.UUIDUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath:spring-config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class EntrustTest {

    @Autowired
    public EntrustService entrustService;

    @Autowired
    public EntrustDao entrustDao;

    @Test
    public void t1(){
        CurrentEntrust ce = new CurrentEntrust();
        ce.setEntrust_date(DateUtils.getDateAndTime());
        ce.setSecurities_account_id("A427230568");
        ce.setStock_code("600000");
        ce.setEntrust_direction(1);
        ce.setEntrust_amount(1000);
        ce.setEntrust_price(12.0f);
        ce.setAmount_money(12000);
        ce.setEntrust_key(UUIDUtils.getUUID());
        int flag = 0;
//        flag = entrustService.normalEntrust(ce);
        System.out.println(flag);
    }

    @Test
    public void t2(){
        System.out.println(entrustDao.queryAllCurrentEntrust());
    }
}
