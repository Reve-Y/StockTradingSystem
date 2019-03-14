package com.domain;

/**
 *  股票信息
 */

public class StockInfo {

    private int stock_code;                 // 股票代码
    private String market_name;             // 交易市场
    private long total_amount;              // 总股本
    private long turnover_amount;           // 流通股本
    private float yesterday_closed_price ;  // 昨收盘价
    private float open_price ;              // 今开盘价
    private String company_name ;           // 发行公司
    private int stop_flag;                  // 停牌标志
    private float last_price;               // 最新价/今收盘价
    private float avg_price;                // 平均价格
    private float max_price;                // 今最高价
    private float min_price;                // 今最低价
    private String publish_date;            // 发行日期
    private float buy_price1;               // 买一价
    private int buy_amount1;                // 买一数量
    private float buy_price2;               // 买二价
    private int buy_amount2;                // 买二数量
    private float buy_price3;               // 买三价
    private int buy_amount3;                // 买三数量
    private float buy_price4;               // 买四价
    private int buy_amount4;                // 买四数量
    private float buy_price5;               // 买五价
    private int buy_amount5;                // 买五数量
    private float sale_price1;               // 卖一价
    private int sale_amount1;                // 卖一数量
    private float sale_price2;               // 卖二价
    private int sale_amount2;                // 卖二数量
    private float sale_price3;               // 卖三价
    private int sale_amount3;                // 卖三数量
    private float sale_price4;               // 卖四价
    private int sale_amount4;                // 卖四数量
    private float sale_price5;               // 卖五价
    private int sale_amount5;                // 卖五数量
    private long deal_amount;                // 成交量

    public int getStock_code() {
        return stock_code;
    }

    public void setStock_code(int stock_code) {
        this.stock_code = stock_code;
    }

    public String getMarket_name() {
        return market_name;
    }

    public void setMarket_name(String market_name) {
        this.market_name = market_name;
    }

    public long getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(long total_amount) {
        this.total_amount = total_amount;
    }

    public long getTurnover_amount() {
        return turnover_amount;
    }

    public void setTurnover_amount(long turnover_amount) {
        this.turnover_amount = turnover_amount;
    }

    public float getYesterday_closed_price() {
        return yesterday_closed_price;
    }

    public void setYesterday_closed_price(float yesterday_closed_price) {
        this.yesterday_closed_price = yesterday_closed_price;
    }

    public float getOpen_price() {
        return open_price;
    }

    public void setOpen_price(float open_price) {
        this.open_price = open_price;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public int getStop_flag() {
        return stop_flag;
    }

    public void setStop_flag(int stop_flag) {
        this.stop_flag = stop_flag;
    }

    public float getLast_price() {
        return last_price;
    }

    public void setLast_price(float last_price) {
        this.last_price = last_price;
    }

    public float getAvg_price() {
        return avg_price;
    }

    public void setAvg_price(float avg_price) {
        this.avg_price = avg_price;
    }

    public float getMax_price() {
        return max_price;
    }

    public void setMax_price(float max_price) {
        this.max_price = max_price;
    }

    public float getMin_price() {
        return min_price;
    }

    public void setMin_price(float min_price) {
        this.min_price = min_price;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    public float getBuy_price1() {
        return buy_price1;
    }

    public void setBuy_price1(float buy_price1) {
        this.buy_price1 = buy_price1;
    }

    public int getBuy_amount1() {
        return buy_amount1;
    }

    public void setBuy_amount1(int buy_amount1) {
        this.buy_amount1 = buy_amount1;
    }

    public float getBuy_price2() {
        return buy_price2;
    }

    public void setBuy_price2(float buy_price2) {
        this.buy_price2 = buy_price2;
    }

    public int getBuy_amount2() {
        return buy_amount2;
    }

    public void setBuy_amount2(int buy_amount2) {
        this.buy_amount2 = buy_amount2;
    }

    public float getBuy_price3() {
        return buy_price3;
    }

    public void setBuy_price3(float buy_price3) {
        this.buy_price3 = buy_price3;
    }

    public int getBuy_amount3() {
        return buy_amount3;
    }

    public void setBuy_amount3(int buy_amount3) {
        this.buy_amount3 = buy_amount3;
    }

    public float getBuy_price4() {
        return buy_price4;
    }

    public void setBuy_price4(float buy_price4) {
        this.buy_price4 = buy_price4;
    }

    public int getBuy_amount4() {
        return buy_amount4;
    }

    public void setBuy_amount4(int buy_amount4) {
        this.buy_amount4 = buy_amount4;
    }

    public float getBuy_price5() {
        return buy_price5;
    }

    public void setBuy_price5(float buy_price5) {
        this.buy_price5 = buy_price5;
    }

    public int getBuy_amount5() {
        return buy_amount5;
    }

    public void setBuy_amount5(int buy_amount5) {
        this.buy_amount5 = buy_amount5;
    }

    public float getSale_price1() {
        return sale_price1;
    }

    public void setSale_price1(float sale_price1) {
        this.sale_price1 = sale_price1;
    }

    public int getSale_amount1() {
        return sale_amount1;
    }

    public void setSale_amount1(int sale_amount1) {
        this.sale_amount1 = sale_amount1;
    }

    public float getSale_price2() {
        return sale_price2;
    }

    public void setSale_price2(float sale_price2) {
        this.sale_price2 = sale_price2;
    }

    public int getSale_amount2() {
        return sale_amount2;
    }

    public void setSale_amount2(int sale_amount2) {
        this.sale_amount2 = sale_amount2;
    }

    public float getSale_price3() {
        return sale_price3;
    }

    public void setSale_price3(float sale_price3) {
        this.sale_price3 = sale_price3;
    }

    public int getSale_amount3() {
        return sale_amount3;
    }

    public void setSale_amount3(int sale_amount3) {
        this.sale_amount3 = sale_amount3;
    }

    public float getSale_price4() {
        return sale_price4;
    }

    public void setSale_price4(float sale_price4) {
        this.sale_price4 = sale_price4;
    }

    public int getSale_amount4() {
        return sale_amount4;
    }

    public void setSale_amount4(int sale_amount4) {
        this.sale_amount4 = sale_amount4;
    }

    public float getSale_price5() {
        return sale_price5;
    }

    public void setSale_price5(float sale_price5) {
        this.sale_price5 = sale_price5;
    }

    public int getSale_amount5() {
        return sale_amount5;
    }

    public void setSale_amount5(int sale_amount5) {
        this.sale_amount5 = sale_amount5;
    }

    public long getDeal_amount() {
        return deal_amount;
    }

    public void setDeal_amount(long deal_amount) {
        this.deal_amount = deal_amount;
    }

    @Override
    public String toString() {
        return "StockInfo{" +
                "stock_code=" + stock_code +
                ", market_name='" + market_name + '\'' +
                ", total_amount=" + total_amount +
                ", turnover_amount=" + turnover_amount +
                ", yesterday_closed_price=" + yesterday_closed_price +
                ", open_price=" + open_price +
                ", company_name='" + company_name + '\'' +
                ", stop_flag=" + stop_flag +
                ", last_price=" + last_price +
                ", avg_price=" + avg_price +
                ", max_price=" + max_price +
                ", min_price=" + min_price +
                ", publish_date='" + publish_date + '\'' +
                ", buy_price1=" + buy_price1 +
                ", buy_amount1=" + buy_amount1 +
                ", buy_price2=" + buy_price2 +
                ", buy_amount2=" + buy_amount2 +
                ", buy_price3=" + buy_price3 +
                ", buy_amount3=" + buy_amount3 +
                ", buy_price4=" + buy_price4 +
                ", buy_amount4=" + buy_amount4 +
                ", buy_price5=" + buy_price5 +
                ", buy_amount5=" + buy_amount5 +
                ", sale_price1=" + sale_price1 +
                ", sale_amount1=" + sale_amount1 +
                ", sale_price2=" + sale_price2 +
                ", sale_amount2=" + sale_amount2 +
                ", sale_price3=" + sale_price3 +
                ", sale_amount3=" + sale_amount3 +
                ", sale_price4=" + sale_price4 +
                ", sale_amount4=" + sale_amount4 +
                ", sale_price5=" + sale_price5 +
                ", sale_amount5=" + sale_amount5 +
                ", deal_amount=" + deal_amount +
                '}';
    }
}
