package com.example.quizapp.ui.activities.question;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.data.network.QuizApiCallBack;
import com.example.quizapp.data.network.Repository;
import com.example.quizapp.models.QuestionModel;
import com.example.quizapp.models.QuizResponse;
import com.example.quizapp.models.ResultQuiz;

import java.util.List;

public class QuestionViewModel extends ViewModel implements QuizApiCallBack.Questions {
    MutableLiveData<List<QuestionModel>> listQuestions = new MutableLiveData<>();
    MutableLiveData<ResultQuiz> result = new MutableLiveData<>();
    private int correctAnswerAmount = 0;
    private int wrongAnswerAmount = 0;

    public void setAmountQuestions(int questionsAmount, int category, String difficulty) {
        if (category == 99 && difficulty.equals("Any type")) {
            Repository.getQuestions(questionsAmount, this);
        } else if (category == 99) {
            Repository.getQuestionsWithDifficulty(questionsAmount, difficulty, this);
        } else if (difficulty.equals("Any type")) {
            Repository.getQuestionsWithCategory(questionsAmount, category, this);
        } else {
            Repository.getQuestionsWithDifficultyAndCategory(questionsAmount, category, difficulty, this);
        }
    }

    public void onAnswerClick(boolean result) {
        if (result) correctAnswerAmount++;
        else wrongAnswerAmount++;
    }

    public void onLastAnswerClick(boolean result, String category, String difficulty) {
        float resPr = (float) (correctAnswerAmount * 100) / (correctAnswerAmount + wrongAnswerAmount);
        if (result) correctAnswerAmount++;
        else wrongAnswerAmount++;
        this.result.setValue(new ResultQuiz(
                correctAnswerAmount > wrongAnswerAmount,
                category,
                difficulty,
                correctAnswerAmount + "/" + (correctAnswerAmount + wrongAnswerAmount),
                resPr + "%"
        ));

    }

    @Override
    public void onSuccess(QuizResponse quizResponse) {
        listQuestions.setValue(quizResponse.getQuestionModels());
    }

    @Override
    public void onFailure(Throwable throwable) {

    }

    @Override
    public void onFailure(Exception e) {

    }

}
