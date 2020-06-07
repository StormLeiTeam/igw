package com.igw.igw.network

import android.os.Handler
import android.util.Log
import com.google.gson.Gson
import com.shengshijingu.yashiji.common.Constants
import com.shengshijingu.yashiji.common.net.Response
import com.shengshijingu.yashiji.common.util.ToastUtil
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import okhttp3.ResponseBody
import java.io.IOException

abstract class NetObserver<M> protected constructor(private val beanType: Class<M>) : Observer<ResponseBody> {
    private val tag = ""
    private val mGson: Gson
    /**
     * 请求成功
     *
     * @param m 回调的实体类型
     */
    protected abstract fun onSuccess(m: M)

    /**
     * 请求失败（业务）
     *
     * @param msg 后台返回的错误信息
     */
    protected abstract fun onFail(code: Int, msg: String?)

    /**
     * 请求失败(网络以及其他原因)
     *
     * @param msg exception的errormsg
     */
    protected abstract fun onError(msg: String?)

    override fun onSubscribe(d: Disposable) {}
    override fun onNext(responseBody: ResponseBody) {
        Handler().postDelayed({
            try { //                Gson gson = new Gson();
//                Response response = gson.fromJson(responseBody.string(), Response.class);
//
//                if (response.isSuccess()) {
//                    onSuccess(gson.fromJson(response.getResult().toString(), beanType));
//                } else {
//                    onFail(response.getCode(), response.getErrorMessage());
//                }
                val response = mGson.fromJson(responseBody.string(), Response::class.java)
                if (response.isSuccess) {
                    onSuccess(mGson.fromJson(response.data.toString(), beanType))
                } else {
                    onFail(response.code, response.message)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: Exception) {
                Log.e("okhttp", e.toString())
                e.printStackTrace()
            }
        }, 500)
    }

    override fun onError(e: Throwable) { //由于网络原因或解析失败catch到的异常打印并回调到调用处
        Log.e("okhttp", e.toString())
        Handler().postDelayed({
            when (e.javaClass.name) {
                "java.net.ConnectException" -> ToastUtil.showToast(Constants.context, "服务器异常，请稍后重试")
                "java.net.SocketTimeoutException" -> ToastUtil.showToast(Constants.context, "服务器异常，请稍后重试")
            }
            onError("网络异常")
        }, 500)
    }

    override fun onComplete() {}

    companion object {
        private const val TAG = "NetObserver"
    }

    init {
        mGson = Gson()
    }
}