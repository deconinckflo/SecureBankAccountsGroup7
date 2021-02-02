package com.group7.secureBankAccounts.data.model;

public class BankAccount {
    private int _id;
    private double amount;
    private String account_name;
    private String currency;
    private String iban;


    public BankAccount() {
    }

    public BankAccount(int _id, double amount, String account_name, String currency, String iban) {
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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
}
