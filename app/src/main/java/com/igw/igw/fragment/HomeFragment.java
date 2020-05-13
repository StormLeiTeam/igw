package com.igw.igw.fragment;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.igw.igw.MainActivity;
import com.igw.igw.R;
import com.igw.igw.utils.LogUtils;
import com.shengshijingu.yashiji.common.base.BaseDataFragment;

/**
 * 创建时间  2020/3/105:42 PM .
 * <p>
 * 作者  雷雷
 */

public class HomeFragment extends BaseDataFragment {


    public static final  String TAG = "HomeFragment";

    private LinearLayout ll_home_citystation;
    private ImageView iv_home_msg;

    public static HomeFragment getInstance() {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }

    @Override
    protected void onReloadData(int type) {

    }

    @Override
    protected boolean onClickImageReload() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.home_fragment;
    }

    @Override
    public void initView() {
        onFirstLoadSuccess();
        ll_home_citystation = bindView(R.id.ll_home_citystation);
        ll_home_citystation.setOnClickListener(this);
        iv_home_msg = bindView(R.id.iv_home_msg);


    }

    @Override
    protected void initViews() {
        super.initViews();

        iv_home_msg = bindView(R.id.iv_home_msg);

        iv_home_msg.setOnClickListener(v -> {

            MainActivity mainActivity = (MainActivity) getActivity();

            mainActivity.showPagerDependButton(R.id.iv_home_msg);

        });
    }

    @Override
    public void onClick(View view) {

        super.onClick(view);
        if (clickTime()) {
            switch (view.getId()) {
                case R.id.ll_home_citystation:
                    MainActivity activity = (MainActivity) getActivity();

                    activity.showPagerDependButton(R.id.ll_main_order);
                    break;
                case R.id.iv_home_msg:


                    MainActivity mainActivity = (MainActivity) getActivity();

                    mainActivity.showPagerDependButton(R.id.iv_home_msg);
                    break;
            }
        }
    }
}