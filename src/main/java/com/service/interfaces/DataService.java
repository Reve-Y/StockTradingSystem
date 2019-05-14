package com.service.interfaces;

import com.domain.StockInfo;

public interface DataService {
    public String getHistoryStockData(String url);

    float queryCurrentStockPrice(String stock_code);

    String getStockName(String stockCode);

    boolean checkStockCode(String stock_code);

    int checkIfAccountExist(String sid, String cid);

    StockInfo getBasicStockInfo(String stock_code);
}
