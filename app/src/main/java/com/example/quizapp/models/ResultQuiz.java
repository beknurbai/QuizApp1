package com.example.quizapp.models;

import androidx.room.TypeConverters;

import com.example.quizapp.data.local.converters.QuestionConverter;

import java.io.Serializable;

public class ResultQuiz implements Serializable {
    private boolean isWin;
    private String category;
    private String difficulty;
    private String correctAns;
    private String resultPercentage = "start";
    @TypeConverters({QuestionConverter.class})
    private Answers answers;

    public ResultQuiz(boolean isWin, String category, String difficulty, String correctAns, String resultPercentage, Answers answers) {
        this.isWin = isWin;
        this.category = category;
        this.difficulty = difficulty;
        this.correctAns = correctAns;
        this.resultPercentage = resultPercentage;
        this.answers = answers;
    }

    public Answers getAnswers() {
        return answers;
    }

    public boolean isWin() {
        return isWin;
    }

    public String getCategory() {
        return category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getCorrectAns() {
        return correctAns;
    }

    public String getResultPercentage() {
        return resultPercentage;
    }
}