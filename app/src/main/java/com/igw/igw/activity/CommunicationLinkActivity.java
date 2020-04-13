package com.igw.igw.activity;

import android.support.v4.app.FragmentTransaction;
import com.igw.igw.R;
import com.igw.igw.fragment.CommunicationLinkFragment;

public class CommunicationLinkActivity extends BaseActivity {


    @Override
    protected void initView() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        CommunicationLinkFragment communicationLinkFragment = CommunicationLinkFragment.getInstance();
        fragmentTransaction.add(R.id.fl_baselayout, communicationLinkFragment);
        fragmentTransaction.commitAllowingStateLoss();
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
