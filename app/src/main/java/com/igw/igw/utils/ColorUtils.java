package com.igw.igw.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;


/**
 * 创建时间  2019/5/2911:22 AM .
 *
 * 作者  雷雷
 */

public class ColorUtils {

    public static int getTextColor(Context context, int color) {
        return context.getResources().getColor(color);
    }

    public static Drawable getDrawable(Context context, int color) {
        return context.getResources().getDrawable(color);
    }

}
