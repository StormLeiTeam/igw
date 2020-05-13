package com.igw.igw.modoule.im.view

import android.view.View
import com.igw.igw.MainActivity
import com.igw.igw.R
import com.igw.igw.fragment.BaseMvpDataFragment
import com.igw.igw.modoule.im.ChatTypeContract
import com.igw.igw.modoule.im.model.ChatModel
import com.igw.igw.modoule.im.presenter.ChatPresenter
import com.igw.igw.utils.LogUtils
import com.shengshijingu.yashiji.common.util.ToastUtil
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


      public  fun getInstance(): ChatTypeFragment = ChatTypeFragment()

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

            mPresenter?.createChatRoom(bussiessChatRoom, "商务聊天室")

        }

        ll_public_chat.setOnClickListener {

            mPresenter?.createChatRoom(publicChatRoom, "公共聊天室")
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
        if (activity is MainActivity){
            status_bar_main.setBackImageVisible(View.GONE)

        }else{

            status_bar_main.setBackImageVisible(View.VISIBLE)

        }
        status_bar_main.setConfirmVisible(View.VISIBLE)
        status_bar_main.setConfirmText("中/En")
        status_bar_main.setConfirmTextColor(R.color.black_000000)
        status_bar_main.setConfirmTextSize(15F)

    }

    override fun createSuccessChatRoom(type: String) {


        when (type) {

            publicChatRoom -> {
                // 公共聊天室 创建成功

                activity?.let {

                    GroupChatRoomActivity.startSelfOfIntent(it, publicChatRoom, "公共聊天室")

                }


            }

            bussiessChatRoom -> {
                // 商务聊天室创建成功

                activity?.let {

                    GroupChatRoomActivity.startSelfOfIntent(it, bussiessChatRoom, "商务聊天室")

                }


            }

        }
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

    override fun fail(o: Any?) {
    }

    override fun success(o: Any?) {
    }

}