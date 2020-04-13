package com.shengshijingu.yashiji.common.net;


import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
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

}
