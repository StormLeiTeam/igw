package com.igw.igw.modoule.splash.model

import com.igw.igw.bean.SplashBean
import com.igw.igw.modoule.splash.SplashContract
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
class SplashModel :SplashContract.Model {

    override fun splashNet(platform: Int, observer: NetObserver<SplashBean.DataBean>) {

        ControllerUtils.getCommonController().splashNet(platform, observer)

    }
}