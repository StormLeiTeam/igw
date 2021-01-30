package com.igw.igw.bean;

/**
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 * @Describe
 */
public class VersionBean {


    /**
     * code : 200
     * data : {"ANDROID":{"appPath":"www.baidu.com","appType":"ANDROID","appUrl":"www.baidu.com","ctime":"2021-01-25 19:39:01","id":1,"isForcedUpdate":false,"sortWithOutOrderBy":"","sort_":"","utime":"2021-01-25 19:39:01","version":"1.0"},"IOS":{"appPath":"www.baidu.com","appType":"IOS","appUrl":"www.baidu.com","ctime":"2021-01-25 19:39:07","id":2,"isForcedUpdate":false,"sortWithOutOrderBy":"","sort_":"","utime":"2021-01-25 19:39:07","version":"1.0"}}
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
         * ANDROID : {"appPath":"www.baidu.com","appType":"ANDROID","appUrl":"www.baidu.com","ctime":"2021-01-25 19:39:01","id":1,"isForcedUpdate":false,"sortWithOutOrderBy":"","sort_":"","utime":"2021-01-25 19:39:01","version":"1.0"}
         * IOS : {"appPath":"www.baidu.com","appType":"IOS","appUrl":"www.baidu.com","ctime":"2021-01-25 19:39:07","id":2,"isForcedUpdate":false,"sortWithOutOrderBy":"","sort_":"","utime":"2021-01-25 19:39:07","version":"1.0"}
         */

        private ANDROIDBean ANDROID;
        private IOSBean IOS;

        public ANDROIDBean getANDROID() {
            return ANDROID;
        }

        public void setANDROID(ANDROIDBean ANDROID) {
            this.ANDROID = ANDROID;
        }

        public IOSBean getIOS() {
            return IOS;
        }

        public void setIOS(IOSBean IOS) {
            this.IOS = IOS;
        }

        public static class ANDROIDBean {
            /**
             * appPath : www.baidu.com
             * appType : ANDROID
             * appUrl : www.baidu.com
             * ctime : 2021-01-25 19:39:01
             * id : 1
             * isForcedUpdate : false
             * sortWithOutOrderBy :
             * sort_ :
             * utime : 2021-01-25 19:39:01
             * version : 1.0
             */

            private String appPath;
            private String appType;
            private String appUrl;
            private String ctime;
            private int id;
            private boolean isForcedUpdate;
            private String sortWithOutOrderBy;
            private String sort_;
            private String utime;
            private String version;

            public String getAppPath() {
                return appPath;
            }

            public void setAppPath(String appPath) {
                this.appPath = appPath;
            }

            public String getAppType() {
                return appType;
            }

            public void setAppType(String appType) {
                this.appType = appType;
            }

            public String getAppUrl() {
                return appUrl;
            }

            public void setAppUrl(String appUrl) {
                this.appUrl = appUrl;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public boolean isIsForcedUpdate() {
                return isForcedUpdate;
            }

            public void setIsForcedUpdate(boolean isForcedUpdate) {
                this.isForcedUpdate = isForcedUpdate;
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

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }
        }

        public static class IOSBean {
            /**
             * appPath : www.baidu.com
             * appType : IOS
             * appUrl : www.baidu.com
             * ctime : 2021-01-25 19:39:07
             * id : 2
             * isForcedUpdate : false
             * sortWithOutOrderBy :
             * sort_ :
             * utime : 2021-01-25 19:39:07
             * version : 1.0
             */

            private String appPath;
            private String appType;
            private String appUrl;
            private String ctime;
            private int id;
            private boolean isForcedUpdate;
            private String sortWithOutOrderBy;
            private String sort_;
            private String utime;
            private String version;

            public String getAppPath() {
                return appPath;
            }

            public void setAppPath(String appPath) {
                this.appPath = appPath;
            }

            public String getAppType() {
                return appType;
            }

            public void setAppType(String appType) {
                this.appType = appType;
            }

            public String getAppUrl() {
                return appUrl;
            }

            public void setAppUrl(String appUrl) {
                this.appUrl = appUrl;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public boolean isIsForcedUpdate() {
                return isForcedUpdate;
            }

            public void setIsForcedUpdate(boolean isForcedUpdate) {
                this.isForcedUpdate = isForcedUpdate;
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

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }
        }
    }
}
