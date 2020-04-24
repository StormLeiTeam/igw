package com.igw.igw.modoule.abouthelp.presenter

import com.igw.igw.bean.help.HelpInfoBean
import com.igw.igw.modoule.abouthelp.HelpContract
import com.igw.igw.modoule.abouthelp.HelpInfoContract
import com.igw.igw.modoule.abouthelp.model.HelpInfoModel
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
class HelpInfoPresenter(model: HelpInfoModel): BasePresenter<HelpInfoContract.View, HelpInfoContract.Model>(model), HelpInfoContract.Presenter {

    companion object{

        val TAG = "HelpInfoPresenter"
    }



    override fun requestData() {

    }



    override fun getHelpInfo(id: Int) {


        mModel.getHelpInfo(id,object :NetObserver<HelpInfoBean.DataBean>(HelpInfoBean.DataBean::class.java){
            override fun onSuccess(m: HelpInfoBean.DataBean?) {

                LogUtils.d(TAG,"获取帮助详情 --> onSuccess ")
                m?.let { mRootView.onSuccess(it) }
            }

            override fun onFail(code: Int, msg: String) {
                LogUtils.d(TAG,"获取帮助详情 --> onFail ")
                mRootView.onFail(code,msg)

            }

            override fun onError(msg: String?) {

                LogUtils.d(TAG,"获取帮助详情 --> onError ")

            }


        })

    }


}