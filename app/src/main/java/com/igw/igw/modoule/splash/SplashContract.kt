package com.igw.igw.modoule.splash

import com.igw.igw.bean.SplashBean
import com.igw.igw.mvp.model.IBaseModel
import com.igw.igw.mvp.presenter.IBasePresenter
import com.igw.igw.mvp.view.IBaseView
import com.igw.igw.network.NetObserver

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */
class SplashContract {


    interface View : IBaseView {


        fun onSuccess(data: SplashBean.DataBean)
        fun onFail(code:Int,msg:String)


    }

    interface Model : IBaseModel {


        fun splashNet(platform:Int,observer: NetObserver<SplashBean.DataBean>)




    }


    interface Presenter : IBasePresenter {

        fun splashNet(platform: Int)
    }
}

