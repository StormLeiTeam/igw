package com.igw.igw.modoule.im.view

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.igw.igw.R
import io.rong.imkit.RongIM
import io.rong.imkit.fragment.ConversationFragment
import io.rong.imlib.model.Conversation


/**
 * 聊天室
 *
 */
class GroupChatRoomActivity : AppCompatActivity() {


    companion object{

        val TAG =  "GroupChatRoomActivity"


        public fun startSelfOfIntent(activity: Activity,chatRoomId:String,chatRoomName:String ){



            var intent = Intent(activity,GroupChatRoomActivity::class.java)
            intent.putExtra("chatRoomId", chatRoomId)
            intent.putExtra("chatRoomName", chatRoomName)

            activity.startActivity(intent)


        }
    }


    private var chatRoomId : String? = null
    private var chatRoomName:String?= null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_chat_room)

        setUpFragment()
        chatRoomId = intent.getStringExtra("chatRoomId")
        chatRoomName = intent.getStringExtra("chatRoomName")


        val group = Conversation.ConversationType.GROUP

        RongIM.getInstance().startConversation(this,group,chatRoomId,chatRoomName,null)


    }

    private fun setUpFragment() {

        val conversationFragment = ConversationFragment()
        val manager = supportFragmentManager
        val bt = manager.beginTransaction()
        bt.replace(R.id.container, conversationFragment)
        bt.commit()


    }
}
