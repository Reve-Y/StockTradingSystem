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

    // 404
    @RequestMapping("404")
    public ModelAndView to404Page(){
        return new ModelAndView("404");
    }

    // 后台管理首页
    @RequestMapping("admin")
    public ModelAndView toAdminPage(){
        return new ModelAndView("admin/index");
    }

    // blog页面
    @RequestMapping("blog")
    public ModelAndView toBlogPage(){
        return new ModelAndView("admin/blog");
    }

    // admin/errors 页面
    @RequestMapping("errors")
    public ModelAndView toErrorsPage(){
        return new ModelAndView("admin/errors");
    }

    // 后台管理 表格页面
    @RequestMapping("adminForm")
    public ModelAndView toAdminFormPage(){
        return new ModelAndView("admin/form");
    }

    // 后台管理 持仓情况页
    @RequestMapping("holdings")
    public ModelAndView toHoldingsPage(){
        return new ModelAndView("admin/holdings");
    }

    // 后台管理 post页
    @RequestMapping("newPost")
    public ModelAndView toPostPage(){
        return new ModelAndView("admin/newpost");
    }

    // 后台管理 用户信息页 userProfile
    @RequestMapping("userProfile")
    public ModelAndView toUserProfilePage(){
        return new ModelAndView("admin/userprofile");
    }

    // 超级管理员首页
    @RequestMapping("superadmin")
    public ModelAndView toSuperAdminPage(){
        return new ModelAndView("admin/superadmin");
    }

    // 超级管理员 管理用户页
    @RequestMapping("adminmanager")
    public ModelAndView toAdminManagerPage(){
        return new ModelAndView("admin/adminmanager");
    }

}
