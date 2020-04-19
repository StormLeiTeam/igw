package com.shengshijingu.yashiji.common.net;


import java.util.NavigableMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    /**
     * 城市列表
     */
    @POST("/api/city/list")
    Observable<ResponseBody> cityList(@Body RequestBody requestBody);

    /**
     * 城市信息
     */
    @POST("/api/station/detail")
    Observable<ResponseBody> cityDetail(@Body RequestBody requestBody);

    /**
     * 交流信息
     */
    @POST("/api/contact/addContact")
    Observable<ResponseBody> addContact(@Body RequestBody requestBody);


    /**
     * 获取国家
     * @return
     */
    @POST("/api/common/countryList")
    Observable<ResponseBody> getNationality(@Body RequestBody requestBody);


    /*
     * 根据 国籍获取城市列表
     */
    @POST("api/common/cityList")
    Observable<ResponseBody> getCityListData(@Body RequestBody requestBody);


    /**
     * 用户注册
     * @param requestBody
     * @return
     */
    @POST("/api/user/register")
    Observable<ResponseBody> registerUser(@Body RequestBody requestBody);
}
