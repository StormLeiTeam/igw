package com.igw.igw.modoule.login.presenter

import android.content.Context
import com.igw.igw.modoule.login.LoginContract
import com.igw.igw.mvp.presenter.BasePresenter
import com.igw.igw.mvp.view.IBaseView

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */
class LoginModePresenter(model: LoginContract.Model) :
        BasePresenter<LoginContract.View, LoginContract.Model>(model) {





    override fun requestData() {
    }
}