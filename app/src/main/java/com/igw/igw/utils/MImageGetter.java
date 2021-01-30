package com.igw.igw.utils;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.TextUtils;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.bumptech.glide.request.transition.Transition;

/**
 * 创建时间  2020/2/139:22 AM .
 * <p>
 * 作者  雷雷
 */

public class MImageGetter implements Html.ImageGetter {


    private Context c;

    private TextView container;

    int width;

    public MImageGetter(TextView text, Context c) {
        this.c = c;
        this.container = text;

//        width = c.getResources().getDisplayMetrics().heightPixels;//横屏的宽

        width = text.getWidth()- text.getPaddingStart() -text.getPaddingEnd();

    }

//    @Override
//    public Drawable getDrawable(String source) {
//        final UrlDrawable urlDrawable = new UrlDrawable();
//        String strSource;
//        if (TextUtils.isEmpty(source)) {
//            return urlDrawable;
//        }
//        if (source.substring(0, 4).contains("http") || source.substring(0, 4).contains("https")) {
//            strSource = source;
//        } else {
//            strSource = "https:" + source;
//        }
//
//
//
//        Glide.with(c)
//                .asBitmap()
//                .load(strSource)
//                .into(new CustomViewTarget<TextView, Bitmap>(container) {
//
//                    @Override
//                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
//
//                    }
//
//                    @Override
//                    public void onResourceReady(@NonNull Bitmap loadedImage, @Nullable Transition<? super Bitmap> transition) {
//                        float scaleWidth = ((float) width) / loadedImage.getWidth();
//
//                        Matrix matrix = new Matrix();
//                        matrix.postScale((float) (scaleWidth+0.1), (float) (scaleWidth+0.1));//根据情况可以适当调整
//                        loadedImage = Bitmap.createBitmap(loadedImage, 0, 0,loadedImage.getWidth(), loadedImage.getHeight(),matrix, true);
//                        urlDrawable.bitmap = loadedImage;
//                        urlDrawable.setBounds(0, 0, loadedImage.getWidth(),loadedImage.getHeight());
////                        container.invalidate();
////                        container.setText(container.getText());
//
//                    }
//
//
//
//
//                    @Override
//                    protected void onResourceCleared(@Nullable Drawable placeholder) {
//
//                    }
//                });
//
//
//        return urlDrawable;
//
//    }


    @Override
    public Drawable getDrawable(String source) {


        final UrlDrawable urlDrawable = new UrlDrawable();
        String strSource;
        if (TextUtils.isEmpty(source)) {
            return urlDrawable;
        }
        if (source.substring(0, 4).contains("http") || source.substring(0, 4).contains("https")) {
            strSource = source;
        } else {
            strSource = "https:" + source;
        }
//
//        final LevelListDrawable drawable = new LevelListDrawable();

        Glide.with(c)
                .asBitmap()
                .load(source)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap loadedImage, Transition<? super Bitmap> transition) {


                        float scaleWidth = ((float) width) / loadedImage.getWidth();

                        Matrix matrix = new Matrix();
                        matrix.postScale((float) (scaleWidth), (float) (scaleWidth));//根据情况可以适当调整
                        loadedImage = Bitmap.createBitmap(loadedImage, 0, 0,loadedImage.getWidth(), loadedImage.getHeight(),matrix, true);
                        urlDrawable.bitmap = loadedImage;
                        urlDrawable.setBounds(0, 0, loadedImage.getWidth(),loadedImage.getHeight());
                        container.invalidate();
                        container.setText(container.getText());


//                        if (resource != null) {
//                            drawable.addLevel(1, 1, resource);
//                            drawable.setBounds(0, 0, width, resource.getIntrinsicHeight());
//                            drawable.setLevel(1);
//                            container.invalidate();
//                            container.setText(container.getText());
//
//                        }
                    }
                });
        return urlDrawable;


    }


    class UrlDrawable extends BitmapDrawable {
        protected Bitmap bitmap;

        @Override
        public void draw(Canvas canvas) {
            // override the draw to facilitate refresh function later
            if (bitmap != null) {
                canvas.drawBitmap(bitmap, 0, 0, getPaint());
            }
        }
    }


}
