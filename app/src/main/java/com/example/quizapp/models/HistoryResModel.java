package com.example.quizapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.quizapp.data.local.converters.QuestionConverter;

@Entity
public class HistoryResModel {


    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "correctAns")
    private String correctAns;
    @ColumnInfo(name = "category")
    private String category;
    @ColumnInfo(name = "difficulty")
    private String difficulty;
    @TypeConverters({QuestionConverter.class})
    private Answers answers;
    @ColumnInfo(name = "data")
    private String data;


    public HistoryResModel(String correctAns, String category, String difficulty, Answers answers, String data) {
        this.correctAns = correctAns;
        this.category = category;
        this.difficulty = difficulty;
        this.answers = answers;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(String  correctAns) {
        this.correctAns = correctAns;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Answers getQuestion() {
        return answers;
    }

    public void setQuestion(Answers answers) {
        this.answers = answers;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Answers getAnswers() {
        return answers;
    }

    public void setAnswers(Answers answers) {
        this.answers = answers;
    }
}
