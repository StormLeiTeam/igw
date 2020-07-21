package com.igw.igw.modoule.login.loginstate

import android.content.Context
import android.net.Uri.parse
import android.util.Log
import cn.jpush.android.api.JPushInterface
import com.igw.igw.bean.login.LoginBean
import com.igw.igw.utils.*
import com.shengshijingu.yashiji.common.Constants
import com.shengshijingu.yashiji.common.Constants.userId
import io.rong.imkit.RongIM
import io.rong.imlib.model.UserInfo


/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */

class LoginManager {


    private var loginState: UserState = LoginOutState()

    //
    companion object {


        public val TAG = "LoginManger"

        val instance: LoginManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {

            LoginManager()
        }
    }


    public fun init() {

        SPUtils.getInstance(Contanct.USER_INFO)
    }

    /**
     * 登陆成功
     */
    fun loginSuccess(userInfo: String) {

        // 存储 用户信息
        // 更改用户状态

        state(LoginInState())


        SPUtils.getInstance(Contanct.USER_INFO).put(Contanct.KEY_LOGIN_STATE, true)
        //保存token

        var user = GsonUtils.instance.fromJson<LoginBean.DataBean>(userInfo, LoginBean.DataBean::class.java)

        SharedUtils.setAccessToken(user.token)
        SharedUtils.setRongToken(user.rongyunToken)

        Log.e("12345",user.rongyunToken+"==="+user.token)
        SPUtils.getInstance(Contanct.USER_INFO).put(Contanct.KEY_TOKEN, user.token)
        SPUtils.getInstance(Contanct.USER_INFO).put(Contanct.KEY_RONGTOKEN, user.rongyunToken)

        (loginState as LoginInState).initData(user.token)
        (loginState as LoginInState).initRongToken(user.rongyunToken)

        (loginState as LoginInState).initRongYun()

        var userInfo = UserInfo("${user.id}", user.agencyName, parse(Constants.BASE_URL + user.headImage))
        RongIM.getInstance().refreshUserInfoCache(userInfo);

        bindJush(user)

    }

    private fun bindJush(user: LoginBean.DataBean) {


        val set: MutableSet<String> = HashSet()
        set.add("userid")
        JPushInterface.setAliasAndTags(AppUtils.appContext, "${user.id}", null, null)


        JPushInterface.setTags(AppUtils.appContext, set) { i: Int, s: String?, set1: Set<String?>? ->
            if (i == 0) {
            } else {
            }
        }
    }


    /**
     * 登出
     */
    fun loginOut() {
        // 清除个人信息
        // 更改用户状态
        state(LoginOutState())
        (loginState as LoginOutState).loginOut()

    }


    fun state(userState: UserState) {

        loginState = userState
    }


    fun initLoginState() {
        val isLogin = SPUtils.getInstance(Contanct.USER_INFO).getBoolean(Contanct.KEY_LOGIN_STATE)

        if (isLogin) {
            state(LoginInState())

            // bug 用
            var token = SPUtils.getInstance(Contanct.USER_INFO).getString(Contanct.KEY_TOKEN)

            LogUtils.d(TAG, "获取的token 的值==> $token")
//          userInfo?.let {
            token?.let {
                (loginState as LoginInState).initData(token)
            }


            var  rongToken = SPUtils.getInstance(Contanct.USER_INFO).getString(Contanct.KEY_RONGTOKEN)
            rongToken?.let {
                (loginState as LoginInState).initRongToken(rongToken)
                (loginState as LoginInState).initRongYun()

            }



        } else {

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
        if (loginState is LoginOutState) {
            loginState.CheckLoginState(context)
        } else {

            token = (loginState as LoginInState).getToken().toString()

        }
        return token
    }


    fun rongYunToken(context: Context){


        var token :String? = null

        if (loginState is LoginInState) {

            token = (loginState as LoginInState).getRongYunToken()

        }


    }


    fun updateRongUserInfo(userId: String, nickname: String, headImg: String) {

        var userInfo = UserInfo(userId, nickname, parse(Constants.BASE_URL + headImg))
        RongIM.getInstance().refreshUserInfoCache(userInfo);
        
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