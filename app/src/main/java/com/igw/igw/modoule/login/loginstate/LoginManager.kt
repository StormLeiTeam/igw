package com.igw.igw.modoule.login.loginstate

import android.content.Context
import com.google.gson.Gson
import com.igw.igw.bean.login.UserInfo
import com.igw.igw.utils.GsonUtils
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

        val instance: LoginManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {

            LoginManager()
        }
    }


    /**
     * 登陆成功
     */
    fun loginSuccess(userInfo: String) {

        // 存储 用户信息
        // 更改用户状态

        state(LoginState())



        SPUtils.getInstance(Contanct.USER_INFO).put(Contanct.KEY_USER_INFO, userInfo)

//        SPUtils.getInstance(USER_INFO).put(KEY_TOKEN,)
        SPUtils.getInstance(Contanct.USER_INFO).put(Contanct.KEY_LOGIN_STATE, true)
        //保存token

        var  user = GsonUtils.getInstance().fromJson<UserInfo.DataBean>(userInfo,UserInfo.DataBean::class.java)

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


            var userInfo = SPUtils.getInstance(Contanct.USER_INFO).getString(Contanct.KEY_USER_INFO)

            userInfo?.let {
                (loginState as LoginState).initData(it)
            }
        } else {

//            state(LoginoutState())
            loginOut()

        }


    }


    /**
     * 必要页面检测登陆状态
     *
     */
    fun checkLoginState(context: Context){


        loginState.CheckLoginState(context)

    }





    fun getUserInfo(): String? {

       return  (loginState as LoginState).getUserInfo()


    }


    fun token(context: Context): String? {

        var token: String? = null
        if (loginState is LoginoutState){
            loginState.CheckLoginState(context)
        }else{

           token = (loginState as LoginState).getToken().toString()

        }
        return token
    }


}