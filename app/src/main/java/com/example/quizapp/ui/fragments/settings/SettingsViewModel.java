package com.example.quizapp.ui.fragments.settings;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.App;
import com.example.quizapp.models.HistoryResModel;

import java.util.List;

public class SettingsViewModel extends ViewModel {
    MutableLiveData<List<HistoryResModel>> mutableLiveData = new MutableLiveData<>();


    public void deleteAllHistory() {
        App.getInstance().dataBase.resultDao().deleteAll();
    }
}