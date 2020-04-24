package com.igw.igw.modoule.messagemodule.presenter

import android.os.Message
import com.igw.igw.bean.help.HelpBean
import com.igw.igw.bean.message.MessageCenterBean
import com.igw.igw.modoule.messagemodule.MessageContract
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
class MessageListPresenter(model: MessageContract.Model) :
        BasePresenter<MessageContract.View, MessageContract.Model>(model), MessageContract.Presenter {


    companion object {

        val TAG = "MessageListPresenter"

    }

    override fun requestData() {


    }

    /**
     *  获取消息中心
     */
    override fun messageCenterList() {
        mModel.messageCenterList(object : NetObserver<MessageCenterBean.DataBean>(MessageCenterBean.DataBean::class.java) {
            override fun onSuccess(m: MessageCenterBean.DataBean?) {

                LogUtils.d(TAG, "消息中心列表 --onSuccess ")
            }

            override fun onFail(code: Int, msg: String?) {

                LogUtils.d(TAG, "消息中心列表 --onFail ")

            }

            override fun onError(msg: String?) {

                LogUtils.d(TAG, "消息中心列表 --onError ")

                // 创建假数据

                val mdatas = ArrayList<MessageCenterBean.DataBean.RowsBean>()

                for (i in 0..10) {

                    if (i < 5) {

                        var bean = MessageCenterBean.DataBean.RowsBean()
                        bean.id = i;
                        bean.messageName = "消息中心"
                        bean.messageContent = "消息内容"
                        bean.messageType = 1
                        mdatas.add(bean)

                    } else {

                        var bean = MessageCenterBean.DataBean.RowsBean()
                        bean.id = i;
                        bean.messageName = "消息中心"
                        bean.messageContent = "消息内容"
                        bean.messageType = 2

                        mdatas.add(bean)
                    }


                }
                mRootView.onSuccess(mdatas)

            }


        })
    }


}