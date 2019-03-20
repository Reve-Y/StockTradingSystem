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
     * 获取某只股票前几天的数据
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
}
