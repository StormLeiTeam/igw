package com.igw.igw.modoule.home.model

import com.igw.igw.bean.message.MessageCenterBean
import com.igw.igw.modoule.home.HomeContract
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
class HomeModel : HomeContract.Model {
    override fun messageCenterList(observer: NetObserver<MessageCenterBean.DataBean>) {
        ControllerUtils.getMessageController().messageCenterlist(observer)

    }


}