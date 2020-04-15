package com.igw.igw.modoule.login.view

import android.content.Intent
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import com.igw.igw.R
import com.igw.igw.activity.BaseActivity
import com.igw.igw.bean.NationalityBean
import com.igw.igw.mvp.presenter.BasePresenter
import com.igw.igw.mvp.view.IBaseView
import com.igw.igw.utils.StatusBarUtils
import com.igw.igw.widget.storm.TextClickPrivacy
import com.igw.igw.widget.storm.popwindowselect.popselectview.WheelViewPopupwindow
import kotlinx.android.synthetic.main.activity_register.*

/**
 * 注册模块
 */

class RegisterActivity : BaseActivity<BasePresenter<IBaseView>>() {


    private var  nationalityPopWindow : WheelViewPopupwindow<NationalityBean.DataBean.CountrysBean>? = null





    override fun initView() {


        StatusBarUtils.setDarkMode(this)

        setUpPop()



        setUserAgreement()


    }


    /**
     * 创建pop
     */
    private fun setUpPop() {

        // 国籍选择
        nationalityPopWindow = WheelViewPopupwindow<NationalityBean.DataBean.CountrysBean>(this)




    }


    override fun initPresenter() {


    }

    override fun getLayoutId(): Int = R.layout.activity_register

    override fun setTitle(): String {
        return ""
    }

    override fun setRightButton(): String {
     return ""
    }

    override fun setStatusBarColor(): Boolean {

        return true
    }





    fun setUserAgreement(){

        var  content = "我同意《i-gw用户协议》"

        val spannable = SpannableStringBuilder(content)
        tv_user_agreement.movementMethod = LinkMovementMethod.getInstance()
        spannable.setSpan(TextClickPrivacy(this), 3, content.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_user_agreement.text = spannable

    }

    companion object{


        val TAG = "RegisterActivity"


        public fun  startSelf(activity: BaseActivity<*>){

            var intent = Intent(activity, RegisterActivity::class.java)

            activity.startActivity(intent)

        }

    }
}
