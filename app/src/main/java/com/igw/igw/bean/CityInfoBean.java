package com.igw.igw.bean;

/**
 * 创建时间  2020/3/122:39 PM .
 *
 * 作者  雷雷
 */

public class CityInfoBean  {


    /**
     * stationDetail : {}
     */

    private StationDetailBean stationDetail;

    public StationDetailBean getStationDetail() {
        return stationDetail;
    }

    public void setStationDetail(StationDetailBean stationDetail) {
        this.stationDetail = stationDetail;
    }

    public static class StationDetailBean {

        private String businessCooperation;

        private String cityInfo;

        public String getBusinessCooperation() {
            return businessCooperation;
        }

        public void setBusinessCooperation(String businessCooperation) {
            this.businessCooperation = businessCooperation;
        }

        public String getCityInfo() {
            return cityInfo;
        }

        public void setCityInfo(String cityInfo) {
            this.cityInfo = cityInfo;
        }
    }
}
