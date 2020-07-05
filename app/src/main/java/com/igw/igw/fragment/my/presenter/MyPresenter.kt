package com.igw.igw.fragment.my.presenter

import com.igw.igw.bean.VersionBean
import com.igw.igw.bean.login.UserInfoBean
import com.igw.igw.fragment.my.MyContract
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
public class MyPresenter(model: MyContract.Model) :
        BasePresenter<MyContract.View, MyContract.Model>(model),
        MyContract.Presenter {


    companion object {

        val TAG = "MyPresenter"

    }


    override fun requestData() {


    }

    override fun userInfo() {


        mModel.userInfo(object: NetObserver<UserInfoBean.DataBean>(UserInfoBean.DataBean::class.java){
            override fun onSuccess(m: UserInfoBean.DataBean) {

                LogUtils.d(TAG,"获取个人信息 --> successful ")


                mRootView.userInfoSuccessful(m)
            }

            override fun onFail(code: Int, msg: String?) {
                LogUtils.d(TAG,"获取个人信息 --> fail  $msg ")

                mRootView.userInfoFail(code,msg!!)

            }

            override fun onError(msg: String?) {

                LogUtils.d(TAG,"获取个人信息 --> error $msg ")
//                mRootView.userInfoFail(,msg)

            }
        })

    }

    override fun updateVersion() {

        mModel.updateVersion(object : NetObserver<VersionBean>(VersionBean::class.java){
            override fun onSuccess(m: VersionBean) {
                LogUtils.d(TAG,"更新版本信息 --> successful")

                m?.let {

                    mRootView.versionSuccessful(it)

                }
            }

            override fun onFail(code: Int, msg: String?) {
                LogUtils.d(TAG,"更新版本信息 --> fail ")

                mRootView.versionFail(code,msg!!)

            }

            override fun onError(msg: String?) {
                LogUtils.d(TAG,"更新版本信息 --> error  ")

            }


        })

    }


}