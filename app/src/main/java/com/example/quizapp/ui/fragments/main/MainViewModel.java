package com.example.quizapp.ui.fragments.main;

import android.widget.SeekBar;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    MutableLiveData<Integer> mResult = new MutableLiveData<>();

    public void plus() {
        if (mResult.getValue() == null) {
            mResult.setValue(0);
        }
        if (mResult.getValue() <= 49) {
            mResult.setValue(mResult.getValue() + 1);
        }
    }

    public void minus() {
        if (mResult.getValue() == null) {
            mResult.setValue(0);
        }
        if (mResult.getValue() >= 1) {
            mResult.setValue(mResult.getValue() - 1);
        }

    }


}