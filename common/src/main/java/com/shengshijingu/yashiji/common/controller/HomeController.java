package com.shengshijingu.yashiji.common.controller;

import java.util.Map;

import com.shengshijingu.yashiji.common.net.NetApi;
import io.reactivex.Observer;

/**
 * Created by leilei on 2019/3/11.
 */

public class HomeController extends Controller {


    /**
     * 城市列表
     */
    public void cityList(Observer observer) {
        Map<String, Object> params = getBaseParams();
        ApiSubscribe(NetApi.getApiService().cityList(getRequestBody(params)), observer);
    }

    /**
     * 城市信息
     */
    public void cityDetail(int cityId, int language, Observer observer) {
        Map<String, Object> params = getBaseParams();
        params.put("cityId", cityId);
        params.put("language", language);
        ApiSubscribe(NetApi.getApiService().cityDetail(getRequestBody(params)), observer);
    }

    /**
     * 添加交流
     */
    public void addContact(String title, String content, String userName, String mobilePhone, String email, String other, Observer observer) {
        Map<String, Object> params = getBaseParams();
        params.put("title", title);
        params.put("content", content);
        params.put("userName", userName);
        params.put("mobilePhone", mobilePhone);
        params.put("email", email);
        params.put("other", other);
        ApiSubscribe(NetApi.getApiService().addContact(getRequestBody(params)), observer);
    }


}
