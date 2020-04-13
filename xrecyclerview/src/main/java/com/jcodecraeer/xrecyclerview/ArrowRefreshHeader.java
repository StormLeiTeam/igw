package com.jcodecraeer.xrecyclerview;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.progressindicator.AVLoadingIndicatorView;

import java.util.Date;

public class ArrowRefreshHeader extends LinearLayout implements BaseRefreshHeader {

    private LinearLayout mContainer;

    private ImageView mArrowImageView;

    private ImageView mProgressBar;

    private TextView mStatusTextView;

    private int mState = STATE_NORMAL;

    private Context mContext;


    private Animation mRotateUpAnim;

    private Animation mRotateDownAnim;

    private final int ROTATE_ANIM_DURATION = 180;

    public int mMeasuredHeight;

    private int mRefreshCount = 0;

    private View listview_header_content;

    private DisplayMetrics dm;

    private int style;

    private String startRefreshText, releaseText, refreshingText;

    private String type = "资讯";

    public ArrowRefreshHeader(Context context) {
        super(context);
        initView(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public ArrowRefreshHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {

        mContext = context;
        // 初始情况，设置下拉刷新view高度为0
        mContainer = (LinearLayout) LayoutInflater.from(context).inflate(
                R.layout.listview_header, null);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0, 0);
        this.setLayoutParams(lp);
        this.setPadding(0, 0, 0, 0);

        addView(mContainer, new LayoutParams(LayoutParams.MATCH_PARENT, 0));
        setGravity(Gravity.BOTTOM);

        mArrowImageView = findViewById(R.id.listview_header_arrow);
        mStatusTextView = findViewById(R.id.refresh_status_textview);


        //init the progress view
        mProgressBar = findViewById(R.id.listview_header_progressbar);

        mRotateUpAnim = new RotateAnimation(0.0f, -180.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        mRotateUpAnim.setDuration(ROTATE_ANIM_DURATION);
        mRotateUpAnim.setFillAfter(true);
        mRotateDownAnim = new RotateAnimation(-180.0f, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        mRotateDownAnim.setDuration(ROTATE_ANIM_DURATION);
        mRotateDownAnim.setFillAfter(true);

        measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mMeasuredHeight = getMeasuredHeight();

        listview_header_content = mContainer.findViewById(R.id.listview_header_content);

        dm = new DisplayMetrics();
        dm = getResources().getDisplayMetrics();
    }

    public void setProgressStyle(int style) {
//        if (style == ProgressStyle.SysProgress) {
//            mProgressBar.setView(new ProgressBar(mContext, null, android.R.attr.progressBarStyle));
//        } else {
//            if (style == ProgressStyle.GifProgress) {
//                set60Style();
//
//                ImageView iv = new ImageView(mContext);
//                SimpleViewSwithcer.LayoutParams params = new SimpleViewSwithcer.LayoutParams((int) (60 * dm.density), (int) (48 * dm.density));
//                iv.setLayoutParams(params);
//                mProgressBar.setView(iv);
//            } else {
//                AVLoadingIndicatorView progressView = new AVLoadingIndicatorView(this.getContext());
//                progressView.setIndicatorColor(0xffB5B5B5);
//                progressView.setIndicatorId(style);
//                mProgressBar.setView(progressView);
//            }
//        }
//        this.style = style;
    }

    public void set60Style() {
        RelativeLayout.LayoutParams mParentParams = new RelativeLayout.LayoutParams((int) (60 * dm.density), (int) (48 * dm.density));
        mParentParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        mProgressBar.setLayoutParams(mParentParams);
    }


//    public ImageView getProgressStyle() {
//        if (style == ProgressStyle.GifProgress) {
//            return (ImageView) mProgressBar.getView();
//        } else {
//            return null;
//        }
//    }

    public void setArrowImageView(int resid) {
        if (style == ProgressStyle.GifProgress) {
            mArrowImageView.setVisibility(View.INVISIBLE);
        } else {
            mArrowImageView.setImageResource(resid);
        }
    }

    public void setState(int state) {
        if (state == mState) {
            return;
        }

        if (state == STATE_REFRESHING) {    // 显示进度
            mArrowImageView.setVisibility(View.GONE);
            if (mProgressBar != null) {
                Glide.with(getContext()).load(R.drawable.icon_header_g).into(mProgressBar);
            }
            mProgressBar.setVisibility(View.VISIBLE);

        } else if (state == STATE_DONE) {
            mArrowImageView.setVisibility(View.VISIBLE);
            if(mProgressBar != null){
                mProgressBar.setVisibility(View.GONE);
            }
//            mProgressBar.setVisibility(View.INVISIBLE);
        } else {    // 显示箭头图片
            hideCountView();
            if (style != ProgressStyle.GifProgress) {
                mArrowImageView.setVisibility(View.VISIBLE);
            } else {
                mArrowImageView.setVisibility(View.INVISIBLE);
            }
            mProgressBar.setVisibility(View.INVISIBLE);
        }

        switch (state) {
            case STATE_NORMAL:
                if (mState == STATE_RELEASE_TO_REFRESH) {
                    if (style != ProgressStyle.GifProgress) {
//                        mArrowImageView.startAnimation(mRotateDownAnim);
                    }
                } else if (mState == STATE_REFRESHING) {
                    if (style != ProgressStyle.GifProgress) {
//                        mArrowImageView.clearAnimation();
                    }
                }
                if (startRefreshText != null) {
                    mStatusTextView.setText(startRefreshText);
                } else {
                    mStatusTextView.setText(R.string.listview_header_hint_normal);
                }
                break;
            case STATE_RELEASE_TO_REFRESH:
                if (mState != STATE_RELEASE_TO_REFRESH) {
                    if (style != ProgressStyle.GifProgress) {
//                        mArrowImageView.clearAnimation();
//                        mArrowImageView.startAnimation(mRotateUpAnim);
                    }
                    if (releaseText != null) {
                        mStatusTextView.setText(releaseText);
                    } else {
                        mStatusTextView.setText(R.string.listview_header_hint_release);
                    }
                }
                break;
            case STATE_REFRESHING:
                if (refreshingText != null) {
                    mStatusTextView.setText(refreshingText);
                } else {
                    mStatusTextView.setText(R.string.refreshing);
                }
                break;
            case STATE_DONE:
                if (startRefreshText != null) {
                    mStatusTextView.setText(startRefreshText);
                } else {
                    mStatusTextView.setText(R.string.refresh_done);
                }
                if (mRefreshCount != 0) {
                    smoothScrollTo((int) (40 * dm.density));
                }
                break;
            default:
        }
        mState = state;
    }

    public void hideCountView() {
        listview_header_content.setVisibility(View.VISIBLE);
//        if (refresh_count.isShown()) {
//            refresh_count.setVisibility(View.GONE);
//        }
    }

    public void hideCountAnimator() {
    }


    public int getState() {
        return mState;
    }

    @Override
    public void refreshComplate() {
        mRefreshCount = 0;
        setState(STATE_DONE);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                reset();
            }
        }, 300);
    }

    public void refreshComplate(int count) {
        this.mRefreshCount = count;
        showRefreshCount();
        setState(STATE_DONE);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                resetOnly();
            }
        }, 700);
    }

    public void refreshComplate(int count, String type) {
        this.mRefreshCount = count;
        this.type = type;
        showRefreshCount();
        setState(STATE_DONE);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                resetOnly();
            }
        }, 700);
    }

    public void showRefreshCount() {
        listview_header_content.setVisibility(View.GONE);
    }

    public void setVisiableHeight(int height) {
        if (height < 0) {
            height = 0;
        }
        LayoutParams lp = (LayoutParams) mContainer
                .getLayoutParams();
        lp.height = height;
        mContainer.setLayoutParams(lp);
    }

    public int getVisiableHeight() {
        int height = 0;
        LayoutParams lp = (LayoutParams) mContainer
                .getLayoutParams();
        height = lp.height;
        return height;
    }

    @Override
    public void onMove(float delta) {
        if (getVisiableHeight() > 0 || delta > 0) {
            setVisiableHeight((int) delta + getVisiableHeight());
            if (mState <= STATE_RELEASE_TO_REFRESH) { // 未处于刷新状态，更新箭头
                if (getVisiableHeight() > mMeasuredHeight) {
                    setState(STATE_RELEASE_TO_REFRESH);
                } else {
                    setState(STATE_NORMAL);
                }
            }
        }
    }

    @Override
    public boolean releaseAction() {
        boolean isOnRefresh = false;
        int height = getVisiableHeight();
        if (height == 0) // not visible.
        {
            isOnRefresh = false;
        }

        if (getVisiableHeight() > mMeasuredHeight && mState < STATE_REFRESHING) {
            setState(STATE_REFRESHING);
            isOnRefresh = true;
        }
        // refreshing and header isn't shown fully. do nothing.
        if (mState == STATE_REFRESHING && height <= mMeasuredHeight) {
            //return;
        }
        int destHeight = 0; // default: scroll back to dismiss header.
        // is refreshing, just scroll back to show all the header.
        if (mState == STATE_REFRESHING) {
            destHeight = mMeasuredHeight;
        }
        smoothScrollTo(destHeight);

        // release info
        return isOnRefresh;
    }

    public void reset() {
        smoothScrollTo(0);
        setState(STATE_NORMAL);
    }

    public void resetOnly() {
        smoothScrollTo(0);
        mState = STATE_NORMAL;
        if (style != ProgressStyle.GifProgress) {
            mArrowImageView.setVisibility(View.VISIBLE);
//            mGoodsRefreshView.setVisibility(View.GONE);
        } else {
//            mGoodsRefreshView.setVisibility(View.VISIBLE);
            mArrowImageView.setVisibility(View.INVISIBLE);
        }
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    private void smoothScrollTo(int destHeight) {
        ValueAnimator animator = ValueAnimator.ofInt(getVisiableHeight(), destHeight);
        animator.setDuration(150).start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setVisiableHeight((int) animation.getAnimatedValue());
            }
        });

        animator.start();
        if (destHeight == 0) {
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
//                    refresh_count.setVisibility(View.GONE);
                    hideCountAnimator();
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            });
        }

    }

    public static String friendlyTime(Date time) {
        //获取time距离当前的秒数
        int ct = (int) ((System.currentTimeMillis() - time.getTime()) / 1000);

        if (ct == 0) {
            return "刚刚";
        }

        if (ct > 0 && ct < 60) {
            return ct + "秒前";
        }

        if (ct >= 60 && ct < 3600) {
            return Math.max(ct / 60, 1) + "分钟前";
        }
        if (ct >= 3600 && ct < 86400) {
            return ct / 3600 + "小时前";
        }
        if (ct >= 86400 && ct < 2592000) { //86400 * 30
            int day = ct / 86400;
            return day + "天前";
        }
        if (ct >= 2592000 && ct < 31104000) { //86400 * 30
            return ct / 2592000 + "月前";
        }
        return ct / 31104000 + "年前";
    }

    public void setRefreshState() {
        releaseAction();
    }

//    public ImageView getRefreshView() {
//        return getProgressStyle();
//    }

    public void setRefreshingText(String str) {
        this.refreshingText = str;
    }

    public void setRefreshReleaseText(String str) {
        this.releaseText = str;
    }

    public void setRefreshStartPullText(String str) {
        this.startRefreshText = str;
    }

}
