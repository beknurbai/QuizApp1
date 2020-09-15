package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.quizapp.adapters.MainAdapterPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ViewPager mainPager;
    private MainAdapterPager adapterPager;
    private BottomNavigationView mBtnNavView;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();//method for initialization

        createBtnNavViewWithViewPager();
    }


    private void init() {
        adapterPager = new MainAdapterPager(getSupportFragmentManager());
        mainPager = findViewById(R.id.main_view_pager);
        mBtnNavView = findViewById(R.id.main_btn_nav);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void createBtnNavViewWithViewPager() {
        mainPager.setAdapter(adapterPager);
        mainPager.setOffscreenPageLimit(2);//
        mBtnNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_main:
                        mainPager.setCurrentItem(0, false);
                        break;
                    case R.id.nav_history:
                        mainPager.setCurrentItem(1, false);
                        break;
                    case R.id.nav_settings:
                        mainPager.setCurrentItem(2, false);
                        break;
                }
                return true;
            }
        });
        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0.0f);
            getSupportActionBar().setTitle("Quiz");
            getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.background_white));
        }

    }
}