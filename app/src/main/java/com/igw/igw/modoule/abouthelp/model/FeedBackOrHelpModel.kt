package com.igw.igw.modoule.abouthelp.model

import com.igw.igw.bean.help.CommonBean
import com.igw.igw.bean.help.HelpBean
import com.igw.igw.modoule.abouthelp.HelpContract
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
class FeedBackOrHelpModel : HelpContract.Model {


    override fun getHelpList(pageNum: Int, pageSize: Int, observer: NetObserver<HelpBean.DataBean>) {

        ControllerUtils.getCommonController().getHelpList(pageNum, pageSize, observer)
    }

    override fun saveFeedback(content: String, observer: NetObserver<CommonBean.DataBean>) {

        ControllerUtils.getCommonController().saveFeedback(content,observer)
    }
}