package com.igw.igw.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.igw.igw.app.IGWApplication;


/**
 * Created by leilei on 2017/2/9.
 */
public class SharedUtils {

    public static SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(IGWApplication.getContext());
    }


    public static void setNavigate(boolean first) {
        getSharedPreferences().edit().putBoolean("navigate", first).apply();
    }

    public static boolean getNavigate() {
        return getSharedPreferences().getBoolean("navigate", true);
    }

    public static void setIsRegiest(int isRegiest) {
        getSharedPreferences().edit().putInt("isRegiest", isRegiest).apply();
    }

    public static Integer getIsRegiest() {
        return getSharedPreferences().getInt("isRegiest", 0);
    }

    public static boolean getLoginStatus() {
        return getSharedPreferences().getBoolean("isLogin", false);
    }

    public static void setFirstLoad(boolean first) {
        getSharedPreferences().edit().putBoolean("firstLoad", first).apply();
    }

    public static boolean isFirstLoad() {
        return getSharedPreferences().getBoolean("firstLoad", false);
    }

    public static String getWXOpenid() {
        return getSharedPreferences().getString("openid", "");
    }

    public static void setWXOpenid(String openid) {
        getSharedPreferences().edit().putString("openid", openid).apply();
    }

    public static String getHeadImg() {
        return getSharedPreferences().getString("headimg", "");
    }

    public static void setHeadImg(String headImg) {
        getSharedPreferences().edit().putString("headimg", headImg).apply();
    }


    public static void setUserName(String userName) {
        getSharedPreferences().edit().putString("userName", userName).apply();
    }

    public static String getUserName() {
        return getSharedPreferences().getString("userName", "");
    }

    public static String getAccessToken() {
        return getSharedPreferences().getString("accessToken", "");
    }

    public static void setAccessToken(String accessToken) {
        getSharedPreferences().edit().putString("accessToken", accessToken).apply();
    }

    public static String getId() {
        return getSharedPreferences().getString("id", "");
    }

    public static void setId(String id) {
        getSharedPreferences().edit().putString("id", id).apply();
    }

    public static String getPhone() {
        return getSharedPreferences().getString("phone", "");
    }

    public static void setPhone(String phone) {
        getSharedPreferences().edit().putString("phone", phone).apply();
    }

    public static String getType() {
        return getSharedPreferences().getString("type", "");
    }

    public static void setType(String phone) {
        getSharedPreferences().edit().putString("type", phone).apply();
    }

    public static String getAuthorization() {
        return getSharedPreferences().getString("authorization", "");
    }

    public static void setAuthorization(String authorization) {
        getSharedPreferences().edit().putString("authorization", authorization).apply();
    }

    public static String getCustomerPhone() {
        return getSharedPreferences().getString("customerPhone", "");
    }

    public static void setCustomerPhone(String phone) {
        getSharedPreferences().edit().putString("customerPhone", phone).apply();
    }

    public static String getUserSign() {
        return getSharedPreferences().getString("userSign", "");
    }

    public static void setUserSign(String phone) {
        getSharedPreferences().edit().putString("userSign", phone).apply();
    }

    public static void setLivePhone(String liveKey, String livePhone) {
        getSharedPreferences().edit().putString(liveKey, livePhone).apply();
    }

    public static void setFirstLogin(String firstLogin) {
        getSharedPreferences().edit().putString("firstLogin", firstLogin).apply();
    }

    public static String getFirstLogin() {
        return getSharedPreferences().getString("firstLogin", "");
    }


    public static String getLivePhone(String liveKey) {
        return getSharedPreferences().getString(liveKey, "");
    }


    public static void setLoginStatus(boolean b) {
        getSharedPreferences().edit().putBoolean("isLogin", b).apply();
    }

    public static void setAddress(String address) {
        getSharedPreferences().edit().putString("address", address).apply();
    }

    public static String getAddress() {
        return getSharedPreferences().getString("address", "");
    }

    public static void logOut() {
        getSharedPreferences().edit()
                .putString("accessToken", "")
                .putBoolean("isLogin", false)
                .putInt("isRegiest", 0)
                .putString("phone", "")
                .putString("id", "")
                .putString("openid", "")
                .putInt("sex", -1)
                .putString("userName", "")
                .putString("headimg", "")
                .putString("customerPhone", "")
                .putString("userSign", "")
                .putString("authorization", "")
                .putString("address", "")
                .apply();
    }


    public static int getSex() {
        return getSharedPreferences().getInt("sex", 0);
    }

    public static void setSex(int sex) {
        getSharedPreferences().edit().putInt("sex", sex).apply();
    }

    public static void setLanguage(int language) {
        getSharedPreferences().edit().putInt("language", language).apply();
    }

    public static int getLanguage() {
        return getSharedPreferences().getInt("language", 1);
    }


}
