package com.group7.secureBankAccounts.data.model;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.group7.secureBankAccounts.ApiRequest.Api;
import com.group7.secureBankAccounts.ApiRequest.ApiInterface;

import org.jetbrains.annotations.NotNull;


import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Users {

    private String userName;
    private String lastName;
    private int id;
    private String password;
    private List<BankAccount> allBankAccount = null;


    public Users(String userName, String lastName, int id, String password) {
        this.userName = userName;
        this.lastName = lastName;
        this.id = id;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userName='" + userName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id=" + id +
                ", password='" + password + '\'' +
                ", allBankAccount=" + allBankAccount +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<BankAccount> getAllBankAccount() {
        return allBankAccount;
    }

    public void setAllBankAccount(List<BankAccount> allBankAccount) {
        this.allBankAccount = allBankAccount;
    }
}
