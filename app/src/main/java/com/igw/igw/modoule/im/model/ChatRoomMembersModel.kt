package com.igw.igw.modoule.im.model

import com.igw.igw.bean.chat.AddFriendBean
import com.igw.igw.bean.chat.ChatRoomUsesBean
import com.igw.igw.bean.login.UserInfoBean
import com.igw.igw.modoule.im.ChatRoomMembersContract
import com.igw.igw.network.NetObserver
import com.shengshijingu.yashiji.common.util.ControllerUtils

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */
class ChatRoomMembersModel : ChatRoomMembersContract.Model {

    companion object{



        val  TAG = "ChatRoomMembersModel"

    }


    /**
     * 获取聊天室群成员
     */
    override fun chatroomUsers(chatRoomId: String, nickName: String, observer: NetObserver<ChatRoomUsesBean.DataBean>) {


        ControllerUtils.getImController().chatroomUsers(chatRoomId, nickName, observer)

    }

    override fun userInfo(observer: NetObserver<UserInfoBean.DataBean>) {


        ControllerUtils.getCommonController().userInfo(observer)

    }

    /**
     * 添加好友
     */
    override fun addFriend(friendUserId: Int, observer: NetObserver<AddFriendBean.DataBean>) {


        ControllerUtils.getImController().addFriend(friendUserId, observer)

    }
}