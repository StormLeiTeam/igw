package com.igw.igw.modoule.abouthelp.model

import com.igw.igw.bean.help.HelpInfoBean
import com.igw.igw.modoule.abouthelp.HelpContract
import com.igw.igw.modoule.abouthelp.HelpInfoContract
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
class HelpInfoModel : HelpInfoContract.Model {

    override fun getHelpInfo(id: Int, observer: NetObserver<HelpInfoBean.DataBean>) {

        ControllerUtils.getCommonController().helpDetail(id,observer)
    }


}