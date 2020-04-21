package com.igw.igw.modoule.login

import com.igw.igw.bean.login.UpdatePwdBean
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

class UpdatePwdContract {


    interface View : IBaseView {


        fun onSuccess()

        fun onFail(code: Int, msg: String)
    }


    interface Model : IBaseModel {

        fun updatePwd(oldPassword: String, newPassword: String, observer: NetObserver<UpdatePwdBean>)

    }

    interface Presenter : IBasePresenter {


        fun updatePwd(oldPassword: String, newPassword: String)
    }
}