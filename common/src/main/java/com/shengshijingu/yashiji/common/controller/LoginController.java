package com.shengshijingu.yashiji.common.controller;

import com.shengshijingu.yashiji.common.net.NetApi;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;

/**
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 * @Describe  登陆相关
 */
public class LoginController extends Controller {


    public static final String  TAG = "LoginController";




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
    public void getNationality(Observer observer){

        Map<String,Object> params = new HashMap<>();
        params.put("","");


        ApiSubscribe(NetApi.getApiService().getNationality(getRequestBody(params)),observer);

    }


}
