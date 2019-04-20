package com.domain;

public class HistoryEntrust {

    private String entrust_date;    // 委托日期
    private String securities_account_id ; // 证券账户id
    private String stock_code;          //  证券代码
    private String stock_name;          //  证券名称：数据库中没有这个字段
    private int entrust_direction;   //  委托方向： 1 买入 2 卖出
    private long entrust_amount;      //  委托数量
    private float entrust_price;        //  委托价格
    private int entrust_status;         // 委托状态： 0 执行中 ， 1 已成交 ，2 已撤单 ，3 系统废弃
    private String status ;             // 委托状态，数据库没有这个字段，根据上个属性转换

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEntrust_date() {
        return entrust_date;
    }

    public void setEntrust_date(String entrust_date) {
        this.entrust_date = entrust_date;
    }

    @Override
    public String toString() {
        return "HistoryEntrust{" +
                "entrust_date='" + entrust_date + '\'' +
                ", securities_account_id='" + securities_account_id + '\'' +
                ", stock_code='" + stock_code + '\'' +
                ", stock_name='" + stock_name + '\'' +
                ", entrust_direction=" + entrust_direction +
                ", entrust_amount=" + entrust_amount +
                ", entrust_price=" + entrust_price +
                ", entrust_status=" + entrust_status +
                ", status='" + status + '\'' +
                '}';
    }

    public String getSecurities_account_id() {
        return securities_account_id;
    }

    public void setSecurities_account_id(String securities_account_id) {
        this.securities_account_id = securities_account_id;
    }

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public int getEntrust_direction() {
        return entrust_direction;
    }

    public void setEntrust_direction(int entrust_direction) {
        this.entrust_direction = entrust_direction;
    }

    public long getEntrust_amount() {
        return entrust_amount;
    }

    public void setEntrust_amount(long entrust_amount) {
        this.entrust_amount = entrust_amount;
    }

    public float getEntrust_price() {
        return entrust_price;
    }

    public void setEntrust_price(float entrust_price) {
        this.entrust_price = entrust_price;
    }

    public int getEntrust_status() {
        return entrust_status;
    }

    public void setEntrust_status(int entrust_status) {
        this.entrust_status = entrust_status;
    }

}
