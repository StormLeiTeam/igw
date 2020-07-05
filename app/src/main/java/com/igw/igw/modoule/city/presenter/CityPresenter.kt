package com.igw.igw.modoule.city.presenter

import com.igw.igw.bean.city.CityLabelBean
import com.igw.igw.bean.city.CityResultBean
import com.igw.igw.modoule.city.CityContract
import com.igw.igw.modoule.city.model.CityModel
import com.igw.igw.mvp.presenter.BasePresenter
import com.igw.igw.network.NetObserver
import com.igw.igw.utils.LogUtils

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */

class CityPresenter(model: CityContract.Model) :
        BasePresenter<CityContract.View, CityContract.Model>(model), CityContract.Presenter {


    companion object {
        val TAG = "CityPresenter"
    }


    override fun requestData() {


    }

    override fun getCityLabel(cityId: Int, language: Int) {


        mModel.getCityLabel(cityId, language, object : NetObserver<CityLabelBean.DataBean>(CityLabelBean.DataBean::class.java) {
            override fun onSuccess(m: CityLabelBean.DataBean) {


                LogUtils.d(TAG, "获取的城市标签 onSuccess")
                mRootView.onSuccessCityLabel(m.labels)
            }

            override fun onFail(code: Int, msg: String?) {

                LogUtils.d(TAG, "获取的城市标签 onFail")


            }

            override fun onError(msg: String?) {

                LogUtils.d(TAG, "获取的城市标签 onError")

            }

        })
    }


    /**
     * 获取搜索结果
     *
     */
    override fun getCitySearchResult(cityId: Int, queryStr: String, labelId: Int, language: Int, pageNum: Int, pageSize: Int) {


        mModel.getCitySearchResult(cityId, queryStr, labelId, language, pageNum, pageSize,
                object : NetObserver<CityResultBean.DataBean>(CityResultBean.DataBean::class.java) {
                    override fun onSuccess(m: CityResultBean.DataBean) {


                        mRootView.onSuccessCitySearch(m.rows)

                        LogUtils.d(TAG, "获取搜索结果 -- onSuccess")
                    }

                    override fun onFail(code: Int, msg: String?) {
                        mRootView.onFailCitySearch(code, msg!!)
                        LogUtils.d(TAG, "获取搜索结果 -- onFail")

                    }

                    override fun onError(msg: String?) {
                        LogUtils.d(TAG, "获取搜索结果 -- onError")

                    }


                })

    }

    override fun getCitySearchResultLoadMore(searchModel: Int , cityId: Int, queryStr: String, labelId: Int, language: Int, pageNum: Int, pageSize: Int) {


        mModel.getCitySearchResult(cityId, queryStr, labelId, language, pageNum, pageSize,
                object : NetObserver<CityResultBean.DataBean>(CityResultBean.DataBean::class.java) {
                    override fun onSuccess(m: CityResultBean.DataBean) {
                        mRootView.onSuccessCityLoadMore(m.rows)

                        LogUtils.d(TAG, "加载更多的结果 --> onSuccess")
                    }

                    override fun onFail(code: Int, msg: String?) {

                        mRootView.onFailCityLoadMore(code,msg!!)

                        LogUtils.d(TAG, "加载更多的结果 --> onFail")

                    }

                    override fun onError(msg: String?) {

                        LogUtils.d(TAG, "加载更多的结果 --> onError")

                    }


                })
    }


}