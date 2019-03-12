package com.controller;

import com.dao.UserDao;
import com.domain.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class TestController {

    @Autowired
    public UserDao userDao;

    @RequestMapping("test")
    public ModelAndView testPage(HttpServletResponse response) throws IOException {
        String name ;
        int age;
        ModelAndView modelAndView = new ModelAndView("test");
        List<People> list = userDao.queryTest();
        modelAndView.addObject("list",list);
        return modelAndView;
    }
}
