package com.controller;

import com.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册，完成后跳转到首页
     */
    @RequestMapping("doregist")
    public ModelAndView doregist(HttpServletRequest request){
        return new ModelAndView("home");
    }

}
