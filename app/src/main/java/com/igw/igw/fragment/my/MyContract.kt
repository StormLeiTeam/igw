package com.igw.igw.fragment.my

import com.igw.igw.bean.login.UserInfoBean
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

class MyContract {

    interface View : IBaseView {


        fun userInfoSuccessful(data: UserInfoBean.DataBean)

        fun userInfoFail(code: Int, msg: String)

    }


    interface Model : IBaseModel {


        fun userInfo(observer: NetObserver<UserInfoBean.DataBean>)
    }


    interface Presenter : IBasePresenter {


        fun userInfo()

    }
}