package com.igw.igw.modoule.login.model

import com.igw.igw.bean.login.UserInfo
import com.igw.igw.bean.login.VerifyBean
import com.igw.igw.modoule.login.LoginContract
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

class LoginModel : LoginContract.Model{


    companion object{

       val TAG = "LoginModel"

    }

    override fun loginByAccent(accent: String, password: String, observer: NetObserver<UserInfo.DataBean>) {


        ControllerUtils.getLoginControllerInstance().loginByAccent(accent,password,observer)

    }

    override fun loginByEmail(email: String, verifyCode: String, observer: NetObserver<UserInfo.DataBean>) {


        ControllerUtils.getLoginControllerInstance().loginByEmail(email, verifyCode, observer)


    }

    override fun sendEmailVerifyCode(email: String, type: Int, observer: NetObserver<VerifyBean>) {


        ControllerUtils.getLoginControllerInstance().sendEmailVerifyCode(email,type,observer)

    }


}