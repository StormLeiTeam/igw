package com.igw.igw.modoule.login.loginstate

import android.content.Context
import android.content.Intent
import com.igw.igw.modoule.login.view.LoginActivity
import com.igw.igw.utils.SPUtils

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */
class LoginoutState : UserState {


    fun loginOut() {

        // 更改用户状态
        SPUtils.getInstance(Contanct.USER_INFO).clear()

    }

    override fun CheckLoginState(context: Context) {


        context.startActivity(Intent(context, LoginActivity::class.java))


    }


}