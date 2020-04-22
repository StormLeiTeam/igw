package com.igw.igw.modoule.login.presenter

import com.igw.igw.bean.NationalityBean
import com.igw.igw.bean.login.CityListBean
import com.igw.igw.bean.login.RegisterBean
import com.igw.igw.bean.login.RegisterSuccessBean
import com.igw.igw.modoule.login.UpdateInfoContract
import com.igw.igw.modoule.login.view.RegisterActivity
import com.igw.igw.mvp.presenter.BasePresenter
import com.igw.igw.network.NetObserver
import com.igw.igw.utils.LogUtils

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */
class UpdateUserInfoPresenter(model: UpdateInfoContract.Model) :
        BasePresenter<UpdateInfoContract.View, UpdateInfoContract.Model>(model), UpdateInfoContract.Presenter {


    companion object {

        val TAG = "UpdateUserInfoPresenter"
    }

    override fun requestData() {


    }


    override fun getNationalityData() {


        mModel.getNationalityData(object : NetObserver<NationalityBean.DataBean>(NationalityBean.DataBean::class.java) {
            override fun onSuccess(m: NationalityBean.DataBean?) {

//                val toString = m?.countrys.toString()


//                LogUtils.d(TAG,"--->  $${m?.countrys?.get(0)?.countryCnName}")

//
//                m?.let {
//                    (rootView as RegisterActivity).showNationality(it.countrys)
//                }

                m?.let {

                    rootView.onSuccessNationality(it.countrys)
                }


            }


            override fun onError(msg: String?) {
                LogUtils.d(TAG, "onError --> ${msg}")


            }

            override fun onFail(code: Int, msg: String?) {

                LogUtils.d(TAG, "onFail --> ${code}")
            }

        })
    }

    override fun getCityData(countryId: Int, isLocal: Boolean) {


        mModel.getCityData(countryId, object : NetObserver<CityListBean.DataBean>(CityListBean.DataBean::class.java) {
            override fun onSuccess(m: CityListBean.DataBean?) {


                m?.let {
                    rootView.onSuccessCitys(it.citys, isLocal)
                }

//                rootView
////                m?.let {
////
////                    (rootView as RegisterActivity).showCityListData(it.citys)
////                }
            }


            override fun onError(msg: String?) {
            }

            override fun onFail(code: Int, msg: String?) {


            }

        })
    }

    /**
     *
     */
    override fun updateUserInfo(registerBean: RegisterBean) {


        mModel.updateUserInfo(registerBean, object : NetObserver<RegisterSuccessBean.DataBean>(RegisterSuccessBean.DataBean::class.java) {
            override fun onSuccess(m: RegisterSuccessBean.DataBean?) {
                LogUtils.d(TAG, "修改个人数据成功 -->  ${m.toString()}")
            }

            override fun onFail(code: Int, msg: String?) {

                LogUtils.d(TAG, "修改个人信息失败 -- ")
            }

            override fun onError(msg: String?) {
                LogUtils.d(TAG, "为空 -- ")

            }


        })


    }


}