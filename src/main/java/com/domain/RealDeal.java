package com.domain;

/**
 *  成交记录
 */
public class RealDeal {

    private String entrust_key ;
    private String deal_date; // 成交日期
    private String securities_account_id ;
    private String stock_code;
    private int deal_direction;
    private String stock_name; // 数据库没有这个字段
    private long deal_amount;
    private float deal_price;
    private float deal_amount_money; // 成交金额
    private float deal_capital_balance ; // 成交后资金账户可用余额
    private String capital_account_id ;

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public String getSecurities_account_id() {
        return securities_account_id;
    }

    public void setSecurities_account_id(String securities_account_id) {
        this.securities_account_id = securities_account_id;
    }

    public String getCapital_account_id() {
        return capital_account_id;
    }

    public void setCapital_account_id(String capital_account_id) {
        this.capital_account_id = capital_account_id;
    }

    public String getEntrust_key() {
        return entrust_key;
    }

    public void setEntrust_key(String entrust_key) {
        this.entrust_key = entrust_key;
    }
    public String getDeal_date() {
        return deal_date;
    }

    public void setDeal_date(String deal_date) {
        this.deal_date = deal_date;
    }

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public int getDeal_direction() {
        return deal_direction;
    }

    public void setDeal_direction(int deal_direction) {
        this.deal_direction = deal_direction;
    }

    @Override
    public String toString() {
        return "RealDeal{" +
                "entrust_key='" + entrust_key + '\'' +
                ", deal_date='" + deal_date + '\'' +
                ", securities_account_id='" + securities_account_id + '\'' +
                ", stock_code='" + stock_code + '\'' +
                ", deal_direction=" + deal_direction +
                ", stock_name='" + stock_name + '\'' +
                ", deal_amount=" + deal_amount +
                ", deal_price=" + deal_price +
                ", deal_amount_money=" + deal_amount_money +
                ", deal_capital_balance=" + deal_capital_balance +
                ", capital_account_id='" + capital_account_id + '\'' +
                '}';
    }

    public long getDeal_amount() {
        return deal_amount;
    }

    public void setDeal_amount(long deal_amount) {
        this.deal_amount = deal_amount;
    }

    public float getDeal_price() {
        return deal_price;
    }

    public void setDeal_price(float deal_price) {
        this.deal_price = deal_price;
    }

    public float getDeal_amount_money() {
        return deal_amount_money;
    }

    public void setDeal_amount_money(float deal_amount_money) {
        this.deal_amount_money = deal_amount_money;
    }

    public float getDeal_capital_balance() {
        return deal_capital_balance;
    }

    public void setDeal_capital_balance(float deal_capital_balance) {
        this.deal_capital_balance = deal_capital_balance;
    }
}
