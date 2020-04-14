package com.igw.igw.widget.storm;

import android.content.Context;
import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;


/**
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 * @Describe
 */
public class TextClickPrivacy extends ClickableSpan {

    public static String TAG = "TextClickPrivacy";

    private Context mContext;

    public TextClickPrivacy(Context context) {
        this.mContext = context;
    }

    @Override
    public void onClick(View widget) {

//        LogUtils.d(TAG,"执行 --->   ");
//        Intent intent = new Intent(mContext, WebsActivity.class);
//        intent.putExtra("title", "隐私政策");
//        intent.putExtra("url", "/portal/privacypolicy.html");
//        mContext.startActivity(intent);
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);

        ds.bgColor = Color.WHITE;
        ds.setUnderlineText(false);
        ds.setColor(Color.parseColor("#3689CF"));

    }

    //    @Override
//    public void onClick(@androidx.annotation. View widget) {
//
//        Intent intent = new Intent(mContext, WebsActivity.class);
//
//        intent.putExtra("title", "隐私政策");
//        intent.putExtra("url","")
//    }
}
