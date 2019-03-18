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

    @Deprecated
    public String getHistoryStockDataOld(String requestUrl) {
        URL url = null;
        HttpURLConnection conn = null;
        InputStream in = null;
        BufferedReader br = null;
        try {
            url = new URL(requestUrl);
            //调用URL对象的openConnection( )来获取HttpURLConnection对象实例
            conn = (HttpURLConnection) url.openConnection();
            //请求方法为GET
            conn.setRequestMethod("GET");
            //设置连接超时为5秒
            conn.setConnectTimeout(5000);
            //服务器返回东西了，先对响应码判断
            if (conn.getResponseCode() == 200) {
                //用getInputStream()方法获得服务器返回的输入流
                in = conn.getInputStream();
                br = new BufferedReader(new InputStreamReader(in,"utf-8"));
                char[] data = new char[40960];
                int b = 0;
                while ((b=br.read(data,0,data.length-1))!=-1){
                    System.out.print(data);
                }
                br = new BufferedReader(new InputStreamReader(in,"utf-8"));
                String html = String.valueOf(data);
                System.out.println(html.length());
                log.info("字符串长度为："+html.length());
                return html;
            }
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

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
