package com.igw.igw.modoule.splash.presenter

import com.igw.igw.bean.SplashBean
import com.igw.igw.modoule.splash.SplashContract
import com.igw.igw.mvp.presenter.BasePresenter
import com.igw.igw.network.NetObserver

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */

class SplashPresenter(model: SplashContract.Model) :
        BasePresenter<SplashContract.View, SplashContract.Model>(model), SplashContract.Presenter {


    override fun requestData() {


    }

    override fun splashNet(platform: Int) {

        mModel.splashNet(platform, object : NetObserver<SplashBean.DataBean>(SplashBean.DataBean::class.java) {
            override fun onSuccess(m: SplashBean.DataBean?) {

                mRootView.onSuccess(m!!)
            }

            override fun onFail(code: Int, msg: String?) {


                msg?.let {
                    mRootView.onFail(code, it)

                }
            }

            override fun onError(msg: String?) {
            }


        })
    }


}