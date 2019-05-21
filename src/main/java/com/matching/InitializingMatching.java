package com.matching;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitializingMatching implements InitializingBean{

    @Autowired
    private Matching matching;

    @Override
    public void afterPropertiesSet() throws Exception {
            new Thread(matching).start();
    }

}
