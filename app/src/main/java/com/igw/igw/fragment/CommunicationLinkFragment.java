package com.igw.igw.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.igw.igw.R;
import com.igw.igw.network.NetObserver;
import com.shengshijingu.yashiji.common.base.BaseDataFragment;
import com.shengshijingu.yashiji.common.util.ControllerUtils;
import com.shengshijingu.yashiji.common.util.ToastUtil;

/**
 * 创建时间  2020/3/111:42 PM .
 *
 * 作者  雷雷
 */

public class CommunicationLinkFragment extends BaseDataFragment {

    private EditText et_communicationlink_other, et_communicationlink_email, et_communicationlink_phone, et_communicationlink_name, et_communicationlink_content, et_communicationlink_title;

    private TextView tv_communication_commit;

    public static CommunicationLinkFragment getInstance() {
        CommunicationLinkFragment communicationLinkFragment = new CommunicationLinkFragment();
        return communicationLinkFragment;
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
        return R.layout.activity_communication_link;
    }

    @Override
    public void initView() {
        onFirstLoadSuccess();
        et_communicationlink_title = bindView(R.id.et_communicationlink_title);
        et_communicationlink_content = bindView(R.id.et_communicationlink_content);
        et_communicationlink_name = bindView(R.id.et_communicationlink_name);
        et_communicationlink_phone = bindView(R.id.et_communicationlink_phone);
        et_communicationlink_email = bindView(R.id.et_communicationlink_email);
        et_communicationlink_other = bindView(R.id.et_communicationlink_other);
        tv_communication_commit = bindView(R.id.tv_communication_commit);
        tv_communication_commit.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (clickTime()) {
            switch (view.getId()) {
                case R.id.tv_communication_commit:
                    if (TextUtils.isEmpty(et_communicationlink_title.getText().toString())) {
                        ToastUtil.showCenterToast(getActivity(), "请输入交流的标题题目");
                        return;
                    }

                    if (TextUtils.isEmpty(et_communicationlink_content.getText().toString())) {
                        ToastUtil.showCenterToast(getActivity(), "请输入交流的内容");
                        return;
                    }

                    if (TextUtils.isEmpty(et_communicationlink_name.getText().toString())) {
                        ToastUtil.showCenterToast(getActivity(), "请输入本人姓名");
                        return;
                    }

                    if (TextUtils.isEmpty(et_communicationlink_phone.getText().toString())) {
                        ToastUtil.showCenterToast(getActivity(), "请输入本人联系方式");
                        return;
                    }

                    if (TextUtils.isEmpty(et_communicationlink_email.getText().toString())) {
                        ToastUtil.showCenterToast(getActivity(), "请输入本人email");
                        return;
                    }

                    addContact();

                    break;
            }
        }
    }

    private void addContact() {
        showLoadingText();
        ControllerUtils.getHomeControllerInstance().addContact(et_communicationlink_title.getText().toString(), et_communicationlink_content.getText().toString(), et_communicationlink_name.getText().toString(), et_communicationlink_phone.getText().toString(), et_communicationlink_email.getText().toString(), et_communicationlink_other.getText().toString(), new NetObserver<Object>(Object.class) {
            @Override
            protected void onSuccess(Object o) {
                hideLoadingText();
                ToastUtil.showCenterToast(getActivity(), "提交成功");
                getActivity().finish();
            }

            @Override
            protected void onFail(String code, String msg) {
                hideLoadingText();

            }

            @Override
            protected void onError(String msg) {
                hideLoadingText();

            }
        });


    }


}

