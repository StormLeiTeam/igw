package com.igw.igw.modoule.im.model

import com.igw.igw.bean.chat.ChatRoomBean
import com.igw.igw.bean.chat.ChatRoomUsesBean
import com.igw.igw.modoule.im.ChatTypeContract
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
class ChatModel :ChatTypeContract.Model {



    override fun createChatRoom(chatRoomId: String, chatRoomName: String, observer: NetObserver<ChatRoomBean.DataBean>) {


        ControllerUtils.getImController().createChatRoom(chatRoomId,chatRoomName,observer)

    }

    override fun destoryChatRoom(chatRoomId: String, observer: NetObserver<ChatRoomBean.DataBean>) {


        ControllerUtils.getImController().destoryChatroom(chatRoomId,observer)
    }

    override fun chatRoomUsers(chatRoomId: String, observer: NetObserver<ChatRoomUsesBean>) {

        ControllerUtils.getImController().chatroomUsers(chatRoomId, observer)


    }


}