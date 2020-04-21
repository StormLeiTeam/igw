package com.igw.igw.fragment;

import android.view.View;
import android.widget.TextView;

import com.igw.igw.MainActivity;
import com.igw.igw.R;
import com.igw.igw.modoule.login.view.UpdateActivity;
import com.shengshijingu.yashiji.common.base.BaseDataFragment;

/**
 * 创建时间  2020/3/105:42 PM .
 *
 * 作者  雷雷
 */

public class MyFragment extends BaseMvpDataFragment{


    public static final String TAG =  "MyFragment";


    private TextView tv_update_pwd;


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

//        tv_update_pwd = mBaseView.findViewById(R.id.tv_update_pwd);

       tv_update_pwd =  bindView(R.id.tv_update_pwd);



        setUpListener();
    }

    private void setUpListener() {

        tv_update_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UpdateActivity.Companion.startSelf((MainActivity)mContext);

            }
        });

    }


    @Override
    protected void initPresenter() {

    }
}
