package com.example.quizapp.utilits;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

public class CustomViewPager extends ViewPager {
    private boolean isPageScrollDisabled = false;
    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return this.isPageScrollDisabled && super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return this.isPageScrollDisabled && super.onInterceptTouchEvent(ev);
    }

    public void setPageScrollDisabled(boolean isPageScrollEnabled) {
        this.isPageScrollDisabled = isPageScrollEnabled;
    }
}
