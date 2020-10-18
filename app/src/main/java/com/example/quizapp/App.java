package com.example.quizapp;

import android.app.Application;

import androidx.room.Room;

import com.example.quizapp.data.local.HistoryResDataBase;
import com.example.quizapp.data.network.RetrofitBuilder;

public class App extends Application {
    private static App instance;
    private RetrofitBuilder service;
    public HistoryResDataBase dataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        dataBase = Room.databaseBuilder(this,
                HistoryResDataBase.class, "question.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
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
