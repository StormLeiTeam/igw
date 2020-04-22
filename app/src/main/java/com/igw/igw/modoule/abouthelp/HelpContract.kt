package com.igw.igw.modoule.abouthelp

import com.igw.igw.bean.help.CommonBean
import com.igw.igw.bean.help.HelpBean
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

class HelpContract {


    interface View : IBaseView {


        fun onSuccessHelpList(bean: HelpBean.DataBean, isLoadMore: Boolean)
        fun onFailHelpList(code: Int, msg: String, isLoadMore: Boolean)

        fun onSuccessFeedBack()
        fun onFailFeedBack(code: Int,msg: String)


    }


    interface Model : IBaseModel {


        fun getHelpList(pageNum: Int, pageSize: Int, observer: NetObserver<HelpBean.DataBean>)

        fun saveFeedback(content: String, observer: NetObserver<CommonBean.DataBean>)

    }

    interface Presenter : IBasePresenter {


        fun getHelpList(pageNum: Int, pageSize: Int, isLoadMore: Boolean)

        // 提交反馈
        fun saveFeedback(content: String)


    }
}