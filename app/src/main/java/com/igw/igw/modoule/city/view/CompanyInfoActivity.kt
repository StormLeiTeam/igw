package com.igw.igw.modoule.city.view

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.text.Html
import android.view.View
import android.widget.TextView
import com.bumptech.glide.load.data.LocalUriFetcher
import com.google.android.flexbox.FlexboxLayout
import com.igw.igw.R
import com.igw.igw.activity.BaseActivity
import com.igw.igw.bean.city.CompanyInfoBean
import com.igw.igw.modoule.city.CompanyInfoContract
import com.igw.igw.modoule.city.model.CompanyInfoModel
import com.igw.igw.modoule.city.presenter.CompanyInfoPresenter
import com.igw.igw.utils.*
import com.igw.igw.widget.storm.StatusBarView
import com.shengshijingu.yashiji.common.Constants
import com.shengshijingu.yashiji.common.util.ToastUtil
import kotlinx.android.synthetic.main.activity_company_info.*
import kotlinx.android.synthetic.main.activity_company_info.fbl_label
import kotlinx.android.synthetic.main.activity_search_city.*
import kotlinx.android.synthetic.main.common_status_bar.*

class CompanyInfoActivity : BaseActivity<CompanyInfoPresenter>(), CompanyInfoContract.View {


    companion object {

        val TAG = "CompanyInfoActivity"


        fun startSelf(activity: Activity, companyId: Int) {

            var intent = Intent(activity, CompanyInfoActivity::class.java)
            intent.putExtra("company_id", companyId)
            activity.startActivity(intent)
        }
    }

    private var companyID: Int = -1;

    override fun initView() {


        StatusBarUtils.setDarkMode(this)

        status_bar_main.setTitle(resources.getString(R.string.componyinfo_city_title))

        status_bar_main.setTitleTextSize(16F)
        status_bar_main.setConfirmVisible(View.VISIBLE)
        status_bar_main.setConfirmText("中/En")
        status_bar_main.setConfirmTextColor(R.color.black_000000)
        status_bar_main.setConfirmTextSize(15F)


        setUpData()
        setUpListener()

    }

    private fun setUpListener() {

        // 切换状态
        status_bar_main.setOnConfirmClickListener(object :
                StatusBarView.OnConfirmClickListener {
            override fun onClick() {

                LocaleUtils.changeLocale(this@CompanyInfoActivity,"company_id",companyID)
            }
        })
    }

    private fun setUpData() {




        companyID = intent.getIntExtra("company_id", -1)

        mPresenter.companyInfo(companyID)


    }

    override fun initPresenter() {
        mPresenter = CompanyInfoPresenter(CompanyInfoModel())
        mPresenter.attachView(this)

    }

    override fun getLayoutId(): Int = R.layout.activity_company_info

    override fun setTitle(): String {
        return ""
    }

    override fun setRightButton(): String {
        return ""


    }

    override fun setStatusBarColor(): Boolean {
        return false
    }

    override fun onSuccessCompanyInfo(dataBean: CompanyInfoBean.DataBean.CompanyBean) {

        updateUiForNet(dataBean)
    }

    private fun updateUiForNet(dataBean: CompanyInfoBean.DataBean.CompanyBean) {

        if (LocaleUtils.isLocaleEn(this)) {
            // 英文

            tv_city.text = dataBean.cityEnName
            GlideUtils.loadImage(this, Constants.BASE_URL + dataBean.companyLogo, iv_name)

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                tv_intro_info.text = Html.fromHtml(dataBean.enDesc, Html.FROM_HTML_MODE_LEGACY,MImageGetter(tv_intro_info,this),null);
            } else {
                tv_intro_info.text = Html.fromHtml(dataBean.enDesc,MImageGetter(tv_intro_info,this),null); }


//            for (label in dataBean.enLabelNames) {
//
//                addLabelView(label)
//
//
//            }
        } else {
            tv_city.text = dataBean.cityCnName

            GlideUtils.loadImage(this, Constants.BASE_URL + dataBean.companyLogo, iv_name)

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                tv_intro_info.text = Html.fromHtml(dataBean.cnDesc, Html.FROM_HTML_MODE_LEGACY,MImageGetter(tv_intro_info,this),null);
            } else {
                tv_intro_info.text = Html.fromHtml(dataBean.cnDesc,MImageGetter(tv_intro_info,this),null); }



//
//            for (label in dataBean.cnLabelNames) {
//
//                addLabelView(label)
//
//
//            }
        }




    }

    private fun addLabelView(label: String) {

        val textView = TextView(this)
        textView.text = label
        textView.setPadding(UIUtils.dp2px(11F).toInt(), UIUtils.dp2px(6.5F).toInt(),
                UIUtils.dp2px(11F).toInt(), UIUtils.dp2px(6.5F).toInt())

        textView.textSize = UIUtils.px2sp(UIUtils.sp2px(12F))
        textView.setTextColor(ContextCompat.getColor(this, R.color.black))
        textView.setBackgroundResource(R.drawable.bg_search_label)

        fbl_label.addView(textView)

        setMargin(textView, 10F, 10F, 10F, 10F)


    }

    /**
     * 设置 margin
     */
    fun setMargin(textView: View, left: Float, top: Float, right: Float, bottom: Float) {
        val layoutParams = textView.layoutParams

        if (layoutParams is FlexboxLayout.LayoutParams) {
            val params = layoutParams as FlexboxLayout.LayoutParams

            params.leftMargin = left.toInt()
            params.topMargin = top.toInt()
            params.rightMargin = right.toInt()
            params.bottomMargin = bottom.toInt()
        }
    }



    override fun onFailCompanyInfo(code: Int, msg: String) {

        ToastUtil.showCenterToast(this, msg)

    }

    override fun fail(o: Any?) {
        TODO("Not yet implemented")
    }

    override fun success(o: Any?) {
        TODO("Not yet implemented")
    }
}