package com.shengshijingu.yashiji.common.base;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.shengshijingu.yashiji.common.R;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by leilei on 2019/3/14.
 */

public abstract class BaseDataFragment extends BaseOldFragment implements OnClickListener {

    protected View errorView;

    private View view;

    private LinearLayout errorLayout;

    private TextView errorTextView, tv_loadding_error;

    protected ImageView errorImageView;

    // loading layout
    protected View loaddingView;

    protected ViewStub mLoadingViewStub;

    protected ViewStub mErrorLayoutViewStub;

    private GifImageView mLoadingProgress;

    protected void initBaseView(LayoutInflater inflater) {
        super.initBaseView(inflater);

        if (getLayoutId() != 0) {
            main.setVisibility(View.GONE); //8 GONE 0 VISIBLE
        }

        mLoadingViewStub = mBaseView.findViewById(R.id.loading_layout);
        if (mLoadingViewStub != null) {
            loaddingView = mLoadingViewStub.inflate();
            mLoadingProgress = loaddingView.findViewById(R.id.loading_progress);
            mLoadingProgress.setImageResource(R.drawable.loading);
        }

        mErrorLayoutViewStub = mBaseView.findViewById(R.id.loading_error_layout);
    }

    public void onLoad(int type) {
        if (1 == type) {
            this.loaddingView.setVisibility(View.VISIBLE);
            if (errorView != null) {
                this.errorView.setVisibility(View.GONE);
            }
            this.main.setVisibility(View.GONE);
        }
        this.onReloadData(type);
    }

    protected abstract void onReloadData(int type);

    protected abstract boolean onClickImageReload();

    public void onFirstLoadDataFailed() {
        this.onFirstLoadDataFailed(null, 0);
    }

    /**
     * 第一次加载， 网络连接失败，
     *
     * @param tip   文字提示  如果不用传空
     * @param msgID 默认图， 如果传0，则显示默认的网络连接错误的图
     */
    public void onFirstLoadDataFailed(String tip, int msgID) {
        if (errorView == null) {
            errorView = mErrorLayoutViewStub.inflate();
            tv_loadding_error = bindView(errorView, R.id.tv_loadding_error);
            errorLayout = bindView(errorView, R.id.ll_loadding_error);
            errorImageView = bindView(errorView, R.id.img_layout_loadding_error);
            errorTextView = bindView(errorView, R.id.tv_loadding_error_tip);
            view = bindView(errorView, R.id.view);
        }

        this.view.setVisibility(View.GONE);
        this.loaddingView.setVisibility(View.GONE);
        this.main.setVisibility(View.GONE);
        this.errorView.setVisibility(View.VISIBLE);
        tv_loadding_error.setVisibility(View.VISIBLE);
        if (TextUtils.isEmpty(tip)) {
            this.errorTextView.setText("加载失败，点击屏幕重试");
        } else {
            this.errorTextView.setText(tip);
        }

        if (msgID <= 0) {
            errorImageView.setImageResource(R.drawable.icon_no_network);
        } else {
            this.errorImageView.setImageResource(msgID);
        }

        this.errorLayout.setOnClickListener(v -> {
            if (onClickImageReload()) {
                onLoad(1);
            }
        });

        tv_loadding_error.setOnClickListener(view -> {
            if (onClickImageReload()) {
                onLoad(1);
            }
        });
    }

    public void onFirstLoadSuccess() {
        this.loaddingView.setVisibility(View.GONE);
        if (this.errorView != null) {
            this.errorView.setVisibility(View.GONE);
        }
        this.main.setVisibility(View.VISIBLE);
    }

    public void onFirstLoadNoData(int resID, int imageID) {
        if (errorView == null) {
            errorView = mErrorLayoutViewStub.inflate();
            view = bindView(view, R.id.view);
            tv_loadding_error = bindView(errorView, R.id.tv_loadding_error);
            errorLayout = bindView(errorView, R.id.ll_loadding_error);
            errorImageView = bindView(errorView, R.id.img_layout_loadding_error);
            errorTextView = bindView(errorView, R.id.tv_loadding_error_tip);
        }
        view.setVisibility(View.VISIBLE);
        tv_loadding_error.setVisibility(View.GONE);
        this.loaddingView.setVisibility(View.GONE);
        this.main.setVisibility(View.GONE);
        if (errorView != null) {
            this.errorView.setVisibility(View.VISIBLE);
            if (resID == 0) {
                this.tv_loadding_error.setText("");
            } else {
                this.tv_loadding_error.setText(resID);
            }
            this.errorView.setOnClickListener(null);
            if (imageID <= 0) {
//                this.errorImageView.setBackgroundResource(R.mipmap.empty);
            } else {
                errorImageView.setImageResource(imageID);
            }
        }
    }

    //todo
    public void onFirstLoadNoData(String noDataTip, String addData, int imageID) {
        if (errorView == null) {
            errorView = mErrorLayoutViewStub.inflate();
            tv_loadding_error = bindView(errorView, R.id.tv_loadding_error);
            view = bindView(errorView, R.id.view);
            errorLayout = bindView(errorView, R.id.ll_loadding_error);
            errorImageView = bindView(errorView, R.id.img_layout_loadding_error);
            errorTextView = bindView(errorView, R.id.tv_loadding_error_tip);
        }
        this.view.setVisibility(View.VISIBLE);
        this.loaddingView.setVisibility(View.GONE);
        this.main.setVisibility(View.GONE);
        if (TextUtils.isEmpty(addData)) {
            this.tv_loadding_error.setVisibility(View.GONE);
        } else {
            this.tv_loadding_error.setVisibility(View.VISIBLE);
            this.tv_loadding_error.setText(addData);
        }

        tv_loadding_error.setOnClickListener(view -> {
            if (onClickImageReload()) {
                onLoad(2);
            }
        });

        if (errorView != null) {
            this.errorView.setVisibility(View.VISIBLE);
            this.errorView.setOnClickListener(null);
            this.errorTextView.setText(noDataTip);
            errorImageView.setImageResource(imageID);
        }
    }

    public void initRecyclerView(XRecyclerView xRecyclerView, boolean isRefresh, boolean isLoad, View layout) {
        if (layout != null) {
            xRecyclerView.addHeaderView(layout);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        layoutManager.setAutoMeasureEnabled(true);

        xRecyclerView.setLayoutManager(layoutManager);

        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xRecyclerView.setLaodingMoreProgressStyle(ProgressStyle.BallBeat);

        xRecyclerView.setPullRefreshEnabled(isRefresh);
        xRecyclerView.setLoadingMoreEnabled(isLoad);
        xRecyclerView.setItemAnimator(new DefaultItemAnimator());
        xRecyclerView.setNoMoreText("没有更多了哟~");

        xRecyclerView.setArrowImageView(R.drawable.icon_header);
        xRecyclerView.setRefreshProgressStyle(ProgressStyle.GifProgress);


    }

    public void initGridRecyclerView(XRecyclerView xRecyclerView, boolean isRefresh, boolean isLoad, int column, View layout) {
        if (layout != null) {
            xRecyclerView.addHeaderView(layout);
        }

        final GridLayoutManager manager = new GridLayoutManager(getActivity(), column);
        xRecyclerView.setLayoutManager(manager);
        manager.setAutoMeasureEnabled(true);


        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xRecyclerView.setLaodingMoreProgressStyle(ProgressStyle.BallBeat);

        xRecyclerView.setPullRefreshEnabled(isRefresh);
        xRecyclerView.setLoadingMoreEnabled(isLoad);
        xRecyclerView.setItemAnimator(new DefaultItemAnimator());
        xRecyclerView.setArrowImageView(R.drawable.icon_header);
        xRecyclerView.setRefreshProgressStyle(ProgressStyle.GifProgress);
        xRecyclerView.setNoMoreText("没有更多了哟~");

    }


    protected void loadFinish(int PAGE, XRecyclerView xRecyclerView) {
        if (xRecyclerView == null) {
            return;
        }
        if (PAGE == 1) {
            xRecyclerView.refreshComplete();
            xRecyclerView.MoreGone();
        } else {
            xRecyclerView.loadMoreComplete();
        }
    }

    @Override
    public void onClick(View view) {
    }

    public boolean clickTime() {
        if (System.currentTimeMillis() - lastClickTime < FAST_CLICK_DELAY_TIME) {
            return false;
        }

        lastClickTime = System.currentTimeMillis();
        return true;
    }

    //全局定义
    private long lastClickTime = 0L;

    // 两次点击间隔不能少于1000ms
    private static final int FAST_CLICK_DELAY_TIME = 300;
}
