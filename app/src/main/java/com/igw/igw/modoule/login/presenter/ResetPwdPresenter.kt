package com.igw.igw.modoule.login.presenter

import com.igw.igw.bean.login.ResetPwdBean
import com.igw.igw.bean.login.VerifyBean
import com.igw.igw.modoule.login.ResetPasswordContract
import com.igw.igw.mvp.presenter.BasePresenter
import com.igw.igw.network.NetObserver

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */
class ResetPwdPresenter(model: ResetPasswordContract.Model) :
        BasePresenter<ResetPasswordContract.View, ResetPasswordContract.Model>(model), ResetPasswordContract.Presenter {


    companion object {
        val TAG = "ResetPwdPresenter"
    }

    override fun requestData() {


    }

    /**
     * 发送呢验证码
     */
    override fun sendEmailVerifyCode(email: String, type: Int) {

        mModel.sendEmailVerifyCode(email, type, object : NetObserver<VerifyBean>(VerifyBean::class.java) {
            override fun onSuccess(m: VerifyBean) {


            }

            override fun onFail(code: Int, msg: String?) {
                mRootView.onFailToCode(code,msg!!)

            }

            override fun onError(msg: String?) {
            }

        })


    }

    override fun resetPassword(email: String, verifyCode: String, password: String) {


        mModel.resetPassword(email, verifyCode, password, object : NetObserver<ResetPwdBean>(ResetPwdBean::class.java) {
            override fun onSuccess(m: ResetPwdBean) {

                mRootView.onSuccess()

            }

            override fun onFail(code: Int, msg: String?) {

                mRootView.onFailToReset(code,msg!!)
            }

            override fun onError(msg: String?) {
            }

        })


    }
}