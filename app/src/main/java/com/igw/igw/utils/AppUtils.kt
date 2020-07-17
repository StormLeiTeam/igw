package com.igw.igw.utils

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import com.igw.igw.app.IGWApplication

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */

object AppUtils {

    val TAG = "AppUtils";

    val appContext: Context
        get() = IGWApplication.getContext()


    /**
     * 判断 应用是否开启
     *
     * @param context
     * @param activity
     * @return
     */
    fun isExistMainActivity(context: Context, activity: Class<*>?): Boolean {
        val intent = Intent(context, activity)
        val cmpName = intent.resolveActivity(context.packageManager)
        var flag = false
        if (cmpName != null) {
            val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val taskInfoList = am.getRunningTasks(10)
            val runTaskInfo = taskInfoList[0]
            val numActivities = taskInfoList[0].numActivities
            for (runningTaskInfo in taskInfoList) {
                if (runningTaskInfo.baseActivity == cmpName) {
                    flag = true
                    break
                }
            }
        }
        return false
    }


}