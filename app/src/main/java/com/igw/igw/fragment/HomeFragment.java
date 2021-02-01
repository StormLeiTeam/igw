package com.igw.igw.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.igw.igw.MainActivity;
import com.igw.igw.R;
import com.igw.igw.bean.VersionBean;
import com.igw.igw.bean.message.MessageCenterBean;
import com.igw.igw.fragment.my.presenter.MyPresenter;
import com.igw.igw.httpclient.DownLoadUtils;
import com.igw.igw.modoule.home.HomeContract;
import com.igw.igw.modoule.home.model.HomeModel;
import com.igw.igw.modoule.home.presenter.HomePresenter;
import com.igw.igw.utils.LogUtils;
import com.igw.igw.widget.storm.BadgeView;
import com.itingchunyu.badgeview.BadgeTextView;
import com.shengshijingu.yashiji.common.Constants;
import com.shengshijingu.yashiji.common.base.BaseDataFragment;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 创建时间  2020/3/105:42 PM .
 * <p>
 * 作者  雷雷
 */

public class HomeFragment extends BaseMvpDataFragment<HomePresenter> implements HomeContract.View {


    public static final String TAG = "HomeFragment";

    private RelativeLayout ll_home_citystation;
    private ImageView iv_home_msg;

    private RelativeLayout rv_city_chat;
    private TextView tv_language_select;

    private BadgeView mBadgeView;
    private BadgeTextView mBadgeTextView;

    public static HomeFragment getInstance() {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
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
        return R.layout.home_fragment;
    }

    @Override
    public void initView() {
        onFirstLoadSuccess();
        ll_home_citystation = bindView(R.id.rv_cityexhibition);
        ll_home_citystation.setOnClickListener(this);


        rv_city_chat = bindView(R.id.tv_city_chat);
        rv_city_chat.setOnClickListener(this);


        iv_home_msg = bindView(R.id.iv_home_msg);

//        mBadgeView = new BadgeView(mContext, iv_home_msg);
//        mBadgeView.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
//        mBadgeView.setBadgeMargin(0, 0);
//        mBadgeView.toggle();
//        mBadgeView.show();

        mBadgeTextView = new BadgeTextView(mContext);
        mBadgeTextView.setTargetView(iv_home_msg);
        mBadgeTextView.setBadgeCount(0);
        mBadgeTextView.setBadgeShown(true);
    }

    @Override
    protected void initViews() {
        super.initViews();

        iv_home_msg = bindView(R.id.iv_home_msg);

        tv_language_select = bindView(R.id.tv_language_select);

        iv_home_msg.setOnClickListener(v -> {

            MainActivity mainActivity = (MainActivity) getActivity();

            mainActivity.showPagerDependButton(R.id.iv_home_msg);

        });

        getMPresenter().messageCenterList();
        checkVersion();
        setUpListener();
    }

    private void checkVersion() {
        getMPresenter().updateVersion();
    }


    private void setUpListener() {


        tv_language_select.setOnClickListener(v -> {
            ((MainActivity) mContext).changeLanuage(R.id.ll_main_home);

//            LocaleUtils.INSTANCE.changeLocale((MainActivity(mContext)));

        });
    }

    @Override
    public void onClick(View view) {

        super.onClick(view);

        MainActivity mainActivity = (MainActivity) getActivity();

        if (clickTime()) {
            switch (view.getId()) {
                case R.id.rv_cityexhibition: // 城市驿站2

                    mainActivity.showPagerDependButton(R.id.ll_main_order);
                    break;
                case R.id.iv_home_msg:


                    mainActivity.showPagerDependButton(R.id.iv_home_msg);
                    break;

                case R.id.tv_city_chat:

                    // 跳转
                    mainActivity.showPagerDependButton(R.id.ll_main_message);
                    break;
            }
        }
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (!hidden) {
            LogUtils.d(TAG, "当前fragment 显示的时候 ");

            getMPresenter().messageCenterList();
        }
    }

    @Override
    protected void initPresenter() {
        setMPresenter(new HomePresenter(new HomeModel()));
        getMPresenter().attachView(this);

    }

    @Override
    public void onResume() {
        super.onResume();

        LogUtils.d(TAG, "刷新未读消息数据 ---- ");
        getMPresenter().messageCenterList();
    }

    @Override
    public void onSuccess(@NotNull List<? extends MessageCenterBean.DataBean.RowsBean> mdatas) {


        int count = 0;
        for (MessageCenterBean.DataBean.RowsBean mdata : mdatas) {

            if (mdata.getIsRead() == 0) {

                count += 1;
            }
        }

        if (count == 0) {
            mBadgeTextView.setBadgeShown(false);
        } else {
            mBadgeTextView.setBadgeShown(true);
        }
    }

    @Override
    public void onFail(int code, @NotNull String msg) {

    }

    @Override
    public void success(Object o) {

    }

    @Override
    public void fail(Object o) {

    }


    @Override
    public void versionSuccessful(@NotNull VersionBean.DataBean data) {

        VersionBean.DataBean.ANDROIDBean android = data.getANDROID();

        updateVersionIntent(android);
    }

    private void updateVersionIntent(VersionBean.DataBean.ANDROIDBean android) {
        boolean isForcedUpdate = android.isIsForcedUpdate();
        if (isForcedUpdate) {

            // 强制
            new AlertDialog.Builder(mContext)
                    .setTitle("软件更新")
                    .setMessage("新版本软件更新")

                    .setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            String apiurl = "https://down.qq.com/qqweb/QQ_1/android_apk/Android_6.0.3.6604_537064871.apk";


                            DownLoadUtils downLoadUtils = new DownLoadUtils(mContext, Constants.BASE_URL + android.getAppUrl(), DownLoadUtils.getApkName(android.getAppUrl()));
//                            DownLoadUtils downLoadUtils = new DownLoadUtils(mContext, apiurl, DownLoadUtils.getApkName(apiurl));


                        }
                    })
                    .setNegativeButton("不更新", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ((MainActivity) mContext).finish();

                        }
                    })
                    .setCancelable(false)
                    .create().show();

        } else {
            // 非强制

            new AlertDialog.Builder(mContext)
                    .setTitle("软件更新")
                    .setMessage("新版本软件更新")
                    .setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            String apiurl = "https://down.qq.com/qqweb/QQ_1/android_apk/Android_6.0.3.6604_537064871.apk";


                            DownLoadUtils downLoadUtils = new DownLoadUtils(mContext, Constants.BASE_URL + android.getAppUrl(), DownLoadUtils.getApkName(android.getAppUrl()));
//                            DownLoadUtils downLoadUtils = new DownLoadUtils(mContext, apiurl, DownLoadUtils.getApkName(apiurl));


                        }
                    })
                    .setNegativeButton("暂不更新", null)
                    .create()
                    .show();


        }
    }

    @Override
    public void versionFail(int code, @NotNull String msg) {

    }
}