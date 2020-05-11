package com.igw.igw.modoule.login.loginstate

import android.content.Context
import com.igw.igw.modoule.login.loginstate.Contanct.KEY_TOKEN
import com.igw.igw.modoule.login.loginstate.Contanct.USER_INFO
import com.igw.igw.utils.LogUtils
import com.igw.igw.utils.SPUtils
import com.shengshijingu.yashiji.common.net.Interceptor.CommonHeaderInterceptor
import io.rong.imlib.RongIMClient

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

    override fun CheckLoginState(context: Context) {


    }


    fun getToken(): String? {


        if (token == null || token!!.isEmpty()) {
            token = SPUtils.getInstance(USER_INFO).getString(KEY_TOKEN)
        }

        return token

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

            RongIMClient.connect(it,object :RongIMClient.ConnectCallback(){

                override fun onSuccess(p0: String?) {

                    LogUtils.d(TAG," 融云 token onSuccess  ---- ")

                }

                override fun onError(p0: RongIMClient.ErrorCode?) {

                    LogUtils.d(TAG," 融云 token onError  ---- ")


                }

                override fun onTokenIncorrect() {

                    LogUtils.d(TAG," 融云 token 无效  ---- ")
                }


            })


        }





    }


}