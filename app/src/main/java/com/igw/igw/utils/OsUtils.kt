package com.igw.igw.utils

import android.app.ActivityManager
import android.content.Context

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */
object OsUtils {


    fun getProgressName(context: Context, pid: Int): String? {
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runingApps = am.runningAppProcesses ?: return null
        for (runingApp in runingApps) {
            if (runingApp.pid == pid) {
                return runingApp.processName
            }
        }
        return null
    }
}