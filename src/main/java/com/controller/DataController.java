package com.controller;

import com.service.interfaces.DataService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Controller
public class DataController {
    private static Logger log=Logger.getLogger(DataController.class);

    @Autowired
    public DataService dataService;

    @RequestMapping("getHistoryData")
    @ResponseBody
    public String getHistoryStockData(HttpServletRequest request, HttpServletResponse response){
        String symbol = request.getParameter("symbol");
        String ma = request.getParameter("ma");
        String scale = request.getParameter("scale");
        String datalen = request.getParameter("datalen");
        log.info(new Date().toString()+"获取到请求参数：股票代码symbol="+symbol+" ;分钟间隔scale="+scale
        +" ;均值ma="+ma+" ;查询个数点datalen="+datalen);
        String url = "http://money.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_MarketData.getKLineData?"+
                "symbol="+symbol+"&scale="+scale+"&ma="+ma+"&datalen="+datalen;
        log.info("请求的url为："+url);
        String data = null;
        data = dataService.getHistoryStockData(url);
        return data;
    }
}
