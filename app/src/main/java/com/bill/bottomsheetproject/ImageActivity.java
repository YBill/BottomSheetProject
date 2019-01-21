package com.bill.bottomsheetproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ImageActivity extends AppCompatActivity {

    MyBottomSheetBehavior behavior;
    View scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        scrollView = findViewById(R.id.item_detail_container);
        View bottomSheet = findViewById(R.id.bottom_sheet);
        behavior = MyBottomSheetBehavior.from(bottomSheet);
    }

    public void handleClick(View view) {
        /*if (behavior.getState() == MyBottomSheetBehavior.STATE_EXPANDED) {
            behavior.setState(MyBottomSheetBehavior.STATE_COLLAPSED);
        } else {
            behavior.setState(MyBottomSheetBehavior.STATE_EXPANDED);
        }*/
    }
}
