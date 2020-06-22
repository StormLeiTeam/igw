package com.igw.igw.bean.city;


import java.util.List;

/**
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 * @Describe
 */
public  class CityLabelBean {


    /**
     * code : 200
     * data : {"labels":[{"id":1,"labelName":"医用外科口罩","language":1,"sortWithOutOrderBy":"","sort_":""},{"id":2,"labelName":"SurgicalMask","language":2,"sortWithOutOrderBy":"","sort_":""}]}
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
        private List<LabelsBean> labels;

        public List<LabelsBean> getLabels() {
            return labels;
        }

        public void setLabels(List<LabelsBean> labels) {
            this.labels = labels;
        }

        public static class LabelsBean {
            /**
             * id : 1
             * labelName : 医用外科口罩
             * language : 1
             * sortWithOutOrderBy :
             * sort_ :
             */

            private int id;
            private String labelName;
            private int language;
            private String sortWithOutOrderBy;
            private String sort_;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLabelName() {
                return labelName;
            }

            public void setLabelName(String labelName) {
                this.labelName = labelName;
            }

            public int getLanguage() {
                return language;
            }

            public void setLanguage(int language) {
                this.language = language;
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
        }
    }
}
