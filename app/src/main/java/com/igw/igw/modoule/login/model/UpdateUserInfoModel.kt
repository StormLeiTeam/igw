package com.igw.igw.modoule.login.model

import com.igw.igw.bean.NationalityBean
import com.igw.igw.bean.login.CityListBean
import com.igw.igw.bean.login.RegisterBean
import com.igw.igw.bean.login.RegisterSuccessBean
import com.igw.igw.modoule.login.UpdateInfoContract
import com.igw.igw.network.NetObserver
import com.shengshijingu.yashiji.common.controller.Controller
import com.shengshijingu.yashiji.common.util.ControllerUtils

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */
class UpdateUserInfoModel : UpdateInfoContract.Model {



    override fun getNationalityData(observer: NetObserver<NationalityBean.DataBean>) {
        ControllerUtils.getLoginControllerInstance().getNationality(observer)


    }

    override fun getCityData(countryId: Int, observer: NetObserver<CityListBean.DataBean>) {

        ControllerUtils.getLoginControllerInstance().getCityListData(countryId, observer)


    }

    override fun updateUserInfo(registerBean: RegisterBean, observer: NetObserver<RegisterSuccessBean.DataBean>) {




        ControllerUtils.getLoginControllerInstance()
                .updateUserInfo(registerBean.countryId!!,registerBean.cityId!!,registerBean.lastName,registerBean.firstName,
                        registerBean.sex!!, registerBean.birthday,registerBean.nickname,registerBean.agencyName,registerBean.description,
                        registerBean.email,registerBean.mobilePhone,registerBean.password, registerBean.inviteCode,registerBean.headImage,observer)


    }


}