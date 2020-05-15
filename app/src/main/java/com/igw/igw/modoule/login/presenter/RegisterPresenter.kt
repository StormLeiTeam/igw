package com.igw.igw.modoule.login.presenter

import com.igw.igw.bean.NationalityBean
import com.igw.igw.bean.login.CityListBean
import com.igw.igw.bean.login.HeadImageBean
import com.igw.igw.bean.login.RegisterBean
import com.igw.igw.bean.login.RegisterSuccessBean
import com.igw.igw.modoule.login.RegisterContract
import com.igw.igw.modoule.login.view.RegisterActivity
import com.igw.igw.mvp.presenter.BasePresenter
import com.igw.igw.network.NetObserver
import com.igw.igw.utils.LogUtils
import java.io.File
import kotlin.math.log

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe 注册
 */
class RegisterPresenter(model: RegisterContract.Model)
    : BasePresenter<RegisterContract.View, RegisterContract.Model>(model) {




    companion object{

        val TAG = "RegisterPresenter"
    }


    /**
     * 获取 国籍信息
     */
    fun getNationalityData(){


        mModel.getNationalityData(object: NetObserver<NationalityBean.DataBean>(NationalityBean.DataBean::class.java){
            override fun onSuccess(m: NationalityBean.DataBean?) {

//                val toString = m?.countrys.toString()


//                LogUtils.d(TAG,"--->  $${m?.countrys?.get(0)?.countryCnName}")


                m?.let {
                    (rootView as RegisterActivity).showNationality(it.countrys)
                }



            }




            override fun onError(msg: String?) {
                LogUtils.d(TAG,"onError --> ${msg}")


            }

            override fun onFail(code: Int, msg: String?) {

                LogUtils.d(TAG,"onFail --> ${code}")
            }

        })
    }


    /**
     * 根据国籍id 获取城市列表
     */
    fun getCityListData(countryId : Int){

        mModel.getCityData(countryId, object: NetObserver<CityListBean.DataBean>(CityListBean.DataBean::class.java){
            override fun onSuccess(m: CityListBean.DataBean?) {


                m?.let {

                    (rootView as RegisterActivity).showCityListData(it.citys)
                }
            }



            override fun onError(msg: String?) {
            }

            override fun onFail(code: Int, msg: String?) {


            }

        })
    }


    override fun requestData() {


    }

    fun registerUser(registerBean: RegisterBean) {

        mModel.registerUser(registerBean,object :NetObserver<RegisterSuccessBean.DataBean>(RegisterSuccessBean.DataBean::class.java){
            override fun onSuccess(m: RegisterSuccessBean.DataBean?) {


                LogUtils.d(TAG,"注册成功 ")
                rootView.registerSuccess()
            }



            override fun onError(msg: String?) {

                LogUtils.d(TAG,"出现错误")
            }

            override fun onFail(code: Int, msg: String?) {

                LogUtils.d(TAG,"注册失败")

                rootView.registerFail(code,msg!!)
            }


        })
    }


     fun uploadImaga(file: File) {

        mModel.unloadImageFile(file,object :NetObserver<HeadImageBean.DataBean>(HeadImageBean.DataBean::class.java){
            override fun onSuccess(m: HeadImageBean.DataBean) {
                LogUtils.d(TAG,"上传照片成功  --> ")

//                mRootView.loadHeadImageSuccessful(m)

            }

            override fun onFail(code: Int, msg: String) {
                LogUtils.d(TAG,"上传照片失败  --> ")

//                mRootView.loadHeadImageFail(code,msg)

            }

            override fun onError(msg: String?) {
                LogUtils.d(TAG,"上传照片异常 --> ")




            }

        })

    }
}