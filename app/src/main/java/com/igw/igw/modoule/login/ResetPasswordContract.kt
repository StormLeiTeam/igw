package com.igw.igw.modoule.login

import com.igw.igw.bean.login.ResetPwdBean
import com.igw.igw.bean.login.VerifyBean
import com.igw.igw.mvp.model.IBaseModel
import com.igw.igw.mvp.presenter.IBasePresenter
import com.igw.igw.mvp.view.IBaseView
import com.igw.igw.network.NetObserver

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */
class ResetPasswordContract {


    interface Model : IBaseModel {

        fun sendEmailVerifyCode(email: String, type: Int, observer: NetObserver<VerifyBean>)

        fun resetPassword(email: String, verifyCode: String, password: String, observer: NetObserver<ResetPwdBean>)

    }

    interface View : IBaseView {

        fun onFailToCode(code: Int,msg: String)

        fun onSuccess()

        fun onFailToReset(code: Int,msg: String)


    }

    interface Presenter : IBasePresenter {


        // 发送验证码
        fun sendEmailVerifyCode(email: String, type: Int)


        fun resetPassword(email: String, verifyCode: String, password: String)
    }
}