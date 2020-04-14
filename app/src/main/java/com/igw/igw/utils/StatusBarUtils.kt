package com.igw.igw.utils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.annotation.ColorInt
import android.support.annotation.IntRange
import android.support.annotation.NonNull
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager

import java.util.regex.Pattern

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe 状态栏工具类
 *
 */
object StatusBarUtils {


    public val TAG = "StatusBarUtils kt"

    public val KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code"

    public val DEFAULT_ALPHA = 0


    /**
     * 设置状态栏颜色
     *
     * @param activity 目标 activity
     * @param color    设置的颜色值
     */
    fun setColor(activity: Activity, @ColorInt color: Int) {
        setColor(
                activity,
                color,
                StatusBarUtils.DEFAULT_ALPHA
        )
    }


    private fun setColor(activity: Activity, color: Int, alpha: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.window
                    .clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS) // 清除透明状态栏
            activity.window
                    .addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            activity.window.statusBarColor = cipherColor(color, alpha)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            setTranslucentView(
                    activity.window.decorView as ViewGroup, color, alpha
            )
            setRootView(activity, true)
        }
    }

    /**
     * 设置透明状态栏 预留状态栏高度
     *
     * @param activity
     */
    public fun setTransparentForWindowBar(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = activity.window
            window.statusBarColor = Color.TRANSPARENT
            window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.window
                    .setFlags(
                            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    )
        }
    }


    /**
     * 设置透明状态栏并且取消预留状态栏高度
     * @param activity
     */
    public fun transparentBarOrFitSystemWindow(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = activity.window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = Color.TRANSPARENT
            window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.window
                    .setFlags(
                            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    )
        }
    }


    /**
     * 设置状态栏字体,图标为深色模式,
     *
     */
    open fun setDarkMode(@NonNull activity: Activity) {

        darkMode(activity.window, true)
    }


    /**
     * 设置状态栏字体 图标为亮色模式
     *
     */
    open fun setLightMode(@NonNull activity: Activity) {

        darkMode(activity.window, false)
    }

    /**
     * 切换状态栏的模式
     *   true -> dark  or  false -> light
     */
    private fun darkMode(window: Window, dark: Boolean) {

        if (isFlyme4()) {

            setFlyme4StatusbarDarkMode(window, dark)

        } else if (isMIUI6()) {
            setMIUIStatusbarDarkMode(window, dark)
        }


        darkModeForM(window, dark)

    }


    /**
     * Android 其他常用的 6.0 以上 设置状态栏字体 图标
     *
     * @param window
     * @param dark   true 黑色 or false 白色
     */
    fun darkModeForM(window: Window, dark: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 大于
            var systemUiVisibility = window.decorView.systemUiVisibility
            systemUiVisibility = if (dark) {
                systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            }
            window.decorView.systemUiVisibility = systemUiVisibility
        }
    }


    /**
     * 魅族手机 状态栏字体颜色 以及图标颜色
     *
     * @param window
     * @param dark   true 黑色  or false 白色
     */
    fun setFlyme4StatusbarDarkMode(window: Window, dark: Boolean): Boolean {
        try {
            val lp = window.attributes
            val darkFlag =
                    lp.javaClass.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON")
            darkFlag.isAccessible = true
            val bit = darkFlag.getInt(lp)
            val meizuFlags = lp.javaClass.getDeclaredField("meizuFlags")
            meizuFlags.isAccessible = true
            var value = meizuFlags.getInt(lp)
            val oldFlag = value
            value = if (dark) {
                value or bit
            } else {
                value and bit.inv()
            }
            if (value != oldFlag) {
                meizuFlags.setInt(lp, value)
                return true
            }
        } catch (e: java.lang.Exception) {
            Log.e(
                    StatusBarUtils.TAG,
                    "Statusbar --> dartmode fail "
            )
            e.printStackTrace()
        }
        return false
    }


    /**
     * 设置 小米手机 miui6以上状态栏的darkMode  字体以及icon
     * @param window 目标windwo
     * @param dark  true 字体黑色 false 字体白色
     */
    fun setMIUIStatusbarDarkMode(window: Window, dark: Boolean) {

        val clazz: Class<*> = window.javaClass

        try {

//            int tranceFlag = 0;
//            int darkModeFlag = 0;

            val layoutParams = Class.forName("android.view.MiuiWindowManager\$LayoutParams")
//        Field field = layoutParams . getField ("EXTRA_FLAG_STATUS_BAR_TRANSPARENT");
//        tranceFlag = field.getInt(layoutParams);

            val field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE")

            val darkModeFlag = field.getInt(layoutParams)

            val extraFlagField = clazz.getMethod(
                    "setExtraFlags",
                    Int::class.javaPrimitiveType,
                    Int::class.javaPrimitiveType
            )


            //            //只需要状态栏透明
//            extraFlagField.invoke(window, tranceFlag, tranceFlag);
//            //状态栏透明且黑色字体
//            extraFlagField.invoke(window, tranceFlag | darkModeFlag, tranceFlag | darkModeFlag);
//            //清除黑色字体
//            extraFlagField.invoke(window, 0, darkModeFlag);
            extraFlagField.invoke(window, if (dark) darkModeFlag else 0, darkModeFlag)

        } catch (e: Exception) {

            e.printStackTrace()
        }

    }


    /**
     * 判断系统版本是否在flyme4 以上
     *
     */
    open fun isFlyme4(): Boolean {

        return (Build.FINGERPRINT.contains("Flyme_OS_4") || Build.VERSION.INCREMENTAL.contains("Flyme_OS_4")
                || Pattern.compile("Flyme OS [4][5]", Pattern.CASE_INSENSITIVE)
                .matcher(Build.DISPLAY).find())

    }

    /**
     *  判断当前手机系统版本是否在MIUI6 以上
     *
     */
    open fun isMIUI6(): Boolean {

        return try {

            val clazz = Class.forName("android.os.SystemProperties")
            val method = clazz.getMethod("get", String::class.java)

            var miVersion: String = method.invoke(null, "ro.miui.ui.version.name") as String

            miVersion = miVersion.replace("[vV]".toRegex(), "")
            val version = miVersion.toInt()

            version >= 6

        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }


    /**
     * 创建透明状态栏 view
     *
     * @param viewGroup 目标 ViewGroup
     * @param color     目标颜色
     * @param alpha     透明度
     */
    private fun setTranslucentView(
            viewGroup: ViewGroup,
            @ColorInt color: Int,
            @IntRange(from = 0, to = 255) alpha: Int
    ) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val cipherColor = StatusBarUtils.cipherColor(color, alpha)
            var translucentView =
                    viewGroup.findViewById<View>(android.R.id.custom)
            if (translucentView == null && cipherColor != 0) {
                translucentView = View(viewGroup.context)
                translucentView.id = android.R.id.custom
                val layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        StatusBarUtils.getStatusBarHeight(viewGroup.context)
                )
                viewGroup.addView(translucentView, layoutParams)
            }
            translucentView?.setBackgroundColor(cipherColor)
        }
    }

    /**
     * 设置根布局
     *
     * @param activity        目标activity
     * @param fitSystemWindow 是否预留状态栏的高度
     */
    private fun setRootView(activity: Activity, fitSystemWindow: Boolean) {
        val parent = activity.findViewById<ViewGroup>(android.R.id.content)
        var i = 0
        val count = parent.childCount
        while (i < count) {
            val childView = parent.getChildAt(i)
            if (childView is ViewGroup) {
                childView.setFitsSystemWindows(fitSystemWindow)
                childView.clipToPadding = fitSystemWindow
            }
            i++
        }
    }


    /**
     * 已有的颜色添加透明度
     *
     * @param color
     * @param alpha
     * @return
     */
    fun cipherColor(@ColorInt color: Int, alpha: Int): Int {
        if (alpha == 0) {
            return color
        }
        val a = 1 - alpha / 255f
        var red = color shr 16 and 0xff
        var green = color shr 8 and 0xff
        var blue = color and 0xff
        red = (red * a + 0.5).toInt()
        green = (green * a + 0.5).toInt()
        blue = (blue * a + 0.5).toInt()
        return 0xff shl 24 or (red shl 16) or (green shl 8) or blue
    }

    /**
     * 获取状态栏高度
     *
     * @param context 上下文
     * @return  状态栏高度
     */
    public fun getStatusBarHeight(context: Context): Int {

        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        return context.resources.getDimensionPixelSize(resourceId)
    }
}