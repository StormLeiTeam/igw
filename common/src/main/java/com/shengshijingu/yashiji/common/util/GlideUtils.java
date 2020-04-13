package com.shengshijingu.yashiji.common.util;

import android.content.Context;
import android.util.TypedValue;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.shengshijingu.yashiji.common.R;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation.CornerType;

/**
 * Created by leilei on 2018/7/17.
 */

public class GlideUtils {


    public static void loading(Context mContext, Object path,
                               ImageView imageview) {
        Glide.with(mContext).load(path).into(imageview);
    }

    public static void loadRoundTransImage(Context mContext, Object path,
                                           ImageView imageview, int placeholder, int width) {
        RoundedCornersTransform transform = new RoundedCornersTransform(mContext, dp2px(mContext, width));
        transform.setNeedCorner(true, true, true, true);
        RequestOptions options = new RequestOptions().placeholder(placeholder).skipMemoryCache(false).transform(transform);

        Glide.with(mContext).load(path)
                .apply(options)
                .into(imageview);
    }

    public static void loadRoundLeftTransImage(Context mContext, Object path,
                                               ImageView imageview, int placeholder, int width) {
        RoundedCornersTransform transform = new RoundedCornersTransform(mContext, dp2px(mContext, width));
        transform.setNeedCorner(true, true, false, false);
        RequestOptions options = new RequestOptions().placeholder(placeholder).skipMemoryCache(false).transform(transform);
        Glide.with(mContext).load(path).apply(options).into(imageview);
    }

    public static void loadLocalImage(Context mContext, int path,
                                      ImageView imageview) {
        Glide.with(mContext).load(path).apply(new RequestOptions().centerCrop().skipMemoryCache(true)).into(imageview);
    }

    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    /**
     * 圆形加载带边框
     */
    public static void loadCircleImage(Context mContext, Object path, int placeholder,
                                       ImageView imageview, float width, int color) {
        Glide.with(mContext)
                .load(path)
                .apply(new RequestOptions()
                        .placeholder(placeholder)
                        .transform(new GlideCircleTransform(mContext, width, color)))
                .into(imageview);
    }

    /**
     * 圆形加载不带边框
     */
    public static void LoadCircleImages(Context mContext, Object path, int placeholder,
                                        ImageView imageview) {

        RoundedCornersTransform transform = new RoundedCornersTransform(mContext, dp2px(mContext, 100));
        transform.setNeedCorner(true, true, true, true);
        RequestOptions options = new RequestOptions().placeholder(placeholder).skipMemoryCache(false).transform(transform);


        Glide.with(mContext)
                .load(path)
                .apply(options)
                .into(imageview);

    }


}
