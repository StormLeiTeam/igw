package com.igw.igw.fragment;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.igw.igw.R;
import com.igw.igw.bean.CityBean;
import com.igw.igw.bean.CityBean.CitysBean;
import com.igw.igw.bean.CityInfoBean;
import com.igw.igw.network.NetObserver;
import com.igw.igw.utils.ActivityUtils;
import com.igw.igw.utils.ColorUtils;
import com.igw.igw.utils.MImageGetter;
import com.igw.igw.utils.SharedUtils;
import com.shengshijingu.yashiji.common.base.BaseDataFragment;
import com.shengshijingu.yashiji.common.util.ControllerUtils;

/**
 * 创建时间  2020/3/105:42 PM .
 * <p>
 * 作者  雷雷
 */

public class CityFragment extends BaseDataFragment {

    private LinearLayout ll_city;

    private ArrayList<CitysBean> options1Items = new ArrayList<>(); //省

    private OptionsPickerView pvOptions;

    private TextView tv_basetitle;

    private ImageView iv_citystation_cooperation;

    private TextView tv_city_cityInfo, tv_city_businessCooperation, tv_city_html;

    private int cityType = 1;

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
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (clickTime()) {
            switch (view.getId()) {
                case R.id.tv_city_businessCooperation:

                    cityType = 2;
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
            }

        }

    }

    private void setCityData() {
        switch (cityType) {
            case 2:
                tv_city_businessCooperation.setBackground(getResources().getDrawable(R.color.colorF33));
                tv_city_businessCooperation.setTextColor(getResources().getColor(R.color.white));

                tv_city_cityInfo.setTextColor(getResources().getColor(R.color.color333));
                tv_city_cityInfo.setBackground(getResources().getDrawable(R.color.white));

                if (infoBean != null) {
                    tv_city_html.setText(Html.fromHtml(infoBean.getStationDetail().getBusinessCooperation(), new MImageGetter(tv_city_html, getActivity()), null));
                }

                break;
            case 1:
                tv_city_cityInfo.setBackground(getResources().getDrawable(R.color.colorF33));
                tv_city_cityInfo.setTextColor(getResources().getColor(R.color.white));

                tv_city_businessCooperation.setTextColor(getResources().getColor(R.color.color333));
                tv_city_businessCooperation.setBackground(getResources().getDrawable(R.color.white));

                if (infoBean != null) {
                    tv_city_html.setText(Html.fromHtml(infoBean.getStationDetail().getCityInfo(), new MImageGetter(tv_city_html, getActivity()), null));
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
        ControllerUtils.getHomeControllerInstance().cityList(new NetObserver<CityBean>(CityBean.class) {
            @Override
            protected void onSuccess(CityBean cityBean) {
                hideLoadingText();
                setAddressPicker(cityBean.getCitys());
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

    private void setAddressPicker(List<CitysBean> dataBean) {
        this.options1Items.clear();
        this.options1Items.addAll(dataBean);
        initJsonData();
    }

    private void initJsonData() {//解析数据 （省市区三级联动）

        showPickerView();
    }

    public void showPickerView() {// 弹出选择器（省市区三级联动）
        if (pvOptions == null) {
            pvOptions = new OptionsPickerBuilder(getActivity(), (options1, options2, options3, v) -> {
                tv_basetitle.setText(options1Items.get(options1).getRegionCnName());

                cityDetail(options1Items.get(options1).getId());
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
        ControllerUtils.getHomeControllerInstance().cityDetail(id, SharedUtils.getLanguage(), new NetObserver<CityInfoBean>(CityInfoBean.class) {
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
