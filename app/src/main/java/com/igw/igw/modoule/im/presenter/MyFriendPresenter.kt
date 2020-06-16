package com.igw.igw.modoule.im.presenter

import com.igw.igw.bean.FriendBean
import com.igw.igw.modoule.im.MyFriendContract
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
class MyFriendPresenter(model:MyFriendContract.Model) :
        BasePresenter<MyFriendContract.View,MyFriendContract.Model>(model),MyFriendContract.Presenter {

    companion object{

        val TAG = "MyFriendPresenter"

    }

    override fun requestData() {


    }



    override fun getFriendsList() {


        mModel.getFriendsList(object :NetObserver<FriendBean.DataBean>(FriendBean.DataBean::class.java){
            override fun onSuccess(m: FriendBean.DataBean) {

                LogUtils.d(TAG," 获取好友列表  -->  onSuccess")

                mRootView.onSuccessFriends(m)
            }

            override fun onFail(code: Int, msg: String?) {
                LogUtils.d(TAG," 获取好友列表  -->  onFail")

                msg?.let {
                    mRootView.onFailFriends(code, it)

                }


            }

            override fun onError(msg: String?) {

                LogUtils.d(TAG," 获取好友列表  -->  onError")



            }


        })
    }


}