package com.example.quizapp.utilits;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {
    private SharedPreferences preferences;

    public Preferences(Context context) {
        preferences = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);

    }

    public void setTheme(int value) {
        preferences.edit().putInt("theme", value).apply();
    }

    public int getTheme() {
        return preferences.getInt("theme", 0);
    }

    public int getTheme(int def) {
        return preferences.getInt("theme", def);
    }

}

