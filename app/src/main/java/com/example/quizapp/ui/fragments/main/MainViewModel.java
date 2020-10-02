package com.example.quizapp.ui.fragments.main;

import android.util.Log;
import android.widget.SeekBar;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.data.network.QuizApiCallBack;
import com.example.quizapp.data.network.Repository;
import com.example.quizapp.models.TriviaCategories;

public class MainViewModel extends ViewModel implements QuizApiCallBack.Categories {
    MutableLiveData<Integer> progressBarSuccess = new MutableLiveData<>();
    MutableLiveData<TriviaCategories> triviaCategories = new MutableLiveData<>();

    public void updateCategory() {
        Repository.getCategory(this);
    }


    @Override
    public void onSuccess(TriviaCategories categories) {
        triviaCategories.setValue(categories);
    }

    @Override
    public void onFailure(Throwable throwable) {
        Log.e("ololo", "onFailure: ", throwable);
    }
}