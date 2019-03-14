package com.domain;

/**
 * 证券账户信息表
 */
public class SecuritiesAccount {

    private String securities_account_id;   //  证券帐号
    private String open_date;               //  开户日期
    private String securities_company_name; //  开户证券公司名称
    private int account_id;                 //  对应的资金账号

    public String getSecurities_account_id() {
        return securities_account_id;
    }

    public void setSecurities_account_id(String securities_account_id) {
        this.securities_account_id = securities_account_id;
    }

    public String getOpen_date() {
        return open_date;
    }

    public void setOpen_date(String open_date) {
        this.open_date = open_date;
    }

    public String getSecurities_company_name() {
        return securities_company_name;
    }

    public void setSecurities_company_name(String securities_company_name) {
        this.securities_company_name = securities_company_name;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    @Override
    public String toString() {
        return "SecuritiesAccount{" +
                "securities_account_id='" + securities_account_id + '\'' +
                ", open_date='" + open_date + '\'' +
                ", securities_company_name='" + securities_company_name + '\'' +
                ", account_id=" + account_id +
                '}';
    }
}