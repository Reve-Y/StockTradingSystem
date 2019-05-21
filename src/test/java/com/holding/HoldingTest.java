package com.holding;

import com.dao.HoldingDao;
import com.domain.Holdings;
import com.service.interfaces.SecuritiesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath:spring-config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class HoldingTest {

    @Autowired
    private SecuritiesService securitiesService;

    @Autowired
    private HoldingDao holdingDao;

    @Test
    public void t1(){
        System.out.println(securitiesService.queryEnableAmountByStockAndSid("A836342142","100000"));
    }

    @Test
    public void t2 (){
        Holdings holdings = holdingDao.queryOneHoldingInfo("A500271877","644511");
        System.out.println(holdings);
    }
}
