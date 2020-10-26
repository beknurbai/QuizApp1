package com.example.quizapp.ui.fragments.settings;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.App;
import com.example.quizapp.R;
import com.example.quizapp.models.ThemeModel;

import java.util.ArrayList;
import java.util.List;

public class SettingsViewModel extends ViewModel {
    MutableLiveData<List<ThemeModel>> mutableLiveData = new MutableLiveData<>();
    MutableLiveData<Integer> show = new MutableLiveData<>();


    public void deleteAllHistory() {
        App.getInstance().dataBase.resultDao().deleteAll();
    }

    public SettingsViewModel() {
        List<ThemeModel> list = new ArrayList<>();
        list.add(new ThemeModel(false, R.drawable.def_theme));
        list.add(new ThemeModel(false, R.drawable.dark_theme));
        list.add(new ThemeModel(false, R.drawable.space_theme));
        list.add(new ThemeModel(false, R.drawable.orange_theme));
        list.add(new ThemeModel(false, R.drawable.blue_theme));
        mutableLiveData.setValue(list);
    }

    public void onChangeTheme(int pos) {
        switch (pos) {
            case 0:
                if (App.getInstance().getPreferences().getTheme() != R.style.AppTheme) {
                    App.getInstance().getPreferences().setTheme(R.style.AppTheme);
                    show.setValue(R.style.AppTheme);
                }
                break;
            case 1:
                if (App.getInstance().getPreferences().getTheme() != R.style.AppDarkTheme) {
                    App.getInstance().getPreferences().setTheme(R.style.AppDarkTheme);
                    show.setValue(R.style.AppDarkTheme);
                }
                break;
            case 2:
                if (App.getInstance().getPreferences().getTheme() != R.style.AppSpaceTheme) {
                    App.getInstance().getPreferences().setTheme(R.style.AppSpaceTheme);
                    show.setValue(R.style.AppSpaceTheme);
                }
                break;
            case 3:
                if (App.getInstance().getPreferences().getTheme() != R.style.AppOrangeTheme) {
                    App.getInstance().getPreferences().setTheme(R.style.AppOrangeTheme);
                    show.setValue(R.style.AppOrangeTheme);
                }
                break;
            case 4:
                if (App.getInstance().getPreferences().getTheme() != R.style.AppBlueTheme) {
                    App.getInstance().getPreferences().setTheme(R.style.AppBlueTheme);
                    show.setValue(R.style.AppBlueTheme);
                }
                break;
        }
    }
}