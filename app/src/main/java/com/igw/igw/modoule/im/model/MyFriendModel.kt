package com.igw.igw.modoule.im.model

import com.igw.igw.bean.FriendBean
import com.igw.igw.modoule.im.MyFriendContract
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
class MyFriendModel : MyFriendContract.Model{



    override fun getFriendsList(observer: NetObserver<FriendBean.DataBean>) {

        ControllerUtils.getImController().getFriends(observer)

    }


}