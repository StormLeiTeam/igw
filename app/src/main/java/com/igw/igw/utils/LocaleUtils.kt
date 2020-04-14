package com.igw.igw.utils

import android.content.Context
import android.os.Build
import com.google.gson.Gson
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

    public  final val  LOCALE_CHINESE = Locale.CHINESE;  // 中文

    public  final  val LOCALE_ENGLISH = Locale.ENGLISH;  // 英文


    // 语言文件名
    private final  val LOCALE_SP_NAME= "LOCALE_SP_NAME"

    // 保存locale key
    private final val LOCALE_KEY = "LOCALE_KEY"


    /**
     * 获取用户设置的语言文文字
     */
    public fun  getUserLocale(context: Context): Locale{

        var localeJson = SPUtils.getInstance(LOCALE_SP_NAME).getString(LOCALE_KEY, "")


        return jsonToLocale(localeJson);



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
        var localeJson = localeToJson(locale)

        SPUtils.getInstance(LOCALE_SP_NAME).put(LOCALE_KEY, localeJson)

    }


//
//    /**
//     * 更新Locale
//     * @param pContext Context
//     * @param pNewUserLocale New User Locale
//     */
//    public static void updateLocale(Context pContext, Locale pNewUserLocale) {
//        if (needUpdateLocale(pContext, pNewUserLocale)) {
//            Configuration _Configuration = pContext.getResources().getConfiguration();
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//                _Configuration.setLocale(pNewUserLocale);
//            } else {
//                _Configuration.locale =pNewUserLocale;
//            }
//            DisplayMetrics _DisplayMetrics = pContext.getResources().getDisplayMetrics();
//            pContext.getResources().updateConfiguration(_Configuration, _DisplayMetrics);
//            saveUserLocale(pContext, pNewUserLocale);
//        }
//    }
    /**
     * 更新语言
     */
    public fun updateLocale(context: Context, newLocale: Locale?) {

        newLocale?.let {
            if (needUpdateLocale(context, it)) {

                val configuration = context.resources.configuration

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    configuration.setLocale(it)

                }else{
                    configuration.locale = it
                }

                val displayMetrics = context.resources.displayMetrics

                context.resources.updateConfiguration(configuration, displayMetrics)

                saveUserLocale(context,it)

            }


        }


    }


    /**
     * 判断是否需要更新
     *
     *
     */
    public fun needUpdateLocale(context: Context, locale: Locale)  :Boolean {

        return locale != null && getCurrentlocale(context) != locale


    }


    /**
     * json 转换成 locale 对象
     */
    private fun jsonToLocale(json: String?): Locale {

        val gson = Gson()
        return gson.fromJson(json, Locale::class.java)

    }


    /**
     * locale对象转化为json
     */
    public  fun  localeToJson(locale: Locale) : String{

        val gson = Gson()

        return gson.toJson(locale)

    }


}