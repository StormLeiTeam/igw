package com.igw.igw.fragment;

import android.nfc.Tag;
import android.nfc.tech.NfcB;
import android.support.design.shape.MaterialShapeDrawable;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.igw.igw.MainActivity;
import com.igw.igw.R;
import com.igw.igw.bean.message.DealMessageBean;
import com.igw.igw.bean.message.MessageCenterBean;
import com.igw.igw.bean.message.ReadAllBean;
import com.igw.igw.bean.message.ReadedMessage;
import com.igw.igw.modoule.messagemodule.MessageContract;
import com.igw.igw.modoule.messagemodule.adapter.MessageCenterAdapter;
import com.igw.igw.modoule.messagemodule.model.MessageListModel;
import com.igw.igw.modoule.messagemodule.presenter.MessageListPresenter;
import com.igw.igw.utils.LocaleUtils;
import com.igw.igw.utils.LogUtils;
import com.igw.igw.utils.StatusBarUtils;
import com.igw.igw.utils.statusbarutils.StatusBarUtil;
import com.igw.igw.widget.storm.BadgeView;
import com.igw.igw.widget.storm.StatusBarView;
import com.shengshijingu.yashiji.common.base.BaseDataFragment;
import com.shengshijingu.yashiji.common.util.ToastUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * 创建时间  2020/3/105:42 PM .
 * <p>
 * 作者  雷雷
 */

public class MessageFragment extends BaseMvpDataFragment<MessageListPresenter> implements MessageContract.View {

    private static final String TAG = "MessageFragment";

    private TextView tv_title;

    private BadgeView mBadgeView;

    private RecyclerView rv_main;

    private MessageCenterAdapter mAdapter;

    private ImageView iv_back;
    private TextView tv_readed;
    private TextView tv_cn_en_select;


    private int noReadCount = 0;


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
        StatusBarUtils.INSTANCE.setDarkMode((MainActivity) mContext);
//        statusBarMain = bindView(R.id.status_bar_main);

//        statusBarMain.setAppActionBarVisible(View.GONE);

        onFirstLoadSuccess();
        tv_title = bindView(R.id.tv_title);
        rv_main = bindView(R.id.rv_main);
        initAdapter();
//        initData();


        // 请求好数据再用

        mBadgeView = new BadgeView(mContext, tv_title);

        mBadgeView.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
        mBadgeView.setBadgeMargin(0, 10);
        mBadgeView.setText("");


    }

    @Override
    protected void initViews() {
        super.initViews();

        iv_back = bindView(R.id.iv_base_back);
        tv_readed = bindView(R.id.tv_readed);
        tv_cn_en_select = bindView(R.id.tv_cn_en_select);
        setUpListener();

    }

    private void setUpListener() {



        tv_cn_en_select.setOnClickListener(v -> {

            ((MainActivity)mContext).changeLanuage(R.id.iv_home_msg);


        });

        tv_readed.setOnClickListener(v -> {

            // 标价已读

            getMPresenter().readAll();

        });


        iv_back.setOnClickListener(v -> {


            MainActivity activity = (MainActivity) getActivity();

            activity.showPagerDependButton(R.id.ll_main_home);
        });



        mAdapter.addOnItemClickListener(new MessageCenterAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(@NotNull MessageCenterBean.DataBean.RowsBean bean, int positon) {


                LogUtils.d(TAG, "item 点击事件 ");

                if (1 == bean.getIsRead()) {
                    return;
                }

                LogUtils.d(TAG,"走已读接口");
                getMPresenter().readMessage(bean.getId());

//                getMPresenter().readedMessage(bean.getId(), 1);





            }
        });


        mAdapter.addOnAgreeListener(new MessageCenterAdapter.OnAgreeListener() {
            @Override
            public void onAgree(@NotNull MessageCenterBean.DataBean.RowsBean bean, int position) {
                LogUtils.d(TAG, "点击了同意");

                getMPresenter().dealMessage(bean.getId(),1);
                getMPresenter().readMessage(bean.getId());

//                getMPresenter().readedMessage(bean.getId(), 1);

            }
        });

        mAdapter.addOnRefuseListener(new MessageCenterAdapter.OnRefuseListener() {
            @Override
            public void onRefuse(@NotNull MessageCenterBean.DataBean.RowsBean bean, int position) {
                LogUtils.d(TAG, "点击了拒绝");

                getMPresenter().dealMessage(bean.getId(),2);
                getMPresenter().readMessage(bean.getId());

//                getMPresenter().readedMessage(bean.getId(), 1);

            }
        });
    }


    private void initAdapter() {
        mAdapter = new MessageCenterAdapter(mContext, false);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        rv_main.setLayoutManager(manager);
        rv_main.setAdapter(mAdapter);


    }



    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            initData();
        }
    }

    private void initData() {


        getMPresenter().messageCenterList();
        showLoadingText();
    }

    @Override
    protected void initPresenter() {

        setMPresenter(new MessageListPresenter(new MessageListModel()));
        getMPresenter().attachView(this);


    }

    @Override
    public void success(Object o) {

    }

    @Override
    public void fail(Object o) {

    }


    @Override
    public void onFail(int code, @NotNull String msg) {
        hideLoadingText();
        ToastUtil.showCenterToast(mContext,msg);

    }

    @Override
    public void onSuccess(@NotNull List<? extends MessageCenterBean.DataBean.RowsBean> mdatas) {
        hideLoadingText();
        noReadCount = 0;

        if (mdatas.size() > 0) {

            LogUtils.d(TAG, "消息中心数据创建成功 -- ");
            if (mAdapter != null) {

                mAdapter.refreshData(mdatas);

            }


            for (MessageCenterBean.DataBean.RowsBean mdata : mdatas) {

                if(mdata.getIsRead() == 0) {

                    noReadCount+=1;
                }

            }



            mBadgeView.setText("" + noReadCount);
            if (noReadCount <= 0) {
                mBadgeView.hide();

            } else {
                mBadgeView.show();

            }

        }else{
            
            mBadgeView.hide();
        }


    }

    @Override
    public void onDealMessageSuccess(@Nullable DealMessageBean.DataBean data) {

        ToastUtil.showCenterToast(mContext, "消息处理成功");

    }

    @Override
    public void onDealMessageFail(int code, @NotNull String msg) {

        ToastUtil.showCenterToast(mContext, msg);

    }

    @Override
    public void readedSuccess(@NotNull ReadedMessage.DataBean data) {

//        noReadCount--;
//        mBadgeView.setText("" + noReadCount);
//        mBadgeView.show();

        getMPresenter().messageCenterList();



    }

    @Override
    public void readedFail(int code, @NotNull String msg) {

        ToastUtil.showCenterToast(mContext,msg);

    }

    @Override
    public void readAllSuccess(@Nullable ReadAllBean.DataBean data) {

        noReadCount = 0 ;
        mBadgeView.setText("" + noReadCount);
        mBadgeView.show();
        getMPresenter().messageCenterList();

    }

    @Override
    public void readAllFail(int code, @NotNull String msg) {

    }
}
