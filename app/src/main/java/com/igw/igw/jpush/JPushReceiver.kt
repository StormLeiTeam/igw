package com.igw.igw.jpush

import android.content.Context
import android.content.Intent
import cn.jpush.android.api.NotificationMessage
import cn.jpush.android.service.JPushMessageReceiver
import com.igw.igw.MainActivity
import com.igw.igw.utils.AppUtils
import com.igw.igw.utils.LogUtils
import org.json.JSONException
import org.json.JSONObject

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */
class JPushReceiver : JPushMessageReceiver() {




    companion object{


        val TAG =  "JPushReceiver"
    }


    var Activity: String = ""



    /**
     * 通知栏消息到达的回调
     */
    override fun onNotifyMessageArrived(p0: Context?, p1: NotificationMessage?) {
        super.onNotifyMessageArrived(p0, p1)
        LogUtils.d(TAG,"推送消息 通知栏消息到达 --> onNotifyMessageArrived")
    }


    /**
     *  通知栏消息被打开时候的回调
     */
    override fun onNotifyMessageOpened(context: Context?, notificationMessage: NotificationMessage?) {
        super.onNotifyMessageOpened(context, notificationMessage)

        LogUtils.d(TAG,"推送消息 通知栏消息被打开 --> onNotifyMessageOpened")

        try {
            val actionData = notificationMessage?.notificationExtras

            var info = JSONObject(actionData)

//            var type = info.opt("type")
//            var content = info.get("val")

//            LogUtils.d(TAG, "推动获取的消息 type -> $type   content --->  $content")


            setUpAction(context!!)

        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }

    private fun setUpAction(context: Context) {
        val flage = AppUtils.isExistMainActivity(context, MainActivity::class.java)


        if (flage){
            var intent = Intent(context, MainActivity::class.java)

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("message_friend_add", 1)
            context.startActivity(intent)
        }else{

            var intent = Intent(context, MainActivity::class.java)

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("message_friend_add", 1)
            context.startActivity(intent)
        }

    }


}