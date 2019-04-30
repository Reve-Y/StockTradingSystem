package com.query;

import com.domain.Holdings;
import com.service.interfaces.CapitalService;
import com.service.interfaces.DataService;
import com.service.interfaces.EntrustService;
import com.service.interfaces.SecuritiesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@ContextConfiguration(locations = "classpath:spring-config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class QueryTest {

    @Autowired
    private SecuritiesService securitiesService;

    @Autowired
    private CapitalService capitalService;

    @Autowired
    private EntrustService entrustService;

    @Autowired
    private DataService dataService;

    @Test
    public void t1(){
        System.out.println(securitiesService.calTotalMarketValue("A500271877"));
    }

    @Test
    public void t2(){
        System.out.println(capitalService.getCapitalAccountBySecuritiesId("A500271877").toString());
    }

    @Test
    public void t3(){
        System.out.println(entrustService.countNumberOfEntrustBySid("A123456789"));
        System.out.println(entrustService.countNumberOfEntrustBySid("111111000"));
    }

    @Test
    public void t4(){
        List<Holdings> list = securitiesService.queryHoldingsBySid("A500271877",1);
        for (Holdings holdings : list)
            System.out.println(holdings.toString());
    }

    @Test
    public void t5(){
        System.out.println(dataService.checkIfAccountExist("A836342142","60467723"));
    }
}
