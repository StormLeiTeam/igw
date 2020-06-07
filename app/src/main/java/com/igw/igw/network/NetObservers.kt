package com.igw.igw.network

import com.google.gson.Gson
import com.igw.igw.bean.login.UserInfoBean
import com.shengshijingu.yashiji.common.net.Response
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import okhttp3.ResponseBody

/**
 *
 *    author: 雷雷
 *
 *    date: 2020/5/294:45 PM .
 *
 *    e-mail: zhangleilei@inmyshow.com
 *
 */
abstract class NetObservers<M> constructor(var beanType: Class<M>) : Observer<ResponseBody> {

    var tag: String

    private val gson: Gson

    private val bean: UserInfoBean

    init {
        tag = "1212"
        bean = UserInfoBean()
        gson = Gson()
    }

    protected abstract fun onSuccess(m: M)
//
//    override fun onNext(t: ResponseBody) {
//
//        t?.let {
//            val response = gson.fromJson(t.toString(), Response::class.java)
//
//            if (response.isSuccess) {
//                onSuccess(gson.fromJson(response.data.toString(), beanType))
//            }
//        }
//    }
//
//    override fun onError(e: Throwable) {
//    }
//
//    override fun onSubscribe(d: Disposable) {
//    }

    override fun onNext(t: ResponseBody) {

     t?.let {
         val response=gson.fromJson(t.toString(),Response::class.java)

         if(response.isSuccess){
             onSuccess(gson.fromJson(response.data.toString(),beanType))
         }


     }

    }

}