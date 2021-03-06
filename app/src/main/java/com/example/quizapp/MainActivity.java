package com.example.quizapp;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.quizapp.adapters.MainAdapterPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ViewPager mainPager;
    private MainAdapterPager adapterPager;
    private BottomNavigationView mBtnNavView;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(App.getInstance().getPreferences().getTheme(R.style.AppTheme));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();//method for initialization
        createBtnNavViewWithViewPager();
        getSupportActionBar().hide();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.setTheme(App.getInstance().getPreferences().getTheme());
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
        mainPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (getSupportActionBar() != null) {
                    switch (position) {
                        case 0:
                            getSupportActionBar().setTitle("Quiz");
                            break;
                        case 1:
                            getSupportActionBar().setTitle("History");
                            break;
                        case 2:
                            getSupportActionBar().setTitle("Settings");
                            break;
                    }

                }
            }
        });
    }


}







