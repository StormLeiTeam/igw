package com.igw.igw.modoule.im

import com.igw.igw.bean.chat.AddFriendBean
import com.igw.igw.bean.chat.BannedBean
import com.igw.igw.bean.chat.ChatRoomUsesBean
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
 * @Describe 聊天室成员控制器
 */
class ChatRoomMembersContract {


    interface View : IBaseView {

        /**
         * 获取聊天室成员数据成功
         */
        fun onSuccessChatRoomUser(data: ChatRoomUsesBean.DataBean)

        /**
         * 获取聊天室成员数据失败
         */
        fun onFailChatRoomUser(code: Int, msg: String)


        /**
         * 获取个人信息
         */
        fun userInfoSuccessful(data: UserInfoBean.DataBean)

        fun userInfoFail(code: Int, msg: String)


        fun addFriendSuccess(data: AddFriendBean.DataBean)

        fun addFriendFail(code: Int, msg: String)


        fun bannedSuccess(data: BannedBean.DataBean)
        fun bannedFail (code: Int, msg: String)

    }


    interface Model : IBaseModel {

        fun chatroomUsers(chatRoomId: String, nickName: String, observer: NetObserver<ChatRoomUsesBean.DataBean>)

        fun userInfo(observer: NetObserver<UserInfoBean.DataBean>)

        fun addFriend(friendUserId: Int, observer: NetObserver<AddFriendBean.DataBean>)

        fun banned(chatRoomId: String, userId: String,blockType: Int, observer: NetObserver<BannedBean.DataBean>)


    }

    interface presenter : IBasePresenter {

        /**
         * 聊天室成员
         */
        fun chatroomUsers(chatRoomId: String, nickName: String)

        /**
         * 获取个人信息
         */
        fun userInfo()

        fun addFriend(friendUserId: Int)


        fun banned(chatroomId: String, userId: String, blockType: Int )

    }

}