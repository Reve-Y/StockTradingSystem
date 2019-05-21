package com.other;

import com.domain.RealDeal;
import com.github.pagehelper.PageHelper;
import com.service.interfaces.DealService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@ContextConfiguration(locations = "classpath:spring-config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestDealService {

    @Autowired
    private DealService dealService;

    @Test
    public void t1(){
        List<RealDeal> list = dealService.queryDealRecordBySid("A720009924",2);
        for (RealDeal realDeal : list){
            System.out.println(realDeal.toString());
        }
    }
}
