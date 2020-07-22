package com.igw.igw.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.media.tv.TvView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.load.data.LocalUriFetcher;
import com.igw.igw.MainActivity;
import com.igw.igw.R;
import com.igw.igw.app.BaseAdapter;
import com.igw.igw.bean.CityBean;
import com.igw.igw.bean.CityInfoBean;
import com.igw.igw.bean.city.CityCompanyBean;
import com.igw.igw.modoule.city.adapter.CityCompanyAdapter;
import com.igw.igw.modoule.city.view.CompanyInfoActivity;
import com.igw.igw.modoule.city.view.SearchCityActivity;
import com.igw.igw.modoule.im.RecentChat1Activity;
import com.igw.igw.network.NetObserver;
import com.igw.igw.utils.ActivityUtils;
import com.igw.igw.utils.ColorUtils;
import com.igw.igw.utils.LocaleUtils;
import com.igw.igw.utils.LogUtils;
import com.igw.igw.utils.MImageGetter;
import com.igw.igw.utils.SharedUtils;
import com.shengshijingu.yashiji.common.base.BaseDataFragment;
import com.shengshijingu.yashiji.common.controller.Controller;
import com.shengshijingu.yashiji.common.util.ControllerUtils;
import com.shengshijingu.yashiji.common.util.ToastUtil;

import org.jetbrains.annotations.Nullable;

/**
 * 创建时间  2020/3/105:42 PM .
 * <p>
 * 作者  雷雷
 */

public class CityFragment extends BaseDataFragment {

    public static final String TAG = "CityFragment";

    private LinearLayout ll_city;

    private ArrayList<CityBean.DataBean.CitysBean> options1Items = new ArrayList<>(); //省

    private OptionsPickerView pvOptions;

    private TextView tv_basetitle;

    private ImageView iv_citystation_cooperation;

    private TextView tv_city_cityInfo, tv_city_businessCooperation, tv_city_html;

    private ImageView iv_search;

    private WebView webView;

    private LinearLayout ll_buss_content;
    private RecyclerView rv_company;
    private TextView tv_language_select;



    private int cityType = 1;

    private int cityId = -1;
    private int pageNum = 1;

    private CityCompanyAdapter mCityCompanyAdapter;

    public static CityFragment getInstance() {
        CityFragment cityFragment = new CityFragment();
        return cityFragment;
    }

    @Override
    protected void onReloadData(int type) {

    }

    @Override
    protected boolean onClickImageReload() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.city_fragment;
    }

    @Override
    public void initView() {
        onFirstLoadSuccess();
        ll_city = bindView(R.id.ll_city);
        iv_citystation_cooperation = bindView(R.id.iv_citystation_cooperation);
        tv_basetitle = bindView(R.id.tv_basetitle);
        tv_city_businessCooperation = bindView(R.id.tv_city_businessCooperation);
        tv_city_cityInfo = bindView(R.id.tv_city_cityInfo);
        tv_city_html = bindView(R.id.tv_city_html);
        iv_citystation_cooperation.setOnClickListener(this);
        ll_city.setOnClickListener(this);

        tv_city_businessCooperation.setOnClickListener(this);
        tv_city_cityInfo.setOnClickListener(this);

        tv_city_html.setMovementMethod(ScrollingMovementMethod.getInstance());

        iv_search = bindView(R.id.iv_search);
        iv_search.setOnClickListener(this);

        webView = bindView(R.id.web);

        ll_buss_content = bindView(R.id.ll_buss_content);
        tv_language_select = bindView(R.id.tv_language_select);

        rv_company = bindView(R.id.tv_company);

        initAdapter();

        cityDetail(1);
        cityId = 1;


        tv_basetitle.setText(mContext.getResources().getString(R.string.beijing));



        setUpListener();


    }

    private void setUpListener() {

        tv_language_select.setOnClickListener(v -> {


            ((MainActivity)mContext).changeLanuage(R.id.ll_main_order);
        });
    }

    private void initAdapter() {

        mCityCompanyAdapter = new
                CityCompanyAdapter(mContext, true);

        mCityCompanyAdapter.setLoadingView(R.layout.item_common_loadend_view);
        mCityCompanyAdapter.setLoadFailView(R.layout.item_common_loadfail_view);
        mCityCompanyAdapter.setLoadEndView(R.layout.item_common_loadend_view);


        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        rv_company.setLayoutManager(layoutManager);

        rv_company.setAdapter(mCityCompanyAdapter);

        mCityCompanyAdapter.setOnLoadMoreListener(new BaseAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isClickLoadMore) {
                pageNum += 1;

                if (cityId != -1) {

                    getCityCompanyDataMore(cityId, pageNum, 15);

                }
            }
        });

        mCityCompanyAdapter.onItemClickListener(new CityCompanyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int companyId) {

                CompanyInfoActivity.Companion.startSelf((MainActivity) mContext, companyId);

            }
        });


    }

    private void getCityCompanyDataMore(int cityId, int pageNum, int pageSize) {

        ControllerUtils.getCityController().companyCityList(cityId, pageNum, pageSize, new NetObserver<CityCompanyBean.DataBean>(CityCompanyBean.DataBean.class) {
            @Override
            protected void onError(@Nullable String msg) {

            }

            @Override
            protected void onFail(int code, @Nullable String msg) {
                mCityCompanyAdapter.setFooterViewState(BaseAdapter.Companion.getLOAD_FAIL());
            }

            @Override
            protected void onSuccess(CityCompanyBean.DataBean dataBean) {

                if (dataBean.getRows().isEmpty()) {
                    mCityCompanyAdapter.setFooterViewState(BaseAdapter.Companion.getLOAD_INIT());
                } else {

                    mCityCompanyAdapter.setFooterViewState(BaseAdapter.Companion.getLOAD_END());
                    mCityCompanyAdapter.refreshLoadMoreData(dataBean.getRows());
                    mCityCompanyAdapter.setFooterViewState(BaseAdapter.Companion.getLOAD_LOADING());
                }


            }
        });

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (clickTime()) {
            switch (view.getId()) {
                case R.id.tv_city_businessCooperation:

                    cityType = 2;

                    if (cityId != -1) {
                        getCityCompanyData(cityId, pageNum, 15);

                    }
                    setCityData();
                    break;
                case R.id.tv_city_cityInfo:
                    cityType = 1;

                    setCityData();
                    break;


                case R.id.ll_city:
                    cityList();
                    break;
                case R.id.iv_citystation_cooperation:
                    ActivityUtils.startCommunicationLinkActivity(getActivity());
                    break;
                case R.id.iv_search:


                    LogUtils.d(TAG, "获取的 cityid " + cityId);

                    if (cityId != -1) {
                        SearchCityActivity.Companion.startSelf(getActivity(), cityId);

                    } else {

                        ToastUtil.showCenterToast(mContext, "请先选择城市");
                    }

                    break;
            }

        }

    }

    private void getCityCompanyData(int cityId, int pageNum, int pageSize) {


        ControllerUtils.getCityController().companyCityList(cityId, pageNum, pageSize, new NetObserver<CityCompanyBean.DataBean>(CityCompanyBean.DataBean.class) {
            @Override
            protected void onError(@Nullable String msg) {

            }

            @Override
            protected void onFail(int code, @Nullable String msg) {

                ToastUtil.showCenterToast(mContext, msg);

            }

            @Override
            protected void onSuccess(CityCompanyBean.DataBean dataBean) {

                if (dataBean.getRows().size() <=0 ) {
                    return;
                }

                mCityCompanyAdapter.setFooterViewState(BaseAdapter.Companion.getLOAD_END());
                mCityCompanyAdapter.refreshData(dataBean.getRows());
                mCityCompanyAdapter.setFooterViewState(BaseAdapter.Companion.getLOAD_LOADING());


            }
        });


    }

    private void  setCityData() {


        switch (cityType) {
            case 2:
                tv_city_businessCooperation.setBackground(getResources().getDrawable(R.color.colorF33));
                tv_city_businessCooperation.setTextColor(getResources().getColor(R.color.white));

                tv_city_cityInfo.setTextColor(getResources().getColor(R.color.color333));
                tv_city_cityInfo.setBackground(getResources().getDrawable(R.color.white));


                ll_buss_content.setVisibility(View.VISIBLE);
                tv_city_html.setVisibility(View.GONE);
                if (null != infoBean && null !=  infoBean.getStationDetail().getBusinessCooperation()  ) {
                    tv_city_html.setText(Html.fromHtml(infoBean.getStationDetail().getBusinessCooperation(), new MImageGetter(tv_city_html, getActivity()), null));
                }

                break;
            case 1:
                tv_city_cityInfo.setBackground(getResources().getDrawable(R.color.colorF33));
                tv_city_cityInfo.setTextColor(getResources().getColor(R.color.white));

                tv_city_businessCooperation.setTextColor(getResources().getColor(R.color.color333));
                tv_city_businessCooperation.setBackground(getResources().getDrawable(R.color.white));


                ll_buss_content.setVisibility(View.GONE);
                tv_city_html.setVisibility(View.VISIBLE);

                // 商务合作请求接口

                // 请求数据

                if (infoBean != null) {
//
                    tv_city_html.setText(Html.fromHtml(infoBean.getStationDetail().getCityInfo(), new MImageGetter(tv_city_html, getActivity()), null));
//                    WebSettings webSettings = webView.getSettings();
//                    webSettings.setJavaScriptEnabled(true);//允许使用js
////不支持屏幕缩放
//                    webSettings.setSupportZoom(true);
//                    webSettings.setBuiltInZoomControls(false);
////不显示webview缩放按钮
//                    webSettings.setDisplayZoomControls(false);
//
//
//                    webView.loadDataWithBaseURL(null,infoBean.getStationDetail().getCityInfo(), "text/html" , "utf-8", null);
                }
                break;
        }
    }

    private void cityList() {
        if (pvOptions != null) {
            pvOptions.show();
            return;
        }
        showLoadingText();
        ControllerUtils.getHomeControllerInstance().cityList(new NetObserver<CityBean.DataBean>(CityBean.DataBean.class) {
            @Override
            protected void onSuccess(CityBean.DataBean dataBean) {
                hideLoadingText();
                setAddressPicker(dataBean.getCitys());
            }

//            @Override
//            protected void onSuccess(CityBean cityBean) {
//                hideLoadingText();
//                setAddressPicker(cityBean.getData().getCitys());
//            }

            @Override
            protected void onFail(int code, String msg) {
                hideLoadingText();

            }

            @Override
            protected void onError(String msg) {
                hideLoadingText();

            }

        });
    }

    private void setAddressPicker(List<CityBean.DataBean.CitysBean> dataBean) {
        this.options1Items.clear();
        this.options1Items.addAll(dataBean);

        if (LocaleUtils.INSTANCE.isLocaleEn(mContext)) {
            for (CityBean.DataBean.CitysBean options1Item : options1Items) {

                options1Item.setEN(true);
            }

        }else{
            for (CityBean.DataBean.CitysBean options1Item : options1Items) {

                options1Item.setEN(false);
            }
        }

        initJsonData();
    }

    private void initJsonData() {//解析数据 （省市区三级联动）

        showPickerView();
    }

    public void showPickerView() {// 弹出选择器（省市区三级联动）
        if (pvOptions == null) {
            pvOptions = new OptionsPickerBuilder(getActivity(), (options1, options2, options3, v) -> {


                String cityName = LocaleUtils.INSTANCE.isLocaleEn(mContext) ? options1Items.get(options1).getCityEn() : options1Items.get(options1).getCityCn();


                tv_basetitle.setText(cityName);

                cityDetail(options1Items.get(options1).getId());


                this.cityId = options1Items.get(options1).getId();

                if (cityId != -1) {
                    getCityCompanyData(cityId, pageNum, 15);

                }

                LogUtils.d(TAG, "获取的城市id  -> " + cityId);

            }).setLayoutRes(R.layout.addresselected_dialog, v -> {
                TextView tv_finish = v.findViewById(R.id.tv_finish);
                tv_finish.setOnClickListener(view -> {
                    pvOptions.returnData();
                    pvOptions.dismiss();
                });

            }).setTextColorCenter(ColorUtils.getTextColor(getActivity(), R.color.colorF33)) //设置选中项文字颜色
                    .setLineSpacingMultiplier(2)
                    .setDividerColor(ColorUtils.getTextColor(getActivity(), R.color.white))
                    .setContentTextSize(16)
                    .build();
            pvOptions.setPicker(options1Items, null, null);//三级选择器


        }

        pvOptions.show();
    }

    private CityInfoBean infoBean;


    private void cityDetail(int id) {



        showLoadingText();

        int lanuage = LocaleUtils.INSTANCE.isLocaleEn(mContext)? 2: 1;

        LogUtils.d(TAG, "开始进来的时候 的语言类型  ---> " + lanuage);

        ControllerUtils.getHomeControllerInstance().cityDetail(id, lanuage, new NetObserver<CityInfoBean>(CityInfoBean.class) {
            @Override
            protected void onSuccess(CityInfoBean o) {
                hideLoadingText();
                CityFragment.this.infoBean = o;
                setCityData();

            }

            @Override
            protected void onFail(int code, String msg) {
                hideLoadingText();

            }

            @Override
            protected void onError(String msg) {
                hideLoadingText();

            }

        });


    }
}
