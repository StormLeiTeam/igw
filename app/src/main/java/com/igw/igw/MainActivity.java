package com.igw.igw;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.igw.igw.activity.BaseActivity;
import com.igw.igw.fragment.CityFragment;
import com.igw.igw.fragment.HomeFragment;
import com.igw.igw.fragment.MessageFragment;
import com.igw.igw.fragment.MyFragment;
import com.igw.igw.modoule.im.view.ChatTypeFragment;
import com.igw.igw.modoule.login.loginstate.LoginManager;
import com.igw.igw.modoule.login.view.LoginActivity;
import com.igw.igw.modoule.login.view.WebActivity;
import com.igw.igw.modoule.splash.view.SplashActivity;
import com.igw.igw.utils.LocaleUtils;
import com.igw.igw.utils.LogUtils;
import com.igw.igw.utils.SPUtils;
import com.igw.igw.utils.SharedUtils;

import com.shengshijingu.yashiji.common.Constants;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

public class MainActivity extends BaseActivity {

    public static final String TAG = "MainActivity";

    private FragmentManager fragmentManager;

    private HomeFragment homeFragment;

    private MessageFragment messageFragment;

    private MyFragment myFragment;

    private CityFragment cityFragment;

    private ChatTypeFragment chatTypeFragment;

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

        Log.e("12345", SharedUtils.getRongToken());
        RongIM.connect(SharedUtils.getRongToken(), new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {

            }

            @Override
            public void onSuccess(String userid) {
                Log.d("12345", "--onSuccess" + userid);
                RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {

                    /**
                     * 获取设置用户信息. 通过返回的 userId 来封装生产用户信息.
                     * @param userId 用户 ID
                     */
                    @Override
                    public UserInfo getUserInfo(String userId) {
                        UserInfo userInfo = new UserInfo(userId, SharedUtils.getUserName(), Uri.parse(Constants.BASE_URL + SharedUtils.getHeadImg()));
                        return userInfo;
                    }

                }, true);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.d("12345", "--onSuccess" + errorCode);
            }
        });





        boolean localeEn = LocaleUtils.INSTANCE.isLocaleEn(this);

        if (localeEn) {
            LogUtils.d(TAG, "语言状态为英文 ");
        } else {
            LogUtils.d(TAG, "语言状态为中文");
        }


        String splashLink = getIntent().getStringExtra("splash_link");

        if (null != splashLink) {

            WebActivity.Companion.startSelf(this,splashLink);

        }

        int message_friend_add = getIntent().getIntExtra("message_friend_add", 0);
        if (message_friend_add == 1) {
            LogUtils.d(TAG, "获取推送信息 ");
            showPagerDependButton(R.id.iv_home_msg);
        }


        updateLanuageOfIntent();


//        switch (modelId) {
//            case  R.id.ll_main_my:
//                showPagerDependButton(R.id.ll_main_my);
//
//                break;
//        }
    }

    private void updateLanuageOfIntent() {

        int modelId = getIntent().getIntExtra("change_language", -1);

        if (modelId != -1) {
            showPagerDependButton(modelId);
        }


    }

    public void showPagerDependButton(int checkedId) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideFragment(fragmentTransaction);

        boolean login = LoginManager.Companion.getInstance().isLogin();

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
            case R.id.iv_home_msg:


                if (!login) {

                    Intent intent = new Intent(this, LoginActivity.class);

                    startActivity(intent);

                    return;
                }

                if (null == messageFragment) {
                    messageFragment = MessageFragment.getInstance();
                    fragmentTransaction.add(R.id.fl_main, messageFragment);
                } else {
                    fragmentTransaction.show(messageFragment);
                }
                position = 4;
                break;
            case R.id.ll_main_my:


                if (!login) {
//            val intent = Intent(this@SplashActivity, LoginActivity::class.java)
//            this@SplashActivity.startActivity(intent)
//            finish()
                    Intent intent = new Intent(this, LoginActivity.class);

                    startActivity(intent);

                    return;
                }


                if (null == myFragment) {
                    myFragment = MyFragment.getInstance();
                    fragmentTransaction.add(R.id.fl_main, myFragment);
                } else {
                    fragmentTransaction.show(myFragment);
                }
                position = 3;
                break;

            case R.id.ll_main_message:


                if (!login) {
                    Intent intent = new Intent(this, LoginActivity.class);

                    startActivity(intent);

                    return;
                }

                if (null == chatTypeFragment) {
                    chatTypeFragment = ChatTypeFragment.Companion.getInstance();
                    fragmentTransaction.add(R.id.fl_main, chatTypeFragment);
                } else {
                    fragmentTransaction.show(chatTypeFragment);

                }
                position = 2;
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

        if (null != chatTypeFragment) {
            fragmentTransaction.hide(chatTypeFragment);
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


    public void changeLanuage(int modelId) {

        LocaleUtils.INSTANCE.changeLocale(this, "change_language", modelId);
    }
}
