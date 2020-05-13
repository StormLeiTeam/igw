package com.igw.igw.modoule.im.presenter

import com.igw.igw.bean.chat.ChatRoomBean
import com.igw.igw.bean.chat.ChatRoomUsesBean
import com.igw.igw.modoule.im.ChatTypeContract
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
class ChatPresenter(model:ChatTypeContract.Model):
        BasePresenter<ChatTypeContract.View,ChatTypeContract.Model>(model) , ChatTypeContract.Presenter{


    companion object{

        val TAG = "ChatPresenter"
    }


    override fun requestData() {


    }

    override fun createChatRoom(chatRoomId: String, chatRoomName: String) {

        mModel.createChatRoom(chatRoomId,chatRoomName,object :NetObserver<ChatRoomBean.DataBean>(ChatRoomBean.DataBean::class.java){
            override fun onSuccess(m: ChatRoomBean.DataBean?) {

                mRootView.createSuccessChatRoom(chatRoomId)
            }

            override fun onFail(code: Int, msg: String?) {
                mRootView.createFailChatRoom(chatRoomId, code, msg!!)

            }

            override fun onError(msg: String?) {
                TODO("Not yet implemented")
            }


        })



    }

    override fun destoryChatRoom(chatRoomId: String) {

        mModel.destoryChatRoom(chatRoomId, object :NetObserver<ChatRoomBean.DataBean>(ChatRoomBean.DataBean::class.java){
            override fun onSuccess(m: ChatRoomBean.DataBean?) {

                mRootView.createSuccessChatRoom(chatRoomId)

            }

            override fun onFail(code: Int, msg: String?) {

                mRootView.createFailChatRoom(chatRoomId,code,msg!!)

            }

            override fun onError(msg: String?) {
            }


        })
    }

    override fun chatRoomUsers(chatRoomId: String) {

        mModel.chatRoomUsers(chatRoomId,
                object :NetObserver<ChatRoomUsesBean>(ChatRoomUsesBean::class.java){
                    override fun onSuccess(m: ChatRoomUsesBean?) {


                    }

                    override fun onFail(code: Int, msg: String?) {
                    }

                    override fun onError(msg: String?) {
                    }


                })
    }
}