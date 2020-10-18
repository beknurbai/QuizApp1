package com.example.quizapp.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.quizapp.models.HistoryResModel;

@Database(entities = {HistoryResModel.class}, version = 1)
public abstract class HistoryResDataBase extends RoomDatabase {
    public abstract ResultDao resultDao();
}
