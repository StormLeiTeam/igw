package com.igw.igw.modoule.login.model

import com.google.gson.Gson
import com.igw.igw.bean.NationalityBean
import com.igw.igw.bean.login.*
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
import java.io.File

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 * * @Describe
 */
class RegisterModel : RegisterContract.Model {


    companion object {
        public val TAG = "RegisterModel"
    }


    /**
     * 获取国籍
     */
    override fun getNationalityData(language: Int, observer: NetObserver<NationalityBean.DataBean>) {

        ControllerUtils.getLoginControllerInstance().getNationality(language, observer)


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

    override fun getCityData(countryId: Int, language: Int,observer: NetObserver<CityListBean.DataBean>) {

        ControllerUtils.getLoginControllerInstance().getCityListData(countryId, language, observer)

    }

    override fun registerUser(registerBean: RegisterBean, observer: NetObserver<LoginBean.DataBean>) {


        ControllerUtils.getLoginControllerInstance()
                .registerUser(registerBean.countryId!!, registerBean.cityId!!, registerBean.lastName, registerBean.firstName,
                        registerBean.sex!!, registerBean.birthday, registerBean.nickname, registerBean.agencyName, registerBean.userDesc,
                        registerBean.email, registerBean.mobilePhone, registerBean.password, registerBean.inviteCode, registerBean.headImage, observer)


    }

    override fun unloadImageFile(file: File, observer: NetObserver<HeadImageBean.DataBean>) {


        ControllerUtils.getLoginControllerInstance()
                .uploadImage(file, observer)


    }


    /**
     * 获取城市列表
     */


    public fun toRequestBody(map: Map<String, Any>): RequestBody {

        var str = Gson().toJson(map)


        return RequestBody.create(MediaType.parse("application/json"), str.toString())


    }


}