package com.igw.igw.modoule.messagemodule.model

import com.igw.igw.bean.message.MessageCenterBean
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


}