package com.igw.igw.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.view.ViewConfiguration;

import java.lang.reflect.Method;

import io.rong.imageloader.utils.L;

/**
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 * @Describe 视图相关工具类
 */
public class UIUtils {

    public static final String TAG = "UIUtils";


    /**
     * 获取顶部状态栏高度
     *
     * @return
     */
    public static int getStatusBarHeight(Context context) {

        int result = 0;

        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {

            result = context.getResources().getDimensionPixelSize(resourceId);
        }

        LogUtils.d(TAG, "状态栏高度 --> " + result);
        return result;

    }

    /**
     * 底部导航栏高度
     *
     * @param context
     * @return
     */
    public static int getNavigationHeight(Context context) {
        int result = 0;
        if (hasNacBar(context)) {
            Resources res = context.getResources();

            int resourceId = res.getIdentifier("navigator_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = res.getDimensionPixelSize(resourceId);
            }
        }

        LogUtils.d(TAG, " 虚拟键盘的高度--> " + result);
        return result;
    }

    /**
     * 判断是否存在虚拟按键栏
     *
     * @param context
     * @return
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private static boolean hasNacBar(Context context) {

        Resources res = context.getResources();

        int resourceId = res.getIdentifier("config_showNavigationBar", "bool", "android");

        if (resourceId != 0) {
            boolean hasNav = res.getBoolean(resourceId);
            String sNavBarOverride = getNavBarOverride();
            if ("1".equals(sNavBarOverride)) {
                hasNav = false;
            } else if ("0".equals(sNavBarOverride)) {
                hasNav = true;
            }

            return hasNav;
        } else {
            return !ViewConfiguration.get(context).hasPermanentMenuKey();
        }

    }

    /**
     * 判断虚拟按键是否被重写
     *
     * @return
     */
    private static String getNavBarOverride() {
        String sNavBarOverride = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            try {
                Class c = Class.forName("android.os.SystemProperties");
                Method method = c.getDeclaredMethod("get", String.class);
                method.setAccessible(true);
                sNavBarOverride = (String) method.invoke(null, "qemu.hw.mainkeys");
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }

        return sNavBarOverride;
    }


    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public static int screenWidth() {
        return AppUtils.INSTANCE.getAppContext().getResources().getDisplayMetrics().widthPixels;
    }

    public static int screenHeight() {
        return AppUtils.INSTANCE.getAppContext().getResources().getDisplayMetrics().heightPixels;
    }


    /**
     * dp --> px
     *
     * @param dpValue
     * @return
     */
    public static float dp2px(float dpValue) {
        float scale = AppUtils.INSTANCE.getAppContext().getResources().getDisplayMetrics().density;

        return dpValue * scale + 0.5f;

    }


    /**
     * px --> dp
     *
     * @param pxValue
     * @return
     */
    public static float px2dp(float pxValue) {
        float scale = AppUtils.INSTANCE.getAppContext().getResources().getDisplayMetrics().density;

        return pxValue / scale + 0.5f;
    }


    /**
     * sp --> px
     *
     * @param spValue
     * @return
     */
    public static float sp2px(float spValue) {

        float scale = AppUtils.INSTANCE.getAppContext().getResources().getDisplayMetrics().scaledDensity;

        return spValue * scale + 0.5f;
    }


    /**
     * px  --> sp
     *
     * @param pxValue
     * @return
     */
    public static float px2sp(float pxValue) {
        float scale = AppUtils.INSTANCE.getAppContext().getResources().getDisplayMetrics().scaledDensity;

        return pxValue / scale + 0.5f;
    }

    /**
     * @param color
     * @param v
     * @return
     */
    public static int changeAlpha(int color, float v) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int alpha = (int) (Color.alpha(color) * v);
        return Color.argb(alpha, red, green, blue);
    }
}
