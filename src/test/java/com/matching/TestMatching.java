package com.matching;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath:spring-config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestMatching {

    @Autowired
    public Matching matching;

    @Test
    public void t1(){
//        matching.start();
    }
}
