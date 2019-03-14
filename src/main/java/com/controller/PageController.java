package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

    //  跳转至注册页面
    @RequestMapping("registerPage")
    public ModelAndView register(){
        return new ModelAndView("register");
    }

}
