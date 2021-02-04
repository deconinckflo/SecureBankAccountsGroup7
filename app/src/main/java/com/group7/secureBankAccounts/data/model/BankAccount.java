package com.group7.secureBankAccounts.data.model;

import java.math.BigDecimal;

public class BankAccount {
    private int _id;
    private BigDecimal amount;
    private String account_name;
    private String currency;
    private String iban;


    public BankAccount() {
    }

    public BankAccount(int _id, BigDecimal amount, String account_name, String currency, String iban) {
        this._id = _id;
        this.amount = amount;
        this.account_name = account_name;
        this.currency = currency;
        this.iban = iban;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }



    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "_id=" + _id +
                ", amount=" + amount +
                ", account_name='" + account_name + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
