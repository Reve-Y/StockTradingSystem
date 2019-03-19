package com.controller;

import com.domain.User;
import com.service.interfaces.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    private static Logger log= Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 注册，完成后跳转到首页
     */
    @RequestMapping("doregist")
    public ModelAndView doregist(HttpServletRequest request){
        log.info("开始获取注册信息 ");
        User user = new User();
        user.setTelephone(request.getParameter("telephone"));
        user.setPassword(request.getParameter("password"));
        user.setEmail(request.getParameter("email"));
        user.setNickname(request.getParameter("nickname"));
        String age = request.getParameter("age");
        user.setAge((age=="") ? 0 : Integer.parseInt(age));
        user.setSex(Integer.parseInt(request.getParameter("sex")));
        log.info("获取完成. 请求注册的user信息为："+user.toString());
        int flag = userService.addUser(user);
        if(flag == 1){
            request.getSession().setAttribute("user",user);
            return new ModelAndView("home");
        }else{
            return new ModelAndView("errorPage");
        }
    }

    @RequestMapping("dologin")
    public ModelAndView dologin(HttpServletRequest request){
        return null;
    }
}
