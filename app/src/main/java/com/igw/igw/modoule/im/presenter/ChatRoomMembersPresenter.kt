package com.igw.igw.modoule.im.presenter

import com.igw.igw.bean.chat.AddFriendBean
import com.igw.igw.bean.chat.BannedBean
import com.igw.igw.bean.chat.ChatRoomUsesBean
import com.igw.igw.bean.login.UserInfoBean
import com.igw.igw.modoule.im.ChatRoomMembersContract
import com.igw.igw.mvp.presenter.BasePresenter
import com.igw.igw.network.NetObserver
import com.igw.igw.utils.LogUtils

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe 聊天室成员
 */
class ChatRoomMembersPresenter(model: ChatRoomMembersContract.Model)
    : BasePresenter<ChatRoomMembersContract.View, ChatRoomMembersContract.Model>(model), ChatRoomMembersContract.presenter {


    companion object {

        val TAG = "ChatRoomMembersPresenter"

    }


    override fun requestData() {


    }

    override fun chatroomUsers(chatRoomId: String, nickName: String) {


        mModel.chatroomUsers(chatRoomId, nickName, object : NetObserver<ChatRoomUsesBean.DataBean>(ChatRoomUsesBean.DataBean::class.java) {
            override fun onSuccess(m: ChatRoomUsesBean.DataBean) {

                LogUtils.d(TAG, "chatRoomUsers --> onSuccess ")
                mRootView.onSuccessChatRoomUser(m)

            }

            override fun onFail(code: Int, msg: String?) {

                LogUtils.d(TAG, "chatRoomUsers --> onFail ")
                mRootView.onFailChatRoomUser(code, msg ?: "")

            }

            override fun onError(msg: String?) {
                LogUtils.d(TAG, "chatRoomUsers --> onError ")

            }
        })


    }


    /**
     * 获取个人信息
     */
    override fun userInfo() {

        mModel.userInfo(object : NetObserver<UserInfoBean.DataBean>(UserInfoBean.DataBean::class.java) {
            override fun onSuccess(m: UserInfoBean.DataBean) {

                mRootView.userInfoSuccessful(m)

            }

            override fun onFail(code: Int, msg: String?) {
                if (msg != null) {
                    mRootView.userInfoFail(code, msg)
                }

            }

            override fun onError(msg: String?) {
            }


        })
    }

    override fun addFriend(friendUserId: Int) {


        mModel.addFriend(friendUserId, object : NetObserver<AddFriendBean.DataBean>(AddFriendBean.DataBean::class.java) {
            override fun onSuccess(m: AddFriendBean.DataBean) {

                mRootView.addFriendSuccess(m)

            }

            override fun onFail(code: Int, msg: String?) {

                mRootView.addFriendFail(code, msg ?: "")

            }

            override fun onError(msg: String?) {
            }


        })
    }

    /**
     *
     *
     */
    override fun banned(chatroomId: String, userId: String, blockType: Int) {


        mModel.banned(chatroomId, userId, blockType, object : NetObserver<BannedBean.DataBean>(BannedBean.DataBean::class.java) {
            override fun onSuccess(m: BannedBean.DataBean) {


            }

            override fun onFail(code: Int, msg: String?) {


            }

            override fun onError(msg: String?) {
            }


        })
    }


}