package com.service.impls;

import com.service.interfaces.DataService;
import com.util.HttpUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@Service
public class DataServiceImpl implements DataService {
    private static Logger log = Logger.getLogger(DataServiceImpl.class);

    /**
     * 获取某只股票近日的行情数据
     * @param url
     * @return
     */
    @Override
    public String getHistoryStockData(String url) {
        String res = null;
        try {
            res =  HttpUtils.get(url);
            log.info("获取到的股票历史数据为："+res);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 获取某只股票当前的价格
     * @param stock_code 证券代码
     * @return 现价
     */
    @Override
    public float queryCurrentStockPrice(String stock_code) {
        // 开源api
        StringBuilder url = new StringBuilder("http://hq.sinajs.cn/list=");

        // 判断该证券是上交所还是深交所上市的股票
        if (stock_code.charAt(0) == '6')
            url.append("sh");
        else
            url.append("sz");
        url.append(stock_code);
        String data;
        float price = 0.0f;
        try {
            data = HttpUtils.get(new String(url));
            price = Float.parseFloat(data.split(",")[3]);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("获取"+stock_code+"信息失败");
        }
        log.info("证券代码"+stock_code+"目前的价格为"+price);
        return price;
    }
}
