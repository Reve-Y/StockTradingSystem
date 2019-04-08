package com.controller;

import com.domain.CurrentEntrust;
import com.domain.User;
import com.service.interfaces.EntrustService;
import com.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class EntrustController {

    @Autowired
    private EntrustService entrustService;

    /**
     * 普通委托
     * 传入的信息在这里不做校验，前端已做过相关校验
     */
    @RequestMapping("doentrust")
    public int doEntrust(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        // 获取委托信息
        String securitiesAccount = user.getSecurities_account_id();
        String date = DateUtils.getDateAndTime();
        String stockCode = request.getParameter("stockcode");
        int entrustDirection = Integer.parseInt(request.getParameter("entrustdirection"));
        long entrustAmount = Long.parseLong(request.getParameter("entrustamount"));
        float entrustPrice = Float.parseFloat(request.getParameter("entrustprice"));
        float amountOfMoney = entrustAmount * entrustPrice;
        CurrentEntrust ce = new CurrentEntrust();
        ce.setEntrust_date(date);
        ce.setSecurities_account_id(securitiesAccount);
        ce.setStock_code(stockCode);
        ce.setEntrust_direction(entrustDirection);
        ce.setEntrust_amount(entrustAmount);
        ce.setEntrust_price(entrustPrice);
        ce.setAmount_money(amountOfMoney);
        int flag = 0;
        flag = entrustService.normalEntrust(ce);
        return flag;
    }
}
