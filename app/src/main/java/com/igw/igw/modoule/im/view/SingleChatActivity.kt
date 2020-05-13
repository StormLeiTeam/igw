package com.igw.igw.modoule.im.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import com.igw.igw.R
import com.igw.igw.utils.LogUtils
import com.igw.igw.utils.StatusBarUtils
import com.igw.igw.widget.storm.StatusBarView
import io.rong.imkit.RongIM
import io.rong.imkit.fragment.ConversationFragment
import io.rong.imlib.model.Conversation
import kotlinx.android.synthetic.main.common_status_bar.*
import kotlinx.android.synthetic.main.status_bar_view.*

/**
 * 单聊会话界面
 */
class SingleChatActivity : FragmentActivity() {


    companion object {

        val TAG = "SingleChatActivity"

        public fun startSelfOfIntent(activity: Activity, friendId: String, friendName: String) {


            var conversationType = Conversation.ConversationType.PRIVATE


            LogUtils.d(TAG, "获取  friendID $friendId he  name --> $friendName")
            RongIM.getInstance()?.startConversation(activity, conversationType, friendId, friendName, null)

        }
    }


    private var friendName: String? = null
    private var friendId: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_chat)

        StatusBarUtils.transparentBarOrFitSystemWindow(this)
        StatusBarUtils.setDarkMode(this)
        status_bar_main.setTitle("")
        status_bar_main.setTitleTextSize(16f)
        status_bar_main.setTitleTextColor(R.color.black_000000)


        setUpFragment()

        intent.data?.let {
            val title = it.getQueryParameter("title")

            status_bar_main.setTitle(title ?: "")
        }

        setUpListener()

    }

    private fun setUpListener() {


        status_bar_main.setOnBackClickListener(object :StatusBarView.OnBackClickListener{
            override fun onClick() {


                finish()
            }


        })
    }


    private fun setUpFragment() {


        val conversationFragment = ConversationFragment()

        val ma = supportFragmentManager

        val bt = ma.beginTransaction()
        bt.replace(R.id.container, conversationFragment)
        bt.commit()


    }
}
