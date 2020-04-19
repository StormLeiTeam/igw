package com.igw.igw.modoule.login.view

import android.content.Intent
import android.support.v4.content.ContextCompat
import android.view.View
import com.igw.igw.R
import com.igw.igw.activity.BaseActivity
import com.igw.igw.modoule.login.LoginContract
import com.igw.igw.modoule.login.loginstate.LoginState
import com.igw.igw.modoule.login.presenter.LoginModePresenter
import com.igw.igw.mvp.presenter.BasePresenter
import com.igw.igw.mvp.view.IBaseView
import com.igw.igw.utils.LocaleUtils
import com.igw.igw.utils.LogUtils
import com.igw.igw.utils.StatusBarUtils
import com.shengshijingu.yashiji.common.dialog.LoadingDialog
import com.shengshijingu.yashiji.common.util.ToastUtil
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.crop__layout_done_cancel.*


/**
 * 登陆页面
 */
class LoginActivity : BaseActivity<LoginModePresenter>() , LoginContract.View  {


    companion object{

        val TAG = "LoginActivity"

        var mode_state: Int  = 1 // 默认为账号登陆模式

    }
    override fun initView() {


        StatusBarUtils.setColor(this, ContextCompat.getColor(this, R.color.colorF33))
        StatusBarUtils.setDarkMode(this)


        setUpListener()

    }

    private fun setUpListener() {




        tv_forget_pwd.setOnClickListener {

            // 忘记密码


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

        }

        // select lanuage  mode
        tv_language_select.setOnClickListener{

            if (LocaleUtils.needUpdateLocale(this, LocaleUtils.LOCALE_ENGLISH)) {
                LocaleUtils.saveUserLocale(this,LocaleUtils.LOCALE_ENGLISH)
                LocaleUtils.updateLocale(this, LocaleUtils.LOCALE_ENGLISH)

            }else{
                LocaleUtils.saveUserLocale(this,LocaleUtils.LOCALE_CHINESE)
                LocaleUtils.updateLocale(this, LocaleUtils.LOCALE_CHINESE)
            }

            resertAct()


        }

    }


    //登陆
    private fun login() {

        when(mode_state){

            1 -> {

                LogUtils.d(TAG,"账号登陆模式  ")


                if (et_accent.text.toString().trim().isEmpty()){
                    ToastUtil.showCenterToast(this,R.string.please_input_accent)
                    return
                }

                if (et_pwd.text.toString().trim().isEmpty()){
                    ToastUtil.showCenterToast(this,R.string.please_input_pwd)
                    return
                }


                var accent = et_accent.text.toString().trim()
                var pwd = et_pwd.text.toString().trim()
            }

            2 -> {

                LogUtils.d(TAG,"邮箱登陆模式 ")

                if (et_email.text.toString().trim().isEmpty()){
                    ToastUtil.showCenterToast(this,R.string.please_input_email)
                    return
                }

                if (et_code.text.toString().trim().isEmpty()){
                    ToastUtil.showCenterToast(this,R.string.please_input_code)
                    return
                }


                var email = et_email.text.toString().trim()
                var code = et_code.text.toString().trim()

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
    }

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun setTitle(): String {

        return ""
    }

    override fun setRightButton(): String {

        return  ""

    }

    override fun setStatusBarColor(): Boolean {
        return true
    }

    override fun fail(o: Any?) {

    }

    override fun success(o: Any?) {


    }


}
