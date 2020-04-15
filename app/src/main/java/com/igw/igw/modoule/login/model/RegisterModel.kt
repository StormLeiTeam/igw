package com.igw.igw.modoule.login.model

import com.google.gson.Gson
import com.igw.igw.bean.NationalityBean
import com.igw.igw.httpclient.HttpClientManager
import com.igw.igw.modoule.login.RegisterContract
import com.igw.igw.network.NetObserver
import com.igw.igw.utils.LogUtils
import com.shengshijingu.yashiji.common.net.ApiService
import com.shengshijingu.yashiji.common.util.ControllerUtils
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 * * @Describe
 */
class RegisterModel : RegisterContract.Model {


    companion object{
        public  val TAG  = "RegisterModel"
    }

    override fun getNationalityData(observer: NetObserver<NationalityBean.DataBean>) {

        ControllerUtils.getLoginControllerInstance().getNationality(observer)




//
//        HttpClientManager.INSANCE.getHttpService(ApiService::class.java).getNationality(toRequestBody(map))
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(object : Observer<ResponseBody>{
//                    override fun onComplete() {
//
//                        LogUtils.d(TAG ,"onComplete -->    ")
//
//                    }
//
//                    override fun onSubscribe(d: Disposable) {
//
//
//                    }
//
//                    override fun onNext(t: ResponseBody) {
//
//                        LogUtils.d(TAG ,"onNext -->   ${t.string()} ")
//                    }
//
//                    override fun onError(e: Throwable) {
//                        LogUtils.d(TAG ,"onNext -->   ${e.printStackTrace().toString()} ")
//
//
//                    }
//
//
//                })

    }

    public fun toRequestBody(map: Map<String, Any>): RequestBody {

        var str  = Gson().toJson(map)


       return   RequestBody.create(MediaType.parse("application/json"), str.toString())


    }



//    override fun getNationalityData(observer: Observer<*>) {
//
//        ControllerUtils.getLoginControllerInstance().getNationality()
//    }


}