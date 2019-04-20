package com.controller;

import com.domain.RealDeal;
import com.domain.User;
import com.service.interfaces.DealService;
import com.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DealController {

    @Autowired
    private DealService dealService;

    /**
     *  获取成交记录，分页显示
     */
    @RequestMapping("getRealDealData")
    @ResponseBody
    public String getRealDealData(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user.getSecurities_account_id() == null || user.getSecurities_account_id() == "")
            return "";
        String securities_account_id = user.getSecurities_account_id();
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));    //  获取页码
        Map<String, Object> map = new HashMap<>();

        List<RealDeal> list = dealService.queryDealRecordBySid(securities_account_id,pageNum);
        map.put("dealInfo",list);

        int count_deal = dealService.countNumberOfDealsBySid(securities_account_id);
        map.put("count_deal",count_deal);
        pageNum = pageNum > 0 ? pageNum : 1 ; //  pageNum为0的时候重置pageNum为1
        map.put("pageNum",pageNum) ;

        int total = count_deal%5==0 ? (count_deal/5) : (count_deal/5+1);   // 计算总页数
        map.put("total",total);

        return JsonUtils.toJson(map);
    }
}
