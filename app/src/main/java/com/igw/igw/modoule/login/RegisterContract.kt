package com.igw.igw.modoule.login

import com.igw.igw.bean.NationalityBean
import com.igw.igw.mvp.model.IBaseModel
import com.igw.igw.mvp.view.IBaseView
import com.igw.igw.network.NetObserver
import io.reactivex.Observer

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe 注册页面
 */
interface RegisterContract {


    interface Model: IBaseModel{

      fun  getNationalityData(observer: NetObserver<NationalityBean.DataBean>)

    }

    interface View : IBaseView{


    }
}