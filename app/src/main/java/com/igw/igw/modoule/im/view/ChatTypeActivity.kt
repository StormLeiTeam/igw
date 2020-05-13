package com.igw.igw.modoule.im.view

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.igw.igw.R
import com.igw.igw.activity.BaseActivity
import com.igw.igw.modoule.im.ChatTypeContract
import com.igw.igw.modoule.im.model.ChatModel
import com.igw.igw.modoule.im.presenter.ChatPresenter
import com.igw.igw.utils.StatusBarUtils
import com.shengshijingu.yashiji.common.util.ToastUtil
import kotlinx.android.synthetic.main.activity_chat_type.*
import kotlinx.android.synthetic.main.common_status_bar.*

class ChatTypeActivity : BaseActivity<ChatPresenter>() , ChatTypeContract.View  {


    companion object{

        val TAG = "ChatTypeActivity"



        val publicChatRoom = "0001"
        val bussiessChatRoom = "0002"
        fun startSelf(activity: Activity){

            var  intent = Intent(activity,ChatTypeActivity::class.java)

            activity.startActivity(intent)
        }
    }



//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_chat_type)
//
//
//        setUpView()
//
//        setUpListener()
//
//
//
//
//
//
//    }

    private fun setUpView() {



        StatusBarUtils.setDarkMode(this)

        status_bar_main.setTitle(resources.getString(R.string.title_chat_type))
        status_bar_main.setTitleTextSize(16F)
        status_bar_main.setConfirmVisible(View.VISIBLE)
        status_bar_main.setConfirmText("中/En")
        status_bar_main.setConfirmTextColor(R.color.black_000000)
        status_bar_main.setConfirmTextSize(15F)
    }

    private fun setUpListener() {


        // 商务聊天
        ll_business_chat.setOnClickListener {

        }

        ll_public_chat.setOnClickListener {

            mPresenter.createChatRoom("0001","公共聊天室")
        }

        ll_recent_chat.setOnClickListener {

            RecentChatActivity.startSelf(this)


        }
    }

    override fun initView() {
        setUpView()

        setUpListener()


    }

    override fun initPresenter() {
        mPresenter = ChatPresenter(ChatModel())
        mPresenter.attachView(this)
    }

    override fun getLayoutId(): Int = R.layout.activity_chat_type

    override fun setTitle(): String {
        return ""
    }


    override fun setRightButton(): String {

        return ""
    }

    override fun setStatusBarColor(): Boolean {
        return false
    }

    override fun createSuccessChatRoom(type: String) {

       when(type){

           publicChatRoom -> {
               // 公共聊天室 创建成功


               GroupChatRoomActivity.startSelfOfIntent(this, publicChatRoom,"公共聊天室")



           }

           bussiessChatRoom -> {
               // 商务聊天室创建成功
               GroupChatRoomActivity.startSelfOfIntent(this, bussiessChatRoom,"商务聊天室")


           }

       }

    }

    override fun createFailChatRoom(type: String, code: Int, msg: String) {
        when(type){

            publicChatRoom -> {
                // 公共聊天室 创建成功


            ToastUtil.showCenterToast(this,"公共聊天室进入失败")


            }

            bussiessChatRoom -> {
                // 商务聊天室创建成功

                ToastUtil.showCenterToast(this,"商务聊天室进入失败")


            }

        }


    }

    override fun fail(o: Any?) {
    }

    override fun success(o: Any?) {
    }
}
