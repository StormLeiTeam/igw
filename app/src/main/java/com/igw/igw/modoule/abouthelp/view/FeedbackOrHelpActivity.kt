package com.igw.igw.modoule.abouthelp.view

import android.app.StatusBarManager
import android.content.Intent
import android.os.BaseBundle
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.animation.LinearInterpolator
import com.igw.igw.R
import com.igw.igw.activity.BaseActivity
import com.igw.igw.app.BaseAdapter
import com.igw.igw.bean.help.HelpBean
import com.igw.igw.modoule.abouthelp.HelpContract
import com.igw.igw.modoule.abouthelp.adapter.HelpAdapter
import com.igw.igw.modoule.abouthelp.model.FeedBackOrHelpModel
import com.igw.igw.modoule.abouthelp.presenter.FeedbackOrHelpPresenter
import com.igw.igw.utils.*
import com.igw.igw.widget.storm.StatusBarView
import com.shengshijingu.yashiji.common.util.ToastUtil
import kotlinx.android.synthetic.main.activity_feedback_or_help.*
import kotlinx.android.synthetic.main.common_status_bar.*

class FeedbackOrHelpActivity : BaseActivity<FeedbackOrHelpPresenter>(), HelpContract.View {

    companion object {

        val TAG = "FeedbackOrHelpActivity"

        public fun startSelf(activity: BaseActivity<*>) {

            val intent = Intent(activity, FeedbackOrHelpActivity::class.java)

            activity.startActivity(intent)
        }
    }


    var pageNum = 1
    var pageSize = 15

    var mDatas: MutableList<HelpBean.DataBean.RowsBean> = ArrayList()


    private lateinit var mAdapter: HelpAdapter

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_feedback_or_help)
//
//
//    }

    override fun getLayoutId(): Int = R.layout.activity_feedback_or_help


    override fun setStatusBarColor(): Boolean = false

    override fun onSuccessHelpList(bean: HelpBean.DataBean, isLoadMore: Boolean) {

        if (isLoadMore) {

            var rows = bean.rows as ArrayList

            for (index in 0..15) {

                var item = HelpBean.DataBean.RowsBean()

                item.helpContent = "帮助内容"
                item.helpTitle = "帮助标题"
                item.id = 1
                rows.add(item)

            }


            mDatas.addAll(rows)


            mAdapter.setFooterViewState(BaseAdapter.LOAD_END)
            mAdapter.refreshLoadMoreData(rows)
            mAdapter.setFooterViewState(BaseAdapter.LOAD_LOADING)

            pageNum++


        } else {

            mDatas = bean.rows as ArrayList

            for (index in 0..15) {

                var item = HelpBean.DataBean.RowsBean()

                item.helpContent = "帮助内容"
                item.helpTitle = "帮助标题"
                item.id = 1
                mDatas.add(item)

            }

            mAdapter.refreshData(mDatas)


        }
        // 假数据走起


    }

    override fun onFailHelpList(code: Int, msg: String, isLoadMore: Boolean) {

        if (isLoadMore) {
            mAdapter.setFooterViewState(BaseAdapter.LOAD_FAIL)


        } else {

            // 首次加载失败

        }
    }

    override fun onSuccessFeedBack() {

        //
        ToastUtil.showCenterToast(this, R.string.success_feedbback)

    }

    override fun onFailFeedBack(code: Int, msg: String) {

        ToastUtil.showCenterToast(this, R.string.fail_feedback)

    }

    override fun fail(o: Any?) {


    }

    override fun success(o: Any?) {
    }


    override fun initView() {


        StatusBarUtils.setDarkMode(this)
        status_bar_main.setTitle(this.resources.getString(R.string.title_help_feedback))
        status_bar_main.setTitleTextSize(16F)
        status_bar_main.setConfirmVisible(View.VISIBLE)
        status_bar_main.setConfirmText("中/en")
        status_bar_main.setConfirmTextColor(R.color.black_000000)
        status_bar_main.setConfirmTextSize(15F)

//        initRv()
        initData()

        initAdapter()

        setUpListener()


//        status_bar_main.setTitle()
//        status_bar_main.setTitle(this.resources.getString(R.string.titl))

    }

    private fun initAdapter() {


        mAdapter = HelpAdapter(this, true)
        mAdapter.setLoadingView(R.layout.item_common_loading_view)
        mAdapter.setLoadFailView(R.layout.item_common_loadfail_view)
        mAdapter.setLoadEndView(R.layout.item_common_loadend_view)

        mAdapter.setFooterViewState(BaseAdapter.LOAD_LOADING)
        mAdapter.setOnLoadMoreListener(object : BaseAdapter.OnLoadMoreListener {
            override fun onLoadMore(isClickLoadMore: Boolean) {


                mPresenter.getHelpList(pageNum, pageSize, true)
            }

        })

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        layoutManager.isAutoMeasureEnabled = true
        rv_help_list.layoutManager = layoutManager
        rv_help_list.adapter = mAdapter


    }

    private fun initData() {

        pageNum = 1
        mPresenter.getHelpList(pageNum, pageSize, false)

    }



    private fun setUpListener() {



//        status_bar_main.setOnConfirmClickListener(object :StatusBarView.OnConfirmClickListener{
//            override fun onClick() {
//
//                LogUtils.d(TAG, "点击了中引文切换 ")
//                LocaleUtils.changeLocale(this@FeedbackOrHelpActivity)
//
//            }
//
//        })

        mAdapter.onItemClickListener(object :HelpAdapter.OnItemClickListener{
            override fun onItemClick(data: HelpBean.DataBean.RowsBean?, position: Int) {


               var json  =  GsonUtils.instance.toJson(data)

                json?.let {
                    HelpInfoActivity.startSelfForData(this@FeedbackOrHelpActivity,json)
                }

            }

        })
        btn_submit.setOnClickListener {

            saveFeedBack()
        }


        status_bar_main.setOnConfirmClickListener(object : StatusBarView.OnConfirmClickListener {
            override fun onClick() {

                LocaleUtils.changeLocale(this@FeedbackOrHelpActivity)


            }

        })
    }


    private fun saveFeedBack() {

        var content = et_question.text.toString().trim()
        if (content.isEmpty()) {

            ToastUtil.showCenterToast(this, R.string.please_input_feedback)
            return

        }
        mPresenter.saveFeedback(content)
    }

    override fun initPresenter() {

        mPresenter = FeedbackOrHelpPresenter(FeedBackOrHelpModel())
        mPresenter.attachView(this)
    }

    override fun setTitle(): String = ""

    override fun setRightButton(): String = ""

}
