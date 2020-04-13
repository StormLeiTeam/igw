package com.shengshijingu.yashiji.common.net;

import java.util.concurrent.TimeUnit;

import com.shengshijingu.yashiji.common.Constants;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WechatApi {

    private static WeChatApiService apiService;

    public static WeChatApiService getApiService() {
        if (apiService == null) {
            synchronized (WechatApi.class) {
                if (apiService == null) {
                    new WechatApi();
                }
            }
        }
        return apiService;
    }

    private WechatApi() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(20, TimeUnit.SECONDS);
        okHttpClient.addInterceptor(httpLoggingInterceptor);
        okHttpClient.sslSocketFactory(SSLSocketClient.getSSLSocketFactory());
        okHttpClient.hostnameVerifier(SSLSocketClient.getHostnameVerifier());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.DOMAIN_WECHAT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient.build())
                .build();
        apiService = retrofit.create(WeChatApiService.class);
    }

}
