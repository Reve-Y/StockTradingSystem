package com.controller;

import com.domain.CapitalAccount;
import com.domain.SecuritiesAccount;
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
     * 注册，完成后默认直接登录，并跳转到后台管理页面
     */
    @RequestMapping("doregist")
    public ModelAndView doregist(HttpServletRequest request){
        log.info("开始获取注册信息 ");
        User user = new User();
        user.setTelephone(request.getParameter("telephone"));
        user.setPassword(request.getParameter("password"));
        log.info("获取完成. 请求注册的user信息为："+user.toString());
        int flag = userService.addUser(user);
        if(flag == 1){
            request.getSession().setAttribute("user",user);
            return new ModelAndView("admin/index");
        }else{
            return new ModelAndView("404");
        }
    }

    /**
     * 检查登录信息
     */
//    @SuppressWarnings("all")
    @RequestMapping("dologin")
    public ModelAndView dologin(HttpServletRequest request){
        String telephone = request.getParameter("telephone");
        String password = request.getParameter("password");
        log.info("获取到请求登录信息： 手机号为："+telephone+" ; 密码为："+password);
        User user = userService.login(telephone,password);
        if(user == null || (user.getUser_id() == 0)){
            log.info("登录失败，跳转到登录页面");
            return new ModelAndView("login");
        }else if(user.getRole() == 2){
            log.info("普通用户:"+user.getTelephone()+" 登陆成功");
            request.getSession().setAttribute("logintel",telephone);
            request.getSession().setAttribute("user",user);
            return new ModelAndView("admin/index");
        }else{
            log.info("管理员："+user.getTelephone()+"登录成功");
            request.getSession().setAttribute("user",user);
            return new ModelAndView("admin/superadmin");
        }
    }

    /**
     *  注销： 销毁session
     */
    @RequestMapping("invalidate")
    public ModelAndView logoff(HttpServletRequest request){
        String usertel = (String) request.getSession().getAttribute("logintel");
        request.getSession().invalidate();
        log.info("用户"+usertel+"已注销...");
        return new ModelAndView("login");
    }

    /**
     *  更新用户信息
     */
    @RequestMapping("updateUserInfo")
    public ModelAndView updateUserInfo(HttpServletRequest request){
        String email = request.getParameter("email");
        int age = 0;
        if (request.getParameter("age")!="" && request.getParameter("age")!=null)
            age = Integer.parseInt(request.getParameter("age"));
        int sex = Integer.parseInt(request.getParameter("sex"));
        String nickname = request.getParameter("nickname");
        User user = (User) request.getSession().getAttribute("user");
        user.setEmail(email);
        user.setAge(age);
        user.setSex(sex);
        user.setNickname(nickname);
        int flag = userService.updateUserInfo(user);
        if (flag != 1){
            log.info("更新用户"+user.getTelephone()+"信息失败...");
            return new ModelAndView("admin/userprofile");
        }else {
            log.info("更新用户"+user.getTelephone()+"信息成功");
            request.getSession().setAttribute("user",user);
            return new ModelAndView("admin/index");
        }
    }

    /**
     * 开户
     */
    @RequestMapping("/openaccount")
    public ModelAndView openAccount(HttpServletRequest request){
        String telephone = (String) request.getSession().getAttribute("logintel");
        SecuritiesAccount sa = new SecuritiesAccount();
        CapitalAccount ca = new CapitalAccount();
        sa.setSecurities_account_id(request.getParameter("securities"));
        sa.setSecurities_company_name(request.getParameter("company"));
        sa.setOpen_date(request.getParameter("opendate"));
        sa.setAccount_id(request.getParameter("capital1"));
        ca.setAccount_id(request.getParameter("capital1"));
        ca.setBank_name(request.getParameter("bank"));
        ca.setBank_card_number(request.getParameter("bankcard"));
        ca.setAccount_balance(Float.parseFloat(request.getParameter("balance")));
        log.info("用户"+telephone+"开始开户");
        log.info("证券账户信息为： "+sa.toString());
        log.info("资金账户信息为： "+ca.toString());
        int flag = userService.openAccount(telephone,sa,ca);
        if (flag == 3) {
            log.info("用户"+telephone+"开户成功...");
            return new ModelAndView("admin/holdings");
        }else{
            log.info("用户"+telephone+"开户失败...");
            return new ModelAndView("admin/create");
        }
    }

}
