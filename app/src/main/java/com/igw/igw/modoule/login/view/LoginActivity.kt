package com.igw.igw.modoule.login.view

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Paint
import android.support.v4.content.ContextCompat
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.View
import android.widget.Toast
import com.igw.igw.MainActivity
import com.igw.igw.R
import com.igw.igw.activity.BaseActivity
import com.igw.igw.bean.login.LoginBean
import com.igw.igw.modoule.login.LoginContract
import com.igw.igw.modoule.login.loginstate.LoginManager
import com.igw.igw.modoule.login.model.LoginModel
import com.igw.igw.modoule.login.presenter.LoginModePresenter
import com.igw.igw.utils.GsonUtils
import com.igw.igw.utils.LocaleUtils
import com.igw.igw.utils.LogUtils
import com.igw.igw.utils.StatusBarUtils
import com.igw.igw.widget.storm.TextClickPrivacy
import com.jakewharton.rxbinding2.view.RxView
import com.shengshijingu.yashiji.common.dialog.LoadingDialog
import com.shengshijingu.yashiji.common.util.ToastUtil
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import java.util.concurrent.TimeUnit


/**
 * 登陆页面
 */
class LoginActivity : BaseActivity<LoginModePresenter>(), LoginContract.View {


    companion object {

        val TAG = "LoginActivity"

        var mode_state: Int = 1 // 默认为账号登陆模式

        var MAX_COUNT_TIME: Long = 180

    }


    private var mCompositeDisposable: CompositeDisposable? = null

//    private var countTimeDisposable: Disposable? = null


    private var countTimeDisposable: Disposable? = null //倒计时

    override fun initView() {


        StatusBarUtils.setColor(this, ContextCompat.getColor(this, R.color.colorF33))
        StatusBarUtils.setDarkMode(this)

        mCompositeDisposable = CompositeDisposable()
        setUserAgreement()
        setUpListener()

    }


    fun setUserAgreement() {

        var content  =  resources.getString(R.string.user_agreement)
        var isEn = LocaleUtils.isLocaleEn(this)

        var contentCn = "我同意《i-gw用户协议》"
        var contentEn = "I agree <i-gw user agreement>"
//        if (isEn) {
//
//        }

        var spannable = SpannableStringBuilder(contentCn)

        tv_user_agreement.movementMethod = LinkMovementMethod.getInstance()

//        spannable = SpannableStringBuilder(contentEn)
//        spannable.setSpan(TextClickPrivacy(this), if (isEn) 7 else 3, contentEn.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//

        if (isEn) {
            spannable =  SpannableStringBuilder(contentEn)
            spannable.setSpan(TextClickPrivacy(this), 7, contentEn.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        } else {
            spannable =  SpannableStringBuilder(contentCn)

            spannable.setSpan(TextClickPrivacy(this), 3, contentCn.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        }


        tv_user_agreement.text = spannable

    }

    private fun setUpListener() {

        initCountTime()

//        tv_send_code.setOnClickListener {
//
//            sendVerifyCode()
//        }

        tv_forget_pwd.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        tv_forget_pwd.setOnClickListener {

            // 忘记密码

            ResetPasswordActivity.startSelf(this)



        }

        btn_login.setOnClickListener {

            login()
        }




        tv_email_mode.setOnClickListener {
            ll_account_mode.visibility = View.GONE
            ll_email_mode.visibility = View.VISIBLE

            mode_state = 2

        }

        tv_accent_mode.setOnClickListener {

            ll_account_mode.visibility = View.VISIBLE
            ll_email_mode.visibility = View.GONE

            mode_state = 1
        }







        btn_register.setOnClickListener {
            // 跳转到注册模块

            RegisterActivity.startSelf(this)

            finish()

        }

        // select lanuage  mode
        tv_language_select.setOnClickListener {


            LocaleUtils.changeLocale(this)

//
//            if (LocaleUtils.needUpdateLocale(this, LocaleUtils.LOCALE_ENGLISH)) {
//                LocaleUtils.saveUserLocale(this, LocaleUtils.LOCALE_ENGLISH)
//                LocaleUtils.updateLocale(this, LocaleUtils.LOCALE_ENGLISH)
//
//            } else {
//                LocaleUtils.saveUserLocale(this, LocaleUtils.LOCALE_CHINESE)
//                LocaleUtils.updateLocale(this, LocaleUtils.LOCALE_CHINESE)
//            }
//
//            resertAct()


        }

    }


    // 发送验证码
    private fun sendVerifyCode() {


        if (et_email.text.toString().trim().isEmpty()) {

            ToastUtil.showCenterToast(this, R.string.please_input_email)
            return

        }

        var email = et_email.text.toString().trim()
        mPresenter.sendEmailVerifyCode(email, 1)

        // 开启倒计时

        initCountTime()

    }

    @SuppressLint("CheckResult")
    private fun initCountTime() {

        countTimeDisposable = RxView.clicks(tv_send_code)
                .subscribeOn(AndroidSchedulers.mainThread())
                .flatMap(io.reactivex.functions.Function<Any, ObservableSource<Boolean>> {

                    if (et_email.text.toString().trim().isEmpty()) {

                        ToastUtil.showCenterToast(this, R.string.please_input_email)
                        return@Function Observable.empty()

                    }

                    return@Function Observable.just(true)
                }).flatMap(io.reactivex.functions.Function<Boolean, ObservableSource<Long>> {

                    tv_send_code.isEnabled = false
                    tv_send_code.setTextColor(ContextCompat.getColor(this, R.color.gray_9FA7B5))

                    tv_send_code.text = "$MAX_COUNT_TIME"

                    val email = et_email.text.toString().trim()
                    mPresenter.sendEmailVerifyCode(email, 2)

                    return@Function Observable.interval(1, TimeUnit.SECONDS, Schedulers.io())
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


    //登陆
    private fun login() {

//        showLoadingText("登录中...")


        when (mode_state) {

            1 -> {

                if (et_accent.text.toString().trim().isEmpty()) {
                    ToastUtil.showCenterToast(this, R.string.please_input_accent)
                    return
                }

                if (et_pwd.text.toString().trim().isEmpty()) {
                    ToastUtil.showCenterToast(this, R.string.please_input_pwd)
                    return
                }


                var accent = et_accent.text.toString().trim()
                var pwd = et_pwd.text.toString().trim()


//                mPresenter.loginByAccent()


                if (!cb_check.isChecked) {

                    ToastUtil.showCenterToast(this, R.string.check_user_agreement)

                    return
                }


                showLoadingText(resources.getString(R.string.loading_login))

                mPresenter.loginByAccent(accent, pwd)

            }

            2 -> {

                if (et_email.text.toString().trim().isEmpty()) {
                    ToastUtil.showCenterToast(this, R.string.please_input_email)
                    return
                }

                if (et_code.text.toString().trim().isEmpty()) {
                    ToastUtil.showCenterToast(this, R.string.please_input_code)
                    return
                }


                var email = et_email.text.toString().trim()
                var code = et_code.text.toString().trim()


                if (!cb_check.isChecked) {

                    ToastUtil.showCenterToast(this, R.string.check_user_agreement)

                    return
                }



                mPresenter.loginByEmail(email, code)

            }
        }


    }


    private fun resertAct() {

        finish()

        var intent = Intent(this, LoginActivity::class.java)

        startActivity(intent)
        overridePendingTransition(0, 0);


    }

    override fun initPresenter() {

        mPresenter = LoginModePresenter(LoginModel())
        mPresenter!!.attachView(this)

    }

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun setTitle(): String {

        return ""
    }

    override fun setRightButton(): String {

        return ""

    }

    override fun setStatusBarColor(): Boolean {
        return true
    }

    override fun loginSuccess(loginBean: LoginBean.DataBean) {
        //登陆成功
        hideLoadingText()
        LoginManager.instance.loginSuccess(GsonUtils.instance.toJson(loginBean))

        startMainActivity()
        finish()


        ToastUtil.showCenterToast(this, R.string.login_success)
        Log.e("12345", "登录成功");

    }

    private fun startMainActivity() {


        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun loginFail(code: String, msg: String) {

        hideLoadingText()



        ToastUtil.showCenterToast(this, msg.toString())


        LoginManager.instance.loginOut()

    }

    override fun sendVerifyCodeSuccess() {


//        Toast.makeText(this, "验证码已发送", Toast.LENGTH_SHORT).show()

        ToastUtil.showCenterToast(this, resources.getString(R.string.verify_code_sended))

    }

    override fun sendVerifyCodeFail(code: String, msg: String) {


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
            if (it.size() > 0) {
                it.clear()

            }
        }
        mCompositeDisposable = null

    }

}
