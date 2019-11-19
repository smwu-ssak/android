package com.example.seed.Network;

import android.app.Application;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// Customized by SY

public class ApplicationController extends Application {

    private String baseUrl = "http://52.79.193.54:3000/";
    private static ApplicationController instance;
    private static ApplicationController getInstance() { return instance; }
    private NetworkService networkService;

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationController.instance = this;
        buildNetworkService();
    }

    public NetworkService getNetworkService() { return networkService; }

    public void buildNetworkService() {
        synchronized (ApplicationController.class) {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create();
            GsonConverterFactory factory = GsonConverterFactory.create(gson);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        networkService = retrofit.create(NetworkService.class);
    }
}
