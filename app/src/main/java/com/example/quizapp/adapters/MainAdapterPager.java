package com.example.quizapp.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.quizapp.ui.fragments.history.HistoryFragment;
import com.example.quizapp.ui.fragments.main.MainFragment;
import com.example.quizapp.ui.fragments.settings.SettingsFragment;

public class MainAdapterPager extends FragmentPagerAdapter {
    public MainAdapterPager(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new Fragment();
        switch (position) {
            case 0:
                fragment = MainFragment.newInstance();
                break;
            case 1:
                fragment = HistoryFragment.newInstance();
                break;
            case 2:
                fragment = SettingsFragment.newInstance();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
