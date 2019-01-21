package com.bill.bottomsheetproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void handleClick1(View view) {
        SlideManager manager = new SlideManager(this);
        manager.show();
    }

    public void handleClick2(View view) {
        SlideManager2 manager = new SlideManager2(this);
        manager.show();
    }

    public void handleClick3(View view) {
        Intent intent = new Intent(this, ImageActivity.class);
        startActivity(intent);
    }
}
