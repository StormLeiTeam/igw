package com.shengshijingu.yashiji.common.controller;


import com.shengshijingu.yashiji.common.net.NetApi;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;

/**
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 * @Describe
 */
public class CityController extends Controller {

    public static final  String TAG  = "CityController";


    /**
     *  获取城市标签
     */
    public void getCityLabel(int cityId, int language, Observer observer){

        Map<String, Object> params = new HashMap<>();
        params.put("cityId", cityId);
        params.put("language", language);

        ApiSubscribe(NetApi.getApiService().getCityLabal(getRequestBody(params)),observer);

    }


    /**
     * 获取搜索结果
     *
     * @param cityId
     * @param queryStr
     * @param labelId
     * @param language
     * @param pageNum
     * @param pageSize
     * @param observer
     */
    public void getCitySearchResult(int cityId, String queryStr,int  labelId ,
                                  int   language, int pageNum, int pageSize,Observer observer){
        Map<String, Object> params = new HashMap<>();

        params.put("cityId",cityId);
        params.put("queryStr",queryStr);
        params.put("labelId",labelId);
        params.put("language",language);
        params.put("pageNum",pageNum);
        params.put("pageSize",pageSize);

        ApiSubscribe(NetApi.getApiService().getCitySearchResult(getRequestBody(params)),observer);



    }


    /**
     * 获取的公司详情
     * @param companyId
     * @param observer
     */
    public void companyCityInfo(int  companyId,Observer observer){

        Map<String, Object> params = new HashMap<>();

        params.put("companyId",companyId);

        ApiSubscribe(NetApi.getApiService().companyCityInfo(getRequestBody(params)), observer);
    }

}
