package com.igw.igw.bean.city;

import java.util.List;

/**
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 * @Describe
 */
public class CityCompanyBean {

    /**
     * code : 200
     * data : {"total":1,"rows":[{"cityId":1,"cnDesc":"<p><img src=\"http://106.54.111.24:83/gameword-system-platform/upload/system/image/20200524/20200524092136_999_common.png\" style=\"width: 763px;\"><br><\/p>","cnName":"博辉瑞进-医用外科口罩","companyLogo":"/gameword-system-platform/upload/system/image/20200515/20200515223245_348_common.png","ctime":"2020-05-15 22:32:48","enDesc":"<p><img src=\"http://106.54.111.24:83/gameword-system-platform/upload/system/image/20200524/20200524092201_988_common.png\" style=\"width: 773px;\"><br><\/p>","enName":"博辉瑞进-医用外科口罩","id":2,"sortWithOutOrderBy":"","sort_":"","utime":"2020-05-24 09:23:03"}]}
     * message : 操作成功
     */

    private int code;
    private CityCompanyBean.DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public CityCompanyBean.DataBean getData() {
        return data;
    }

    public void setData(CityCompanyBean.DataBean data) {
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
         * total : 1
         * rows : [{"cityId":1,"cnDesc":"<p><img src=\"http://106.54.111.24:83/gameword-system-platform/upload/system/image/20200524/20200524092136_999_common.png\" style=\"width: 763px;\"><br><\/p>","cnName":"博辉瑞进-医用外科口罩","companyLogo":"/gameword-system-platform/upload/system/image/20200515/20200515223245_348_common.png","ctime":"2020-05-15 22:32:48","enDesc":"<p><img src=\"http://106.54.111.24:83/gameword-system-platform/upload/system/image/20200524/20200524092201_988_common.png\" style=\"width: 773px;\"><br><\/p>","enName":"博辉瑞进-医用外科口罩","id":2,"sortWithOutOrderBy":"","sort_":"","utime":"2020-05-24 09:23:03"}]
         */

        private int total;
        private List<CityCompanyBean.DataBean.RowsBean> rows;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<CityCompanyBean.DataBean.RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<CityCompanyBean.DataBean.RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            /**
             * cityId : 1
             * cnDesc : <p><img src="http://106.54.111.24:83/gameword-system-platform/upload/system/image/20200524/20200524092136_999_common.png" style="width: 763px;"><br></p>
             * cnName : 博辉瑞进-医用外科口罩
             * companyLogo : /gameword-system-platform/upload/system/image/20200515/20200515223245_348_common.png
             * ctime : 2020-05-15 22:32:48
             * enDesc : <p><img src="http://106.54.111.24:83/gameword-system-platform/upload/system/image/20200524/20200524092201_988_common.png" style="width: 773px;"><br></p>
             * enName : 博辉瑞进-医用外科口罩
             * id : 2
             * sortWithOutOrderBy :
             * sort_ :
             * utime : 2020-05-24 09:23:03
             */

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
        }
    }
}
