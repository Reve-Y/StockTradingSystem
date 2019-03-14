package com.domain;

/**
 * 资金账户表
 */
public class CapitalAccount {

    private int account_id ;          //  资金账号号码
    private String bank_name;         //  存管银行
    private String bank_card_number;  //  银行卡号
    private long account_balance;     //  余额

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBank_card_number() {
        return bank_card_number;
    }

    public void setBank_card_number(String bank_card_number) {
        this.bank_card_number = bank_card_number;
    }

    public long getAccount_balance() {
        return account_balance;
    }

    public void setAccount_balance(long account_balance) {
        this.account_balance = account_balance;
    }

    @Override
    public String toString() {
        return "CapitalAccount{" +
                "account_id=" + account_id +
                ", bank_name='" + bank_name + '\'' +
                ", bank_card_number='" + bank_card_number + '\'' +
                ", account_balance=" + account_balance +
                '}';
    }
}

