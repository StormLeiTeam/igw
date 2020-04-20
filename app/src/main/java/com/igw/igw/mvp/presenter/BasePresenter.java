package com.igw.igw.mvp.presenter;

import android.content.Context;

import com.igw.igw.mvp.model.IBaseModel;
import com.igw.igw.mvp.view.IBaseView;

import java.io.PushbackInputStream;
import java.lang.ref.WeakReference;


public abstract class BasePresenter<V extends IBaseView, M extends IBaseModel> implements IBasePresenter {


    //Presenter持有的View
    protected V mRootView;
    //上下文
    protected Context mContext;

    //构造方法让Presenter层持有View层的引用

    protected M mModel;


    private WeakReference<V> mRootViewRef;


    public BasePresenter(M mModel) {
        this.mModel = mModel;
    }


    public void attachView(V rootView) {

        mRootViewRef = new WeakReference<V>(rootView);

       this.mRootView = getRootView();
    }


    /**
     * view 是否建立连接
     *
     * @return
     */
    public boolean isViewAttach() {


        if (mRootViewRef != null) {

            return mRootViewRef.get() != null;

        } else {
            return false;
        }


    }


    public V getRootView() {
        if (isViewAttach()) {
            if (mRootViewRef != null) {
                return mRootViewRef.get();
            }
        }
        return null;


    }

    public void detachView() {


        if (mRootViewRef != null) {
            mRootViewRef.clear();
            mRootViewRef = null;

            mModel = null;
        }

    }

    /**
     * 请求数据
     */
    protected abstract void requestData();

}