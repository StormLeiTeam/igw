package com.igw.igw.mvp.presenter;

import android.content.Context;
import com.igw.igw.mvp.view.ITestView;


public class TestPresenter extends BasePresenter<ITestView> {


    public TestPresenter(Context context, ITestView view) {
        super(context, view);
    }

    @Override
    protected void requestData() {

    }

    public  void getTestResult(){
        mView.requestData();
    }
}
