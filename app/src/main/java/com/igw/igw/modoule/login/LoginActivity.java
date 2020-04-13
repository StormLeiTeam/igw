package com.igw.igw.modoule.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.igw.igw.R;
import com.igw.igw.activity.BaseActivity;

/**
 * 登陆页面模块
 */
public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected String setRightButton() {
        return null;
    }

    @Override
    protected boolean setStatusBarColor() {
        return false;
    }
}
