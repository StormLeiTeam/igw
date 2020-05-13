package com.shengshijingu.yashiji.common.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.shengshijingu.yashiji.common.R;
import com.shengshijingu.yashiji.common.dialog.LoadingDialog;

/**
 * Created by leilei
 * 2019/3/12
 */

public abstract class BaseOldFragment extends Fragment {

    protected View mBaseView;

    protected LinearLayout mBodyLayout;

    protected View main;

    protected Context mContext;

    // 加载中...
    private LoadingDialog dialog;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }
//
//    @Override
//    public void onAttachFragment(Fragment childFragment) {
//        super.onAttachFragment(childFragment);
//        mContext=childFragment.getActivity();
//
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mBaseView == null) {
            mBaseView = inflater.inflate(R.layout.base_view, container, false);

            initBaseView(inflater);
            initView();
        }

        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) mBaseView.getParent();
        if (parent != null) {
            parent.removeView(mBaseView);
        }
        return mBaseView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews();
    }

    protected  void initViews(){

    };

    /**
     * 显示什么样的标题
     *
     * @return 0：不显示   1普通的标题
     */
    protected int isHome() {
        return 0;
    }

    public <T extends View> T bindView(int id) {
        if (main != null) {
            return (T) main.findViewById(id);
        }
        return mBodyLayout.findViewById(id);
    }

    public <T extends View> T bindView(View root, int id) {
        if (root != null) {
            return (T) root.findViewById(id);
        }
        return mBodyLayout.findViewById(id);
    }

    protected void initBaseView(LayoutInflater inflater) {
        mBodyLayout = bindView(mBaseView, R.id.body);
        if (getLayoutId() != 0 && main == null && mContext != null) {
            main = View.inflate(mContext, getLayoutId(), null);
            mBodyLayout.addView(main);
        }

    }

    public void showLoadingText(String string) {
        if (TextUtils.isEmpty(string) || getActivity() == null) {
            return;
        }
        LoadingDialog.Builder builder1 = new LoadingDialog.Builder(getActivity())
                .setMessage(string)
                .setCancelable(false);
        dialog = builder1.create();
        dialog.show();
    }

    public void showLoadingText() {
        showLoadingText("加载中...");
    }

    public void hideLoadingText() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public abstract int getLayoutId();

    public abstract void initView();
}
