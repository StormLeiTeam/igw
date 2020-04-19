package com.shengshijingu.yashiji.common.controller;

import android.os.strictmode.NonSdkApiUsedViolation;

import com.shengshijingu.yashiji.common.net.NetApi;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import io.reactivex.Observer;

/**
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 * @Describe 登陆相关
 */
public class LoginController extends Controller {


    public static final String TAG = "LoginController";


//    /**
//     * 城市列表
//     */
//    public void cityList(Observer observer) {
//        Map<String, Object> params = getBaseParams();
//        ApiSubscribe(NetApi.getApiService().cityList(getRequestBody(params)), observer);
//    }

    /**
     * 获取国家信息
     */
    public void getNationality(Observer observer) {

        Map<String, Object> params = new HashMap<>();
        params.put("", "");


        ApiSubscribe(NetApi.getApiService().getNationality(getRequestBody(params)), observer);

    }


    /**
     * 根据 国籍id 获取城市列表
     *
     * @param countryId 国籍id
     * @param observer
     */
    public void getCityListData(int countryId, Observer observer) {

        Map<String, Object> params = new HashMap<>();

        params.put("countryId", countryId);

        ApiSubscribe(NetApi.getApiService().getCityListData(getRequestBody(params)), observer);
    }


    public void registerUser(int countryId, int cityId, String lastName, String firstName,
                             int sex, String birthday, String nickname, String agencyName, String description,
                             String email, String mobilePhone, String password, String inviteCode, String headImage,
                             Observer observer) {


        Map<String,Object> params = new HashMap<>();
        params.put("countryId",countryId);
        params.put("cityId",cityId);
        params.put("lastName",lastName);
        params.put("firstName",firstName);
        params.put("sex",sex);
        params.put("birthday",birthday);
        params.put("nickname",nickname);
        params.put("agencyName",agencyName);
        params.put("description",description);
        params.put("email",email);
        params.put("mobilePhone",mobilePhone);
        params.put("password",password);
        params.put("inviteCode",inviteCode);
        params.put("headImage",headImage);

        ApiSubscribe(NetApi.getApiService().registerUser(getRequestBody(params)),observer);



    }


}
