package com.igw.igw.fragment;

import com.igw.igw.R;
import com.shengshijingu.yashiji.common.base.BaseDataFragment;

/**
 * 创建时间  2020/3/105:42 PM .
 *
 * 作者  雷雷
 */

public class MyFragment extends BaseDataFragment{

    public static MyFragment getInstance() {
        MyFragment myFragment = new MyFragment();
        return myFragment;
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
        return R.layout.my_fragment;
    }

    @Override
    public void initView() {
        onFirstLoadSuccess();
    }
}
