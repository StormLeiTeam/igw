package com.igw.igw.modoule.city

import com.igw.igw.bean.city.CompanyInfoBean
import com.igw.igw.mvp.model.IBaseModel
import com.igw.igw.mvp.presenter.IBasePresenter
import com.igw.igw.mvp.view.IBaseView
import com.igw.igw.network.NetObserver

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */

class CompanyInfoContract{

    interface  View : IBaseView{


        fun onSuccessCompanyInfo(dataBean: CompanyInfoBean.DataBean.CompanyBean)


        fun onFailCompanyInfo(code: Int, msg: String)


    }



    interface  Presenter: IBasePresenter{

        fun companyInfo(companyId: Int)

    }


    interface Model:IBaseModel{




        fun companyInfo(companyId: Int, observer: NetObserver<CompanyInfoBean.DataBean>)
    }
}