package com.igw.igw.bean;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

/**
 * 创建时间  2020/3/111:01 PM .
 * <p>
 * 作者  雷雷
 */

public class CityBean {
    /**
     * code : 200
     * data : {"citys":[{"cityCn":"北京","cityEn":"BEIJING","code":"BEIJING","contact":"马建成","countryId":1,"createUserId":1,"ctime":"2020-04-20 14:16:42","email":"bjmajiancheng@davdian.com","id":1,"isDel":0,"isOnline":1,"sortWithOutOrderBy":"","sort_":"","updateUserId":1,"utime":"2020-04-20 14:16:42"}]}
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
        private List<CitysBean> citys;

        public List<CitysBean> getCitys() {
            return citys;
        }

        public void setCitys(List<CitysBean> citys) {
            this.citys = citys;
        }

        public static class CitysBean implements IPickerViewData {
            /**
             * cityCn : 北京
             * cityEn : BEIJING
             * code : BEIJING
             * contact : 马建成
             * countryId : 1
             * createUserId : 1
             * ctime : 2020-04-20 14:16:42
             * email : bjmajiancheng@davdian.com
             * id : 1
             * isDel : 0
             * isOnline : 1
             * sortWithOutOrderBy :
             * sort_ :
             * updateUserId : 1
             * utime : 2020-04-20 14:16:42
             */

            private String cityCn;
            private String cityEn;
            private String code;
            private String contact;
            private int countryId;
            private int createUserId;
            private String ctime;
            private String email;
            private int id;
            private int isDel;
            private int isOnline;
            private String sortWithOutOrderBy;
            private String sort_;
            private int updateUserId;
            private String utime;
            private boolean isEN = false;

            public String getCityCn() {
                return cityCn;
            }

            public void setCityCn(String cityCn) {
                this.cityCn = cityCn;
            }

            public String getCityEn() {
                return cityEn;
            }

            public void setCityEn(String cityEn) {
                this.cityEn = cityEn;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getContact() {
                return contact;
            }

            public void setContact(String contact) {
                this.contact = contact;
            }

            public int getCountryId() {
                return countryId;
            }

            public void setCountryId(int countryId) {
                this.countryId = countryId;
            }

            public int getCreateUserId() {
                return createUserId;
            }

            public void setCreateUserId(int createUserId) {
                this.createUserId = createUserId;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getIsDel() {
                return isDel;
            }

            public void setIsDel(int isDel) {
                this.isDel = isDel;
            }

            public int getIsOnline() {
                return isOnline;
            }

            public void setIsOnline(int isOnline) {
                this.isOnline = isOnline;
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

            public int getUpdateUserId() {
                return updateUserId;
            }

            public void setUpdateUserId(int updateUserId) {
                this.updateUserId = updateUserId;
            }

            public String getUtime() {
                return utime;
            }

            public void setUtime(String utime) {
                this.utime = utime;
            }

            @Override
            public String getPickerViewText() {
                if (isEN)
                    return cityEn;
                else

                    return cityCn;
            }
        }
    }


//
//    private List<CitysBean> citys;
//
//    public List<CitysBean> getCitys() {
//        return citys;
//    }
//
//    public void setCitys(List<CitysBean> citys) {
//        this.citys = citys;
//    }
//
//    public static class CitysBean implements IPickerViewData {
//
//        /**
//         * cityCn : 北京 cityEn : BEIJING code : BEIJING contact : 马建成 countryId : 1 createUserId : 1
//         * ctime : 2020-03-09 23:30:56 email : bjmajiancheng@davdian.com id : 1 sortWithOutOrderBy :
//         * sort_ : updateUserId : 1 utime : 2020-03-09 23:30:56
//         */
//
//        private String cityCn;
//
//        private String cityEn;
//
//        private String code;
//
//        private String contact;
//
//        private String regionCnName;
//
//        private String regionEnName;
//
//        public String getRegionCnName() {
//            return regionCnName;
//        }
//
//        public void setRegionCnName(String regionCnName) {
//            this.regionCnName = regionCnName;
//        }
//
//        public String getRegionEnName() {
//            return regionEnName;
//        }
//
//        public void setRegionEnName(String regionEnName) {
//            this.regionEnName = regionEnName;
//        }
//
//        private int countryId;
//
//        private int createUserId;
//
//        private String ctime;
//
//        private String email;
//
//        private int id;
//
//        private String sortWithOutOrderBy;
//
//        private String sort_;
//
//        private int updateUserId;
//
//        private String utime;
//
//        public String getCityCn() {
//            return cityCn;
//        }
//
//        public void setCityCn(String cityCn) {
//            this.cityCn = cityCn;
//        }
//
//        public String getCityEn() {
//            return cityEn;
//        }
//
//        public void setCityEn(String cityEn) {
//            this.cityEn = cityEn;
//        }
//
//        public String getCode() {
//            return code;
//        }
//
//        public void setCode(String code) {
//            this.code = code;
//        }
//
//        public String getContact() {
//            return contact;
//        }
//
//        public void setContact(String contact) {
//            this.contact = contact;
//        }
//
//        public int getCountryId() {
//            return countryId;
//        }
//
//        public void setCountryId(int countryId) {
//            this.countryId = countryId;
//        }
//
//        public int getCreateUserId() {
//            return createUserId;
//        }
//
//        public void setCreateUserId(int createUserId) {
//            this.createUserId = createUserId;
//        }
//
//        public String getCtime() {
//            return ctime;
//        }
//
//        public void setCtime(String ctime) {
//            this.ctime = ctime;
//        }
//
//        public String getEmail() {
//            return email;
//        }
//
//        public void setEmail(String email) {
//            this.email = email;
//        }
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public String getSortWithOutOrderBy() {
//            return sortWithOutOrderBy;
//        }
//
//        public void setSortWithOutOrderBy(String sortWithOutOrderBy) {
//            this.sortWithOutOrderBy = sortWithOutOrderBy;
//        }
//
//        public String getSort_() {
//            return sort_;
//        }
//
//        public void setSort_(String sort_) {
//            this.sort_ = sort_;
//        }
//
//        public int getUpdateUserId() {
//            return updateUserId;
//        }
//
//        public void setUpdateUserId(int updateUserId) {
//            this.updateUserId = updateUserId;
//        }
//
//        public String getUtime() {
//            return utime;
//        }
//
//        public void setUtime(String utime) {
//            this.utime = utime;
//        }
//
//        @Override
//        public String getPickerViewText() {
//            return regionCnName;
//        }
//    }
}
