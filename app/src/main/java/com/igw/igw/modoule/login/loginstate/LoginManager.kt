package com.igw.igw.modoule.login.loginstate

import android.content.Context
import android.content.QuickViewConstants
import android.net.Uri.parse
import android.util.Log
import cn.jpush.android.api.JPushInterface
import com.igw.igw.bean.login.LoginBean
import com.igw.igw.bean.login.UserInfoBean
import com.igw.igw.utils.*
import com.shengshijingu.yashiji.common.Constants
import io.rong.imkit.RongIM
import io.rong.imlib.RongIMClient
import io.rong.imlib.model.UserInfo
import kotlin.math.log


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


    public var UserName: String? = null
        get() {
            if (field == null) {

                if (loginState is LoginInState) {
                    field = (loginState as LoginInState).userName

                }

            }

            return field
        }


    public var userHeadImage: String? = null
        get() {
            if (field == null) {
                if (loginState is LoginInState) {
                    field = (loginState as LoginInState).userHeadImg

                }
            }
            return field
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
        SharedUtils.setUserName(user.userName)

        SharedUtils.setHeadImg(user.headImage)
        SharedUtils.setId("${user.id}")

        SPUtils.getInstance(Contanct.USER_INFO).put(Contanct.KEY_USER_NAME, user.userName)
        SPUtils.getInstance(Contanct.USER_INFO).put(Contanct.KEY_USER_HEADPIC, user.headImage)


        Log.e("12345", user.rongyunToken + "===" + user.token)
        SPUtils.getInstance(Contanct.USER_INFO).put(Contanct.KEY_TOKEN, user.token)
        SPUtils.getInstance(Contanct.USER_INFO).put(Contanct.KEY_RONGTOKEN, user.rongyunToken)
        SPUtils.getInstance(Contanct.USER_INFO).put(Contanct.KEY_USER_ID, "${user.id}")


        (loginState as LoginInState).initData(user.token)
        (loginState as LoginInState).initRongToken(user.rongyunToken)

        (loginState as LoginInState).initRongYun()
        (loginState as LoginInState).bindJpush("${user.id}")


        updateRongUserInfo("${user.id}", user.nickName, user.headImage)

//        var userInfo = UserInfo("${user.id}", user.agencyName, parse(Constants.BASE_URL + user.headImage))
//        RongIM.getInstance().refreshUserInfoCache(userInfo);

//        bindJush(user)


        loginRongChatIM(user.roomId)

//        dealWithChatRoom(user.roomId)

    }

    private fun dealWithChatRoom(roomId: String) {
        var oldRoomId = SPUtils.getInstance(Contanct.USER_INFO).getString(Contanct.KEY_ROOMID)

        // 退出旧的
        if (oldRoomId != null && oldRoomId.isNotEmpty()) {


            LogUtils.d(TAG, "oldroomid --> ${oldRoomId}")

            // 退出
            RongIMClient.getInstance().quitChatRoom(oldRoomId, object : RongIMClient.OperationCallback() {
                /**
                 * 成功回调
                 */
                override fun onSuccess() {

                    LogUtils.d(TAG, "退出聊天群  ")
                    // 存储新的
                    SPUtils.getInstance(Contanct.USER_INFO).put(Contanct.KEY_ROOMID, roomId)
                    loginRongChatIM(roomId)


                }

                /**
                 * 失败回调
                 * @param errorCode 错误码
                 */
                override fun onError(errorCode: RongIMClient.ErrorCode) {

                    LogUtils.d(TAG, "退出聊天群 -----")
                }
            })


        } else {

            SPUtils.getInstance(Contanct.USER_INFO).put(Contanct.KEY_ROOMID, roomId)
            loginRongChatIM(roomId)

        }

    }

    private fun loginRongChatIM(roomId: String) {

        // 先存起来
        SPUtils.getInstance(Contanct.USER_INFO).put(Contanct.KEY_ROOMID, roomId)


        if (roomId.isNotEmpty()) {

            RongIMClient.getInstance().joinChatRoom(roomId, 50, object : RongIMClient.OperationCallback() {
                override fun onSuccess() {

                    LogUtils.d(TAG, "成功加入聊天群")
                }

                override fun onError(p0: RongIMClient.ErrorCode?) {
                    LogUtils.d(TAG, "加入聊天群---------")


                }

            })

        }


    }

    private fun bindJush(user: LoginBean.DataBean) {

        val set: MutableSet<String> = HashSet()
        set.add("${user.id}")
//        JPushInterface.setAliasAndTags(AppUtils.appContext, "${user.id}", null, null)


        JPushInterface.setTags(AppUtils.appContext, set) { i: Int, s: String?, set1: Set<String?>? ->
            if (i == 0) {
                LogUtils.d(TAG, "设置成功")
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
        var oldRoomId = SPUtils.getInstance(Contanct.USER_INFO).getString(Contanct.KEY_ROOMID)
        quitChatRoom(oldRoomId!!)
        (loginState as LoginOutState).loginOut()
//        SPUtils.getInstance(Contanct.USER_INFO).put(Contanct.KEY_ROOMID, oldRoomId!!)


    }


    fun state(userState: UserState) {

        loginState = userState
    }


    fun initLoginState() {
        val isLogin = SPUtils.getInstance(Contanct.USER_INFO).getBoolean(Contanct.KEY_LOGIN_STATE)

        if (isLogin) {
            state(LoginInState())

            // bug 用
            val token = SPUtils.getInstance(Contanct.USER_INFO).getString(Contanct.KEY_TOKEN)

            LogUtils.d(TAG, "获取的token 的值==> $token")
//          userInfo?.let {
            token.let {

                (loginState as LoginInState).initData(it!!)

            }


            val rongToken = SPUtils.getInstance(Contanct.USER_INFO).getString(Contanct.KEY_RONGTOKEN)

            rongToken?.let {
                (loginState as LoginInState).initRongToken(rongToken)
                (loginState as LoginInState).initRongYun()

            }

            val userId = SPUtils.getInstance(Contanct.USER_INFO).getString(Contanct.KEY_USER_ID)
            userId?.let {
                (loginState as LoginInState).bindJpush(userId)

            }


            // 添加
            var roomId = SPUtils.getInstance(Contanct.USER_INFO).getString(Contanct.KEY_ROOMID)
            loginRongChatIM(roomId!!)


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

    public fun userId(): String {

        if (loginState is
                        LoginInState) {

            return (loginState as LoginInState).getUserId().toString()

        }
        return ""

    }


    fun rongYunToken(context: Context): String? {


        var token: String = ""

        if (loginState is LoginInState) {

            token = (loginState as LoginInState).getRongYunToken()!!

        }

        return token
    }


    fun updateRongUserInfo(userId: String, nickname: String, headImg: String) {

        var userInfo = UserInfo(userId, nickname, parse(Constants.BASE_URL + headImg))
        RongIM.getInstance().refreshUserInfoCache(userInfo);

    }


    fun updateUserInfo(userInfo: String) {


        var user = GsonUtils.instance.fromJson<UserInfoBean.DataBean>(userInfo, UserInfoBean.DataBean::class.java)

//        SharedUtils.setAccessToken(user.token)
//        SharedUtils.setRongToken(user.rongyunToken)
//        SharedUtils.setUserName(user.userName)
//
//        SharedUtils.setHeadImg(user.headImage)

        SPUtils.getInstance(Contanct.USER_INFO).put(Contanct.KEY_USER_NAME, user.userName)
        SPUtils.getInstance(Contanct.USER_INFO).put(Contanct.KEY_USER_HEADPIC, user.headImage)


//        Log.e("12345", user.rongyunToken + "===" + user.)
//        SPUtils.getInstance(Contanct.USER_INFO).put(Contanct.KEY_TOKEN, user.token)
        SPUtils.getInstance(Contanct.USER_INFO).put(Contanct.KEY_RONGTOKEN, user.rongyunToken)
        SPUtils.getInstance(Contanct.USER_INFO).put(Contanct.KEY_USER_ID, "${user.id}")


//        (loginState as LoginInState).initData(user.token)
        (loginState as LoginInState).initRongToken(user.rongyunToken)


        //先退出
        var oldRoomId = SPUtils.getInstance(Contanct.USER_INFO).getString(Contanct.KEY_ROOMID)

        if (oldRoomId!! != user.roomId) {
            quitChatRoom(oldRoomId)
            SPUtils.getInstance(Contanct.USER_INFO).put(Contanct.KEY_ROOMID, user.roomId)
            loginRongChatIM(user.roomId)
        }

    }

    private fun quitChatRoom(roomid: String) {


        if (roomid.isNotEmpty()) {

            RongIMClient.getInstance().quitChatRoom(roomid, object : RongIMClient.OperationCallback() {
                /**
                 * 成功回调
                 */
                override fun onSuccess() {

                    LogUtils.d(TAG, "退出聊天群")
                    // 存储新的
//                    SPUtils.getInstance(Contanct.USER_INFO).put(Contanct.KEY_ROOMID, roomId)
//                    loginRongChatIM(roomId)


                }

                /**
                 * 失败回调
                 * @param errorCode 错误码
                 */
                override fun onError(errorCode: RongIMClient.ErrorCode) {

                    LogUtils.d(TAG, "退出聊天群 -----")
                }
            })

        }

    }


}