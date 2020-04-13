package com.igw.igw.mvp.presenter;

import android.content.Context;
import com.igw.igw.mvp.view.IBaseView;


public abstract class BasePresenter<V extends IBaseView> {

    //Presenter持有的View
    protected V mView;
    //上下文
    protected Context mContext;

    //构造方法让Presenter层持有View层的引用
    public BasePresenter(Context context, V view){
        this.mContext = context;
        this.mView = view;
    }

    /**
     * 请求数据
     */
    protected abstract void requestData();

}