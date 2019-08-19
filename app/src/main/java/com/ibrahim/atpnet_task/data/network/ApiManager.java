package com.ibrahim.atpnet_task.data.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {



    public static Retrofit retrofitInstance;
    public static Retrofit getInstance(){
        if(retrofitInstance==null){//create

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

           retrofitInstance = new Retrofit.Builder()
                    .baseUrl("http://atpnet.net/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofitInstance;
    }

    public static MetroApi getAPIs(){
        MetroApi services = getInstance().create(MetroApi.class);
        return services;
    }
}




