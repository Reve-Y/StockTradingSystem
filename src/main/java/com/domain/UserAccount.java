package com.domain;

/**
 *  用户-证券账户对应表
 */
public class UserAccount {

    private String user_id;
    private int securities_company_id;   // 证券公司代号
    private String securities_account_id; //  证券账户号码

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getSecurities_company_id() {
        return securities_company_id;
    }

    public void setSecurities_company_id(int securities_company_id) {
        this.securities_company_id = securities_company_id;
    }

    public String getSecurities_account_id() {
        return securities_account_id;
    }

    public void setSecurities_account_id(String securities_account_id) {
        this.securities_account_id = securities_account_id;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "user_id='" + user_id + '\'' +
                ", securities_company_id=" + securities_company_id +
                ", securities_account_id='" + securities_account_id + '\'' +
                '}';
    }
}
