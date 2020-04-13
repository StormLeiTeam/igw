package com.shengshijingu.yashiji.common.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

/**
 * Created by ym
 * 2018/5/4.18:40
 */

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> {

    protected LayoutInflater layoutInflater;

    protected List<T> dataList;

    protected int layoutId;

    protected MultiTypeSupport<T> multiTypeSupport;

    protected Context context;

    public CommonAdapter(Context context, List<T> dataList, int layoutId) {
        try {
            this.layoutInflater = LayoutInflater.from(context);
        } catch (NullPointerException e) {

        }
        this.dataList = dataList;
        this.layoutId = layoutId;
        this.context = context;
    }


    @Override
    public int getItemViewType(int position) {
        if (multiTypeSupport != null) {
            return multiTypeSupport.getLayoutId(dataList.get(position), position);
        }
        return super.getItemViewType(position);
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (multiTypeSupport != null) {
            layoutId = viewType;
        }
        View itemView = layoutInflater.inflate(layoutId, parent, false);
        return new CommonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        bindData(holder, dataList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    protected abstract void bindData(CommonViewHolder holder, T data, int position);

    public boolean clickTime() {
        if (System.currentTimeMillis() - lastClickTime < FAST_CLICK_DELAY_TIME) {
            return false;
        }

        lastClickTime = System.currentTimeMillis();
        return true;
    }

    //全局定义
    private long lastClickTime = 0L;

    // 两次点击间隔不能少于1000ms
    private static final int FAST_CLICK_DELAY_TIME = 300;

}
