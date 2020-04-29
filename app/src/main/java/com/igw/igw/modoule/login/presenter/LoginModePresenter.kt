package com.igw.igw.modoule.login.presenter

import com.igw.igw.bean.login.LoginBean
import com.igw.igw.bean.login.VerifyBean
import com.igw.igw.modoule.login.LoginContract
import com.igw.igw.mvp.presenter.BasePresenter
import com.igw.igw.network.NetObserver
import com.igw.igw.utils.LogUtils

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */
class LoginModePresenter(model: LoginContract.Model) :
        BasePresenter<LoginContract.View, LoginContract.Model>(model), LoginContract.Presenter {


    companion object{

        val TAG = "LoginModePresenter"
    }

    override fun requestData() {
    }


    override fun loginByAccent(accent: String, password: String) {

        mModel.loginByAccent(accent, password, object : NetObserver<LoginBean.DataBean>(LoginBean.DataBean::class.java) {
            override fun onSuccess(m: LoginBean.DataBean?) {
                LogUtils.d(TAG,m.toString())

                m?.let {

                    mRootView.loginSuccess(m)

                }

            }

            override fun onFail(code: Int, msg: String?) {


            }



            override fun onError(msg: String?) {

            }


        })
    }

    override fun loginByEmail(email: String, verifyCode: String) {


        mModel.loginByEmail(email, verifyCode, object : NetObserver<LoginBean.DataBean>(LoginBean.DataBean::class.java) {
            override fun onSuccess(m: LoginBean.DataBean?) {


                m?.let {
                    mRootView.loginSuccess(m)
                }

            }

            override fun onFail(code: Int, msg: String?) {


            }

            override fun onError(msg: String?) {


            }

        })

    }

    override fun sendEmailVerifyCode(email: String, type: Int) {


        mModel.sendEmailVerifyCode(email, type, object : NetObserver<VerifyBean>(VerifyBean::class.java) {
            override fun onSuccess(m: VerifyBean?) {


            }


            override fun onError(msg: String?) {
            }

            override fun onFail(code: Int, msg: String?) {


            }
        })
    }
}