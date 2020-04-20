package com.igw.igw.network;

import java.io.IOException;

import android.os.Handler;
import android.util.Log;
import com.google.gson.Gson;
import com.shengshijingu.yashiji.common.Constants;
import com.shengshijingu.yashiji.common.net.Response;
import com.shengshijingu.yashiji.common.util.ToastUtil;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

public abstract class NetObserver<M> implements Observer<ResponseBody> {

    private static final String TAG = "NetObserver";

    private Class<M> beanType;

    private String tag = "";

    private Gson mGson;

    protected NetObserver(Class<M> beanType) {
        this.beanType = beanType;

        mGson = new Gson();
    }


    /**
     * 请求成功
     *
     * @param m 回调的实体类型
     */
    protected abstract void onSuccess(M m);

    /**
     * 请求失败（业务）
     *
     * @param msg 后台返回的错误信息
     */
    protected abstract void onFail(int  code, String msg);

    /**
     * 请求失败(网络以及其他原因)
     *
     * @param msg exception的errormsg
     */
    protected abstract void onError(String msg);

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(final ResponseBody responseBody) {
        new Handler().postDelayed(() -> {
            try {
//                Gson gson = new Gson();
//                Response response = gson.fromJson(responseBody.string(), Response.class);
//
//                if (response.isSuccess()) {
//                    onSuccess(gson.fromJson(response.getResult().toString(), beanType));
//                } else {
//                    onFail(response.getCode(), response.getErrorMessage());
//                }


                Response response = mGson.fromJson(responseBody.string(), Response.class);


                if(response.isSuccess()) {

                    onSuccess(mGson.fromJson(response.getData().toString(),beanType));

                }else {

                    onFail(response.getCode(),response.getMessage());
                }




            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                Log.e("okhttp", e.toString());
                e.printStackTrace();
            }

        }, 500);

    }


    @Override
    public void onError(Throwable e) {
        //由于网络原因或解析失败catch到的异常打印并回调到调用处
        Log.e("okhttp", e.toString());
        new Handler().postDelayed(() -> {
            switch (e.getClass().getName()) {
                case "java.net.ConnectException":
                    ToastUtil.showToast(Constants.context, "服务器异常，请稍后重试");
                    break;
                case "java.net.SocketTimeoutException":
                    ToastUtil.showToast(Constants.context, "服务器异常，请稍后重试");
                    break;
            }
            onError("网络异常");
        }, 500);


    }

    @Override
    public void onComplete() {
    }
}
