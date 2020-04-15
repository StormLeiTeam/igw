package com.igw.igw.modoule.login.presenter

import com.igw.igw.bean.NationalityBean
import com.igw.igw.modoule.login.RegisterContract
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

            override fun onFail(code: String?, msg: String?) {
                LogUtils.d(TAG,"onFail --> ${code}")

            }

            override fun onError(msg: String?) {
                LogUtils.d(TAG,"onError --> ${msg}")


            }

        })
    }




    override fun requestData() {


    }
}