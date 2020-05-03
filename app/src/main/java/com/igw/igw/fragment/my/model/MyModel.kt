package com.igw.igw.fragment.my.model

import com.igw.igw.bean.VersionBean
import com.igw.igw.bean.login.UserInfoBean
import com.igw.igw.fragment.my.MyContract
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
class MyModel : MyContract.Model{
    override fun userInfo(observer: NetObserver<UserInfoBean.DataBean>) {

        ControllerUtils.getCommonController().userInfo(observer)

    }

    override fun updateVersion(observer: NetObserver<VersionBean>) {


        ControllerUtils.getCommonController().updateVersion(observer)

    }


}