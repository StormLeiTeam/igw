package com.igw.igw.modoule.home.presenter

import com.igw.igw.bean.VersionBean
import com.igw.igw.bean.message.MessageCenterBean
import com.igw.igw.fragment.my.presenter.MyPresenter
import com.igw.igw.modoule.home.HomeContract
import com.igw.igw.modoule.messagemodule.presenter.MessageListPresenter
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

class HomePresenter(model: HomeContract.Model) : BasePresenter<HomeContract.View, HomeContract.Model>(model), HomeContract.Presenter {
    override fun requestData() {


    }

    override fun messageCenterList() {


        mModel.messageCenterList(object : NetObserver<MessageCenterBean.DataBean>(MessageCenterBean.DataBean::class.java) {
            override fun onSuccess(m: MessageCenterBean.DataBean) {

                LogUtils.d(MessageListPresenter.TAG, "消息中心列表 --onSuccess ")
                m.let { mRootView.onSuccess(it.rows) }

            }

            override fun onFail(code: Int, msg: String?) {

                LogUtils.d(MessageListPresenter.TAG, "消息中心列表 --onFail ")

                mRootView.onFail(code, msg!!)

            }

            override fun onError(msg: String?) {

                LogUtils.d(MessageListPresenter.TAG, "消息中心列表 --onError ")

            }


        })
    }

    override fun updateVersion() {

        mModel.checkVersion(object : NetObserver<VersionBean.DataBean>(VersionBean.DataBean::class.java){
            override fun onSuccess(m: VersionBean.DataBean) {
                LogUtils.d(MyPresenter.TAG,"更新版本信息 --> successful")

                m?.let {

                    mRootView.versionSuccessful(it)


                }
            }

            override fun onFail(code: Int, msg: String?) {
                LogUtils.d(MyPresenter.TAG,"更新版本信息 --> fail ")

                mRootView.versionFail(code,msg!!)

            }

            override fun onError(msg: String?) {
                LogUtils.d(MyPresenter.TAG,"更新版本信息 --> error  ")

            }


        })
    }


}