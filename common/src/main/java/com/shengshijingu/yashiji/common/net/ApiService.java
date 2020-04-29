package com.shengshijingu.yashiji.common.net;


import java.util.NavigableMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.internal.operators.observable.ObservableRange;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

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
     *
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
     *
     * @param requestBody
     * @return
     */
    @POST("/api/user/register")
    Observable<ResponseBody> registerUser(@Body RequestBody requestBody);


    /**
     * 正常账号登陆
     *
     * @param requestBody
     * @return
     */
    @POST("/api/user/login")
    Observable<ResponseBody> loginByAccent(@Body RequestBody requestBody);


    /**
     * @param requestBody
     * @return
     */
    @POST("/api/user/loginByEmail")
    Observable<ResponseBody> loginByEmail(@Body RequestBody requestBody);


    /**
     * 发送验证码
     *
     * @param requestBody
     * @return
     */
    @POST("/api/common/sendEmailVerifyCode")
    Observable<ResponseBody> sendEmailVerifyCode(@Body RequestBody requestBody);


    /**
     * 重置密码
     *
     * @param requestBody
     * @return
     */
    @POST("/api/user/resetPassword")
    Observable<ResponseBody> resetPassword(@Body RequestBody requestBody);


    /**
     * 修改密码
     *
     * @param requestBody
     * @return
     */
    @POST("/api/user/updatePassword")
    Observable<ResponseBody> updatePassword(@Body RequestBody requestBody);


    /**
     * 更新用户信息
     *
     * @param requestBody
     * @return
     */
    @POST("/api/user/updateUser")
    Observable<ResponseBody> updateUserInfo(@Body RequestBody requestBody);


    /**
     * 获取帮助列表
     *
     * @param requestBody
     * @return
     */
    @POST("/api/help/helpList")
    Observable<ResponseBody> getHelpList(@Body RequestBody requestBody);


    /**
     * 提交反馈
     *
     * @param requestBody
     * @return
     */
    @POST("/api/feedback/saveFeedback")
    Observable<ResponseBody> saveFeedback(@Body RequestBody requestBody);

    /**
     * 帮助详情
     *
     * @param requestBody
     * @return
     */
    @POST("/api/help/helpDetail")
    Observable<ResponseBody> helpDetail(@Body RequestBody requestBody);


    /**
     * 消息中心
     *
     * @param requestBody
     * @return
     */
    @POST("/api/message/list")
    Observable<ResponseBody> messageCenterList(@Body RequestBody requestBody);


    /**
     * 消息处理
     *
     * @param requestBody
     * @return
     */
    @POST("/api/message/dealMessage")
    Observable<ResponseBody> dealMessage(@Body RequestBody requestBody);


    /**
     * 上传图片
     *
     * @param
     * @return
     */
    @Multipart
    @POST("/api/common/uploadImage")
    Observable<ResponseBody> upLoadImage(@Part MultipartBody.Part file);



    /**
     * 上传图片
     *
     * @param
     * @return
     */
    @POST("/api/user/info")
    Observable<ResponseBody> userInfo(@Body RequestBody requestBody);



}


