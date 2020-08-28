package com.igw.igw.modoule.login.loginstate

import android.content.Context
import android.content.Intent
import com.igw.igw.modoule.login.view.LoginActivity
import com.igw.igw.utils.SPUtils
import com.igw.igw.utils.SharedUtils

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */
class LoginOutState : UserState {


    fun loginOut() {

        // 更改用户状态
        SPUtils.getInstance(Contanct.USER_INFO).clear()

        SharedUtils.setRongToken("")
        SharedUtils.setAccessToken("")
        SharedUtils.setId("")
        SharedUtils.setHeadImg("")
        SharedUtils.setUserName("")
        SharedUtils.logOut()


    }

    override fun CheckLoginState(context: Context) {


        context.startActivity(Intent(context, LoginActivity::class.java))


    }


}