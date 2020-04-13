package com.shengshijingu.yashiji.common.util;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;

import static com.shengshijingu.yashiji.common.util.ScreenPxdpUtils.PHONE_SIZE.HUGE;
import static com.shengshijingu.yashiji.common.util.ScreenPxdpUtils.PHONE_SIZE.LARGE;
import static com.shengshijingu.yashiji.common.util.ScreenPxdpUtils.PHONE_SIZE.MIDDLE;
import static com.shengshijingu.yashiji.common.util.ScreenPxdpUtils.PHONE_SIZE.SMALL;

/**
 * 创建时间  2019/7/1711:14 AM .
 *
 * 作者  雷雷
 */

public class ScreenPxdpUtils {

    private static PHONE_SIZE phoneSize;

    private static int BASE_SIZE = 0;

    public enum PHONE_SIZE {
        SMALL,
        MIDDLE,
        LARGE,
        HUGE
    }

    public static int screenWidth;

    public static int screenHeight;

    public static float density;

    public static float scaleDensity;

    private ScreenPxdpUtils() {
    }

    public static PHONE_SIZE getPhoneSize() {
        return phoneSize;
    }

    public static int getBaseSize() {
        return BASE_SIZE;
    }

    public static void getInstance(Activity activity) {
        if (activity == null) {
            return;
        }
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindow().getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        density = activity.getResources().getDisplayMetrics().density;
        scaleDensity = activity.getResources().getDisplayMetrics().scaledDensity;

        if (screenHeight < 1300) {
            phoneSize = SMALL;
            BASE_SIZE = 400;
        } else if (screenHeight < 2000) {
            phoneSize = MIDDLE;
            BASE_SIZE = 600;
        } else if (screenHeight < 2600) {
            phoneSize = LARGE;
            BASE_SIZE = 800;
        } else {
            phoneSize = HUGE;
            BASE_SIZE = 1000;
        }
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(float dpValue) {
        return (int) (dpValue * density + 0.5f);
    }

    public static int getMeasureHeight(View view) {
        int width = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        view.measure(width, height);
        return view.getMeasuredHeight();
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue) {
        return (int) (pxValue / density + 0.5f);
    }

    public static int getScreenHeight(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    public static int getScreenWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int spTopx(int sp, float density) {
        int px = (int) (sp * ScreenPxdpUtils.scaleDensity + 0.5f);
        return px;
    }

    public static int getViewHeight(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        int height = view.getMeasuredHeight();
        return height;
    }

    public static int getViewWidth(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        int width = view.getMeasuredWidth();
        return width;
    }


    /**
     * 获取导航栏高度
     */
    public static int getDaoHangHeight(Context context) {
        int result = 0;
        int resourceId = 0;
        int rid = context.getResources().getIdentifier("config_showNavigationBar", "bool", "android");
        if (rid != 0) {
            resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
            return context.getResources().getDimensionPixelSize(resourceId);
        } else {
            return 0;
        }
    }

    public static int getDialogWidth() {
        return (int) (0.85 * screenWidth);
    }

    // 将px值转换为sp值
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static <T> boolean isEmpty(List<T> list) {
        if (list == null || list.size() == 0) {
            return true;
        }
        return false;
    }

    // 将px值转换为dip或dp值
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
