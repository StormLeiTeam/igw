package com.igw.igw.fragment;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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


    public static final String TAG = "HomeFragment";

    private RelativeLayout ll_home_citystation;
    private ImageView iv_home_msg;

    private RelativeLayout rv_city_chat;

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
        ll_home_citystation = bindView(R.id.rv_cityexhibition);
        ll_home_citystation.setOnClickListener(this);


        rv_city_chat = bindView(R.id.tv_city_chat);
        rv_city_chat.setOnClickListener(this);


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

        MainActivity mainActivity = (MainActivity) getActivity();

        if (clickTime()) {
            switch (view.getId()) {
                case R.id.rv_cityexhibition: // 城市驿站2

                    mainActivity.showPagerDependButton(R.id.ll_main_order);
                    break;
                case R.id.iv_home_msg:



                    mainActivity.showPagerDependButton(R.id.iv_home_msg);
                    break;

                case R.id.tv_city_chat:

                    // 跳转
                    mainActivity.showPagerDependButton(R.id.ll_main_message);
                    break;
            }
        }
    }
}