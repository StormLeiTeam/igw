package com.shengshijingu.yashiji.common.net;

import java.time.temporal.ValueRange;
import java.util.concurrent.TimeUnit;
import java.util.logging.LogManager;

import com.shengshijingu.yashiji.common.Constants;
import com.shengshijingu.yashiji.common.controller.LoginController;
import com.shengshijingu.yashiji.common.net.Interceptor.CommonHeaderInterceptor;

import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetApi {

    private static ApiService apiService;

    private static String token = "";

    public static ApiService getApiService() {
        if (apiService == null) {
            synchronized (NetApi.class) {
                if (apiService == null) {
                    new NetApi();
                }
            }
        }
        return apiService;
    }

    private NetApi() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient.Builder okHttpClient = genericClient().newBuilder();
        okHttpClient.connectTimeout(30, TimeUnit.SECONDS);
        okHttpClient.addInterceptor(httpLoggingInterceptor);
//        okHttpClient.sslSocketFactory(SSLSocketClient.getSSLSocketFactory());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient.build())
                .build();
        apiService = retrofit.create(ApiService.class);
    }


    public static OkHttpClient genericClient() {

        CommonHeaderInterceptor commonHeaderInterceptor = new CommonHeaderInterceptor();

        OkHttpClient httpClient = new OkHttpClient.Builder()

                .addInterceptor(chain -> {
                    Request request = chain.request()
                            .newBuilder()
                            .addHeader("Authorization", Constants.authorization)
                            .build();
                    return chain.proceed(request);

                })
                .addNetworkInterceptor(commonHeaderInterceptor)
                .build();


        return httpClient;

    }


}
