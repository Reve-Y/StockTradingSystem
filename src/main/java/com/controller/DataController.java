package com.controller;

import com.domain.User;
import com.service.interfaces.CapitalService;
import com.service.interfaces.DataService;
import com.util.JsonUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class DataController {
    private static Logger log=Logger.getLogger(DataController.class);

    @Autowired
    public DataService dataService;

    @Autowired
    public CapitalService capitalService;

    /**
     * 获取某只股票近日的行情，需要传入四个参数，采用sina的开源接口：
     * http://money.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_MarketData.getKLineData?
     * 参数1: symbol: 股票编号，上交所的有sh前缀，即 sh+股票代码，例如"sh600570"
     * 参数2：scale: 分钟间隔，数值为 5、15、30、60
     * 参数3：ma: 均值，数值为 5、10、15、20、25
     * 参数4：datalen: 查询个数点，最大值242
     */
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

    /**
     * 在交易界面输入完证券代码后，获取相应的证券名称并显示
     */
    @RequestMapping("getStockNameByCode")
    @ResponseBody
    public String getStockNameByCode(@Param("stock_code")String stock_code){
        Map<String,String> map = new HashMap<>();
        if (!dataService.checkStockCode(stock_code)){
            map.put("errCode","2");
            return JsonUtils.toJson(map);
        }
        map.put("stock_name",dataService.getStockName(stock_code));
        return JsonUtils.toJson(map);
    }

    /**
     *  试图买入某只股票时获取的数据，返回一些账户信息和该支股票的信息
     *  具体包括这只股票的现价、以及当前登录用户的余额信息等
     */
    @RequestMapping("getBuyInfoByStockCode")
    @ResponseBody
    public String getBuyInfoByStockCode(HttpServletRequest request){
        Map<String,String> map = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        String securities_account_id = user.getSecurities_account_id();
        if (securities_account_id == null || securities_account_id == "") {
            map.put("errCode","1");
            return JsonUtils.toJson(map);
        }

        String stock_code = request.getParameter("stock_code");
        if (!dataService.checkStockCode(stock_code)){
            map.put("errCode","2");
            return JsonUtils.toJson(map);
        }

        map.put("errCode","0");  //  0 表示ok

        // 获取这只股票的现价
        map.put("current_price", Float.toString(dataService.queryCurrentStockPrice(stock_code)));

        // 获取账户可用余额
        String capital_id = capitalService.getAccountIdBySid(securities_account_id);
        map.put("enable_balance",String.valueOf(capitalService.queryEnableBalanceByCid(capital_id)));

        return JsonUtils.toJson(map);
    }

    /**
     * 检查证券账户和资金账户是否已存在
     */
    @RequestMapping("checkIfAccountExist")
    public String checkIfAccountExist(@Param("sid")String sid,@Param("cid") String cid){
        int flag = dataService.checkIfAccountExist(sid,cid);
        if (flag == 0)
            return "ok";
        else
            return "fail";
    }
}
