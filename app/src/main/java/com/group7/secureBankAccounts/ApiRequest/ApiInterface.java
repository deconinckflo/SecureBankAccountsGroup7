package com.group7.secureBankAccounts.ApiRequest;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;



import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface ApiInterface {

    static final String BASE_URL = "https://6007f1a4309f8b0017ee5022.mockapi.io/api/m1/";


    @GET("config/{id}")
    Call<JsonObject> login(@Path("id") String id);

    @GET("accounts")
    Call<JsonArray> getAccount();
}
