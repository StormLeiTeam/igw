package com.igw.igw.modoule.im

import com.igw.igw.bean.FriendBean
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

class MyFriendContract {


    interface View : IBaseView {


        fun onSuccessFriends(data: FriendBean.DataBean)
        fun onFailFriends(code: Int, msg: String)


    }

    interface Model : IBaseModel {


        fun getFriendsList(observer: NetObserver<FriendBean.DataBean>)

    }


    interface Presenter : IBasePresenter {

        fun getFriendsList()
    }
}