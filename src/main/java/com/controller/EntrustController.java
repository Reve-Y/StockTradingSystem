package com.controller;

import com.domain.CurrentEntrust;
import com.domain.HistoryEntrust;
import com.domain.Holdings;
import com.domain.User;
import com.service.interfaces.DataService;
import com.service.interfaces.EntrustService;
import com.util.DateUtils;
import com.util.JsonUtils;
import com.util.UUIDUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EntrustController {

    @Autowired
    private EntrustService entrustService;

    @Autowired
    private DataService dataService;

    /**
     * 普通委托
     * 如果没有开户前端是不会发送请求的，所以这里只校验传入的证券代码是否合理
     */
    @RequestMapping("doentrust")
    @ResponseBody
    public String doEntrust(HttpServletRequest request){
        if (!dataService.checkStockCode(request.getParameter("stock_code"))){
            return "fail";
        }

        User user = (User) request.getSession().getAttribute("user");
        // 获取委托信息
        CurrentEntrust ce = new CurrentEntrust();

        ce.setEntrust_date(DateUtils.getDateAndTime());
        ce.setSecurities_account_id(user.getSecurities_account_id());
        ce.setStock_code(request.getParameter("stock_code"));
        ce.setEntrust_direction(Integer.parseInt(request.getParameter("entrust_direction")));
        ce.setEntrust_amount(Long.parseLong(request.getParameter("entrust_amount")));
        ce.setEntrust_price(Float.parseFloat(request.getParameter("entrust_price")));
        ce.setAmount_money(Float.parseFloat(request.getParameter("amount_money")));
        ce.setEntrust_key(UUIDUtils.getUUID());

        int flag = 0;

        flag = entrustService.normalEntrust(ce);
        return (flag == 0 ? "fail" : "ok" );
    }

    /**
     * 获取当前正在执行的委托信息,使用PageHelper分页插件进行分页
     * 从request中获取pageNum参数，pageSize设置为5（即每页只有5条数据）
     */
    @RequestMapping("getCurrentEntrustData")
    @ResponseBody
    public String getCurrentEntrustData(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        if (user.getSecurities_account_id() == null || user.getSecurities_account_id() == "")
            return "";
        String securities_account_id = user.getSecurities_account_id();
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));    //  获取页码

        List<CurrentEntrust> list = entrustService.queryCurrentEntrustBySid(securities_account_id,pageNum);
        map.put("entrustInfo",list);

        int count_entrust = entrustService.countNumberOfEntrustBySid(securities_account_id);
        map.put("count_entrust",count_entrust);
        pageNum = pageNum > 0 ? pageNum : 1 ; //  pageNum为0的时候重置pageNum为1
        map.put("pageNum",pageNum) ;

        int total = count_entrust%5==0 ? (count_entrust/5) : (count_entrust/5+1);   // 计算总页数
        map.put("total",total);

        return JsonUtils.toJson(map);
    }

    /**
     *  获取所有历史委托信息，包括各种状态的委托，分页显示，每页默认最多5条数据
     */
    @RequestMapping("getHistoryEntrustData")
    @ResponseBody
    public String getHistoryEntrustData(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        if (user.getSecurities_account_id() == null || user.getSecurities_account_id() == "")
            return "";
        String securities_account_id = user.getSecurities_account_id();
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));    //  获取页码

        List<HistoryEntrust> list = entrustService.queryHistoryEntrustBySid(securities_account_id,pageNum);
        map.put("historyInfo",list);

        int count_history = entrustService.countNumberOfHistoryEntBySid(securities_account_id);
        map.put("count_history",count_history);
        pageNum = pageNum > 0 ? pageNum : 1 ; //  pageNum为0的时候重置pageNum为1
        map.put("pageNum",pageNum) ;

        int total = count_history%5==0 ? (count_history/5) : (count_history/5+1);   // 计算总页数
        map.put("total",total);

        return JsonUtils.toJson(map);
    }

    /**
     *  委托撤单：根据每笔执行中的委托的唯一标识 entrust_key 来进行撤单
     *  成功返回"ok",失败返回"fail"
     */
    @RequestMapping("withdrawEntrust")
    @ResponseBody
    public String withdrawEntrust(@Param("entrust_key")String entrust_key){
        int flag = entrustService.withdrawEntrustByKey(entrust_key);
        Map<String,String> map = new HashMap<>();

        if(flag == 2) {
            map.put("status","ok");
        } else {
            map.put("status","fail");
        }
        return JsonUtils.toJson(map);
    }
}
