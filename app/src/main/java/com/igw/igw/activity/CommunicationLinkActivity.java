package com.igw.igw.activity;

import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;

import com.igw.igw.R;
import com.igw.igw.fragment.CommunicationLinkFragment;
import com.igw.igw.utils.LocaleUtils;
import com.igw.igw.utils.LogUtils;

public class CommunicationLinkActivity extends BaseActivity {



    public static final String   TAG = "CommunicationLinkActivity";
    private TextView tv_base_right;


    @Override
    protected void initView() {

        tv_base_right = findViewById(R.id.tv_base_right);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        CommunicationLinkFragment communicationLinkFragment = CommunicationLinkFragment.getInstance();
        fragmentTransaction.add(R.id.fl_baselayout, communicationLinkFragment);
        fragmentTransaction.commitAllowingStateLoss();

        setUpListener();


    }

    private void setUpListener() {
        tv_base_right.setOnClickListener(v -> {

//            LogUtils.d(TAG, "点击了 切换状态 ");

            LocaleUtils.INSTANCE.changeLocale(this);

        });

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.base_layout;
    }

    @Override
    protected String setTitle() {
        return "交流联系";
    }

    @Override
    protected String setRightButton() {
        return "中/en";
    }

    @Override
    protected boolean setStatusBarColor() {
        return true;
    }
}
