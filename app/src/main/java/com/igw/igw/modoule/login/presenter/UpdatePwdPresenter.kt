package com.igw.igw.modoule.login.presenter

import com.igw.igw.bean.login.UpdatePwdBean
import com.igw.igw.modoule.login.UpdatePwdContract
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
class UpdatePwdPresenter(model: UpdatePwdContract.Model):
        BasePresenter<UpdatePwdContract.View,UpdatePwdContract.Model>(model), UpdatePwdContract.Presenter {


    companion object{

        val TAG = "UpdatePwdPresenter"
    }


    override fun requestData() {


    }

    override fun updatePwd(oldPassword: String, newPassword: String) {

        mModel.updatePwd(oldPassword,newPassword,object : NetObserver<UpdatePwdBean>(UpdatePwdBean::class.java){
            override fun onSuccess(m: UpdatePwdBean?) {



                mRootView.onSuccess()

               LogUtils.d(TAG,"修改密码 -- > onSuccess")
            }

            override fun onFail(code: Int, msg: String?) {

                mRootView.onFail(code,msg!!)

                LogUtils.d(TAG,"修改密码 -- > onFail")

            }

            override fun onError(msg: String?) {
                LogUtils.d(TAG,"修改密码 -- > onError")


            }

        })
    }


}