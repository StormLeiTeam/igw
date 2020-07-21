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
     *
     * @param observer
     */
    public void messageCenterlist(Observer observer) {

        Map<String, Object> params = new HashMap<>();
        params.put("", "");

        ApiSubscribe(NetApi.getApiService().messageCenterList(getRequestBody(params)), observer);

    }


    /**
     * 处理消息
     *
     * @param id
     * @param isAgree  1 同意 2 不同意
     * @param observer
     */
    public void dealMessage(int id, int isAgree, Observer observer) {

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("isAgree", isAgree);
        ApiSubscribe(NetApi.getApiService().dealMessage(getRequestBody(params)), observer);
    }


    /**
     * 此接口未完成 需要修改
     *
     * @param id
     * @param observer
     */
    public void readMessage(int id, Observer observer) {


        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        ApiSubscribe(NetApi.getApiService().readMessage(getRequestBody(params)), observer);

    }


    /**
     * 消息全部阅读
     *
     * @param observer
     */
    public void readAll(Observer observer) {
        Map<String, Object> params = new HashMap<>();
        params.put("", "");
        ApiSubscribe(NetApi.getApiService().readAll(getRequestBody(params)), observer);

    }

}
