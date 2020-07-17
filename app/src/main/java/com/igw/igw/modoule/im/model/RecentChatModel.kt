package com.igw.igw.modoule.im.model

import com.igw.igw.bean.FriendBean
import com.igw.igw.bean.login.UserInfoBean
import com.igw.igw.modoule.im.RecentChatContract
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

class RecentChatModel : RecentChatContract.Model{




        companion object{
            val TAG = "RecentChatModel"
        }

    override fun getFriendsList(observer: NetObserver<FriendBean.DataBean>) {

        ControllerUtils.getImController().getFriends(observer)

    }

    override fun userInfo(observer: NetObserver<UserInfoBean.DataBean>) {


        ControllerUtils.getCommonController().userInfo(observer)

    }

}