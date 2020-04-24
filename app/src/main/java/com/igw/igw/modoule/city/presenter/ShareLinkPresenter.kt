package com.igw.igw.modoule.city.presenter

import com.igw.igw.modoule.city.ShareLInkContract
import com.igw.igw.mvp.presenter.BasePresenter

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */
class ShareLinkPresenter(model: ShareLInkContract.Model) :
        BasePresenter<ShareLInkContract.View,ShareLInkContract.Model>(model), ShareLInkContract.Presenter{


    companion object{
        val TAG  = "ShareLinkPresenter"
    }
    override fun requestData() {


    }
}