package com.example.quizapp;

import android.app.Application;

import com.example.quizapp.data.network.RetrofitBuilder;

public class App extends Application {
    private static App instance;
    private RetrofitBuilder service;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        service = new RetrofitBuilder();
    }

    public static App getInstance() {
        return instance;
    }

    public RetrofitBuilder getService() {
        return service;
    }
}
