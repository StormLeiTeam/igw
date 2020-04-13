package com.igw.igw.fragment;

import android.view.View;
import android.widget.LinearLayout;
import com.igw.igw.MainActivity;
import com.igw.igw.R;
import com.shengshijingu.yashiji.common.base.BaseDataFragment;

/**
 * 创建时间  2020/3/105:42 PM .
 *
 * 作者  雷雷
 */

public class HomeFragment extends BaseDataFragment {

    private LinearLayout ll_home_citystation;

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
            }
        }
    }
}
