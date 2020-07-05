package com.igw.igw.modoule.login.presenter

import com.igw.igw.bean.NationalityBean
import com.igw.igw.bean.login.*
import com.igw.igw.modoule.login.UpdateInfoContract
import com.igw.igw.mvp.presenter.BasePresenter
import com.igw.igw.network.NetObserver
import com.igw.igw.utils.LogUtils
import java.io.File

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
            override fun onSuccess(m: NationalityBean.DataBean) {

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
            override fun onSuccess(m: CityListBean.DataBean) {


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


        mModel.updateUserInfo(registerBean, object : NetObserver<UserInfoBean.DataBean>(UserInfoBean.DataBean::class.java) {
            override fun onSuccess(m: UserInfoBean.DataBean) {
                LogUtils.d(TAG, "修改个人数据成功 -->  ${m.toString()}")
                mRootView.updateUserInfoSuccessful(m)
            }

            override fun onFail(code: Int, msg: String?) {

                LogUtils.d(TAG, "修改个人信息失败 -- ")


                mRootView.updateUserInfoFail(code,msg!!)
            }

            override fun onError(msg: String?) {
                LogUtils.d(TAG, "为空 -- ")

            }


        })


    }

    override fun  uploadImaga(file: File) {

        mModel.unloadImageFile(file,object :NetObserver<HeadImageBean.DataBean>(HeadImageBean.DataBean::class.java){
            override fun onSuccess(m: HeadImageBean.DataBean) {
                LogUtils.d(TAG,"上传照片成功  --> ")

                mRootView.loadHeadImageSuccessful(m)

            }

            override fun onFail(code: Int, msg: String?) {
                LogUtils.d(TAG,"上传照片失败  --> ")

                mRootView.loadHeadImageFail(code,msg!!)

            }

            override fun onError(msg: String?) {
                LogUtils.d(TAG,"上传照片异常 --> ")




            }

        })

    }


}