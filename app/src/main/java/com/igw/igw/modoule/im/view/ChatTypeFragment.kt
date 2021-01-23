package com.igw.igw.modoule.im.view

import android.view.View
import com.igw.igw.MainActivity
import com.igw.igw.R
import com.igw.igw.bean.login.UserInfoBean
import com.igw.igw.fragment.BaseMvpDataFragment
import com.igw.igw.modoule.im.ChatTypeContract
import com.igw.igw.modoule.im.model.ChatModel
import com.igw.igw.modoule.im.presenter.ChatPresenter
import com.igw.igw.modoule.login.loginstate.LoginManager
import com.igw.igw.utils.LogUtils
import com.igw.igw.widget.storm.StatusBarView
import com.itingchunyu.badgeview.BadgeTextView
import com.shengshijingu.yashiji.common.Constants
import com.shengshijingu.yashiji.common.util.ToastUtil
import io.rong.imlib.RongIMClient
import io.rong.imlib.model.Conversation
import kotlinx.android.synthetic.main.common_status_bar.*
import kotlinx.android.synthetic.main.fragment_chattype.*
import kotlin.math.log


/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */
class ChatTypeFragment : BaseMvpDataFragment<ChatPresenter>(), ChatTypeContract.View {


    companion object {


        val publicChatRoom = "0001"
        val bussiessChatRoom = "0002"

        val TAG = "ChatTypeFragment"


        public fun getInstance(): ChatTypeFragment = ChatTypeFragment()

    }

    private lateinit var mPublicBadge: BadgeTextView;
    private lateinit var mBussessBadge: BadgeTextView;
    private lateinit var mRecentBadge: BadgeTextView;

    override fun initPresenter() {

        mPresenter = ChatPresenter(ChatModel())
        mPresenter?.attachView(this)
    }

    override fun getLayoutId() = R.layout.fragment_chattype

    override fun onReloadData(type: Int) {
    }

    override fun onClickImageReload(): Boolean {
        return false
    }

    override fun initView() {

//        StatusBarUtils.setDarkMode(activity.t)
        onFirstLoadSuccess()

    }

    override fun initViews() {
        super.initViews()


        setUpView()

        setUpListener()
    }

    private fun setUpListener() {

        status_bar_main.setOnConfirmClickListener(object :StatusBarView.OnConfirmClickListener{
            override fun onClick() {

                (mContext as MainActivity).changeLanuage(R.id.ll_main_message)
            }
        })

        // 商务聊天
        ll_business_chat.setOnClickListener {

            roomId?.let {
                if (it.isNotEmpty()) {
                    mPresenter?.createChatRoom(it
                            , "商务聊天室")

                }

            }

        }

        ll_public_chat.setOnClickListener {

            roomId?.let {

                if (it.isNotEmpty()) {
                    mPresenter?.createChatRoom(it, "公共聊天室")

                }
            }
        }

        ll_recent_chat.setOnClickListener {

            activity?.let {
                RecentChatActivity.startSelf(it)

            }


        }
    }


    private fun setUpView() {

        LogUtils.d(TAG, "atcitiy  -->  ${status_bar_main == null}")

        status_bar_main.setTitle(resources.getString(R.string.title_chat_type))
        status_bar_main.setTitleTextSize(16F)
        if (activity is MainActivity) {
            status_bar_main.setBackImageVisible(View.GONE)

        } else {

            status_bar_main.setBackImageVisible(View.VISIBLE)

        }
        status_bar_main.setConfirmVisible(View.VISIBLE)
        status_bar_main.setConfirmText("中/En")
        status_bar_main.setConfirmTextColor(R.color.black_000000)
        status_bar_main.setConfirmTextSize(15F)

//        mPresenter?.userInfo()


        mPublicBadge = BadgeTextView(mContext)
        mPublicBadge.setTargetView(iv_public_chat)
        mPublicBadge.setBadgeShown(false)

        mBussessBadge = BadgeTextView(mContext)
        mBussessBadge.setTargetView(iv_business_chat)
        mBussessBadge.setBadgeShown(false)

        mRecentBadge = BadgeTextView(mContext)
        mRecentBadge.setTargetView(iv_recent_chat)
        mRecentBadge.setBadgeShown(false)


    }

    override fun createSuccessChatRoom(type: String) {

        LogUtils.d(tag, "获取创建好的 room $type")

        when (type) {

            publicChatRoom -> {
                // 公共聊天室 创建成功

                activity?.let {

                    SingleChatActivity.startSelfOfIntent(it, publicChatRoom, "公共聊天室", Conversation.ConversationType.CHATROOM)


//                    GroupChatRoomActivity.startSelfOfIntent(it, publicChatRoom,"公共聊天室")
//                    val chatroomId = "聊天室 ID"
//                    val defMessageCount = 50
//
//                    RongIMClient.getInstance().joinChatRoom(publicChatRoom, defMessageCount, object : RongIMClient.OperationCallback() {
//                        /**
//                         * 成功回调
//                         */
//                        override fun onSuccess() {
//
//                            LogUtils.d(TAG,"进入聊天室成功")
//
//                        }
//
//                        /**
//                         * 失败回调
//                         * @param errorCode 错误码
//                         */
//                        override fun onError(errorCode: RongIMClient.ErrorCode) {
//                            LogUtils.d(TAG,"进入聊天室失")
//
//                        }
//                    })


//                    SingleChatActivity.startSelfOfIntent(it, "5555", "公共聊天室", Conversation.ConversationType.GROUP)

                }


            }

            bussiessChatRoom -> {
                // 商务聊天室创建成功

                activity?.let {

                    SingleChatActivity.startSelfOfIntent(it, bussiessChatRoom, "商务聊天室", Conversation.ConversationType.CHATROOM)

                }


            }

        }
    }


    override fun onResume() {
        super.onResume()

        mPresenter?.userInfo()

    }

    override fun createFailChatRoom(type: String, code: Int, msg: String) {


        when (type) {

            publicChatRoom -> {
                // 公共聊天室 创建成功


                ToastUtil.showCenterToast(mContext, "公共聊天室进入失败")


            }

            bussiessChatRoom -> {
                // 商务聊天室创建成功

                ToastUtil.showCenterToast(mContext, "商务聊天室进入失败")


            }

        }
    }


    private var userType: Int? = null
    private var roomId: String? = null


    override fun userInfoSuccessful(data: UserInfoBean.DataBean) {

        this.userType = data.userType
        this.roomId = data.roomId

        LoginManager.instance.updateRongUserInfo("${data.id}", data.nickName, data.headImage)

        updateView(this.userType!!)




    }

    private fun setUnreadCount(roomId: String, userType: Int) {


        when (userType) {
            0 -> {


                RongIMClient.getInstance().getUnreadCount(Conversation.ConversationType.CHATROOM, roomId, object : RongIMClient.ResultCallback<Int>() {
                    override fun onSuccess(p0: Int?) {


                        LogUtils.d(TAG, "获取的未读聊天 公共 -- $p0")
                        p0?.let {
                            if (it != 0) {
                                mPublicBadge.setBadgeCount(it)
                                mPublicBadge.setBadgeShown(true)

                            } else {
                                mPublicBadge.setBadgeShown(false)

                            }
                        }
                    }

                    override fun onError(p0: RongIMClient.ErrorCode?) {
                    }


                })

            }


            1 -> {


                RongIMClient.getInstance().getUnreadCount(Conversation.ConversationType.CHATROOM, roomId, object : RongIMClient.ResultCallback<Int>() {
                    override fun onSuccess(p0: Int?) {

                        p0?.let {
                            if (it != 0) {
                                mBussessBadge.setBadgeCount(it)
                                mBussessBadge.setBadgeShown(true)

                            } else {

                                mBussessBadge.setBadgeShown(false)

                            }
                        }
                    }

                    override fun onError(p0: RongIMClient.ErrorCode?) {
                    }


                })


            }
        }





        RongIMClient.getInstance().getTotalUnreadCount(object : RongIMClient.ResultCallback<Int>() {
            override fun onSuccess(p0: Int?) {
                LogUtils.d(TAG, "获取的未读聊天 最近 -- $p0")

                p0?.let {
                    if (it != 0) {
                        mRecentBadge.setBadgeCount(it)
                        mRecentBadge.setBadgeShown(true)

                    } else {
                        mRecentBadge.setBadgeShown(false)
                    }
                }
            }

            override fun onError(p0: RongIMClient.ErrorCode?) {
            }


        })


    }

    private fun updateView(userType: Int) {

        when (userType) {


            0 -> {

                // 普通会员

                ll_public_chat.visibility = View.VISIBLE
                ll_business_chat.visibility = View.GONE


            }

            1 -> {

                // 商务会员
                ll_public_chat.visibility = View.GONE
                ll_business_chat.visibility = View.VISIBLE

            }


        }


    }

    override fun userInfoFail(code: Int, msg: String) {

        ToastUtil.showCenterToast(mContext, "获取聊天信息失败")

    }

    override fun fail(o: Any?) {
    }

    override fun success(o: Any?) {
    }

}