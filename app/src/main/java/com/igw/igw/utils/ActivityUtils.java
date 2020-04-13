package com.igw.igw.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.igw.igw.activity.CommunicationLinkActivity;

/**
 * 创建时间  2020/3/111:29 PM .
 *
 * 作者  雷雷
 */

public class ActivityUtils {

    /**
     * 交流合作
     * @param activity
     */
    public static void startCommunicationLinkActivity(Activity activity) {
        Intent intent = new Intent(activity, CommunicationLinkActivity.class);
        activity.startActivity(intent);
    }

}
