package com.igw.igw.modoule.im.view

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.igw.igw.R
import com.igw.igw.utils.StatusBarUtils
import com.igw.igw.widget.storm.StatusBarView
import io.rong.imkit.fragment.ConversationFragment
import io.rong.imlib.RongIMClient
import kotlinx.android.synthetic.main.common_status_bar.*


/**
 * 聊天室
 *
 */
class GroupChatRoomActivity : AppCompatActivity() {


    companion object {

        val TAG = "GroupChatRoomActivity"


        public fun startSelfOfIntent(activity: Activity, chatRoomId: String, chatRoomName: String) {


//            val group = Conversation.ConversationType.GROUP

            val defMessageCount = 50

            RongIMClient.getInstance().joinExistChatRoom(chatRoomId, defMessageCount, object : RongIMClient.OperationCallback() {
                /**
                 * 成功回调
                 */
                override fun onSuccess() {


                }

                /**
                 * 失败回调
                 * @param errorCode 错误码
                 */
                override fun onError(errorCode: RongIMClient.ErrorCode) {}
            })


        }
    }


    private var chatRoomId: String? = null
    private var chatRoomName: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_chat_room)

        StatusBarUtils.transparentBarOrFitSystemWindow(this)
        StatusBarUtils.setDarkMode(this)

        status_bar_main.setTitle("")
        status_bar_main.setTitleTextSize(16f)
        status_bar_main.setTitleTextColor(R.color.black_000000)

        setUpFragment()


        intent.data?.let {
            var title = it.getQueryParameter("title")
            status_bar_main.setTitle(title ?: "")
        }



        setUpListener()

    }


    private fun setUpListener() {


        status_bar_main.setOnBackClickListener(object : StatusBarView.OnBackClickListener{
            override fun onClick() {


                finish()
            }


        })
    }

    private fun setUpFragment() {

        val conversationFragment = ConversationFragment()
        val manager = supportFragmentManager
        val bt = manager.beginTransaction()
        bt.replace(R.id.container, conversationFragment)
        bt.commit()


    }
}
