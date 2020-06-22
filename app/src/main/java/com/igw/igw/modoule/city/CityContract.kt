package com.igw.igw.modoule.city

import com.igw.igw.bean.city.CityLabelBean
import com.igw.igw.bean.city.CityResultBean
import com.igw.igw.mvp.model.IBaseModel
import com.igw.igw.mvp.presenter.IBasePresenter
import com.igw.igw.mvp.view.IBaseView
import com.igw.igw.network.NetObserver

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */

class CityContract {


    interface View : IBaseView {


        fun onSuccessCityLabel(data: List<CityLabelBean.DataBean.LabelsBean>)

        fun onFailCityLabel(code: String, msg: String)


        fun onSuccessCitySearch(data:List<CityResultBean.DataBean.RowsBean>)

        fun onFailCitySearch(code: Int, msg: String)

        fun onSuccessCityLoadMore(data: List<CityResultBean.DataBean.RowsBean>)
        fun onFailCityLoadMore(code: Int, msg: String)



    }


    interface Model : IBaseModel {
        fun getCityLabel(cityId: Int, language: Int, observer: NetObserver<CityLabelBean.DataBean>)


        fun getCitySearchResult(cityId: Int, queryStr: String, labelId: Int,
                                language: Int, pageNum: Int, pageSize: Int,
                                observer: NetObserver<CityResultBean.DataBean>)

    }

    interface Presenter : IBasePresenter {


        fun getCityLabel(cityId: Int, language: Int)

//        */
//        public void getCitySearchResult(int cityId, String queryStr,int  labelId ,
//        int   language, int pageNum, int pageSize,Observer observer){
//            Map<String, Object> params = new HashMap<>();


        fun getCitySearchResult(cityId: Int, queryStr: String, labelId: Int,
                                language: Int, pageNum: Int, pageSize: Int)


        // 搜索结果加载更多

        fun getCitySearchResultLoadMore(searchModel: Int ,cityId: Int, queryStr: String, labelId: Int,
                                        language: Int, pageNum: Int, pageSize: Int)
    }

}