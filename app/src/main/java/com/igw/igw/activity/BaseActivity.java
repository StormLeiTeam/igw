package com.igw.igw.activity;


import android.content.Context;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.igw.igw.R;
import com.igw.igw.app.IGWApplication;
import com.igw.igw.mvp.presenter.BasePresenter;
import com.igw.igw.utils.ColorUtils;
import com.igw.igw.utils.statusbarutils.StatusBarUtil;
import com.shengshijingu.yashiji.common.dialog.LoadingDialog;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements OnClickListener {

    protected P mPresenter;

    private IGWApplication application;

    public TextView tv_basetitle, tv_base_right;

    public ImageView iv_base_back;

    private BaseActivity context;

    public ImageView iv_base_background;

    public RelativeLayout rl_base_background;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutId());
        if (application == null) {
            // 得到Application对象
            application = (IGWApplication) getApplication();
        }
        context = this;// 把当前的上下文对象赋值给BaseActivity
        addActivity();// 调用添加方法
        initPresenter();
        rl_base_background = findViewById(R.id.rl_base_background);
        iv_base_background = findViewById(R.id.iv_base_background);
        tv_basetitle = findViewById(R.id.tv_basetitle);
        tv_base_right = findViewById(R.id.tv_base_right);
        iv_base_back = findViewById(R.id.iv_base_back);
        initView();
        if (!TextUtils.isEmpty(setRightButton())) {
            tv_base_right.setText(setRightButton());
            tv_base_right.setVisibility(View.VISIBLE);
        }
        if (tv_basetitle != null || iv_base_back != null) {
            tv_basetitle.setText(setTitle());
            iv_base_back.setOnClickListener(this);
        }
        initStatusBar();
//        Intent intent = getIntent();
//        Uri uri = intent.getData();
//
//        if (uri != null) {
//            String data = uri.toString();
//            if (data.contains("?")) {
//                int index = data.indexOf("?");
//                String params = data.substring(index + 1, data.length());
//                String ps[] = params.split("&");
//                mParams.clear();
//
//                for (int i = 0, j = ps.length; i < j; i++) {
//                    String str = ps[i];
//                    if (str.contains("=")) {
//                        String[] strings = str.split("=");
//                        if (strings.length == 2) {
//                            mParams.put(strings[0], strings[1]);
//                        }
//                    }
//                }
//            }
//            Log.e("12345",uri.toString());
//            if(!TextUtils.isEmpty(mParams.get("liveId"))){
//                ControllerUtils.getOrderControllerInstance().H5LiveInfo(mParams.get("liveId"), new NetObserver<LiveInfoData>(LiveInfoData.class) {
//                    @Override
//                    protected void onSuccess(LiveInfoData liveInfoBean) {
//                        Intent liveIntent = new Intent(context, LiveActivity.class);
//                        LiveListBean listBean = new LiveListBean();
//                        listBean.setRounds(Long.parseLong(liveInfoBean.getRoundsX()));
//                        listBean.setId(Long.parseLong(liveInfoBean.getLiveId()));
//                        listBean.setUserId(Long.parseLong(liveInfoBean.getUserIdX()));
//                        listBean.setPullUrl(liveInfoBean.getPullUrlX());
//                        listBean.setBackgroundImgUrl(liveInfoBean.getPicurl());
//                        listBean.setStatus(1);
//                        listBean.setLiveRoomName(liveInfoBean.getContent());
//                        liveIntent.putExtra("bean", listBean);
//                        liveIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(liveIntent);
//
//
//                    }
//
//                    @Override
//                    protected void onFail(String code, String msg) {
//
//                    }
//
//                    @Override
//                    protected void onError(String msg) {
//
//                    }
//
//                });
//            }


//        }
    }


    // 添加Activity方法
    public void addActivity() {
        application.addActivity_(context);// 调用myApplication的添加Activity方法
    }

    //销毁当个Activity方法
    public void removeActivity() {
        application.removeActivity_(context);// 调用myApplication的销毁单个Activity方法
    }

    //销毁所有Activity方法
    public void removeALLActivity() {
        application.removeALLActivity_();// 调用myApplication的销毁所有Activity方法
    }

    public void initStatusBar() {
        //  当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        //  不要忘记了, 在当前activity onCreate中设置 取消padding,  因为这个padding 我们用代码实现了,不需要系统帮我
        //  设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        //  一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //  所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true) && setStatusBarColor()) {
            //  如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //  这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(this, 0x33000000);
            if (tv_basetitle != null) {
                tv_basetitle.setTextColor(ColorUtils.getTextColor(this, R.color.color333));
                tv_base_right.setTextColor(ColorUtils.getTextColor(this, R.color.color333));
            }
            if (iv_base_back != null) {
                iv_base_back.setImageResource(R.mipmap.icon_back_black);
            }
            if (rl_base_background != null) {
                rl_base_background.setBackground(ColorUtils.getDrawable(this, R.color.white));
            }
            if (iv_base_background != null) {
                iv_base_background.setBackground(ColorUtils.getDrawable(this, R.color.white));
            }

        }
        // 如果支持设置深色文字的，需要再次进行判断状态栏背景是否为用户自定义
        if (StatusBarUtil.setStatusBarDarkTheme(this, true) && setStatusBarColor()) {
//            StatusBarUtil.setStatusBarDarkTheme(this, false);

            if (tv_basetitle != null) {
                tv_basetitle.setTextColor(ColorUtils.getTextColor(this, R.color.color333));
                tv_base_right.setTextColor(ColorUtils.getTextColor(this, R.color.color333));
            }
            if (iv_base_back != null) {
                iv_base_back.setImageResource(R.mipmap.icon_back_black);
            }
            if (iv_base_background != null) {
                iv_base_background.setBackground(ColorUtils.getDrawable(this, R.color.white));
            }
            if (rl_base_background != null) {
                rl_base_background.setBackground(ColorUtils.getDrawable(this, R.color.white));
            }

        }

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_base_back:
                finish();
                break;
        }

    }

    public boolean clickTime() {
        if (System.currentTimeMillis() - lastClickTime < FAST_CLICK_DELAY_TIME) {
            return false;
        }

        lastClickTime = System.currentTimeMillis();
        return true;
    }

    //全局定义
    private long lastClickTime = 0L;

    // 两次点击间隔不能少于1000ms
    private static final int FAST_CLICK_DELAY_TIME = 1000;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeActivity();
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    private LoadingDialog loadingDialog;

    public void showLoadingText(String string) {
        if (TextUtils.isEmpty(string)) {
            return;
        }
        LoadingDialog.Builder builder = new LoadingDialog.Builder(this)
                .setMessage(string)
                .setCancelable(false);
        loadingDialog = builder.create();
        loadingDialog.show();
    }

    public void showLoadingText() {
        showLoadingText("加载中...");
    }

    public void hideLoadingText() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }


    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 初始化presenter
     */
    protected abstract void initPresenter();

    /**
     * 布局
     */
    protected abstract int getLayoutId();

    /**
     * title
     */
    protected abstract String setTitle();


    /**
     * title右侧按钮
     */
    protected abstract String setRightButton();

    /**
     * 状态栏颜色设置
     */
    protected abstract boolean setStatusBarColor();

}