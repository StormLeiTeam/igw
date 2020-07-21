package com.igw.igw.modoule.messagemodule.presenter

import com.igw.igw.bean.message.DealMessageBean
import com.igw.igw.bean.message.MessageCenterBean
import com.igw.igw.bean.message.ReadAllBean
import com.igw.igw.bean.message.ReadedMessage
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
            override fun onSuccess(m: MessageCenterBean.DataBean) {

                LogUtils.d(TAG, "消息中心列表 --onSuccess ")
                m.let { mRootView.onSuccess(it.rows) }

            }

            override fun onFail(code: Int, msg: String?) {

                LogUtils.d(TAG, "消息中心列表 --onFail ")

                mRootView.onFail(code,msg!!)

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
//                mRootView.onSuccess(mdatas)

            }


        })
    }


    /**
     * 消息处理
     */
    override fun dealMessage(id: Int, isAgree: Int) {

        mModel.dealMessage(id,isAgree,
                object :NetObserver<DealMessageBean.DataBean>(DealMessageBean.DataBean::class.java){
                    override fun onSuccess(m: DealMessageBean.DataBean) {

                        mRootView.onDealMessageSuccess(m)

                    }

                    override fun onFail(code: Int, msg: String?) {
                        mRootView.onDealMessageFail(code,msg!!)
                    }

                    override fun onError(msg: String?) {
                        TODO("Not yet implemented")
                    }


                })


    }

    override fun readMessage(id: Int) {

        mModel.readedMessage(id,object : NetObserver<ReadedMessage.DataBean>(ReadedMessage.DataBean::class.java){
            override fun onSuccess(m: ReadedMessage.DataBean) {

                LogUtils.d(TAG,"readedMessage --> onSuccess")

                m.let {
                    mRootView.readedSuccess(it)

                }
            }

            override fun onFail(code: Int, msg: String?) {

                LogUtils.d(TAG,"readedMessage --> onFail")

                mRootView.readedFail(code,msg!!)

            }

            override fun onError(msg: String?) {

                LogUtils.d(TAG,"readedMessage --> onError")

            }


        })
    }

    override fun readAll() {


        mModel.readAll(object : NetObserver<ReadAllBean.DataBean>(ReadAllBean.DataBean::class.java){
            override fun onSuccess(m: ReadAllBean.DataBean) {


                LogUtils.d(TAG,"  ReadALL  -->  onSuccess")
                m.let {
                    mRootView.readAllSuccess(it)
                }
            }

            override fun onFail(code: Int, msg: String?) {
                LogUtils.d(TAG,"  ReadALL  -->  onFail")
                mRootView.readAllFail(code, msg!!)


            }

            override fun onError(msg: String?) {
                LogUtils.d(TAG,"  ReadALL  -->  onError")


            }


        })


    }




//    override fun readedMessage(id: Int,isRead:Int) {
//
//        mModel.readedMessage(id,isRead,
//        object :NetObserver<ReadedMessage.DataBean>(ReadedMessage.DataBean::class.java){
//            override fun onSuccess(m: ReadedMessage.DataBean) {
//
//
//                m.let {
//
//                    mRootView.readedSuccess(it)
//
//                }
//            }
//
//            override fun onFail(code: Int, msg: String?) {
//
//
//                mRootView.readedFail(code,msg!!)
//            }
//
//            override fun onError(msg: String?) {
//            }
//
//
//        })



//    }


}