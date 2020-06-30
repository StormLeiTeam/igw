package com.igw.igw.modoule.im.view

import android.view.View
import com.igw.igw.MainActivity
import com.igw.igw.R
import com.igw.igw.bean.login.UserInfoBean
import com.igw.igw.fragment.BaseMvpDataFragment
import com.igw.igw.modoule.im.ChatTypeContract
import com.igw.igw.modoule.im.model.ChatModel
import com.igw.igw.modoule.im.presenter.ChatPresenter
import com.igw.igw.utils.LogUtils
import com.shengshijingu.yashiji.common.util.ToastUtil
import io.rong.imlib.model.Conversation
import kotlinx.android.synthetic.main.common_status_bar.*
import kotlinx.android.synthetic.main.fragment_chattype.*

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


        // 商务聊天
        ll_business_chat.setOnClickListener {

            roomId?.let {
                if (it.isNotEmpty()){
                    mPresenter?.createChatRoom(it
                            , "商务聊天室")

                }

            }

        }

        ll_public_chat.setOnClickListener {

            roomId?.let {

                if (it.isNotEmpty()){
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

    }

    override fun createSuccessChatRoom(type: String) {


        when (type) {

            publicChatRoom -> {
                // 公共聊天室 创建成功

                activity?.let {

                    SingleChatActivity.startSelfOfIntent(it, publicChatRoom, "公共聊天室", Conversation.ConversationType.GROUP)

                }


            }

            bussiessChatRoom -> {
                // 商务聊天室创建成功

                activity?.let {

                    SingleChatActivity.startSelfOfIntent(it, bussiessChatRoom, "商务聊天室", Conversation.ConversationType.GROUP)

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


        updateView(this.userType!!)

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
                ll_public_chat.visibility =View.GONE
                ll_business_chat.visibility = View.VISIBLE

            }


        }
    }

    override fun userInfoFail(code: Int, msg: String) {

        ToastUtil.showCenterToast(mContext,"获取聊天信息失败")

    }

    override fun fail(o: Any?) {
    }

    override fun success(o: Any?) {
    }

}