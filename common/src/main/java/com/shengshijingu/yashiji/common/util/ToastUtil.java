package com.shengshijingu.yashiji.common.util;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.dovar.dtoast.DToast;
import com.dovar.dtoast.inner.IToast;
import com.shengshijingu.yashiji.common.R;

/**
 * 兼容系统版本的Toast  防止一些手机拒绝权限后，Toast无法提示
 */

public class ToastUtil {

    public static void showToast(Context context, String msg) {
//        if (context == null) {
//            return;
//        }
//        if (msg == null) {
//            return;
//        }
//        //使用默认布局
//        DToast.make(context)
//                .setText(R.id.tv_content_default, msg)
//                .setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 100)
//                .show();
        showCenterToast(context, msg);
    }

    public static void showCenterToast(Context context, String msg) {
        if (context == null) {
            return;
        }
        if (msg == null) {
            return;
        }
        //通过 setView()设置自定义的 Toast 布局
        //使用默认布局
        DToast.make(context)
                .setText(R.id.tv_content_default, msg)
                .setGravity(Gravity.CENTER, 0, 0)
                .show();
    }

    //退出APP时调用
    public static void cancelAll() {
        DToast.cancel();
    }


}
