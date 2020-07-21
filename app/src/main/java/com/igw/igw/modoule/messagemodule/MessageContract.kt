package com.igw.igw.modoule.messagemodule

import com.igw.igw.bean.message.DealMessageBean
import com.igw.igw.bean.message.MessageCenterBean
import com.igw.igw.bean.message.ReadAllBean
import com.igw.igw.bean.message.ReadedMessage
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


        fun onDealMessageSuccess(data: DealMessageBean.DataBean?)
        fun onDealMessageFail(code: Int, msg: String)


        fun readedSuccess(data: ReadedMessage.DataBean)
        fun readedFail(code: Int, msg: String)

        fun readAllSuccess(data: ReadAllBean.DataBean?)
        fun readAllFail(code: Int, msg: String)

    }


    interface Model : IBaseModel {


        fun messageCenterList(observer: NetObserver<MessageCenterBean.DataBean>)

        fun dealMessage(id: Int, isAgree: Int, observer: NetObserver<DealMessageBean.DataBean>)


        // 接口 未给出
        fun readedMessage(id: Int, observer: NetObserver<ReadedMessage.DataBean>)

        fun readAll(observer: NetObserver<ReadAllBean.DataBean>)

    }

    interface Presenter : IBasePresenter {


        fun messageCenterList()

        fun dealMessage(id: Int, isAgree: Int)

        /**
         *阅读
         */
        fun readMessage(id: Int)

        fun readAll()
    }
}