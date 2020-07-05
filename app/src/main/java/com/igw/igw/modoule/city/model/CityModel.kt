package com.igw.igw.modoule.city.model

import com.igw.igw.bean.city.CityLabelBean
import com.igw.igw.bean.city.CityResultBean
import com.igw.igw.modoule.city.CityContract
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
class CityModel : CityContract.Model {


    companion object {

        val TAG = "CityModel"


    }


    /**
     * 获取标签
     */
    override fun getCityLabel(cityId: Int, language: Int, observer: NetObserver<CityLabelBean.DataBean>) {


        ControllerUtils.getCityController().getCityLabel(cityId, language, observer)


    }

    override fun getCitySearchResult(cityId: Int, queryStr: String, labelId: Int,
                                     language: Int, pageNum: Int, pageSize: Int,
                                     observer: NetObserver<CityResultBean.DataBean>) {


        ControllerUtils.getCityController().
        getCitySearchResult(cityId, queryStr, labelId, language, pageNum, pageSize, observer)
    }


}