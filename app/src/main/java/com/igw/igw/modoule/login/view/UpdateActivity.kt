package com.igw.igw.modoule.login.view

import android.content.Intent
import android.util.Log
import android.view.View
import com.igw.igw.R
import com.igw.igw.activity.BaseActivity
import com.igw.igw.modoule.login.UpdatePwdContract
import com.igw.igw.modoule.login.model.UpdatePwdModel
import com.igw.igw.modoule.login.presenter.UpdatePwdPresenter
import com.igw.igw.utils.LocaleUtils
import com.igw.igw.utils.StatusBarUtils
import com.igw.igw.widget.storm.StatusBarView
import com.shengshijingu.yashiji.common.Constants
import com.shengshijingu.yashiji.common.util.ToastUtil
import kotlinx.android.synthetic.main.activity_update.*
import kotlinx.android.synthetic.main.common_status_bar.*

/**
 *
 */
class UpdateActivity : BaseActivity<UpdatePwdPresenter>(), UpdatePwdContract.View {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_update)
//    }

    override fun initView() {

        StatusBarUtils.setDarkMode(this)
        status_bar_main.setTitle(resources.getString(R.string.update_pwd_title))
        status_bar_main.setTitleTextSize(16F)
        status_bar_main.setConfirmVisible(visible = View.VISIBLE)
        status_bar_main.setConfirmText("中/en")
        status_bar_main.setConfirmTextColor(R.color.black_000000)
        status_bar_main.setConfirmTextSize(15F)


        setUpListener()

        Log.e("12345", Constants.authorization);
    }

    private fun setUpListener() {


        status_bar_main.setOnConfirmClickListener(object : StatusBarView.OnConfirmClickListener {
            override fun onClick() {

                LocaleUtils.changeLocale(this@UpdateActivity)

            }
        })
        btn_submit.setOnClickListener {

            updatePwd()
        }
    }

    override fun initPresenter() {


        mPresenter = UpdatePwdPresenter(UpdatePwdModel())
        mPresenter.attachView(this)
    }

    private fun updatePwd() {


        if (et_original_pwd.text.toString().trim().isEmpty()) {

            ToastUtil.showCenterToast(this, R.string.warning_input_original_pwd)

            return
        }

        if (et_new_pwd.text.toString().trim().isEmpty()) {

            ToastUtil.showCenterToast(this, R.string.warning_input_new_pwd)

            return
        }

        if (et_new_pwd_again.text.toString().trim().isEmpty()) {

            ToastUtil.showCenterToast(this, R.string.warning_input_new_pwd_again)

            return
        }


        var original = et_original_pwd.text.toString().trim()
        var newPwd = et_new_pwd.text.toString().trim()
        var newPwdAgain = et_new_pwd_again.text.toString().trim()

        if (!newPwd.equals(newPwdAgain)) {
            ToastUtil.showCenterToast(this, R.string.waring_password_inconsistency)

            return
        }



        mPresenter.updatePwd(original, newPwd)
        showLoadingText()

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_update
    }

    override fun setTitle(): String {
        return ""
    }

    override fun setRightButton(): String {
        return ""
    }

    override fun setStatusBarColor(): Boolean {

        return false
    }

    companion object {
        val TAG = "UpdateActivity"

        fun startSelf(activity: BaseActivity<*>) {

            var intent = Intent(activity, UpdateActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onSuccess() {
        hideLoadingText()
        //登出
        ToastUtil.showCenterToast(this, R.string.toast_update_pwd_success)
        finish()
    }

    override fun onFail(code: Int, msg: String) {
        hideLoadingText()
        // 是否需要英文提示
        ToastUtil.showCenterToast(this, msg)
    }

    override fun fail(o: Any?) {
    }

    override fun success(o: Any?) {

    }
}
