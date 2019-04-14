package com.data;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class GetDataTest {

    public static void main(String[] args){
        URL url = null;
        HttpURLConnection conn = null;
        InputStream in = null;
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            url = new URL("http://money.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_MarketData.getKLineData?symbol=sh600570&scale=5&ma=5&datalen=1023");
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
                int b = 0;
                File file = new File("D:stock.txt");
                if(!file.exists()){
                    file.createNewFile();
                }
                br = new BufferedReader(new InputStreamReader(in,"utf-8"));
                bw = new BufferedWriter(new FileWriter(file));
                while ((b=br.read())!=-1){
                    bw.write(b);
                }
//                JSONObject jsonObject = (JSONObject) JSONObject.parse(html);
            }
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
                br.close();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
