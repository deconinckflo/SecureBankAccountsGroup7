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
    private int _IdDb;
    private String Password;
    private List<BankAccount> allBankAccount;

    public Users(String userName, String lastName, int _IdDb, String password) {
        this.userName = userName;
        this.lastName = lastName;
        this._IdDb = _IdDb;
        Password = password;
    }

    public Users() {
    }

    @Override
    public String toString() {
        return "Users{" +
                "userName='" + userName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", _IdDb=" + _IdDb +
                ", Password='" + Password + '\'' +
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

    public int get_IdDb() {
        return _IdDb;
    }

    public void set_IdDb(int _IdDb) {
        this._IdDb = _IdDb;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }



    public void setAccount(){
        Executor mBackgroundThread = Executors.newSingleThreadExecutor();
        mBackgroundThread.execute(() -> {

            ApiInterface apiInterface = Api.getClient().create(ApiInterface.class);

            Call<JsonArray> call = apiInterface.getAccount();
           /* Objects.requireNonNull(getActivity()).runOnUiThread(()-> {
                Toast.makeText(getContext(), R.string.api_call, Toast.LENGTH_SHORT).show();
                view.findViewById(R.id.frag_log_next_button).setEnabled(false);
            });*/

            call.enqueue(new Callback<JsonArray>() {
                @Override
                public void onResponse(@NotNull Call<JsonArray> call, @NotNull Response<JsonArray> response) {
                    JsonArray object = response.body();


                    if (response.code() == 200) {
                        for (int i = 0; i < Objects.requireNonNull(object).size(); i++) {

                            JsonObject obj = object.get(i).getAsJsonObject();

                            int _id = obj.get("id").getAsInt();
                            double amount = obj.get("amount").getAsDouble();
                            String account_name  = obj.get("account_name").getAsString();
                            String currency= obj.get("currency").getAsString();

                            allBankAccount.add(new BankAccount(_id,amount,account_name,currency));
                        }

                    } else {
                        Log.d("application","ca a chié");
                    }
                }

                @Override
                public void onFailure(@NotNull Call<JsonArray> call, @NotNull Throwable t) {
                    call.cancel();
                }
            });
        });
    }



    public void logging(){

    }
}
