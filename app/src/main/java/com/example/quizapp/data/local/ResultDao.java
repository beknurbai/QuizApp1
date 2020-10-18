package com.example.quizapp.data.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.quizapp.models.HistoryResModel;

import java.util.List;

@Dao
public interface ResultDao {
    @Insert
    void insert(HistoryResModel resModel);

    @Query("SELECT*FROM historyresmodel WHERE id=:id")
    HistoryResModel getById(int id);

    @Delete
    void delete(HistoryResModel resModel);

    @Query("SELECT * FROM historyresmodel")
    List<HistoryResModel> getAll();

    @Query("DELETE FROM historyresmodel")
    void deleteAll();
}
