package com.igw.igw;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.igw.igw.activity.BaseActivity;
import com.igw.igw.fragment.CityFragment;
import com.igw.igw.fragment.HomeFragment;
import com.igw.igw.fragment.MessageFragment;
import com.igw.igw.fragment.MyFragment;
import com.igw.igw.utils.LogUtils;

public class MainActivity extends BaseActivity {


    public static final String TAG = "MainActivity";

    private FragmentManager fragmentManager;

    private HomeFragment homeFragment;

    private MessageFragment messageFragment;

    private MyFragment myFragment;

    private CityFragment cityFragment;

    private LinearLayout ll_main_my, ll_main_order, ll_main_message, ll_main_home;

    private ImageView iv_main_home, iv_main_order, iv_main_message, iv_main_my;

    private int position;

    private int[] array = {1, 4, 2, 151, 15, 6, 11, 61, 919};

    private int temp, temp1, minNumber;

    @Override
    protected void initView() {
        fragmentManager = getSupportFragmentManager();
        initViews();
        showPagerDependButton(R.id.ll_main_home);

        LogUtils.d(TAG, "添加 log 功能");

        for (int i = 0; i < array.length - 1; i++) {
            int flag = 0;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    flag++;
                }


            }
            if (flag == 0) {
                break;
            }

        }
//        for (int i = 0; i < array.length - 1; i++) {
//            int flag = 0;
//            for (int j = 0; j < array.length - 1 - i; j++) {
//                if (array[j] > array[j + 1]) {
//                    temp = array[j];
//                    array[j] = array[j + 1];
//                    array[j + 1] = temp;
//                    flag++;
//
//                }
//
//            }
//            if (flag == 0) {
//                break;
//            }
//
//        }
        //选择排序 第一个元素依次与后面的每一个元素比大小，直到最小的位于第一个上

//        for (int i = 0; i < array.length; i++) {
//            minNumber = i;
//            for (int j = i + 1; j < array.length; j++) {
//                if (array[minNumber] > array[j]) {
//                    temp1 = array[minNumber];
//                    array[minNumber] = array[j];
//                    array[j] = temp1;
//
//                }
//
//            }
//
//        }


        for (int i = 0; i < array.length; i++) {
            Log.e("12345", array[i] + "");
        }


    }

    public void showPagerDependButton(int checkedId) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideFragment(fragmentTransaction);
        switch (checkedId) {
            case R.id.ll_main_home:
                if (null == homeFragment) {
                    homeFragment = HomeFragment.getInstance();
                    fragmentTransaction.add(R.id.fl_main, homeFragment);
                } else {
                    fragmentTransaction.show(homeFragment);
                }
                position = 0;
                break;
            case R.id.ll_main_order:
                if (null == cityFragment) {
                    cityFragment = CityFragment.getInstance();
                    fragmentTransaction.add(R.id.fl_main, cityFragment);
                } else {
                    fragmentTransaction.show(cityFragment);
                }
                position = 1;
                break;
            case R.id.ll_main_message:
                if (null == messageFragment) {
                    messageFragment = MessageFragment.getInstance();
                    fragmentTransaction.add(R.id.fl_main, messageFragment);
                } else {
                    fragmentTransaction.show(messageFragment);
                }
                position = 2;
                break;
            case R.id.ll_main_my:
                if (null == myFragment) {
                    myFragment = MyFragment.getInstance();
                    fragmentTransaction.add(R.id.fl_main, myFragment);
                } else {
                    fragmentTransaction.show(myFragment);
                }
                position = 3;
                break;
        }
        fragmentTransaction.commitAllowingStateLoss();
        onTabData(position);


    }

    @Override
    public void onClick(View view) {
        showPagerDependButton(view.getId());
    }

    private void onTabData(int position) {
        iv_main_my.setImageResource(R.mipmap.tab_my_unselect);
        iv_main_message.setImageResource(R.mipmap.tab_message_unselect);
        iv_main_order.setImageResource(R.mipmap.tab_city_unselect);
        iv_main_home.setImageResource(R.mipmap.tab_home_unselect);
        switch (position) {
            case 0:
                iv_main_home.setImageResource(R.mipmap.tab_home_select);
                break;
            case 1:
                iv_main_order.setImageResource(R.mipmap.tab_city_select);
                break;
            case 2:
                iv_main_message.setImageResource(R.mipmap.tab_message_select);
                break;
            case 3:
                iv_main_my.setImageResource(R.mipmap.tab_my_select);
                break;
        }

    }

    void hideFragment(FragmentTransaction fragmentTransaction) {
        if (null != homeFragment) {
            fragmentTransaction.hide(homeFragment);
        }
        if (null != messageFragment) {
            fragmentTransaction.hide(messageFragment);
        }
        if (null != myFragment) {
            fragmentTransaction.hide(myFragment);
        }
        if (null != cityFragment) {
            fragmentTransaction.hide(cityFragment);
        }

    }


    @Override
    public void onAttachFragment(Fragment fragment) {
        if (homeFragment == null && fragment instanceof HomeFragment) {
            homeFragment = (HomeFragment) fragment;
        }
        if (messageFragment == null && fragment instanceof MessageFragment) {
            messageFragment = (MessageFragment) fragment;
        }
        if (cityFragment == null && fragment instanceof CityFragment) {
            cityFragment = (CityFragment) fragment;
        }
        if (myFragment == null && fragment instanceof MyFragment) {
            myFragment = (MyFragment) fragment;
        }

    }


    private void initViews() {
        iv_main_home = findViewById(R.id.iv_main_home);
        iv_main_message = findViewById(R.id.iv_main_message);
        iv_main_my = findViewById(R.id.iv_main_my);
        iv_main_order = findViewById(R.id.iv_main_order);

        ll_main_home = findViewById(R.id.ll_main_home);
        ll_main_message = findViewById(R.id.ll_main_message);
        ll_main_my = findViewById(R.id.ll_main_my);
        ll_main_order = findViewById(R.id.ll_main_order);
        ll_main_order.setOnClickListener(this);
        ll_main_message.setOnClickListener(this);
        ll_main_home.setOnClickListener(this);
        ll_main_my.setOnClickListener(this);


    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        getSupportFragmentManager().findFragmentByTag(MyFragment.class.getName())
//                myFragment.onActivityResult(requestCode, resultCode, data);
    }
}
