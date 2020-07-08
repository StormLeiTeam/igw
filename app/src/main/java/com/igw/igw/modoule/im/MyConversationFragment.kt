package com.igw.igw.modoule.im

import android.content.Context
import android.os.Bundle
import android.view.View
import com.igw.igw.modoule.im.view.SingleChatActivity
import com.igw.igw.utils.LogUtils
import com.igw.igw.utils.MImageGetter
import io.rong.imkit.RongIM
import io.rong.imkit.fragment.ConversationFragment
import io.rong.imlib.model.Conversation
import io.rong.imlib.model.Message
import io.rong.imlib.model.UserInfo
import io.rong.push.RongPushClient

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */
class MyConversationFragment : ConversationFragment(){


    companion object{

        val TAG  =  "MyConversationFragment"
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as SingleChatActivity).setConversionType(conversationType)

        LogUtils.d(TAG,"获取的房间号码 $targetId")
//        (activity as SingleChatActivity).setConversionType()

        setUpListener()
    }



    public  fun getConversionType() : Conversation.ConversationType{


        return conversationType;
    }





    private fun setUpListener() {

//        conversationTyp


//        RongIM.setConversationClickListener(object : RongIM.ConversationClickListener{
//            override fun onUserPortraitLongClick(p0: Context?, type: Conversation.ConversationType?, p2: UserInfo?, p3: String?): Boolean {
//
//                when (type) {
//
//                    Conversation.ConversationType.CHATROOM -> {
//                        LogUtils.d(SingleChatActivity.TAG, "获取当前对话的类型 --> 聊天室")
//
//
//                    }
//                    Conversation.ConversationType.PRIVATE -> {
//                        LogUtils.d(SingleChatActivity.TAG, "获取当前对话的类型 --> 单聊")
//
//
//                    }
//
//
//                }
//                return false
//            }
//
//            override fun onMessageLinkClick(p0: Context?, p1: String?, p2: Message?): Boolean {
//                return false
//            }
//
//            override fun onMessageLongClick(p0: Context?, p1: View?, p2: Message?): Boolean {
//                return false
//            }
//
//            override fun onUserPortraitClick(p0: Context?, p1: Conversation.ConversationType?, p2: UserInfo?, p3: String?): Boolean {
//                return false
//            }
//
//            override fun onMessageClick(p0: Context?, p1: View?, p2: Message?): Boolean {
//                return false
//            }
//        })
    }
}