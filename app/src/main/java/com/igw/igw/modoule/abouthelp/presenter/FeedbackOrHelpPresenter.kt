package com.igw.igw.modoule.abouthelp.presenter

import com.igw.igw.bean.help.CommonBean
import com.igw.igw.bean.help.HelpBean
import com.igw.igw.modoule.abouthelp.HelpContract
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
class FeedbackOrHelpPresenter(model: HelpContract.Model) :
        BasePresenter<HelpContract.View, HelpContract.Model>(model), HelpContract.Presenter {


    companion object {

        val TAG = "FeedbackOrHelpPresenter"
    }

    override fun requestData() {


    }

    override fun getHelpList(pageNum: Int, pageSize: Int,isLoadMore: Boolean) {

        mModel.getHelpList(pageNum, pageSize, object : NetObserver<HelpBean.DataBean>(HelpBean.DataBean::class.java) {
            override fun onSuccess(m: HelpBean.DataBean) {

                LogUtils.d(TAG, "帮助列表 请求成功")

//
//                    mRootView.onFailHelpList(1," ",true)
//                    return
//                }

                m?.let {

                    mRootView.onSuccessHelpList(m,isLoadMore)
                }


            }

            override fun onFail(code: Int, msg: String?) {
                mRootView.onFailHelpList(code,msg!!,isLoadMore)

                LogUtils.d(TAG, "帮助列表请求失败 ")
            }

            override fun onError(msg: String?) {
                LogUtils.d(TAG, "帮助列表请求发生错误  ")

            }


        })
    }

    override fun saveFeedback(content: String,language: Int, cityId: Int) {

        mModel.saveFeedback(content,language, cityId,object : NetObserver<CommonBean.DataBean>(CommonBean.DataBean::class.java){
            override fun onFail(code: Int, msg: String?) {
                LogUtils.d(TAG, "提交反馈失败 ")

                mRootView.onFailFeedBack(code,msg!!)
            }

            override fun onSuccess(m: CommonBean.DataBean) {
                LogUtils.d(TAG, "提交反馈成功 ")


                mRootView.onSuccessFeedBack()
            }

            override fun onError(msg: String?) {
            }


        })
    }
}