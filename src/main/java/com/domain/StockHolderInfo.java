package com.domain;

/**
 * 某一证券账户持仓信息表
 */
public class StockHolderInfo {

    private String securities_account_id ;   // 证券账户号码
    private int stock_code ;                 // 股票代码
    private String stock_name ;              // 股票名称
    private long hold_amount ;               // 持仓数量
    private float buy_price ;                // 买入时价格

    public float getBuy_price() {
        return buy_price;
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

    public int getStock_code() {
        return stock_code;
    }

    public void setStock_code(int stock_code) {
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

    @Override
    public String toString() {
        return "StockHolderInfo{" +
                "securities_account_id='" + securities_account_id + '\'' +
                ", stock_code=" + stock_code +
                ", stock_name='" + stock_name + '\'' +
                ", hold_amount=" + hold_amount +
                ", buy_price=" + buy_price +
                '}';
    }
}
