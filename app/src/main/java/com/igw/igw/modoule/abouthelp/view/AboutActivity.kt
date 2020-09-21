package com.igw.igw.modoule.abouthelp.view

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import com.igw.igw.R
import com.igw.igw.activity.BaseActivity
import com.igw.igw.mvp.presenter.BasePresenter
import com.igw.igw.utils.LocaleUtils
import com.igw.igw.utils.LogUtils
import com.igw.igw.utils.StatusBarUtils
import com.igw.igw.widget.storm.StatusBarView
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.android.synthetic.main.common_status_bar.*


class AboutActivity : BaseActivity<BasePresenter<*,*>>() {


    companion object {

        val TAG = "AboutActivity"


        fun startSelf(activity: Activity) {

            var intent = Intent(activity, AboutActivity::class.java)
            activity.startActivity(intent)

        }
    }


    var cnContent = "<p>I-GW以城市为单位，通过万能的互联网，架设城市与城市、企业\n" +
            "    与企业、人与人之间的桥梁。</p><br>\n" +
            "<p>“城市驿站”，为各城市打造自己的线上城市展览馆，展现城市概\n" +
            "    况、城市中知名企业、知名景点、著名文化艺术精典。既可成为\n" +
            "    线上的城市企业采购交易平台（“采购交易展”）、知识产权交易\n" +
            "    平台（“知识产权交易会”）、招商引资平台（“招商博览会”）、\n" +
            "    旅游交易平台（“旅交会”），又可办为文化艺术宣传平台（“文\n" +
            "    化艺术展”），还可办为综合展示平台（“嘉年华”）。根据城市\n" +
            "    主办方需求而定，可随时更换主题及展示内容。成为城市365天，\n" +
            "    7x24小时线上不落幕的线上展台。</p><br>\n" +
            "\n" +
            "<p>“双语聊天城”，配合“城市驿站”， 既可做为交流的聊天室，也\n" +
            "    可做为城市\n" +
            "    或企业发布会的交流大厅。可在城市驿站内容中介绍某日某时在\n" +
            "    双语聊天城商务洽谈室中召开线上发布大会。由商会或企业主持，\n" +
            "    在聊天室中介绍内容，并现场答疑。中、英双语的支持，更便于\n" +
            "    国际间的交流。</p>"


    var enContent = "<p>I-GW以城市为单位，通过万能的互联网，架设城市与城市、企业\n"

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_about)
//
//        initView()
//
//        setUpListener()
//    }

    private fun setUpListener() {


        status_bar_main.setOnConfirmClickListener(object : StatusBarView.OnConfirmClickListener {
            override fun onClick() {

                LogUtils.d(TAG, "点击切换 关于页面  ")
                LocaleUtils.changeLocale(this@AboutActivity)

            }
        })
    }

//    private fun initView() {
//
//        //  StatusBarUtils.setTransparentForWindowBar(this)
//        StatusBarUtils.setDarkMode(this)
//
//        status_bar_main.setTitle(this.resources.getString(R.string.title_about_activity))
//        status_bar_main.setTitleTextSize(16F)
//        status_bar_main.setConfirmVisible(View.VISIBLE)
//        status_bar_main.setConfirmText("中/en")
//        status_bar_main.setConfirmTextColor(R.color.black_000000)
//        status_bar_main.setConfirmTextSize(15F)
//
//
//        if (LocaleUtils.isLocaleEn(this)) {
//            //英语
//            tv_content.text = Html.fromHtml(enContent)
//
//        } else {
//            //中文
//
//            tv_content.text = Html.fromHtml(cnContent)
//
//
//        }
//
//    }

    override fun initPresenter() {


    }

    override fun getLayoutId(): Int = R.layout.activity_about


    override fun setTitle(): String {
        TODO("Not yet implemented")
    }

    override fun setRightButton(): String {
        return  ""
    }

    override fun setStatusBarColor(): Boolean {
      return false
    }

    override fun initView() {

        //  StatusBarUtils.setTransparentForWindowBar(this)
        StatusBarUtils.setDarkMode(this)

        status_bar_main.setTitle(this.resources.getString(R.string.title_about_activity))
        status_bar_main.setTitleTextSize(16F)
        status_bar_main.setConfirmVisible(View.VISIBLE)
        status_bar_main.setConfirmText("中/en")
        status_bar_main.setConfirmTextColor(R.color.black_000000)
        status_bar_main.setConfirmTextSize(15F)


        if (LocaleUtils.isLocaleEn(this)) {
            //英语
            tv_content.text = Html.fromHtml(enContent)

        } else {
            //中文

            tv_content.text = Html.fromHtml(cnContent)


        }

        setUpListener()
    }
}