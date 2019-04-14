package com.controller;

import com.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

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
    public ModelAndView toAdminPage(HttpServletRequest request){
        if (checkSessionUserExists(request))
            return new ModelAndView("admin/index");
        else
            return new ModelAndView("login");
    }

    // blog页面
    @RequestMapping("blog")
    public ModelAndView toBlogPage(HttpServletRequest request){
        if (checkSessionUserExists(request))
            return new ModelAndView("admin/blog");
        else
            return new ModelAndView("login");
    }

    // admin/create 页面
    @RequestMapping("createAccount")
    public ModelAndView toErrorsPage(HttpServletRequest request){
        if (checkSessionUserExists(request))
            return new ModelAndView("admin/create");
        else
            return new ModelAndView("login");
    }

    // 后台管理 表格页面
    @RequestMapping("adminForm")
    public ModelAndView toAdminFormPage(HttpServletRequest request){
        if (checkSessionUserExists(request))
            return new ModelAndView("admin/form");
        else
            return new ModelAndView("login");
    }

    // 后台管理 持仓情况页
    @RequestMapping("holdings")
    public ModelAndView toHoldingsPage(HttpServletRequest request){
        if (checkSessionUserExists(request))
            return new ModelAndView("admin/holdings");
        else
            return new ModelAndView("login");
    }

    // 后台管理 post页
    @RequestMapping("newPost")
    public ModelAndView toPostPage(HttpServletRequest request){
        if (checkSessionUserExists(request))
            return new ModelAndView("admin/newpost");
        else
            return new ModelAndView("login");
    }

    // 后台管理 用户信息页 userProfile
    @RequestMapping("userProfile")
    public ModelAndView toUserProfilePage(HttpServletRequest request){
        if (checkSessionUserExists(request))
            return new ModelAndView("admin/userprofile");
        else
            return new ModelAndView("login");
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

    // 用来判断当前是否已登录，如果未登录则无法跳转到后台管理，会跳转到登录页面。
    public boolean checkSessionUserExists(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || user.getTelephone() == "" || user.getTelephone() == null)
            return false;
        else
            return true;
    }
}
