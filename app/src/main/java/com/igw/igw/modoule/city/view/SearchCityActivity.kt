package com.igw.igw.modoule.city.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v13.view.inputmethod.EditorInfoCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.google.android.flexbox.FlexboxLayout
import com.igw.igw.R
import com.igw.igw.activity.BaseActivity
import com.igw.igw.app.BaseAdapter
import com.igw.igw.bean.city.CityLabelBean
import com.igw.igw.bean.city.CityResultBean
import com.igw.igw.modoule.city.CityContract
import com.igw.igw.modoule.city.adapter.CitySearchAdapter
import com.igw.igw.modoule.city.model.CityModel
import com.igw.igw.modoule.city.presenter.CityPresenter
import com.igw.igw.utils.*
import com.jakewharton.rxbinding2.view.RxView
import com.shengshijingu.yashiji.common.util.ToastUtil
import kotlinx.android.synthetic.main.activity_search_city.*
import kotlinx.android.synthetic.main.common_status_bar.*
import kotlin.math.log

class SearchCityActivity : BaseActivity<CityPresenter>(), CityContract.View {

    companion object {

        val TAG = "SearchCityActivity"


        public fun startSelf(activity: Activity, cityId: Int) {

            var intent = Intent(activity, SearchCityActivity::class.java)
            intent.putExtra("cityId", cityId)
            activity.startActivity(intent)

        }


    }

    private var cityIdExtra: Int = -1

    private var preTextView: TextView? = null

    /**
     * 搜索模式  1 为自定义搜索模式 2 为标签搜索模式
     *
     */
    private var searchModel: Int = 1
    private var currentLabelId: Int = -1

    private lateinit var mAdapter: CitySearchAdapter

    private var pageNum: Int = 1
    private var pageSize: Int = 15


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_search_city)
//    }

    override fun initView() {

        StatusBarUtils.setDarkMode(this)

        // 隐藏actionbar
        status_bar_main.setAppActionBarVisible(View.GONE)

        initAdapter()
        initData();
        setUpListener()


    }

    private fun initAdapter() {

        mAdapter = CitySearchAdapter(this, true)
        mAdapter.setLoadingView(R.layout.item_common_loadend_view)
        mAdapter.setLoadFailView(R.layout.item_common_loadfail_view)
        mAdapter.setLoadEndView(R.layout.item_common_loadend_view)


        val linearLayoutManager = LinearLayoutManager(this)

        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        rv_search_result.layoutManager = linearLayoutManager
        rv_search_result.adapter = mAdapter
        mAdapter.setFooterViewState(BaseAdapter.LOAD_LOADING)

        mAdapter.setOnLoadMoreListener(object : BaseAdapter.OnLoadMoreListener {
            override fun onLoadMore(isClickLoadMore: Boolean) {
                pageNum += 1

                getLoadMoreSearchResult(pageNum,pageSize)

            }

        })


        mAdapter.onItemClickListener(object:CitySearchAdapter.OnItemClickListener{
            override fun onItemClick(companyId: Int) {

                CompanyInfoActivity.startSelf(this@SearchCityActivity, companyId)

            }


        })
    }


    override fun onResume() {
        super.onResume()
        LogUtils.d(TAG,"获取的东西 ---onResume")


    }


    override fun onRestart() {
        super.onRestart()

        LogUtils.d(TAG,"获取的东西 ---onRestart")

    }

    private fun initData() {

        LogUtils.d(TAG,"获取的东西 ---initData")

        cityIdExtra = intent.getIntExtra("cityId", -1)
        LogUtils.d(TAG,"获取的东西 ---initData + $cityIdExtra")

        val localeEn = LocaleUtils.isLocaleEn(this)

        if (cityIdExtra != -1) {
            mPresenter.getCityLabel(cityIdExtra, if (localeEn) 2 else 1)

        }
    }

    private fun setUpListener() {
        tv_language_select.setOnClickListener{


            LocaleUtils.changeLocale(this, "cityId", cityIdExtra)



        }



        iv_edit_clear.setOnClickListener {


            et_search_content.text.clear()

        }

        et_search_content.setOnClickListener {


        }


        iv_back_to.setOnClickListener {

           finish()
        }



        et_search_content.setOnEditorActionListener { v, actionId, event ->


            if (actionId == EditorInfo.IME_ACTION_SEARCH) {


                LogUtils.d(TAG, "点击了搜索功能  ")

                searchModel = 1

                if (cityIdExtra != -1 && v.text.isNotEmpty()) {
                    pageNum = 1

                    mPresenter.getCitySearchResult(cityIdExtra, v.text.toString(), layoutId,
                            if (!LocaleUtils.isLocaleEn(this)) 1 else 2, 1, 15)


                    SoftInputUtils.hideSoftInput(this, et_search_content)

                }
                true
            } else {
                false
            }

        }


    }

    override fun initPresenter() {
        mPresenter = CityPresenter(CityModel())
        mPresenter.attachView(this)
    }

    override fun getLayoutId(): Int = R.layout.activity_search_city

    override fun setTitle(): String {

        return ""
    }

    override fun setRightButton(): String {

        return ""

    }

    override fun setStatusBarColor(): Boolean {
        return false
    }

    override fun onSuccessCityLabel(data: List<CityLabelBean.DataBean.LabelsBean>) {

        for (datum in data) {
            addLabelView(datum)


        }
    }

    private fun addLabelView(datum: CityLabelBean.DataBean.LabelsBean) {


        val textView = TextView(this)
        textView.text = datum.labelName
        textView.setPadding(UIUtils.dp2px(11F).toInt(), UIUtils.dp2px(6.5F).toInt(),
                UIUtils.dp2px(11F).toInt(), UIUtils.dp2px(6.5F).toInt())

        textView.textSize = UIUtils.px2sp(UIUtils.sp2px(12F))
        textView.setTextColor(ContextCompat.getColor(this, R.color.black))
        textView.setBackgroundResource(R.drawable.bg_search_label)

        fbl_label.addView(textView)

        setMargin(textView, 10F, 10F, 10F, 10F)


//       text

        currentLabelId = datum.id

        textView.setOnClickListener { view ->

            val content = textView.text.toString()
            et_search_content.setText(content)
            et_search_content.clearFocus()

            (view as TextView).setTextColor(ContextCompat.getColor(this, R.color.white))
            (view as TextView).setBackgroundResource(R.drawable.bg_search_label_selected)


            preTextView?.let { it ->

                it.setTextColor(ContextCompat.getColor(this, R.color.black))
                it.setBackgroundResource(R.drawable.bg_search_label)


            }


            preTextView = view as TextView


            searchModel = 2
            //

//            var tag = view.getTag(1) as CityLabelBean.DataBean.LabelsBean
//
//
//            // 请求数据
            cityIdExtra.let {

                if (it != -1 && currentLabelId != -1) {
                    pageNum = 1
                    mPresenter.getCitySearchResult(it, "", currentLabelId,
                            if (!LocaleUtils.isLocaleEn(this)) 1 else 2, 1, 15)

                }

            }
        }


    }


    fun getLoadMoreSearchResult(pageNum: Int, pageSize: Int) {

        if (cityIdExtra == -1) {


            when (searchModel) {
                1 -> { // 自定义模式

//                    if (cityIdExtra != -1 && v.text.isNotEmpty()) {
//
//                        mPresenter.getCitySearchResult(cityIdExtra, v.text.toString(), -1,
//                                if (!LocaleUtils.isLocaleEn(this)) 1 else 2, 1, 15)
//
//                    }

                    if (et_search_content.text.toString().isNotEmpty()) {

                        mPresenter.getCitySearchResultLoadMore(1, cityIdExtra, et_search_content.text.toString(), -1,
                                if (!LocaleUtils.isLocaleEn(this)) 1 else 2, pageNum, pageSize)

                    }
                }


                2 -> {// 标签模式

                    if (currentLabelId != -1) {
                        mPresenter.getCitySearchResultLoadMore(2, cityIdExtra, "", currentLabelId,
                                if (!LocaleUtils.isLocaleEn(this)) 1 else 2, pageNum, pageSize)

                    }

                }
            }
        }
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

    override fun onFailCityLabel(code: String, msg: String) {


    }

    override fun onSuccessCitySearch(data: List<CityResultBean.DataBean.RowsBean>) {

        mAdapter.setFooterViewState(BaseAdapter.LOAD_END)
        mAdapter.refreshData(data)
        mAdapter.setFooterViewState(BaseAdapter.LOAD_LOADING)


    }

    override fun onFailCitySearch(code: Int, msg: String) {

        ToastUtil.showCenterToast(this, msg)

    }

    override fun onSuccessCityLoadMore(data: List<CityResultBean.DataBean.RowsBean>) {

        if (data.isEmpty()) {
            mAdapter.setFooterViewState(BaseAdapter.LOAD_INIT)
        }else{

            mAdapter.setFooterViewState(BaseAdapter.LOAD_END)
            mAdapter.refreshLoadMoreData(data)
            mAdapter.setFooterViewState(BaseAdapter.LOAD_LOADING)
        }


    }

    override fun onFailCityLoadMore(code: Int, msg: String) {

        mAdapter.setFooterViewState(BaseAdapter.LOAD_FAIL)

        ToastUtil.showCenterToast(this, msg)

    }

    override fun fail(o: Any?) {
    }

    override fun success(o: Any?) {
    }
}