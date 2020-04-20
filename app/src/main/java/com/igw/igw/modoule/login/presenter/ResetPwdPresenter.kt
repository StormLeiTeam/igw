package com.igw.igw.modoule.login.presenter

import com.igw.igw.modoule.login.ResetPasswordContract
import com.igw.igw.mvp.presenter.BasePresenter

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */
class ResetPwdPresenter(model: ResetPasswordContract.Model) :
        BasePresenter<ResetPasswordContract.View,ResetPasswordContract.Model>(model) {



    companion object{
        val TAG = "ResetPwdPresenter"
    }

    override fun requestData() {



    }
}