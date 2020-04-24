package com.igw.igw.modoule.city.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.igw.igw.R
import com.igw.igw.activity.BaseActivity
import com.igw.igw.modoule.city.ShareLInkContract
import com.igw.igw.modoule.city.model.ShareLinkModel
import com.igw.igw.modoule.city.presenter.ShareLinkPresenter
import com.igw.igw.utils.LocaleUtils
import com.igw.igw.utils.StatusBarUtils
import com.igw.igw.widget.storm.StatusBarView
import kotlinx.android.synthetic.main.activity_share_link.*
import kotlinx.android.synthetic.main.common_status_bar.*
import kotlinx.android.synthetic.main.status_bar_view.*


/**
 * 添加交流联系
 *
 */
class ShareLinkActivity : BaseActivity<ShareLinkPresenter>(), ShareLInkContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_link)
    }

    override fun initView() {
        StatusBarUtils.setDarkMode(this)

        status_bar_main.setTitle(resources.getString(R.string.share_link_title))
        status_bar_main.setTitleTextSize(16F)
        status_bar_main.setConfirmVisible(View.VISIBLE)
        status_bar_main.setConfirmText("中/En")
        status_bar_main.setConfirmTextSize(15F)


        setUpListener()

    }

    private fun setUpListener() {

        status_bar_main.setOnConfirmClickListener(object :StatusBarView.OnConfirmClickListener{
            override fun onClick() {

                LocaleUtils.changeLocale(this@ShareLinkActivity)
            }

        })


        btn_submit.setOnClickListener {

            submitLink()
        }
    }

    private fun submitLink() {


    }

    override fun initPresenter() {

        mPresenter = ShareLinkPresenter(ShareLinkModel())
        mPresenter.attachView(this)

    }

    override fun getLayoutId(): Int = R.layout.activity_share_link

    override fun setTitle(): String = ""

    override fun setRightButton(): String = ""

    override fun setStatusBarColor(): Boolean = false


    override fun fail(o: Any?) {
        TODO("Not yet implemented")
    }

    override fun success(o: Any?) {
        TODO("Not yet implemented")
    }
}
