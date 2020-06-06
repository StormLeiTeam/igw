package com.igw.igw.modoule.abouthelp.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.load.data.LocalUriFetcher
import com.igw.igw.R
import com.igw.igw.activity.BaseActivity
import com.igw.igw.bean.help.HelpBean
import com.igw.igw.bean.help.HelpInfoBean
import com.igw.igw.modoule.abouthelp.HelpInfoContract
import com.igw.igw.modoule.abouthelp.model.HelpInfoModel
import com.igw.igw.modoule.abouthelp.presenter.HelpInfoPresenter
import com.igw.igw.utils.GsonUtils
import com.igw.igw.utils.LocaleUtils
import com.igw.igw.utils.LogUtils
import com.igw.igw.utils.StatusBarUtils
import com.igw.igw.widget.storm.StatusBarView
import kotlinx.android.synthetic.main.common_status_bar.*
import kotlinx.android.synthetic.main.status_bar_view.*

/**
 * 帮助具体详情页面
 */
class HelpInfoActivity : BaseActivity<HelpInfoPresenter>(),HelpInfoContract.View {



    companion object{

        val TAG = "HelpInfoActivity"


        public fun startSelfForData(activity: BaseActivity<*>, data:String){

            val intent = Intent(activity,HelpInfoActivity::class.java)
            intent.putExtra("intent_data",data)

            activity.startActivity(intent)

        }
    }


    private var mData : HelpBean.DataBean.RowsBean? = null


    override fun initView() {

        StatusBarUtils.setDarkMode(this)
        status_bar_main.setTitle(this.resources.getString(R.string.title_help_detail))
        status_bar_main.setTitleTextSize(16f)
        status_bar_main.setConfirmVisible(View.VISIBLE)
        status_bar_main.setConfirmText("中/En")
        status_bar_main.setConfirmTextSize(15F)

        initIntentData()

        setUpListener()


    }

    private fun initIntentData() {

        val json = intent.getStringExtra("intent_data") ?: return


        mData = GsonUtils.instance.fromJson(json, HelpBean.DataBean.RowsBean::class.java)
        mPresenter.getHelpInfo(mData!!.id)



    }

    private fun setUpListener() {




        status_bar_main.setOnConfirmClickListener(object :StatusBarView.OnConfirmClickListener{
            override fun onClick() {

                LocaleUtils.changeLocale(this@HelpInfoActivity)
            }


        })



    }

    override fun initPresenter() {

        mPresenter = HelpInfoPresenter(HelpInfoModel())
        mPresenter.attachView(this)

    }

    override fun getLayoutId(): Int = R.layout.activity_help_info



    override fun setTitle(): String {
        return ""
    }

    override fun setRightButton(): String  = ""

    override fun setStatusBarColor(): Boolean  = false




    override fun onSuccess(bean: HelpInfoBean.DataBean) {

        LogUtils.d(TAG,"获取帮助详情 onSuccess")
    }

    override fun onFail(code: Int, msg: String) {
        LogUtils.d(TAG,"获取帮助详情 onFail")

    }

    override fun fail(o: Any?) {
    }

    override fun success(o: Any?) {
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mPresenter != null){
            mPresenter.detachView()
        }
    }

}
