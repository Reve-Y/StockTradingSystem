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

    // 跳转到股票信息页面
    @RequestMapping("stockinfo")
    public ModelAndView toHomePage(){
        return new ModelAndView("stockinfo");
    }

    // 跳转到登录页
    @RequestMapping("login")
    public ModelAndView toLoginPage(){
        return new ModelAndView("login");
    }

    // 帮助页面
    @RequestMapping("help")
    public ModelAndView toHelpPage() {
        return new ModelAndView("help");
    }

    // 跳转到首页
    @RequestMapping("start")
    public ModelAndView getStart(){
        return new ModelAndView("start");
    }

    // 关于我们
    @RequestMapping("about")
    public ModelAndView toAboutUs(){
        return new ModelAndView("about");
    }
}
