package com.igw.igw.modoule.im.view

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.FragmentActivity
import com.igw.igw.R
import com.igw.igw.utils.StatusBarUtils
import com.igw.igw.widget.storm.StatusBarView
import io.rong.imkit.RongIM
import io.rong.imkit.fragment.ConversationListFragment
import io.rong.imlib.model.Conversation
import io.rong.push.RongPushClient
import kotlinx.android.synthetic.main.common_status_bar.*
import kotlinx.android.synthetic.main.status_bar_view.*

/**
 * 最近聊天
 */
class RecentChatActivity : FragmentActivity() {

    companion object{

        val TAG  = "RecentChatActivity"


        public fun startSelf(activity: Activity){

            var intent  = Intent(activity,RecentChatActivity::class.java)

            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recent_chat)


        StatusBarUtils.transparentBarOrFitSystemWindow(this)
        StatusBarUtils.setDarkMode(this)
        status_bar_main.setTitle(resources.getString(R.string.title_recent_chat))
        status_bar_main.setTitleTextColor(R.color.black_000000)
        status_bar_main.setTitleTextSize(16f)
        setUpFragment()

        initChat()

        setUpListener()
    }

    private fun setUpListener() {


        status_bar_main.setOnBackClickListener(object : StatusBarView.OnBackClickListener{
            override fun onClick() {


                finish()
            }


        })
    }

    private fun initChat() {
//
//        Map<String, Boolean> supportedConversation = new HashMap<>();
//       supportedConversation.put(RongPushClient.ConversationType.PRIVATE.getValue(), false)
//        RongIM.getInstance().startConversationList(Context , supportedConversation)
//
//
//        var supportedConversation : HashMap<String,Boolean> = HashMap<String , Boolean>()
//        supportedConversation[Conversation.ConversationType.PRIVATE.name] = false
//
//        RongIM.getInstance().startConversationList(this, supportedConversation)


//
    }

    private fun setUpFragment() {


        val chatFragment  =  ConversationListFragment()

        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()

        transaction.replace(R.id.container,chatFragment)
        transaction.commit()

    }
}
