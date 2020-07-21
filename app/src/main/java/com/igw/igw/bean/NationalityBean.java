package com.igw.igw.bean;

import com.igw.igw.widget.storm.popwindowselect.popselectview.bean.IWheelEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 * @Describe 国籍
 */
public class NationalityBean {


    /**
     * code : 200
     * message : 操作成功
     * data : {"countrys":[{"id":1,"countryCnName":"中国","countryEnName":"China","code":"CN"}]}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<CountrysBean> countrys;

        public List<CountrysBean> getCountrys() {
            return countrys;
        }

        public void setCountrys(List<CountrysBean> countrys) {
            this.countrys = countrys;
        }

        public static class CountrysBean implements IWheelEntity, Serializable {
            /**
             * id : 1
             * countryCnName : 中国
             * countryEnName : China
             * code : CN
             */

            private int id;
            private String regionCnName;
            private String regionEnName;
            private String code;

            private boolean isEnglish;

            // 判断状态
            public boolean isEnglish() {
                return isEnglish;
            }

            public void setEnglish(boolean english) {
                isEnglish = english;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCountryCnName() {
                return regionCnName;
            }

            public void setCountryCnName(String countryCnName) {
                this.regionCnName = countryCnName;
            }

            public String getCountryEnName() {
                return regionEnName;
            }

            public void setCountryEnName(String countryEnName) {
                this.regionEnName = countryEnName;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            @Override
            public String getWheelText() {
                if (isEnglish) { // 英语

                    return regionEnName;
                } else {

                    return regionCnName;
                }
            }
        }
    }
}
