package com.shengshijingu.yashiji.common.controller;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shengshijingu.yashiji.common.Constants;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by leilei on 2019/3/11.
 */

public class Controller {

    public static String app_version;

//    private Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    private Gson gson = new Gson();


    void ApiSubscribe(Observable observable, Observer observer) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    Map<String, Object> getBaseParams() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("app_version", app_version);
        params.put("userId", Constants.userId);
        params.put("channel", Constants.chanleName);
        params.put("app_type", "Android");
        return params;
    }


    RequestBody getRequestBody(Map<String, Object> queryMap) {
        return RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), getRequestStr(queryMap));
    }

    private String getRequestStr(Map<String, Object> queryMap) {
        return gson.toJson(queryMap);
    }

    Map<String, Object> getSignParams(Map<String, Object> queryMap) {
//        Object[] keys = queryMap.keySet().toArray();
//        Arrays.sort(keys);
//
//        StringBuilder sb = new StringBuilder();
//        for (Object key : keys) {
//            Object value = queryMap.get(key);
//            if (value != null && TextUtils.isEmpty(value.toString())) {
//                sb.append(key).append(value.toString());
//            }
//        }
//        sb.append(Constants.SECRET_VAL);

//        queryMap.put("sign", CipherUtil.toHex(CipherUtil.toMD5(sb.toString())));

        StringBuilder sbParams = new StringBuilder("?");

//        for (Object key : keys) {
//            Object value = queryMap.get(key);
//                sbParams.append(key).append("=").append(value.toString()).append("&");
//        }
//        Log.d("okhttp",sbParams.toString());

        return queryMap;
    }

}
