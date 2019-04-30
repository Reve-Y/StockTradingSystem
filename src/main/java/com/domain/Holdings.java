package com.domain;

/**
 * 某一证券账户持仓信息表
 * 这张表靠证券账户、证券代码两个字段可以确定唯一的一条记录
 * 所以更新单条记录时需要同时传入证券账户、证券代码
 * 另外，buy_price是这只股票买入的均价
 */
public class Holdings {

    private String securities_account_id ;   // 证券账户号码
    private String stock_code ;                 // 股票代码
    private String stock_name ;              // 股票名称
    private long hold_amount ;               // 持仓数量
    private float buy_price ;                // 买入均价
    private float now_price ;               //  现价 数据库表中没有此字段，后期获取
    private long enable_amount ;             // 可用数量

    public float getBuy_price() {
        return buy_price;
    }

    public long getEnable_amount() {
        return enable_amount;
    }

    @Override
    public String toString() {
        return "Holdings{" +
                "securities_account_id='" + securities_account_id + '\'' +
                ", stock_code='" + stock_code + '\'' +
                ", stock_name='" + stock_name + '\'' +
                ", hold_amount=" + hold_amount +
                ", buy_price=" + buy_price +
                ", now_price=" + now_price +
                ", enable_amount=" + enable_amount +
                '}';
    }

    public void setEnable_amount(long enable_amount) {
        this.enable_amount = enable_amount;
    }

    public float getNow_price() {
        return now_price;
    }

    public void setNow_price(float now_price) {
        this.now_price = now_price;
    }

    public void setBuy_price(float buy_price) {
        this.buy_price = buy_price;
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

    public long getHold_amount() {
        return hold_amount;
    }

    public void setHold_amount(long hold_amount) {
        this.hold_amount = hold_amount;
    }

}
