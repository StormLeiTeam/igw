package com.igw.igw.modoule.im.view

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.View
import com.igw.igw.R
import com.igw.igw.modoule.im.MyConversationFragment
import com.igw.igw.utils.LogUtils
import com.igw.igw.utils.StatusBarUtils
import com.igw.igw.widget.storm.MorePopWindow
import com.igw.igw.widget.storm.StatusBarView
import io.rong.imkit.RongIM
import io.rong.imkit.fragment.ConversationFragment
import io.rong.imlib.model.Conversation
import io.rong.imlib.model.Message
import io.rong.imlib.model.UserInfo
import kotlinx.android.synthetic.main.common_status_bar.*
import kotlinx.android.synthetic.main.status_bar_view.view.*

/**
 * 单聊会话界面
 */
class SingleChatActivity : FragmentActivity(), MorePopWindow.OnPopWindowItemClickListener {


    companion object {

        val TAG = "SingleChatActivity"

        public fun startSelfOfIntent(activity: Activity, chatId: String, chatName: String, type: Conversation.ConversationType) {


            var conversationType = type




            LogUtils.d(TAG, "获取  friendID $chatId he  name --> $chatName")
            RongIM.getInstance()?.startConversation(activity, conversationType, chatId, chatName)

        }
    }


    private var friendName: String? = null
    private var friendId: String? = null
    private var conversionType: Conversation.ConversationType = Conversation.ConversationType.PRIVATE
    private lateinit var conversationFragment: ConversationFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_chat)

        StatusBarUtils.transparentBarOrFitSystemWindow(this)
        StatusBarUtils.setDarkMode(this)
        status_bar_main.setTitle("")
        status_bar_main.setTitleTextSize(16f)
        status_bar_main.setTitleTextColor(R.color.black_000000)


        updateStatusBarViews();



        setUpFragment()

        intent.data?.let {
            val title = it.getQueryParameter("title")

            status_bar_main.setTitle(title ?: "")
        }



//        var type = intent.getStringExtra("conversation")
//        LogUtils.d(TAG, "type --- > $type")

        setUpListener()

    }

    private fun updateStatusBarViews() {


        if (conversionType == Conversation.ConversationType.PRIVATE) {

            status_bar_main.setShareVisible(View.GONE)


        } else {


            status_bar_main.setShareVisible(View.VISIBLE)

            status_bar_main.setShareBackground(R.drawable.more)
        }

    }

    private fun setUpListener() {


        status_bar_main.setOnShareClickListener(object : StatusBarView.OnShareClickListener {
            override fun onShare() {


                var popWindow = MorePopWindow(this@SingleChatActivity, this@SingleChatActivity)

                popWindow.showPopupWindow(status_bar_main.iv_share)

                LogUtils.d(TAG, "获取 嗲积极事件 ")

//                showPopupMenu()
            }

        })

        status_bar_main.setOnBackClickListener(object : StatusBarView.OnBackClickListener {
            override fun onClick() {


                finish()
            }


        })




        RongIM.setConversationClickListener(object : RongIM.ConversationClickListener {
            override fun onUserPortraitLongClick(p0: Context?, type: Conversation.ConversationType?, p2: UserInfo?, p3: String?): Boolean {

                when (type) {

                    Conversation.ConversationType.CHATROOM -> {
                        LogUtils.d(SingleChatActivity.TAG, "获取当前对话的类型 --> 聊天室")


                    }
                    Conversation.ConversationType.PRIVATE -> {
                        LogUtils.d(SingleChatActivity.TAG, "获取当前对话的类型 --> 单聊")


                    }


                }
                return false
            }

            override fun onMessageLinkClick(p0: Context?, p1: String?, p2: Message?): Boolean {
                return false
            }

            override fun onMessageLongClick(p0: Context?, p1: View?, p2: Message?): Boolean {
                return false
            }

            override fun onUserPortraitClick(p0: Context?, p1: Conversation.ConversationType?, p2: UserInfo?, p3: String?): Boolean {
                return false
            }

            override fun onMessageClick(p0: Context?, p1: View?, p2: Message?): Boolean {
                return false
            }
        })

    }

////    @SuppressLint("RestrictedApi")
//    private fun showPopupMenu() {
//
//        var popupMenu = PopupMenu(this, status_bar_main)
//        popupMenu.inflate(R.menu.im_menu)
//
//        popupMenu.setOnMenuItemClickListener {
//
//
//            when(it.itemId){
//
//                R.id.action_language -> {
//
//
//                    LogUtils.d(TAG,"点击了 切换语言")
//
//                }
//
//                R.id.action_users -> {
//
//                    LogUtils.d(TAG,"点击了 聊天室成员")
//
//                }
//            }
//
//            false
//
//
//        }
//
//
//    popupMenu.show()
//
//
////
//////
////        var field = popupMenu.javaClass.getDeclaredField("mPopup");
////        field.isAccessible = true;
////        var  helper =  field.get(popupMenu) as MenuPopupHelper;
////
////        helper.show(0, 0)
//
//
//
//    }


    private fun setUpFragment() {


         conversationFragment = MyConversationFragment()

        val ma = supportFragmentManager

        val bt = ma.beginTransaction()
        bt.replace(R.id.container, conversationFragment)
        bt.commit()


    }


    public fun setConversionType(type: Conversation.ConversationType) {


        this.conversionType = type

        updateStatusBarViews()
    }

    override fun languageSelector() {

        LogUtils.d(TAG, "点击切换了语言 ")



    }

    override fun groupMember() {

        LogUtils.d(TAG, "点击切换了群成员  房间id + ${conversationFragment.targetId} ")



        ChatRoomMembersActivity.startSelf(this, conversationFragment.targetId)


    }
}
