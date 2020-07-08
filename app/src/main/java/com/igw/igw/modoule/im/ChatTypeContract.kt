package com.igw.igw.modoule.im

import com.igw.igw.bean.chat.ChatRoomBean
import com.igw.igw.bean.chat.ChatRoomUsesBean
import com.igw.igw.bean.login.UserInfoBean
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
 * @Describe  聊天类型接口
 */
class ChatTypeContract {



    interface Model:IBaseModel{

        fun createChatRoom(chatRoomId: String, chatRoomName:String,observer: NetObserver<ChatRoomBean.DataBean>)

        fun destoryChatRoom(chatRoomId:String,observer: NetObserver<ChatRoomBean.DataBean>)


        fun chatRoomUsers(chatRoomId: String, nickName: String , observer: NetObserver<ChatRoomUsesBean>)

        fun userInfo(observer: NetObserver<UserInfoBean.DataBean>)


    }



    interface View: IBaseView{

        fun createSuccessChatRoom(type:String)

        fun createFailChatRoom(type: String, code:Int,msg:String)


        fun userInfoSuccessful(data: UserInfoBean.DataBean)

        fun userInfoFail(code: Int, msg: String)

    }

    interface Presenter:IBasePresenter{


        fun createChatRoom(chatRoomId: String, chatRoomName:String)

        fun destoryChatRoom(chatRoomId:String)


        fun chatRoomUsers(chatRoomId: String)


        fun  userInfo()

    }




}