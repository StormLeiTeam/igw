package com.igw.igw.modoule.im.view

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.igw.igw.R
import io.rong.imkit.RongIM
import io.rong.imkit.fragment.ConversationFragment
import io.rong.imlib.model.Conversation
import io.rong.push.RongPushClient

/**
 * 单聊会话界面
 */
class SingleChatActivity : AppCompatActivity() {


    companion object{

        val TAG =  "SingleChatActivity"

        public fun  startSelfOfIntent(activity: Activity,friendId:String,friendName:String ){

            val intent = Intent(activity,SingleChatActivity::class.java)
            intent.putExtra("friendId",friendId)
            intent.putExtra("friendName",friendName)
            activity.startActivity(intent)
        }
    }


    private var friendName : String? = null
    private var friendId:String ?= null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_chat)
        setUpFragment()

        startChat()
    }

    private fun startChat() {


     var  conversationType  = Conversation.ConversationType.PRIVATE;

        friendId = intent.getStringExtra("friendId")
        friendName = intent.getStringExtra("friendName")

        RongIM.getInstance().startConversation(this , conversationType, friendId, friendName, null)

    }

    private fun setUpFragment() {


        val conversationFragment = ConversationFragment()

        val ma = supportFragmentManager

        val bt = ma.beginTransaction()
        bt.replace(R.id.container, conversationFragment)
        bt.commit()


    }
}
