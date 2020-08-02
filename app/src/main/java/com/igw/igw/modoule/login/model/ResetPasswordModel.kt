package com.igw.igw.modoule.login.model

import com.igw.igw.bean.login.ResetPwdBean
import com.igw.igw.bean.login.VerifyBean
import com.igw.igw.modoule.login.ResetPasswordContract
import com.igw.igw.network.NetObserver
import com.shengshijingu.yashiji.common.util.ControllerUtils

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */
class ResetPasswordModel : ResetPasswordContract.Model {


    companion object {
        val TAG = "ResetPasswordModel"
    }


    override fun sendEmailVerifyCode(email: String, type: Int, observer: NetObserver<VerifyBean.DataBean>) {


        ControllerUtils.getLoginControllerInstance().sendEmailVerifyCode(email, type, observer)

    }

    override fun resetPassword(email: String, verifyCode: String, password: String, observer: NetObserver<ResetPwdBean>) {

        ControllerUtils.getLoginControllerInstance().resetPassword(email, verifyCode, password, observer)
    }


}