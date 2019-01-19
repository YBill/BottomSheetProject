package com.bill.bottomsheetproject;

public class MyBean {

    public String content;

    public MyBean(String content) {
        this.content = content;
        this.loadMore = 0;
    }

    public MyBean(int loadMore) {
        this.loadMore = loadMore;
    }

    public int loadMore; // 1:loading 2:load all data 3:load error
}
