package com.igw.igw.fragment;

import com.igw.igw.R;
import com.shengshijingu.yashiji.common.base.BaseDataFragment;

/**
 * 创建时间  2020/3/111:25 PM .
 *
 * 作者  雷雷
 */

public class BusinessCooperationFragment extends BaseDataFragment {

    @Override
    protected void onReloadData(int type) {

    }

    @Override
    protected boolean onClickImageReload() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.cityinfo_fragment;
    }

    @Override
    public void initView() {
        onFirstLoadSuccess();

    }
}
