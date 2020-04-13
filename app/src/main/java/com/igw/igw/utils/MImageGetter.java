package com.igw.igw.utils;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.text.Html;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

/**
 * 创建时间  2020/2/139:22 AM .
 *
 * 作者  雷雷
 */

public class MImageGetter implements Html.ImageGetter {

    private Context c;

    private TextView container;

    public MImageGetter(TextView text, Context c) {
        this.c = c;
        this.container = text;
    }

    @Override
    public Drawable getDrawable(String source) {
        final LevelListDrawable drawable = new LevelListDrawable();

        Glide.with(c).load(source)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        if (resource != null) {
                            drawable.addLevel(1, 1, resource);
                            drawable.setBounds(0, 0, resource.getMinimumWidth(), resource.getMinimumHeight());
                            drawable.setLevel(1);
                            container.invalidate();
                            container.setText(container.getText());

                        }
                    }
                });
        return drawable;
    }

}
