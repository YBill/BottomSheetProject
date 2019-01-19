package com.bill.bottomsheetproject;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.util.ArrayList;
import java.util.List;

public class SlideManager {

    private BottomSheetDialog bottomSheetDialog;
    private Handler handler = new Handler();
    private int flag = 0;

    public SlideManager(final Activity activity) {
        bottomSheetDialog = new BottomSheetDialog(activity, R.style.TransBottomSheetDialog);
        bottomSheetDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
//        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

        View view = LayoutInflater.from(activity).inflate(R.layout.layout_sheet_dialog, null, false);
        final RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        final MyAdapter myAdapter = new MyAdapter(activity);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.addItemDecoration(new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(myAdapter);

        View sayView = view.findViewById(R.id.tv_say);
        sayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSoftBoard(activity);
            }
        });

        myAdapter.setRefreshListener(new MyAdapter.CommentRefreshListener() {
            @Override
            public void loadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        flag++;
                        if (flag == 1) {
                            myAdapter.loadComplete(produceData());
                        } else if (flag == 2) {
                            myAdapter.loadError();
                        } else if (flag == 3) {
                            myAdapter.loadAllData();
                        }
                    }
                }, 2000);

            }
        });

        bottomSheetDialog.setContentView(view);
    }

    private List<MyBean> produceData() {
        List<MyBean> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            MyBean bean = new MyBean("new Data " + (i + 1));
            list.add(bean);
        }

        return list;
    }

    private void showSoftBoard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public void show() {
        bottomSheetDialog.show();
    }

}
