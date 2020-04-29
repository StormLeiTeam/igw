package com.igw.igw.modoule.login.loginstate

import android.content.Context
import com.igw.igw.bean.login.LoginBean
import com.igw.igw.utils.GsonUtils
import com.igw.igw.utils.LogUtils
import com.igw.igw.utils.SPUtils

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */

class LoginManager {


    private var loginState: UserState = LoginoutState()

    //
    companion object {


        public val TAG = "LoginManger"

        val instance: LoginManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {

            LoginManager()
        }
    }



    public fun  init(){

        SPUtils.getInstance(Contanct.USER_INFO)
    }

    /**
     * 登陆成功
     */
    fun loginSuccess(userInfo: String) {

        // 存储 用户信息
        // 更改用户状态

        state(LoginState())


//        SPUtils.getInstance(Contanct.USER_INFO).put(Contanct.KEY_USER_INFO, userInfo)

//        SPUtils.getInstance(USER_INFO).put(KEY_TOKEN,)
        SPUtils.getInstance(Contanct.USER_INFO).put(Contanct.KEY_LOGIN_STATE, true)
        //保存token

        var user = GsonUtils.getInstance().fromJson<LoginBean.DataBean>(userInfo, LoginBean.DataBean::class.java)

        SPUtils.getInstance(Contanct.USER_INFO).put(Contanct.KEY_TOKEN, user.token)


        (loginState as LoginState).initData(userInfo)

    }


    /**
     * 登出
     */
    fun loginOut() {
        // 清除个人信息
        // 更改用户状态

        state(LoginoutState())

        (loginState as LoginoutState).loginOut()

    }


    fun state(userState: UserState) {

        loginState = userState
    }


    fun initLoginState() {
        val isLogin = SPUtils.getInstance(Contanct.USER_INFO).getBoolean(Contanct.KEY_LOGIN_STATE)

        if (isLogin) {
            state(LoginState())


//            var userInfo = SPUtils.getInstance(Contanct.USER_INFO).getString(Contanct.KEY_USER_INFO)


            // bug 用
            var token = SPUtils.getInstance(Contanct.USER_INFO).getString(Contanct.KEY_TOKEN)

            LogUtils.d(TAG,"获取的token 的值==> $token")
//            userInfo?.let {
            token?.let {
                (loginState as LoginState).initData(token)
            }
//            }
        } else {

//            state(LoginoutState())
            loginOut()

        }


    }


    /**
     * 必要页面检测登陆状态
     *
     */
    fun checkLoginState(context: Context) {


        loginState.CheckLoginState(context)

    }


    fun isLogin(): Boolean {

        val isLogin = SPUtils.getInstance(Contanct.USER_INFO).getBoolean(Contanct.KEY_LOGIN_STATE)

        return isLogin
    }




    fun token(context: Context): String? {

        var token: String? = null
        if (loginState is LoginoutState) {
            loginState.CheckLoginState(context)
        } else {

            token = (loginState as LoginState).getToken().toString()

        }
        return token
    }

//    fun updateUserInfo(token: String, userinfoJson: String) {
//
//        if (loginState is LoginState) {
//
//            SPUtils.getInstance(Contanct.USER_INFO).put(Contanct.KEY_TOKEN, token)
//            SPUtils.getInstance(Contanct.USER_INFO).put(Contanct.KEY_USER_INFO,userinfoJson)
//        }
//
//
//    }


}