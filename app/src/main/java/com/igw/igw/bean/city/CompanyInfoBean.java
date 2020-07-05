package com.igw.igw.bean.city;

import java.util.List;

/**
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 * @Describe
 */
public  class CompanyInfoBean {
    /**
     * code : 200
     * data : {"company":{"cityCnName":"北京","cityEnName":"BEIJING","cityId":1,"cnDesc":"<p><img src=\"http://106.54.111.24:83/gameword-system-platform/upload/system/image/20200524/20200524092136_999_common.png\" style=\"width: 763px;\"><br><\/p>","cnLabelNames":["医用外科口罩"],"cnName":"博辉瑞进-医用外科口罩","companyLogo":"/gameword-system-platform/upload/system/image/20200515/20200515223245_348_common.png","ctime":"2020-05-15 22:32:48","enDesc":"<p><img src=\"http://106.54.111.24:83/gameword-system-platform/upload/system/image/20200524/20200524092201_988_common.png\" style=\"width: 773px;\"><br><\/p>","enLabelNames":["SurgicalMask"],"enName":"博辉瑞进-医用外科口罩","id":2,"sortWithOutOrderBy":"","sort_":"","utime":"2020-05-24 09:23:03"}}
     * message : 操作成功
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * company : {"cityCnName":"北京","cityEnName":"BEIJING","cityId":1,"cnDesc":"<p><img src=\"http://106.54.111.24:83/gameword-system-platform/upload/system/image/20200524/20200524092136_999_common.png\" style=\"width: 763px;\"><br><\/p>","cnLabelNames":["医用外科口罩"],"cnName":"博辉瑞进-医用外科口罩","companyLogo":"/gameword-system-platform/upload/system/image/20200515/20200515223245_348_common.png","ctime":"2020-05-15 22:32:48","enDesc":"<p><img src=\"http://106.54.111.24:83/gameword-system-platform/upload/system/image/20200524/20200524092201_988_common.png\" style=\"width: 773px;\"><br><\/p>","enLabelNames":["SurgicalMask"],"enName":"博辉瑞进-医用外科口罩","id":2,"sortWithOutOrderBy":"","sort_":"","utime":"2020-05-24 09:23:03"}
         */

        private CompanyBean company;

        public CompanyBean getCompany() {
            return company;
        }

        public void setCompany(CompanyBean company) {
            this.company = company;
        }

        public static class CompanyBean {
            /**
             * cityCnName : 北京
             * cityEnName : BEIJING
             * cityId : 1
             * cnDesc : <p><img src="http://106.54.111.24:83/gameword-system-platform/upload/system/image/20200524/20200524092136_999_common.png" style="width: 763px;"><br></p>
             * cnLabelNames : ["医用外科口罩"]
             * cnName : 博辉瑞进-医用外科口罩
             * companyLogo : /gameword-system-platform/upload/system/image/20200515/20200515223245_348_common.png
             * ctime : 2020-05-15 22:32:48
             * enDesc : <p><img src="http://106.54.111.24:83/gameword-system-platform/upload/system/image/20200524/20200524092201_988_common.png" style="width: 773px;"><br></p>
             * enLabelNames : ["SurgicalMask"]
             * enName : 博辉瑞进-医用外科口罩
             * id : 2
             * sortWithOutOrderBy :
             * sort_ :
             * utime : 2020-05-24 09:23:03
             */

            private String cityCnName;
            private String cityEnName;
            private int cityId;
            private String cnDesc;
            private String cnName;
            private String companyLogo;
            private String ctime;
            private String enDesc;
            private String enName;
            private int id;
            private String sortWithOutOrderBy;
            private String sort_;
            private String utime;
            private List<String> cnLabelNames;
            private List<String> enLabelNames;

            public String getCityCnName() {
                return cityCnName;
            }

            public void setCityCnName(String cityCnName) {
                this.cityCnName = cityCnName;
            }

            public String getCityEnName() {
                return cityEnName;
            }

            public void setCityEnName(String cityEnName) {
                this.cityEnName = cityEnName;
            }

            public int getCityId() {
                return cityId;
            }

            public void setCityId(int cityId) {
                this.cityId = cityId;
            }

            public String getCnDesc() {
                return cnDesc;
            }

            public void setCnDesc(String cnDesc) {
                this.cnDesc = cnDesc;
            }

            public String getCnName() {
                return cnName;
            }

            public void setCnName(String cnName) {
                this.cnName = cnName;
            }

            public String getCompanyLogo() {
                return companyLogo;
            }

            public void setCompanyLogo(String companyLogo) {
                this.companyLogo = companyLogo;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getEnDesc() {
                return enDesc;
            }

            public void setEnDesc(String enDesc) {
                this.enDesc = enDesc;
            }

            public String getEnName() {
                return enName;
            }

            public void setEnName(String enName) {
                this.enName = enName;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getSortWithOutOrderBy() {
                return sortWithOutOrderBy;
            }

            public void setSortWithOutOrderBy(String sortWithOutOrderBy) {
                this.sortWithOutOrderBy = sortWithOutOrderBy;
            }

            public String getSort_() {
                return sort_;
            }

            public void setSort_(String sort_) {
                this.sort_ = sort_;
            }

            public String getUtime() {
                return utime;
            }

            public void setUtime(String utime) {
                this.utime = utime;
            }

            public List<String> getCnLabelNames() {
                return cnLabelNames;
            }

            public void setCnLabelNames(List<String> cnLabelNames) {
                this.cnLabelNames = cnLabelNames;
            }

            public List<String> getEnLabelNames() {
                return enLabelNames;
            }

            public void setEnLabelNames(List<String> enLabelNames) {
                this.enLabelNames = enLabelNames;
            }
        }
    }

//





//    private DataBean dataBean;
//
//
//    public DataBean getDataBean() {
//        return dataBean;
//    }
//
//    public void setDataBean(DataBean dataBean) {
//        this.dataBean = dataBean;
//    }
//
//    public static class  DataBean{
//
//
//    }
}
