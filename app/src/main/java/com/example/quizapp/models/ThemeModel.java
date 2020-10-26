package com.example.quizapp.models;

public class ThemeModel {
   boolean checkChange;
   int defImage;

    public ThemeModel(boolean checkChange, int defImage) {
        this.checkChange = checkChange;
        this.defImage = defImage;
    }

    public boolean isCheckChange() {
        return checkChange;
    }

    public void setCheckChange(boolean checkChange) {
        this.checkChange = checkChange;
    }

    public int getDefImage() {
        return defImage;
    }

    public void setDefImage(int defImage) {
        this.defImage = defImage;
    }
}
