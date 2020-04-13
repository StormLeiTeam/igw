package com.shengshijingu.yashiji.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.shengshijingu.yashiji.common.R;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by tjy on 2017/6/19.
 */
public class LoadingDialog extends Dialog {

    public LoadingDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    public LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
    }


    public LoadingDialog builder() {

        return this;
    }


    public static class Builder {

        private Context context;

        private String message;

        private boolean isShowMessage = true;

        private boolean isCancelable = false;

        private boolean isCancelOutside = false;


        public Builder(Context context) {
            this.context = context;
        }

        /**
         * 设置提示信息
         */

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        /**
         * 设置是否显示提示信息
         */
        public Builder setShowMessage(boolean isShowMessage) {
            this.isShowMessage = isShowMessage;
            return this;
        }

        /**
         * 设置是否可以按返回键取消
         */

        public Builder setCancelable(boolean isCancelable) {
            this.isCancelable = isCancelable;
            return this;
        }

        /**
         * 设置是否可以取消
         */
        public Builder setCancelOutside(boolean isCancelOutside) {
            this.isCancelOutside = isCancelOutside;
            return this;
        }

        public LoadingDialog create() {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.include_layout_loadding, null);
            LoadingDialog loadingDialog = new LoadingDialog(context, R.style.MyDialogStyle);
            GifImageView loading_progress = view.findViewById(R.id.loading_progress);
            TextView tv_loadding_tip = view.findViewById(R.id.tv_loadding_tip);
            tv_loadding_tip.setText(message);
            loading_progress.setImageResource(R.drawable.loading);
            loadingDialog.setContentView(view);
            loadingDialog.setCancelable(isCancelable);
            loadingDialog.setCanceledOnTouchOutside(true);
            return loadingDialog;

        }


    }
}
