package com.igw.igw.modoule.abouthelp

import com.igw.igw.bean.help.HelpInfoBean
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

class HelpInfoContract {

    interface View : IBaseView {

        fun onSuccess(bean: HelpInfoBean.DataBean)
        fun onFail(code: Int, msg: String)


    }


    interface Model : IBaseModel {

        fun getHelpInfo(id: Int, observer: NetObserver<HelpInfoBean.DataBean>)

    }


    interface Presenter : IBasePresenter {


        fun getHelpInfo(id: Int)
    }


}