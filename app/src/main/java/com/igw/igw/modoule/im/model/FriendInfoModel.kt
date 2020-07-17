package com.igw.igw.modoule.im.model

import com.igw.igw.bean.chat.DelFriendBean
import com.igw.igw.modoule.im.FriendInfoContract
import com.igw.igw.network.NetObserver
import com.shengshijingu.yashiji.common.util.ControllerUtils

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe 我的好友
 */
class FriendInfoModel : FriendInfoContract.Model {


    companion object {
        val TAG = "FriendInfoModel"
    }

    override fun delFriend(friendUserId: Int, observer: NetObserver<DelFriendBean.DataBean>) {


        ControllerUtils.getImController().getFriends(observer)

    }


}
