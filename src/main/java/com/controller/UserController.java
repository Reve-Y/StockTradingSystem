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
     * 注册，完成后默认直接登录，并跳转到首页
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

    /**
     * 检查登录信息
     */
    @RequestMapping("dologin")
    public ModelAndView dologin(HttpServletRequest request){
        String telephone = request.getParameter("telephone");
        String password = request.getParameter("password");
        log.info("获取到请求登录信息： 手机号为："+telephone+" ; 密码为："+password);
        User user = userService.login(telephone,password);
        if(user == null || (user.getUser_id() == 0)){
            log.info("用户:"+user.getTelephone()+" 登陆成功");
            request.getSession().setAttribute("user",user);
            return new ModelAndView("home");
        }else{
            log.info("登录失败，跳转到登录页面");
            return new ModelAndView("login");
        }
    }

    /**
     *  注销： 销毁session
     */
    @RequestMapping("invalidate")
    public ModelAndView logoff(HttpServletRequest request){
        request.getSession().invalidate();
        return new ModelAndView("home");
    }
}
