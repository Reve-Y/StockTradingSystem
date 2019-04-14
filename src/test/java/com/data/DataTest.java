package com.data;

import com.service.interfaces.DataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath:spring-config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DataTest {

    @Autowired
    private DataService dataService;

    @Test
    public void t1(){
        System.out.println(dataService.queryCurrentStockPrice("600570"));
        System.out.println(dataService.queryCurrentStockPrice("000001"));
    }
}


