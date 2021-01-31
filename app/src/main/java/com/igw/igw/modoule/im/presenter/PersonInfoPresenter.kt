package com.igw.igw.modoule.im.presenter

import android.content.pm.FeatureInfo
import com.igw.igw.bean.chat.DelFriendBean
import com.igw.igw.modoule.im.PersonInfoContract
import com.igw.igw.mvp.presenter.BasePresenter
import com.igw.igw.network.NetObserver

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */
class PersonInfoPresenter(model: PersonInfoContract.Model) :
        BasePresenter<PersonInfoContract.View, PersonInfoContract.Model>(model) , PersonInfoContract.Presenter{

    companion object{
        val TAG = "PersonInfoPresenter"
    }


    override fun requestData() {
    }

    override fun delFriend(friendUserId: Int) {

        mModel.delFriend(friendUserId, object: NetObserver<DelFriendBean.DataBean>(DelFriendBean.DataBean::class.java){
            override fun onSuccess(m: DelFriendBean.DataBean) {

                mRootView.delFriendSuccess(m)
            }

            override fun onFail(code: Int, msg: String?) {
                mRootView.delFriendFail(code, msg!!)

            }

            override fun onError(msg: String?) {
            }

        })


    }


}