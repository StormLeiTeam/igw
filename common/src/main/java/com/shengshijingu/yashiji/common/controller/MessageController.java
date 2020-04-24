package com.shengshijingu.yashiji.common.controller;


import com.shengshijingu.yashiji.common.net.NetApi;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;

/**
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 * @Describe
 */
public class MessageController extends Controller {


    public static final String TAG = "MessageController";


    /**
     * 消息中心列表
     * @param observer
     */
    public void  messageCenterlist(Observer observer){

        Map<String, Object> params = new HashMap<>();
        params.put("", "");

        ApiSubscribe(NetApi.getApiService().messageCenterList(getRequestBody(params)), observer);

    }


}
