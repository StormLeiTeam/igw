package com.shengshijingu.yashiji.common.controller;

import com.shengshijingu.yashiji.common.net.NetApi;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;

/**
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 * @Describe IM 即时通讯
 */
public class IMController extends Controller {

    public static final String TAG = "IMController";


    /**
     * 获取我的好友
     *
     */
    public void getFriends(Observer observer){

        Map<String, Object> params = new HashMap<>();
        params.put("", "");

        ApiSubscribe(NetApi.getApiService().getFriends(getRequestBody(params)),observer);

    }
}
