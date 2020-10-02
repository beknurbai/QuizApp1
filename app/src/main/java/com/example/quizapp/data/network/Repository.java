package com.example.quizapp.data.network;

import com.example.quizapp.App;

public class Repository {
    static public void getCategory(QuizApiCallBack.Categories callBack){
        App.getInstance().getService().getCategories(callBack);
    }

    static public void getQuestions(int amount, QuizApiCallBack.Questions callBack){
        App.getInstance().getService().getQuestionNoCategoryAndDifficulty(amount, callBack);
    }

    static public void getQuestionsWithDifficultyAndCategory(int amount, int category, String difficulty, QuizApiCallBack.Questions callBack){
        App.getInstance().getService().getQuestionsWithDifficultyAndCategory(amount, category, difficulty, callBack);
    }

    static public void getQuestionsWithDifficulty(int amount, String difficulty,QuizApiCallBack.Questions callBack){
        App.getInstance().getService().getQuestionsWithDifficulty(amount, difficulty, callBack);
    }

    static public void getQuestionsWithCategory(int amount, int category,QuizApiCallBack.Questions callBack){
        App.getInstance().getService().getQuestionsWithCategory(amount, category, callBack);
    }
}
