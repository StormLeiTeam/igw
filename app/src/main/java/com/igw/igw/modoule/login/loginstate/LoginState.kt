package com.igw.igw.modoule.login.loginstate

import android.content.Context
import com.igw.igw.bean.login.LoginBean
import com.igw.igw.modoule.login.loginstate.Contanct.KEY_TOKEN
import com.igw.igw.modoule.login.loginstate.Contanct.KEY_USER_INFO
import com.igw.igw.modoule.login.loginstate.Contanct.USER_INFO
import com.igw.igw.utils.GsonUtils
import com.igw.igw.utils.SPUtils
import com.shengshijingu.yashiji.common.net.Interceptor.CommonHeaderInterceptor

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe 登陆状态
 */
class LoginState : UserState {


    companion object {
        val TAG = "LoginState"


    }

//    private var userInfo: String? = null
    private var token: String? = null


    override fun CheckLoginState(context: Context) {


    }


    fun getToken(): String? {


        if (token == null || token!!.isEmpty()) {
            token = SPUtils.getInstance(USER_INFO).getString(KEY_TOKEN)
        }

        return token

    }

//    fun getUserInfo(): String? {
//
//        if (userInfo == null || userInfo!!.isEmpty()) {
//
//            userInfo = SPUtils.getInstance(USER_INFO).getString(KEY_USER_INFO)
//        }
//
//        return userInfo
//
//    }
    // 初始化 数据

    fun initData(token: String) {


//        this.userInfo = SPUtils.getInstance(USER_INFO).getString(KEY_USER_INFO)
//        this.token = SPUtils.getInstance(userInfo)
//        // gson


//        this.userInfo = userInfo
//        val user = GsonUtils.getInstance().fromJson<LoginBean.DataBean>(userInfo, LoginBean.DataBean::class.java)

//
//        this.userInfo

        this.token = token

        CommonHeaderInterceptor.token = this.token!!
//        this.token

    }


//    fun getToken(): String? {
//
//        var token = SPUtils.getInstance(USER_INFO).getString(KEY_TOKEN)
//
//        return token
//    }


}