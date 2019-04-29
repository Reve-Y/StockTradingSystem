package com.controller;

import com.domain.CapitalAccount;
import com.domain.Holdings;
import com.domain.SecuritiesAccount;
import com.domain.User;
import com.service.interfaces.*;
import com.util.DateUtils;
import com.util.JsonUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    private static Logger log= Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private SecuritiesService securitiesService;

    @Autowired
    private CapitalService capitalService;

    @Autowired
    private EntrustService entrustService;

    @Autowired
    private DataService dataService;

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
            request.getSession().setAttribute("logintel",user.getTelephone());
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
    @RequestMapping("openaccount")
    public ModelAndView openAccount(HttpServletRequest request){
        String telephone = (String) request.getSession().getAttribute("logintel");
        SecuritiesAccount sa = new SecuritiesAccount();
        CapitalAccount ca = new CapitalAccount();
        sa.setSecurities_account_id(request.getParameter("securities"));
        //  dataService.checkSecuritiesAndCapital();检查账号合法性
        sa.setSecurities_company_name(request.getParameter("company"));
        sa.setOpen_date(request.getParameter("opendate"));
        sa.setAccount_id(request.getParameter("capital1"));
        ca.setAccount_id(request.getParameter("capital1"));
        ca.setBank_name(request.getParameter("bank"));
        ca.setBank_card_number(request.getParameter("bankcard"));
        ca.setAccount_balance(Float.parseFloat(request.getParameter("balance")));
        ca.setEnable_balance(Float.parseFloat(request.getParameter("balance")));
        log.info("用户"+telephone+"开始开户");
        log.info("证券账户信息为： "+sa.toString());
        log.info("资金账户信息为： "+ca.toString());
        int flag = userService.openAccount(telephone,sa,ca);
        if (flag == 3) {
            log.info("用户"+telephone+"开户成功...");
            User user = (User) request.getSession().getAttribute("user");
            user.setSecurities_account_id(sa.getSecurities_account_id());
            request.getSession().setAttribute("user",user);
            return new ModelAndView("admin/holdings");
        }else{
            log.info("用户"+telephone+"开户失败...");
            return new ModelAndView("admin/create");
        }
    }

    /**
     *  用户登录后，进入后台管理首页，加载页面时获取账户基本信息:
     *      总市值、收益、可用余额、冻结金额、当前委托笔数 和用户基本信息
     */
    @RequestMapping("getAdminHomePageData")
    @ResponseBody
    public String getAdminHomePageData(HttpServletRequest request){
        Map<String,String> map = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        String securities_account_id = user.getSecurities_account_id();
        float marketValue = 0.0f;

        // 1. 获取当前持仓信息：证券代码和持有数量
        if (user.getSecurities_account_id() != "" && user.getSecurities_account_id() != null){
           marketValue = securitiesService.calTotalMarketValue(securities_account_id);
        }

        // 2. 获取当前资金情况: 可用余额和冻结金额
        CapitalAccount ca = capitalService.getCapitalAccountBySecuritiesId(securities_account_id);
        float enable_balance = ca.getEnable_balance();
        float frozen_balance = ca.getFrozen_balance();

        // 3.计算总的盈亏金额(初始金额为100000000)
        float earnings = marketValue + enable_balance + frozen_balance - 100000000.0f;

        // 4. 查询当前登录用户正在执行的委托笔数
        int entrust_count = entrustService.countNumberOfEntrustBySid(securities_account_id);

        String account_id = capitalService.getAccountIdBySid(securities_account_id);

        map.put("total_market_value",DateUtils.floatToString(marketValue));
        map.put("enable_balance",DateUtils.floatToString(enable_balance));
        map.put("frozen_balance",DateUtils.floatToString(frozen_balance));
        map.put("earnings",DateUtils.floatToString(earnings));
        map.put("entrust_num",DateUtils.floatToString(entrust_count));
        map.put("telephone",user.getTelephone());
        map.put("nickname",user.getNickname());
        map.put("securitiesAccount",securities_account_id);
        map.put("capitalAccount",account_id);
        map.put("email",user.getEmail());

        return JsonUtils.toJson(map);
    }

    /**
     * 获取当前用户持仓信息，未开户返回空结果
     */
    @RequestMapping("getHoldingData")
    @ResponseBody
    public String getHoldingData(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user.getSecurities_account_id() == null || user.getSecurities_account_id() == "")
            return "";
        String securities_account_id = user.getSecurities_account_id();
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));    //  获取页码
        Map<String, Object> map = new HashMap<>();

        List<Holdings> list = securitiesService.queryHoldingsBySid(securities_account_id,pageNum);
        map.put("holdingInfo",list);

        // 计算总市值
        float marketValue = securitiesService.calTotalMarketValue(securities_account_id);
        map.put("marketValue",marketValue);

        // 计算证券种类数量（公司数），也是总记录条数
        int count_company = securitiesService.countNumberOfCompanyBySid(securities_account_id);
        map.put("count_company",count_company);

        // 计算总页数
        int total = count_company%5==0 ? (count_company/5) : (count_company/5+1);   // 计算总页数
        map.put("total",total);

        // 计算总持有数量
        int count_stock = 0;
        for (Holdings holdings : list)
            count_stock += holdings.getHold_amount();
        map.put("count_stock",count_stock);

        pageNum = pageNum > 0 ? pageNum : 1 ; //  pageNum为0的时候重置pageNum为1
        map.put("pageNum",pageNum) ;

        return JsonUtils.toJson(map);
    }

    /**
     * 获取单只股票持仓信息
     * 返回 现价、可用数量
     */
    @RequestMapping("getHoldingInfoByStockCode")
    @ResponseBody
    public String getHoldingInfo(HttpServletRequest request){
        Map<String,String> map = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        String securities_account_id = user.getSecurities_account_id();
        if (securities_account_id == null || securities_account_id == "") {
            map.put("errCode","1");
            return JsonUtils.toJson(map);
        }
        map.put("errCode","0");

        String stock_code = request.getParameter("stock_code");
        long hold_amount = securitiesService.queryEnableAmountByStockAndSid(securities_account_id,stock_code);
        map.put("hold_amount", String.valueOf(hold_amount));   // 当前该支股票持仓可用数量
        map.put("current_price", String.valueOf(dataService.queryCurrentStockPrice(stock_code)));

        return JsonUtils.toJson(map);
    }
}
