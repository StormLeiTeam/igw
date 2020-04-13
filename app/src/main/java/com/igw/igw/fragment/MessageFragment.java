package com.igw.igw.fragment;

import com.igw.igw.R;
import com.shengshijingu.yashiji.common.base.BaseDataFragment;

/**
 * 创建时间  2020/3/105:42 PM .
 *
 * 作者  雷雷
 */

public class MessageFragment extends BaseDataFragment {

    public static MessageFragment getInstance() {
        MessageFragment messageFragment = new MessageFragment();
        return messageFragment;
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
        return R.layout.message_fragment;
    }

    @Override
    public void initView() {
        onFirstLoadSuccess();
    }
}
