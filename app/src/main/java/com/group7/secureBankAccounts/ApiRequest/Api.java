package com.group7.secureBankAccounts.ApiRequest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {


    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ApiInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static Retrofit getClient() {
        return retrofit;
    }

}
