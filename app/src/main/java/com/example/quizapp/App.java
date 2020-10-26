package com.example.quizapp;

import android.app.Application;

import androidx.room.Room;

import com.example.quizapp.data.local.HistoryResDataBase;
import com.example.quizapp.data.network.RetrofitBuilder;
import com.example.quizapp.utilits.Preferences;

public class App extends Application {
    private static App instance;
    private RetrofitBuilder service;
    public HistoryResDataBase dataBase;


    private Preferences preferences;

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
        preferences = new Preferences(instance);
    }

    public static App getInstance() {
        return instance;
    }

    public RetrofitBuilder getService() {
        return service;
    }

    public Preferences getPreferences() {
        return preferences;
    }


}
