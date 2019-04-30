package com.service.impls;

import com.dao.CapitalDao;
import com.dao.SecuritiesDao;
import com.service.interfaces.DataService;
import com.service.interfaces.SecuritiesService;
import com.util.HttpUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@Service
public class DataServiceImpl implements DataService {
    private static Logger log = Logger.getLogger(DataServiceImpl.class);

    @Autowired
    private SecuritiesDao securitiesDao;

    @Autowired
    private CapitalDao capitalDao;

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
     * 获取某只股票当前的价格：url示例：http://hq.sinajs.cn/list=sh600570
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

    /**
     * 根据股票代码获取股票名称
     */
    public String getStockName(String stockCode){
        // 开源api
        StringBuilder url = new StringBuilder("http://hq.sinajs.cn/list=");

        // 判断该证券是上交所还是深交所上市的股票
        if (stockCode.charAt(0) == '6')
            url.append("sh");
        else
            url.append("sz");
        url.append(stockCode);
        String data;
        String stockName = "";
        try {
            data = HttpUtils.get(new String(url));
            stockName = data.split(",")[0].replaceAll(".*=\"","");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("获取"+stockCode+"信息失败");
        }
        return stockName;
    }

    /**
     * 用来校验某证券代码的合法性:根据返回的数据长度来判断，查询不到那就当做是非法的吧。。。
     * 已经停牌的也是可以查到的
     * @param stockCode 需要校验的代码
     * @return
     */
    @Override
    public boolean checkStockCode(String stockCode) {
        StringBuilder url = new StringBuilder("http://hq.sinajs.cn/list=");

        // 判断该证券是上交所还是深交所上市的股票
        if (stockCode.charAt(0) == '6')
            url.append("sh");
        else
            url.append("sz");
        url.append(stockCode);
        String data = "";
        try {
            data = HttpUtils.get(new String(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (data.length() < 40)
            return false;
        return true;
    }

    /**
     * 检查证券账户和资金账户是否已存在，重复
     * @param sid
     * @param cid
     * @return 返回 0 即表示没有重复
     */
    @Override
    public int checkIfAccountExist(String sid, String cid) {
        int flag = 0;
        flag += securitiesDao.checkIfSidExists(sid);
        flag += capitalDao.checkIfCidExists(cid);
        return flag;
    }
}
