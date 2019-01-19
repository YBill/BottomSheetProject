package com.bill.bottomsheetproject;

import android.support.v7.app.AppCompatActivity;

public class SlideManager2 {

    private BottomSheetFragment bottomSheetFragment;
    private AppCompatActivity appCompatActivity;

    public SlideManager2(AppCompatActivity activity) {
        appCompatActivity = activity;
        bottomSheetFragment = new BottomSheetFragment();
    }

    public void show() {
        bottomSheetFragment.show(appCompatActivity.getSupportFragmentManager(), "dialog");
    }

}
