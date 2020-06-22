package com.igw.igw.modoule.city.presenter

import com.igw.igw.bean.city.CompanyInfoBean
import com.igw.igw.modoule.city.CompanyInfoContract
import com.igw.igw.mvp.presenter.BasePresenter
import com.igw.igw.network.NetObserver
import com.igw.igw.utils.LogUtils

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */
class CompanyInfoPresenter(model: CompanyInfoContract.Model) :
        BasePresenter<CompanyInfoContract.View, CompanyInfoContract.Model>(model), CompanyInfoContract.Presenter {



    companion object{

        val TAG  =  "CompanyInfoPresenter"
    }

    override fun requestData() {


    }

    override fun companyInfo(companyId: Int) {

        mModel.companyInfo(companyId,object :NetObserver<CompanyInfoBean.DataBean>(CompanyInfoBean.DataBean::class.java){
            override fun onSuccess(m: CompanyInfoBean.DataBean) {

                LogUtils.d(TAG, "公司详情 -->  onSuccess")

                mRootView.onSuccessCompanyInfo(m.company)


            }

            override fun onFail(code: Int, msg: String?) {
                LogUtils.d(TAG, "公司详情 -->  onFail")

                mRootView.onFailCompanyInfo(code, msg!!)


            }

            override fun onError(msg: String?) {
                LogUtils.d(TAG, "公司详情 -->  onError")

            }


        })
    }
}