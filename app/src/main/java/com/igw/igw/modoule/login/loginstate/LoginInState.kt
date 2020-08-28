package com.igw.igw.modoule.login.loginstate

import android.content.Context
import android.net.Uri
import cn.jpush.android.api.JPushInterface
import com.igw.igw.modoule.login.loginstate.Contanct.KEY_TOKEN
import com.igw.igw.modoule.login.loginstate.Contanct.KEY_USER_ID
import com.igw.igw.modoule.login.loginstate.Contanct.USER_INFO
import com.igw.igw.utils.AppUtils
import com.igw.igw.utils.LogUtils
import com.igw.igw.utils.SPUtils
import com.igw.igw.utils.SharedUtils
import com.shengshijingu.yashiji.common.Constants
import com.shengshijingu.yashiji.common.net.Interceptor.CommonHeaderInterceptor
import io.rong.imkit.RongIM
import io.rong.imlib.RongIMClient
import io.rong.imlib.model.UserInfo

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe 登陆状态
 */
class LoginInState : UserState {


    companion object {
        val TAG = "LoginState"


    }

    //    private var userInfo: String? = null
    private var token: String? = null

    private var rongYunToken: String? = null

    private var userId: String? = null
    public var userName: String? = null
        get() {
            if (field == null) {
                field = SPUtils.getInstance(Contanct.USER_INFO).getString(Contanct.KEY_USER_NAME)
            }

            return field
        }


    public var userHeadImg: String? = null
        get() {

            if (field == null) {
                field = SPUtils.getInstance(Contanct.USER_INFO).getString(Contanct.KEY_USER_HEADPIC)

            }

            return field
        }


    override fun CheckLoginState(context: Context) {

    }




    fun getToken(): String? {


        if (token == null || token!!.isEmpty()) {
            token = SPUtils.getInstance(USER_INFO).getString(KEY_TOKEN)
        }

        return token

    }

    fun getUserId(): String? {

        if (userId == null || userId!!.isEmpty()) {
            userId = SPUtils.getInstance(USER_INFO).getString(KEY_USER_ID)

        }

        return userId
    }
    // 初始化 数据

    fun initData(token: String) {

        this.token = token

        CommonHeaderInterceptor.token = this.token!!

    }

    fun getRongYunToken(): String? {


        if (rongYunToken == null || rongYunToken!!.isEmpty()) {

            rongYunToken = SPUtils.getInstance(USER_INFO).getString(Contanct.KEY_RONGTOKEN)

        }

        return rongYunToken

    }

    fun initRongToken(rongToken: String) {

        this.rongYunToken = rongToken


    }

    fun initRongYun() {

        rongYunToken?.let {

            RongIMClient.connect(it, object : RongIMClient.ConnectCallback() {

                override fun onSuccess(p0: String?) {

                    LogUtils.d(TAG, " 融云 token onSuccess  ---- ")
                    RongIM.setUserInfoProvider({ userId ->
                        /**
                         * 获取设置用户信息. 通过返回的 userId 来封装生产用户信息.
                         * @param userId 用户 ID
                         */


                        LogUtils.d(TAG,"获取的userid ------>  $userId")
                        UserInfo(p0, SharedUtils.getUserName(), Uri.parse(Constants.BASE_URL + SharedUtils.getHeadImg()))
                    }, true)


                    RongIM.getInstance().setMessageAttachedUserInfo(true)


                }

                override fun onError(p0: RongIMClient.ErrorCode?) {

                    LogUtils.d(TAG, " 融云 token onError  ---- ")


                }

                override fun onTokenIncorrect() {


                }


            })
        }
    }


    private fun setRongUserInfo() {


        RongIM.setUserInfoProvider({ id ->

            UserInfo(id, userName, Uri.parse(userHeadImg))


        }, true)

//        RongIM.getInstance().setMessageAttachedUserInfo(true)

    }

    /**
     * bind jpush
     */
    fun bindJpush(id: String) {

        val set: MutableSet<String> = HashSet()
        set.add("${id}")
//        JPushInterface.setAliasAndTags(AppUtils.appContext, "${user.id}", null, null)

        JPushInterface.setTags(AppUtils.appContext, set) { i: Int, s: String?, set1: Set<String?>? ->
            if (i == 0) {
                LogUtils.d(LoginManager.TAG, "设置成功")
            } else {
            }
        }


    }


}
