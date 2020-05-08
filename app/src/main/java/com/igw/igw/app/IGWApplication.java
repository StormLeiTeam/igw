package com.igw.igw.app;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.LoggingMXBean;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.igw.igw.BuildConfig;
import com.igw.igw.httpclient.HttpClientManager;
import com.igw.igw.modoule.login.loginstate.LoginManager;
import com.igw.igw.utils.ConfigUtil;
import com.igw.igw.utils.LogUtils;
import com.shengshijingu.yashiji.common.Constants;
import io.rong.imkit.RongIM;

/**
 * 创建时间  2020/3/105:34 PM .
 * <p>
 * 作者  雷雷
 */

public class IGWApplication extends Application {

    private static IGWApplication context;

    private List<Activity> activityList = new ArrayList<>();

    public static final int ENVIRONMENT = Constants.ENVIRONMENT_DEBUG;

    public static LogUtils.Config logConfig;

    @Override
    public void onCreate() {
        super.onCreate();
        initConfig();
        context = this;
        initLogConfig();
        LoginManager.Companion.getInstance().init();
        LoginManager.Companion.getInstance().initLoginState();
        RongIM.init(this, "p5tvi9dspqek4");

    }

    private void initLogConfig() {

        logConfig = LogUtils.getConfig()
                .setLogSwitch(true)
                .setConsoleSwitch(BuildConfig.DEBUG)
                .setGlobalTag("ZZZ")
                .setLog2FileSwitch(false)
                .setSingleTagSwitch(true)
                .setLogHeadSwitch(true)
                .setBorderSwitch(true)
                .setConsoleFilter(LogUtils.V)
                .setFileFilter(LogUtils.V);


    }

    /**
     * 添加Activity
     */
    public void addActivity_(Activity activity) {
        // 判断当前集合中不存在该Activity
        if (!activityList.contains(activity)) {
            activityList.add(activity);//把当前Activity添加到集合中
        }
    }

    public Activity getTopActivity() {
        return activityList.isEmpty() ? null : activityList.get(activityList.size() - 1);
    }


    /**
     * 销毁单个Activity
     */
    public void removeActivity_(Activity activity) {
        //判断当前集合中存在该Activity
        if (activityList.contains(activity)) {
            activityList.remove(activity);//从集合中移除
            activity.finish();//销毁当前Activity
        }
    }

    /**
     * 销毁所有的Activity
     */
    public void removeALLActivity_() {
        //通过循环，把集合中的所有Activity销毁
        for (Activity activity : activityList) {
            activity.finish();
        }
    }

    /**
     * 初始化  域名配置等信息
     */
    private void initConfig() {
        ConfigUtil config = ConfigUtil.getInstance();
        Constants.context = context;
        Constants.QQ_APP_ID = config.getProperty("QQ_APP_ID");
        Constants.WX_APP_ID = config.getProperty("WX_APP_ID");
        Constants.WB_APP_KEY = config.getProperty("WB_APP_KEY");
        Constants.IM_APP_ID = config.getProperty("IM_APP_ID");
        Constants.XM_PUSH_APPID = config.getProperty("XM_PUSH_APPID");
        Constants.XM_PUSH_APPKEY = config.getProperty("XM_PUSH_APPKEY");
        Constants.WX_APP_SECRET = config.getProperty("WX_APP_SECRET");
        switch (ENVIRONMENT) {
            case Constants.ENVIRONMENT_DEBUG:
                Constants.BASE_URL = config.getProperty("BASE_URL_DEBUG");
                Constants.HTML_BASE_URL = config.getProperty("HTML_BASE_URL_DEBUG");
                HttpClientManager.Companion.initBaseUrl(config.getProperty("BASE_URL_DEBUG"));
                break;
            case Constants.ENVIRONMENT_RELEASE:
                Constants.BASE_URL = config.getProperty("BASE_URL_RELEASE");
                Constants.HTML_BASE_URL = config.getProperty("HTML_BASE_URL_RELEASE");
                break;
        }
    }

    public static Context getContext() {

        return context;
    }

}
