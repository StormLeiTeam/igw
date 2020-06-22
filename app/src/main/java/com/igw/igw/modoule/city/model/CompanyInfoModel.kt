package com.igw.igw.modoule.city.model

import com.igw.igw.bean.city.CompanyInfoBean
import com.igw.igw.modoule.city.CompanyInfoContract
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
class CompanyInfoModel : CompanyInfoContract.Model {



    companion object{
        val TAG  = "CompanyInfoModel"


    }

    override fun companyInfo(companyId: Int, observer: NetObserver<CompanyInfoBean.DataBean>) {

        ControllerUtils.getCityController().companyCityInfo(companyId, observer)

    }


}