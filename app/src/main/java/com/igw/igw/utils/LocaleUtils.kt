package com.igw.igw.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.nfc.Tag
import android.os.Build
import android.os.LocaleList
import com.google.gson.Gson
import com.igw.igw.activity.BaseActivity
import retrofit2.http.PUT
import java.net.ConnectException
import java.util.*

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe 切换语言辅助
 *
 */
object LocaleUtils {

    public final val TAG = "LocaleUtils"

    public final val LOCALE_CHINESE = Locale.CHINESE;  // 中文

    public final val LOCALE_ENGLISH = Locale.ENGLISH;  // 英文


    // 语言文件名
    private final val LOCALE_SP_NAME = "LOCALE_SP_NAME"

    // 保存locale key
    private final val LOCALE_KEY = "LOCALE_KEY"


    /**
     * 判断语言状态是否是英文
     */
    public fun isLocaleEn(context: Context): Boolean {


        val currentlocale = getUserLocale(context)


        return currentlocale == LOCALE_ENGLISH


    }

    /**
     * 获取用户设置的语言文文字
     */
    public fun getUserLocale(context: Context): Locale? {

        var locale = SPUtils.getInstance(LOCALE_SP_NAME).getString(LOCALE_KEY, "")


        if (locale.equals("")) {
            locale = "cn"
        }

        when (locale) {

            "cn" -> {

                return LOCALE_CHINESE
            }

            else -> {
                return LOCALE_ENGLISH

            }
        }

    }


    /**
     * 获取当前的local
     */
    public fun getCurrentlocale(context: Context): Locale {

        var locale: Locale

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = context.resources.configuration.locales.get(0)

        } else {
            locale = context.resources.configuration.locale
        }


        return locale;

    }


    /**
     * 保存用户设置的locale
     */
    public fun saveUserLocale(context: Context, locale: Locale) {
//        var localeJson = localeToJson(locale)
//
//        SPUtils.getInstance(LOCALE_SP_NAME).put(LOCALE_KEY, localeJson)


        if (locale == LOCALE_CHINESE) {
            SPUtils.getInstance(LOCALE_SP_NAME).put(LOCALE_KEY, "cn", true)
        } else {
            SPUtils.getInstance(LOCALE_SP_NAME).put(LOCALE_KEY, "en", true)

        }

    }


    /**
     * 更新语言
     */
    public fun updateLocale(context: Context, newLocale: Locale?) {

        newLocale?.let {
            if (needUpdateLocale(context, it)) {

                val configuration = context.resources.configuration

//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                configuration.setLocale(it)

//                } else {
//                    configuration.locale = it
//                }

                val displayMetrics = context.resources.displayMetrics

                context.resources.updateConfiguration(configuration, displayMetrics)


            }


        }


    }


    /**
     * 判断是否需要更新
     *
     *
     */
    public fun needUpdateLocale(context: Context, locale: Locale): Boolean {

        return getUserLocale(context) != locale


    }


    /**
     * json 转换成 locale 对象
     */
    private fun jsonToLocale(json: String?): Locale? {

        val gson = Gson()

        return json?.let { gson.fromJson(it, Locale::class.java) }


    }


    /**
     * locale对象转化为json
     */
    public fun localeToJson(locale: Locale): String {

        val gson = Gson()

        return gson.toJson(locale)

    }


    public fun changeLocale(activity: Activity, userLocale: Locale) {

        LocaleUtils.updateLocale(activity, userLocale)

        activity.finish()


        var intent = Intent(activity, activity.javaClass)

        activity.startActivity(intent)
        activity.overridePendingTransition(0, 0);
    }

    public fun changeLocale(activity: Activity) {

        if (LocaleUtils.isLocaleEn(activity)) {
            LogUtils.d(TAG, "切换成中文")
            saveUserLocale(activity, LOCALE_CHINESE)

            LocaleUtils.updateLocale(activity, LocaleUtils.LOCALE_CHINESE)

        } else {
            LogUtils.d(TAG, "切换成英语")
            saveUserLocale(activity, LOCALE_ENGLISH)

            LocaleUtils.updateLocale(activity, LocaleUtils.LOCALE_ENGLISH)


        }



        activity.finish()


        var intent = Intent(activity, activity::class.java)

        activity.startActivity(intent)
        activity.overridePendingTransition(0, 0);

//        reconfirmAct()

    }


    public fun changeLocale(activity: Activity, tag: String, value: Any) {

        if (LocaleUtils.isLocaleEn(activity)) {
            LogUtils.d(TAG, "当前语言为英语")
            saveUserLocale(activity, LOCALE_CHINESE)
            LocaleUtils.updateLocale(activity, LocaleUtils.LOCALE_CHINESE)
        } else {

            LogUtils.d(TAG, "当前语言为中文")
            saveUserLocale(activity, LOCALE_ENGLISH)
            LocaleUtils.updateLocale(activity, LocaleUtils.LOCALE_ENGLISH)
        }



        activity.finish()


        var intent = Intent(activity, activity.javaClass)


        if (value is Int) {

            intent.putExtra(tag, value)

        }

        if (value is String) {

            intent.putExtra(tag, value)

        }

        activity.startActivity(intent)
        activity.overridePendingTransition(0, 0);

//        reconfirmAct()

    }


//
//    private fun loadRes(context: Context): Context {
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//
//            val resources = context.resources
//
//            val userLocale = LocaleUtils.getUserLocale(context)
//
//            val configuration = resources.configuration
//
//            if (userLocale == null) {
//                return context
//            }
//            configuration.setLocale(userLocale)
//            configuration.setLocales(LocaleList(userLocale))
//            return context.createConfigurationContext(configuration)
//
//
//        } else (
//                return context
//                )
//    }

    fun attachBaseContext(context: Context, userLocale: Locale?): Context {
//
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            val resources = context.resources
            val configuration = resources.configuration
            if (null == userLocale) {
                return context
            } else {

                configuration.setLocale(userLocale)
                configuration.setLocales(LocaleList(userLocale))
                return context.createConfigurationContext(configuration)

            }

        } else {
            return context
        }


    }


}