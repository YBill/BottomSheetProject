package com.bill.bottomsheetproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<MyBean> list = new ArrayList<>();

    public MyAdapter(Context context) {
        this.context = context;
        for (int i = 0; i < 20; i++) {
            MyBean bean = new MyBean("Hello World!" + (i + 1));
            list.add(bean);
        }

        list.add(new MyBean(1));
    }

    @Override
    public int getItemViewType(int position) {
        if (position == list.size() - 1)
            return 0;
        else
            return 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                View refreshView = LayoutInflater.from(context).inflate(R.layout.adapter_refresh, parent, false);
                return new RefreshViewHolder(refreshView);
            default:
                View view = LayoutInflater.from(context).inflate(R.layout.adapter_item, parent, false);
                return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((HolderInterface) holder).update(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class RefreshViewHolder extends RecyclerView.ViewHolder implements HolderInterface {

        private View refreshView;
        private TextView textView;

        public RefreshViewHolder(@NonNull View itemView) {
            super(itemView);
            refreshView = itemView.findViewById(R.id.progress_bar);
            textView = itemView.findViewById(R.id.tv_refresh);
        }

        @Override
        public void update(int position) {
            int loadMore = list.get(position).loadMore;
            if (loadMore == 2) {
                refreshView.setVisibility(View.GONE);
                textView.setText("已加载全部");
            } else if (loadMore == 3) {
                refreshView.setVisibility(View.GONE);
                textView.setText("加载失败");

                if (commentRefreshListener != null) {
                    commentRefreshListener.loadMore();
                }
            } else {
                refreshView.setVisibility(View.VISIBLE);
                textView.setText("正在加载");

                if (commentRefreshListener != null) {
                    commentRefreshListener.loadMore();
                }
            }

        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements HolderInterface {

        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.textview);
        }

        @Override
        public void update(int position) {
            textView.setText(list.get(position).content);
        }

    }

    public void loadComplete(List<MyBean> list) {
        if (list.size() > 0) {
            int size = this.list.size();

            this.list.remove(size - 1);
            this.list.addAll(list);
            list.add(new MyBean(1));

            this.notifyItemRangeChanged(size, list.size() + 1);
        }
    }

    public void loadAllData() {
        int position = list.size() - 1;
        if (position >= 0) {
            MyBean bean = list.get(list.size() - 1);
            bean.loadMore = 2;
            this.notifyItemChanged(position);
        }
    }

    public void loadError() {
        int position = list.size() - 1;
        if (position >= 0) {
            MyBean bean = list.get(list.size() - 1);
            bean.loadMore = 3;
            this.notifyItemChanged(position);
        }
    }

    private CommentRefreshListener commentRefreshListener;

    public void setRefreshListener(CommentRefreshListener commentRefreshListener) {
        this.commentRefreshListener = commentRefreshListener;
    }

    public interface CommentRefreshListener {
        void loadMore();
    }

    public interface HolderInterface {
        void update(int position);
    }

}