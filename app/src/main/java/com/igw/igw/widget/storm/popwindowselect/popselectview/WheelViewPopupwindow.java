package com.igw.igw.widget.storm.popwindowselect.popselectview;


import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;


import com.igw.igw.R;
import com.igw.igw.utils.LogUtils;
import com.igw.igw.widget.storm.popwindowselect.popselectview.view.WheelView;

import java.util.List;

/**
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 * @Describe
 */
public class WheelViewPopupwindow<T> extends PopupWindow {

    public static final String TAG = "WheelViewPopupwindow";


    private Context mContext;
    private View mRootView;

    private TextView mCancel; // 取消
    private TextView mCommit;// 提交
    private WheelView<T> mContent; // 主体

    private List<T> mData;

    private T mCurrentSelectData; // 当前选择对象

    private int mCurrentPosition; // 当前选择position

    private boolean outSideDismiss = true;  // 点击控件外部取消

    private T commitData;  // 最后提交的数据
    private int commitPosition;  // 最后提交的值


    public WheelViewPopupwindow(Context mContext) {
        this.mContext = mContext;

//        LogUtils.d(TAG,"---> " +mContext == null);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mRootView = inflater.inflate(R.layout.layout_wheelview_popupwindow, null);
        initView();
        this.setContentView(mRootView);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        this.setOutsideTouchable(false);
        ColorDrawable backGround = new ColorDrawable(0x00000000);
        this.setBackgroundDrawable(backGround);
//        this.setClippingEnabled(false);
        setUpListener();
        initOutsideDismiss();


    }

    /**
     * 设置点击外部取消
     */
    private void initOutsideDismiss() {

        if (!outSideDismiss) {
            return;
        }

        mRootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                LinearLayout rootMain = mRootView.findViewById(R.id.root_main);

                int bottom = rootMain.getBottom();
                int top = rootMain.getTop();

                int downY = (int) event.getRawY();

                if (downY < top) {
                    dismiss();
                }

                return false;
            }
        });

    }

    public void setOutSideDismiss(boolean open) {
        outSideDismiss = open;
    }


    public void setData(List<T> data) {
        this.mData = data;
        mContent.setData(data);

        setDefaultPosition(0);

    }


    /**
     * 设置默认选中项
     *
     * @param index
     */
    public void setDefaultPosition(int index) {

        mCurrentPosition = index;
        if (mData != null) {
            mCurrentSelectData = mData.get(index);
        }

    }

    public void setSelectItemPosition(int position) {

        mContent.setSelectedItemPosition(position);
    }

    private void setUpListener() {


        mContent.setOnItemSelectedListener(new WheelView.OnItemSelectedListener<T>() {
            @Override
            public void onItemSelected(WheelView<T> wheelView, T data, int position) {


                setDefaultPosition(position);


            }
        });

        mCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                commitData = mCurrentSelectData;
                commitPosition = mCurrentPosition;
                // 添加回调
                if (mCommitListener != null) {

                    mCommitListener.onCommit(commitData, commitPosition);
                }

                dismiss();
            }
        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.d("桑钱--> " + mCurrentPosition);

                mContent.setSelectedItemPosition(commitPosition);
                dismiss();
            }
        });


    }

    private LinearLayout rootmMain;
    private void initView() {
        mCancel = mRootView.findViewById(R.id.tv_cancel);
        mCommit = mRootView.findViewById(R.id.tv_commit);
        mContent = mRootView.findViewById(R.id.wl_content);
        rootmMain = mRootView.findViewById(R.id.root_main);
    }



    public void onCommitlistener(OnClickListener<T> listener) {
        this.mCommitListener = listener;
    }

    private OnClickListener<T> mCommitListener;

    public void setAnimationStyles(int pop_anim) {



    }

    public interface OnClickListener<T> {
        void onCommit(T data, int position);
    }

}
