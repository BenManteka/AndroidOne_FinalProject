package com.hit.androidonefinalproject.api;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofit {

    public static Retrofit provideRetrofitInstance() {
        Gson gson = new Gson();
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl("https://www.freetogame.com/api/")
                .addConverterFactory(GsonConverterFactory.create(gson));

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        return retrofitBuilder
                .client(okHttpClient)
                .build();
    }
}