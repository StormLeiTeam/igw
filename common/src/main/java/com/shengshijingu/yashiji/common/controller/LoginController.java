package com.shengshijingu.yashiji.common.controller;

import com.shengshijingu.yashiji.common.net.NetApi;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import io.reactivex.Observer;
import io.reactivex.internal.operators.observable.ObservableError;

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


}
