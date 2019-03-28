package com.domain;

/**
 * 资金账户表
 */
public class CapitalAccount {

    private String account_id ;          //  资金账号号码
    private String bank_name;         //  存管银行
    private String bank_card_number ;  //  银行卡号
    private float frozen_balance ;    //  冻结金额
    private float account_balance ;     //  账户余额
    private float enable_balance ;    //  可用余额


    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
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

    public float getAccount_balance() {
        return account_balance;
    }

    public void setAccount_balance(float account_balance) {
        this.account_balance = account_balance;
    }

    public float getFrozen_balance() {
        return frozen_balance;
    }

    public void setFrozen_balance(float frozen_balance) {
        this.frozen_balance = frozen_balance;
    }

    public float getEnable_balance() {
        return enable_balance;
    }

    public void setEnable_balance(float enable_balance) {
        this.enable_balance = enable_balance;
    }

    @Override
    public String toString() {
        return "CapitalAccount{" +
                "account_id=" + account_id +
                ", bank_name='" + bank_name + '\'' +
                ", bank_card_number='" + bank_card_number + '\'' +
                ", account_balance=" + account_balance +
                ", frozen_balance=" + frozen_balance +
                ", enable_balance=" + enable_balance +
                '}';
    }
}

