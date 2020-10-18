package com.example.quizapp.ui.fragments.history;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.App;
import com.example.quizapp.models.HistoryResModel;

import java.util.List;

public class HistoryViewModel extends ViewModel {

    MutableLiveData<List<HistoryResModel>> mutableLiveData = new MutableLiveData<>();

    public void upData() {
        mutableLiveData.setValue(App.getInstance().dataBase.resultDao().getAll());
    }

}