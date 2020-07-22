package com.igw.igw.modoule.im.view

import android.app.Activity
import android.app.Person
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.sax.StartElementListener
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.google.gson.Gson
import com.igw.igw.R
import com.igw.igw.activity.BaseActivity
import com.igw.igw.bean.chat.AddFriendBean
import com.igw.igw.bean.chat.ChatRoomUsesBean
import com.igw.igw.bean.login.UserInfoBean
import com.igw.igw.modoule.im.ChatRoomMembersContract
import com.igw.igw.modoule.im.adapter.ChatRoomAdapter
import com.igw.igw.modoule.im.model.ChatRoomMembersModel
import com.igw.igw.modoule.im.presenter.ChatRoomMembersPresenter
import com.igw.igw.modoule.login.loginstate.LoginManager
import com.igw.igw.utils.GsonUtils
import com.igw.igw.utils.LocaleUtils
import com.igw.igw.utils.LogUtils
import com.igw.igw.utils.StatusBarUtils
import com.igw.igw.widget.storm.StatusBarView
import com.shengshijingu.yashiji.common.util.ToastUtil
import io.rong.imlib.RongIMClient
import kotlinx.android.synthetic.main.activity_chat_room_members.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.common_status_bar.*

/**
 * 聊天室成员
 */
class ChatRoomMembersActivity : BaseActivity<ChatRoomMembersPresenter>(), ChatRoomMembersContract.View {


    companion object {
        val TAG = "ChatRoomMembersActivity"


        fun startSelf(activity: Activity, chatRoomId: String) {


            var intent = Intent(activity, ChatRoomMembersActivity::class.java)
            intent.putExtra("chatRoomId", chatRoomId)
            activity.startActivity(intent)


        }
    }


    private lateinit var targetId: String

    private var isAdministrator = false //是否是管理员  true 是 / false 否
    private lateinit var mAdapter: ChatRoomAdapter


    override fun initView() {


        StatusBarUtils.setDarkMode(this)

        status_bar_main.setTitle("公共聊天室成员(0)")
        status_bar_main.setTitleTextSize(16F)
        status_bar_main.setConfirmVisible(View.VISIBLE)
        status_bar_main.setConfirmText("中/En")
        status_bar_main.setConfirmTextColor(R.color.black_000000)
        status_bar_main.setConfirmTextSize(15F)

        intent.getStringExtra("chatRoomId")?.let {
            targetId = it
        }

        initAdapter()
        setUpListener()
        mPresenter.userInfo()
        showLoadingText()


    }

    private fun setUpListener() {

        et_search.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent): Boolean {
                if (actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {


                    if (event.keyCode == KeyEvent.KEYCODE_ENTER) {

                        LogUtils.d(TAG, " 获取 点击了回车事件 ")
                        var content = et_search.text.toString()
                        getMembersForNet(content)
                        return true
                    }

                }

                return false
            }


        })

//        et_search.addTextChangedListener(object : TextWatcher{
//            override fun afterTextChanged(s: Editable?) {
//
//
//                var toString = s.toString()
//
//                LogUtils.d(TAG, "获取值 -- > $toString")
//
//
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//
//
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//
//
//
//            }
//
//
//        })


        status_bar_main.setOnConfirmClickListener(object : StatusBarView.OnConfirmClickListener {
            override fun onClick() {

                LocaleUtils.changeLocale(this@ChatRoomMembersActivity, "chatRoomId", targetId)
            }


        })
    }

    private fun initAdapter() {


        mAdapter = ChatRoomAdapter(this, false)

        var layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        rv_members.layoutManager = layoutManager
        rv_members.adapter = mAdapter



        mAdapter.onItemClickListener(object : ChatRoomAdapter.OnItemClickListener {
            override fun onItemClick(bean: ChatRoomUsesBean.DataBean.RoomUsersBean, position: Int) {
                // 跳转到 信息

                PersonInfoActivity.startSelf(this@ChatRoomMembersActivity, GsonUtils.instance.toJson(bean))


            }

            override fun onAddFriend(bean: ChatRoomUsesBean.DataBean.RoomUsersBean, position: Int) {

                val userId = LoginManager.instance.userId()

                if (userId.isNotEmpty() && userId == "${bean.userId}") {

                    ToastUtil.showCenterToast(this@ChatRoomMembersActivity, resources.getString(R.string.no_add_self))
                    return;
                }
                mPresenter.addFriend(bean.userId)

            }

            override fun onBanned(bean: ChatRoomUsesBean.DataBean.RoomUsersBean, position: Int) {
            }

            override fun onUnbanned(bean: ChatRoomUsesBean.DataBean.RoomUsersBean, position: Int) {
            }
        })

    }

    /**
     * 网络请求获取群成员
     */
    private fun getMembersForNet(nickName: String) {

        LogUtils.d(TAG, "targetid -> $targetId   nickname -> $nickName")
        mPresenter.chatroomUsers(targetId, nickName)


    }

    override fun initPresenter() {

        mPresenter = ChatRoomMembersPresenter(ChatRoomMembersModel())
        mPresenter.attachView(this)


    }

    override fun getLayoutId(): Int = R.layout.activity_chat_room_members

    override fun setTitle(): String {

        return ""
    }

    override fun setRightButton(): String {
        return ""
    }

    override fun setStatusBarColor(): Boolean {
        return false
    }

    override fun onSuccessChatRoomUser(data: ChatRoomUsesBean.DataBean) {

        // 获取成功
        status_bar_main.setTitle("公共聊天室成员(${data.roomUsers.size})")

        mAdapter.refreshData(data.roomUsers)
        hideLoadingText()
    }

    override fun onFailChatRoomUser(code: Int, msg: String) {

        hideLoadingText()
        ToastUtil.showCenterToast(this, msg)

    }

    override fun userInfoSuccessful(data: UserInfoBean.DataBean) {

        LogUtils.d(TAG, "获取个人信心 成功")

        val userType = data.userType


        isAdministrator = userType == 1

        mAdapter.userType = data.userType

        getMembersForNet("")


    }

    override fun userInfoFail(code: Int, msg: String) {
        hideLoadingText()
        ToastUtil.showCenterToast(this, msg)

    }

    override fun addFriendSuccess(data: AddFriendBean.DataBean) {
        ToastUtil.showCenterToast(this, "添加好友已经发送")
        getMembersForNet("")

    }

    override fun addFriendFail(code: Int, msg: String) {
        ToastUtil.showCenterToast(this, msg)

    }

    override fun fail(o: Any?) {
    }

    override fun success(o: Any?) {
    }

}