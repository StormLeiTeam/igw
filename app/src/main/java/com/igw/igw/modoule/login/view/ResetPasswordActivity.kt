package com.igw.igw.modoule.login.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import com.igw.igw.R
import com.igw.igw.activity.BaseActivity
import com.igw.igw.modoule.login.ResetPasswordContract
import com.igw.igw.modoule.login.model.ResetPasswordModel
import com.igw.igw.modoule.login.presenter.ResetPwdPresenter
import com.igw.igw.utils.LocaleUtils
import com.igw.igw.utils.StatusBarUtils
import com.igw.igw.widget.storm.StatusBarView
import com.jakewharton.rxbinding2.view.RxView
import com.shengshijingu.yashiji.common.util.ToastUtil
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_reset_password.*
import kotlinx.android.synthetic.main.activity_reset_password.btn_submit
import kotlinx.android.synthetic.main.activity_reset_password.et_email
import kotlinx.android.synthetic.main.activity_reset_password.et_password_again
import kotlinx.android.synthetic.main.common_status_bar.*
import kotlinx.android.synthetic.main.status_bar_view.*

/**
 * 重置密码
 */
class ResetPasswordActivity : BaseActivity<ResetPwdPresenter>(), ResetPasswordContract.View {


    companion object {

        val TAG = "ResetPasswordActivity"

        var MAX_COUNT_TIME: Long = 60
    }


    private var mCompositeDisposable: CompositeDisposable? = null


    private var countTimeDisposable: Disposable? = null //倒计时


    override fun initView() {
        StatusBarUtils.setDarkMode(this)
        status_bar_main.setTitle(resources.getString(R.string.reset_pwd_title))
        status_bar_main.setTitleTextSize(16F)
        status_bar_main.setConfirmVisible(View.VISIBLE)
        status_bar_main.setConfirmText("中/英")
        status_bar_main.setConfirmTextColor(R.color.black_000000)
        status_bar_main.setConfirmTextSize(15F)


        mCompositeDisposable = CompositeDisposable()


        setUpListener()

    }

    private fun setUpListener() {


        status_bar_main.setOnConfirmClickListener(object :StatusBarView.OnConfirmClickListener{
            override fun onClick() {


                LocaleUtils.changeLocale(this@ResetPasswordActivity)
            }

        })

        initCountTime()

        btn_submit.setOnClickListener {

            resetPassword()
        }

    }


    private fun initCountTime() {

        countTimeDisposable = RxView.clicks(tv_send_code)
                .subscribeOn(AndroidSchedulers.mainThread())
                .flatMap(io.reactivex.functions.Function<Any, ObservableSource<Boolean>> {

                    if (et_email.text.toString().trim().isEmpty()) {

                        ToastUtil.showCenterToast(this, R.string.please_input_email)
                        return@Function Observable.empty()

                    }

                    return@Function Observable.just(true)
                }).flatMap(io.reactivex.functions.Function<kotlin.Boolean, io.reactivex.ObservableSource<kotlin.Long>> {

                    tv_send_code.isEnabled = false
                    tv_send_code.setTextColor(android.support.v4.content.ContextCompat.getColor(this, com.igw.igw.R.color.gray_9FA7B5))

                    tv_send_code.text = "$MAX_COUNT_TIME"

                    val email = et_email.text.toString().trim()
                    mPresenter.sendEmailVerifyCode(email, 1)

                    return@Function io.reactivex.Observable.interval(1, java.util.concurrent.TimeUnit.SECONDS, io.reactivex.schedulers.Schedulers.io())
                            .take(MAX_COUNT_TIME)
                            .map {
                                return@map MAX_COUNT_TIME - (it + 1)
                            }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe {

                    if (it.toInt() == 0) {
                        tv_send_code.text = resources.getText(R.string.login_send_code)
                        tv_send_code.isEnabled = true
                        tv_send_code.setTextColor(ContextCompat.getColor(this, R.color.black_FF333333))

                    } else {
                        tv_send_code.text = it.toLong().toString()

                    }
                }

        mCompositeDisposable?.add(countTimeDisposable!!)


    }


    /**
     * 重置密码
     *
     */
    private fun resetPassword() {


        if (et_verify_code.text.toString().trim().isEmpty()){

            ToastUtil.showCenterToast(this,R.string.verify_not_empty)
            return
        }


        if (et_new_pwd.text.toString().trim().isEmpty()){
            ToastUtil.showCenterToast(this,R.string.warning_input_new_pwd)
            return
        }


        var password = et_new_pwd.text.toString().trim()
        var passwordAgain = et_password_again.text.toString().trim()

        if (!password.equals(passwordAgain)){

            ToastUtil.showCenterToast(this,R.string.waring_password_inconsistency)
            return
        }


        var code = et_verify_code.text.toString().trim()
        var email = et_email.text.toString().trim()



        mPresenter.resetPassword(email, code, password)


    }

    override fun initPresenter() {
        mPresenter = ResetPwdPresenter(ResetPasswordModel())
        mPresenter.attachView(this)
    }

    override fun getLayoutId(): Int {

        return  R.layout.activity_reset_password
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

    override fun onFailToCode(code: Int, msg: String) {

        ToastUtil.showCenterToast(this,msg)


    }

    override fun onSuccess() {


        ToastUtil.showCenterToast(this,R.string.reset_pwd_success)
        finish()
    }

    override fun onFailToReset(code: Int, msg: String) {

        ToastUtil.showCenterToast(this,msg)
    }

    override fun fail(o: Any?) {
    }

    override fun success(o: Any?) {
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
        mCompositeDisposable?.let {
            if (it.size()>0){
                it.clear()
            }
        }
        mCompositeDisposable = null
    }
}
