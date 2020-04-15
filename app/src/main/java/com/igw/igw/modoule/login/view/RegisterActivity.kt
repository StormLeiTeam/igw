package com.igw.igw.modoule.login.view

import android.content.Intent
import android.content.LocusId
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.view.Gravity
import com.igw.igw.R
import com.igw.igw.activity.BaseActivity
import com.igw.igw.bean.NationalityBean
import com.igw.igw.modoule.login.RegisterContract
import com.igw.igw.modoule.login.model.RegisterModel
import com.igw.igw.modoule.login.presenter.RegisterPresenter
import com.igw.igw.utils.LocaleUtils
import com.igw.igw.utils.LogUtils
import com.igw.igw.utils.StatusBarUtils
import com.igw.igw.widget.storm.TextClickPrivacy
import com.igw.igw.widget.storm.popwindowselect.popselectview.WheelViewPopupwindow
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.common_status_bar.*
import java.util.*

/**
 * 注册模块
 */

class RegisterActivity : BaseActivity<RegisterPresenter>(), RegisterContract.View {


    // 国籍选择器
    private var nationalityPopWindow: WheelViewPopupwindow<NationalityBean.DataBean.CountrysBean>? = null

    //国籍
    private var mCountrys: List<NationalityBean.DataBean.CountrysBean>? = null


    override fun initView() {


        StatusBarUtils.setDarkMode(this)
        status_bar_main.setTitle("注册")
        getSystemNationality()

        setUpPop()
        requestNationality();

        setUserAgreement()

        setUpListener()

    }

    /**
     * 获取系统的手机语言状太
     */
    private fun getSystemNationality() {


        val locale = LocaleUtils.getCurrentlocale(this)
        if (locale.equals(Locale.ENGLISH)) {
            LogUtils.d(TAG, "当前用户保存的是english")
            LocaleUtils.updateLocale(this, LocaleUtils.LOCALE_ENGLISH)

        } else {
            LogUtils.d(TAG, "当前用户保存的是 中文")

            LocaleUtils.updateLocale(this, LocaleUtils.LOCALE_CHINESE)

        }


    }

    private fun setUpListener() {


        /**
         * 国籍选中监听
         */
        nationalityPopWindow?.onCommitlistener { data, position ->


        }




        tv_select_nationality.setOnClickListener {

            if (nationalityPopWindow != null && !nationalityPopWindow!!.isShowing) {
                nationalityPopWindow!!.setAnimationStyles(R.style.pop_anim)
                nationalityPopWindow!!.showAtLocation(root_main, Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 0)
            }
        }

    }

    /**
     *  网络请求国籍
     */
    private fun requestNationality() {

        mPresenter.getNationalityData()

    }


    /**
     * 创建pop
     */
    private fun setUpPop() {

        // 国籍选择
        nationalityPopWindow = WheelViewPopupwindow<NationalityBean.DataBean.CountrysBean>(this)


    }


    override fun initPresenter() {

        mPresenter = RegisterPresenter(RegisterModel())
        mPresenter.attachView(this)


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


    fun setUserAgreement() {

        var content = "我同意《i-gw用户协议》"

        val spannable = SpannableStringBuilder(content)
        tv_user_agreement.movementMethod = LinkMovementMethod.getInstance()
        spannable.setSpan(TextClickPrivacy(this), 3, content.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_user_agreement.text = spannable

    }

    companion object {


        val TAG = "RegisterActivity"


        public fun startSelf(activity: BaseActivity<*>) {

            var intent = Intent(activity, RegisterActivity::class.java)

            activity.startActivity(intent)

        }

    }

    override fun fail(o: Any?) {
    }

    override fun success(o: Any?) {
    }

    /**
     *  国籍数据
     */
    fun showNationality(countrys: List<NationalityBean.DataBean.CountrysBean>) {

        this.mCountrys = countrys

        nationalityPopWindow?.let {


            it.setData(mCountrys)
            when (LocaleUtils.getCurrentlocale(this)) {

                LocaleUtils.LOCALE_ENGLISH -> {

//                    mCountrys.in

                    for (item: NationalityBean.DataBean.CountrysBean in mCountrys!!) {


                        item.isEnglish = true
                    }
                }

                LocaleUtils.LOCALE_CHINESE -> {

                    for (item: NationalityBean.DataBean.CountrysBean in mCountrys!!) {


                        item.isEnglish = false
                    }
                }
            }

            it.setSelectItemPosition(0)
        }


    }
}
