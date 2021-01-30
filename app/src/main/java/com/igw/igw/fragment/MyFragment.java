package com.igw.igw.fragment;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.igw.igw.MainActivity;
import com.igw.igw.R;
import com.igw.igw.bean.FriendBean;
import com.igw.igw.bean.VersionBean;
import com.igw.igw.bean.login.LoginBean;
import com.igw.igw.bean.login.UserInfoBean;
import com.igw.igw.fragment.my.MyContract;
import com.igw.igw.fragment.my.model.MyModel;
import com.igw.igw.fragment.my.presenter.MyPresenter;
import com.igw.igw.modoule.abouthelp.view.AboutActivity;
import com.igw.igw.modoule.abouthelp.view.FeedbackOrHelpActivity;
import com.igw.igw.modoule.im.view.MyFriendActivity;
import com.igw.igw.modoule.login.loginstate.LoginManager;
import com.igw.igw.modoule.login.view.LoginActivity;
import com.igw.igw.modoule.login.view.UpdateActivity;
import com.igw.igw.modoule.login.view.UpdateUserInfoActivity;
import com.igw.igw.utils.GlideUtils;
import com.igw.igw.utils.GsonUtils;
import com.igw.igw.utils.LogUtils;
import com.igw.igw.widget.storm.CommonDialog;
import com.igw.igw.widget.storm.StormCircleImageView;
import com.shengshijingu.yashiji.common.Constants;
import com.shengshijingu.yashiji.common.util.ToastUtil;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.locks.Condition;


/**
 * 创建时间  2020/3/105:42 PM .
 * <p>
 * 作者  雷雷
 */

public class MyFragment extends BaseMvpDataFragment<MyPresenter> implements MyContract.View {

    public static final String TAG = "MyFragment";

    private TextView tv_update_pwd;

    private LinearLayout ll_update_info;

    private LinearLayout ll_help_or_feedback;

    private LinearLayout llLoginOut; // 退出登陆

    private TextView tv_nickName;

    private ImageView iv_headView;

    private TextView tv_desc; // 个人描述

    private TextView tvMoneyDollar; //

    private TextView tvMoneyRmb; //

    private StormCircleImageView iv_head_view; //

    private LinearLayout ll_update_version;

    private LinearLayout ll_my_friend;
    private LinearLayout ll_about;
    private UserInfoBean.DataBean mData; // 用户数据

    private TextView tv_my_friend;
    private TextView tv_change_lanuage;
    private ImageView iv_sex;
    private TextView tv_update_version; // 软件更新


    public static MyFragment getInstance() {
        MyFragment myFragment = new MyFragment();
        return myFragment;
    }

    @Override
    protected void onReloadData(int type) {

    }

    @Override
    protected boolean onClickImageReload() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.my_fragment;
    }

    @Override
    public void initView() {
        onFirstLoadSuccess();

//        tv_update_pwd = mBaseView.findViewById(R.id.tv_update_pwd);


    }

    @Override
    protected void initViews() {
        super.initViews();


        tv_update_pwd = bindView(R.id.tv_update_pwd);
        ll_update_info = bindView(R.id.ll_update_info);
        ll_help_or_feedback = bindView(R.id.ll_help_or_feedback);
        llLoginOut = bindView(R.id.ll_login_out);
        ll_about = bindView(R.id.ll_about);

        tv_nickName = bindView(R.id.tv_my_nickName);
        tv_desc = bindView(R.id.tv_my_desc);
        tvMoneyDollar = bindView(R.id.tv_money_dollar);
        tvMoneyRmb = bindView(R.id.tv_money_rmb);
        iv_head_view = bindView(R.id.iv_head_view);
        ll_update_version = bindView(R.id.ll_update_version);
        ll_my_friend = bindView(R.id.ll_my_friend);
        tv_my_friend = bindView(R.id.tv_my_friend);
        tv_change_lanuage = bindView(R.id.tv_change_lanuage);
        iv_sex = bindView(R.id.iv_sex);
        tv_update_version = bindView(R.id.tv_update_version);


        initData();

        setUpListener();

    }


    @Override
    public void onResume() {
        super.onResume();

        LogUtils.d(TAG,"myframent -- > onresume");

        boolean login = LoginManager.Companion.getInstance().isLogin();
        if(login) {
            mPresenter.userInfo();
            mPresenter.getFriendsList();
        }

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        LogUtils.d(TAG,"myframent -- > onHiddenChanged");

        if (!hidden) {
            mPresenter.userInfo();
            mPresenter.getFriendsList();
        }
    }

    private void initData() {

        // 获取个人信息
        initUserInfoForView();


    }


    private LoginBean.DataBean mUserInfo;

    private void initUserInfoForView() {

        boolean login = LoginManager.Companion.getInstance().isLogin();
        if (login) {

//            onLoad(2);
            mPresenter.userInfo();
            mPresenter.getFriendsList();

        } else {

            // 显示未登陆时候的数据
//            this.mData = data ;
            //昵称
            tv_nickName.setText(R.string.not_login_in);
            tv_desc.setText("");

            tvMoneyRmb.setText("$0.0");
            tvMoneyDollar.setText("¥0.0");

            tv_my_friend.setText(mContext.getResources().getString(R.string.my_friend));

            GlideUtils.INSTANCE.loadImage(mContext, R.drawable.normal_headimage, iv_head_view);

        }


    }


    private void setUpListener() {

        tv_update_version.setOnClickListener(v ->{

            // 软件更新
            updateVersion();
        });

        ll_about.setOnClickListener(v -> {

            AboutActivity.Companion.startSelf(getActivity());
                    });

        tv_change_lanuage.setOnClickListener(v -> {

            LogUtils.d(TAG, "用户点击事件  --切换语言 ");

            ((MainActivity) mContext).changeLanuage(R.id.ll_main_my);
//            .changeLanuage(R.id.ll_main_my);
        });

        tv_nickName.setOnClickListener(v -> {

            boolean login = LoginManager.Companion.getInstance().isLogin();

            if (!login) {
                Intent intent = new Intent(mContext, LoginActivity.class);
                mContext.startActivity(intent);
            }
        });

        ll_my_friend.setOnClickListener(v -> {


            if (!LoginManager.Companion.getInstance().isLogin()) {

                ToastUtil.showCenterToast(mContext, mContext.getResources().getString(R.string.toast_no_login));
                return;
            }

            MyFriendActivity.Companion.startSelf((MainActivity) mContext);

//            if (LoginManager.Companion.getInstance().isLogin()) {
//                MyFriendActivity.Companion.startSelf((MainActivity) mContext);
//            }else{
//                Intent intent = new Intent(mContext, LoginActivity.class);
//                mContext.startActivity(intent);
//            }


        });


        ll_update_version.setOnClickListener(v -> {

            checkAppVersion();
        });
        llLoginOut.setOnClickListener(v -> {


            new CommonDialog.Builder(mContext)
                    .setTitle(mContext.getResources().getString(R.string.title_login_out))
                    .setContent(mContext.getResources().getString(R.string.content_login_out))
                    .setCancel(mContext.getResources().getString(R.string.cancel), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    })
                    .setCommit(mContext.getResources().getString(R.string.commit_login_out), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            loginOut();
                            initUserInfoForView();

                        }
                    })
                    .build().show();


        });

        ll_help_or_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FeedbackOrHelpActivity.Companion.startSelf((MainActivity) mContext);
            }
        });


        ll_update_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!LoginManager.Companion.getInstance().isLogin()) {

                    ToastUtil.showCenterToast(mContext, mContext.getResources().getString(R.string.toast_no_login));
                    return;
                }

                if (mData != null) {
                    String userInfo = GsonUtils.getInstance().toJson(mData);
//                    UpdateUserInfoActivity.Companion.startSelfforResult(, userInfo);

                    Intent intent = new Intent(getActivity(), UpdateUserInfoActivity.class);
                    intent.putExtra("user_info", userInfo);
                    startActivityForResult(intent, 1001);

                }


            }
        });

        tv_update_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!LoginManager.Companion.getInstance().isLogin()) {

                    ToastUtil.showCenterToast(mContext, mContext.getResources().getString(R.string.toast_no_login));
                    return;
                }

                UpdateActivity.Companion.startSelf((MainActivity) mContext);

            }
        });

    }

    //软件更新
    private void updateVersion() {

        mPresenter.updateVersion();


    }


    /**
     * 版本检测
     */
    private void checkAppVersion() {


        mPresenter.updateVersion();

    }

    /**
     * 登出功能
     */
    private void loginOut() {


        if (LoginManager.Companion.getInstance().isLogin()) {

            LoginManager.Companion.getInstance().loginOut();

        }

    }


    protected MyPresenter mPresenter;

    @Override
    protected void initPresenter() {


        setMPresenter(new MyPresenter(new MyModel()));
        mPresenter = getMPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }

    }

    @Override
    public void success(Object o) {


    }

    @Override
    public void fail(Object o) {

    }

    @Override
    public void userInfoSuccessful(@NotNull UserInfoBean.DataBean data) {

        setUpData2View(data);

    }

    private void setUpData2View(UserInfoBean.DataBean data) {
        // 昵

        this.mData = data;
        //昵称
        tv_nickName.setText(mData.getNickName());
        tv_desc.setText(mData.getUserDesc());
        tvMoneyRmb.setText("¥" + mData.getCnBalance() + "");
        tvMoneyDollar.setText("$" + mData.getEnBalance() + "");

        LogUtils.d(TAG, "获取的图片地址 " + mData.getHeadImage());

        GlideUtils.INSTANCE.loadImageNormal(this.mContext, Constants.BASE_URL + mData.getHeadImage(), R.drawable.normal_headimage, iv_head_view);

        iv_sex.setBackgroundResource(data.getSex() == 1 ? R.drawable.man : R.drawable.girl);
    }

    @Override
    public void userInfoFail(int code, @NotNull String msg) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1001 && resultCode == Activity.RESULT_OK) {
            LogUtils.d(TAG, "回信哦");
            getMPresenter().userInfo();


        }
    }

    /**
     * 检测版本数据
     *
     * @param data
     */
    @Override
    public void versionSuccessful(@NotNull VersionBean.DataBean data) {



    }

    // 检测版本数据失败
    @Override
    public void versionFail(int code, @NotNull String msg) {


    }


    @Override
    public void onDestroy() {
        if (null != getMPresenter()) {
            getMPresenter().detachView();

        }
        super.onDestroy();

    }

    @Override
    public void onSuccessFriends(@NotNull FriendBean.DataBean data) {

        tv_my_friend.setText(mContext.getResources().getString(R.string.my_friend) + "(" + data.getFriends().size() + ")");


    }

    @Override
    public void onFailFriends(int code, @NotNull String msg) {

    }
}
