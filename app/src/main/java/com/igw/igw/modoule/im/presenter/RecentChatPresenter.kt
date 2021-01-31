package com.igw.igw.modoule.im.presenter

import com.igw.igw.bean.FriendBean
import com.igw.igw.bean.login.UserInfoBean
import com.igw.igw.modoule.im.RecentChatContract
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
class RecentChatPresenter(model: RecentChatContract.Model) :
        BasePresenter<RecentChatContract.View, RecentChatContract.Model>(model), RecentChatContract.Presenter {
    companion object{
        val TAG = "RecentChatPresenter"
    }


    override fun requestData() {
    }

    override fun getFriendsList() {


        mModel.getFriendsList(object : NetObserver<FriendBean.DataBean>(FriendBean.DataBean::class.java){
            override fun onSuccess(m: FriendBean.DataBean) {

                LogUtils.d(TAG," 获取好友列表  -->  onSuccess")

                mRootView.onSuccessFriends(m)
            }

            override fun onFail(code: Int, msg: String?) {
                LogUtils.d(" 获取好友列表  -->  onFail")

                msg?.let {
                    mRootView.onFailFriends(code, it)

                }


            }

            override fun onError(msg: String?) {

                LogUtils.d(" 获取好友列表  -->  onError")



            }


        })
    }

    override fun userInfo() {


        mModel.userInfo(object : NetObserver<UserInfoBean.DataBean>(UserInfoBean.DataBean::class.java){
            override fun onSuccess(m: UserInfoBean.DataBean) {

                mRootView.userInfoSuccessful(m)

            }

            override fun onFail(code: Int, msg: String?) {
                if (msg != null) {
                    mRootView.userInfoFail(code,msg)
                }

            }

            override fun onError(msg: String?) {
            }


        })
    }
}