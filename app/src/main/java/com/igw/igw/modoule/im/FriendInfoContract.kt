package com.igw.igw.modoule.im

import com.igw.igw.bean.chat.DelFriendBean
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
class FriendInfoContract {


    interface View : IBaseView {

        fun delFriendSuccess(data: DelFriendBean.DataBean)
        fun delFriendFail(code: Int, msg: String)


    }


    interface Model : IBaseModel {
        fun delFriend(friendUserId: Int, observer: NetObserver<DelFriendBean.DataBean>)


    }


    interface Presenter : IBasePresenter {

        fun delFriend(friendUserId: Int)


    }
}