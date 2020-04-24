package com.igw.igw.modoule.messagemodule

import android.os.Message
import com.igw.igw.bean.message.MessageCenterBean
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
class MessageContract {


    interface View : IBaseView {

        fun onSuccess(mdatas: List<MessageCenterBean.DataBean.RowsBean>)
        fun onFail(code: Int, msg: String)



    }


    interface Model : IBaseModel {


        fun messageCenterList(observer: NetObserver<MessageCenterBean.DataBean>)


    }

    interface Presenter: IBasePresenter{


        fun messageCenterList()
    }
}