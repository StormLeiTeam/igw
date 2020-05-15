package com.igw.igw.modoule.messagemodule.model

import com.igw.igw.bean.message.DealMessageBean
import com.igw.igw.bean.message.MessageCenterBean
import com.igw.igw.bean.message.ReadedMessage
import com.igw.igw.modoule.messagemodule.MessageContract
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
class MessageListModel : MessageContract.Model {

    /**
     * 消息中心获取列表
     */
    override fun messageCenterList(observer: NetObserver<MessageCenterBean.DataBean>) {


        ControllerUtils.getMessageController().messageCenterlist(observer)


    }

    override fun dealMessage(id: Int, isAgree: Int, observer: NetObserver<DealMessageBean.DataBean>) {

        ControllerUtils.getMessageController().dealMessage(id, isAgree, observer)

    }

    override fun readedMessage(id: Int, isRead:Int, observer: NetObserver<ReadedMessage.DataBean>) {

        ControllerUtils.getMessageController().readedMessage(id,isRead,observer)
    }


}