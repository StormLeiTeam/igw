package com.igw.igw.bean;

/**
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 * @Describe
 */
public class SplashBean {


    /**
     * code : 200
     * message : 操作成功
     * data : {"advertiseInfo":{"image":"http://127.0.0.1/test.png","link":"http://www.baidu.com"}}
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
        /**
         * advertiseInfo : {"image":"http://127.0.0.1/test.png","link":"http://www.baidu.com"}
         */

        private AdvertiseInfoBean advertiseInfo;

        public AdvertiseInfoBean getAdvertiseInfo() {
            return advertiseInfo;
        }

        public void setAdvertiseInfo(AdvertiseInfoBean advertiseInfo) {
            this.advertiseInfo = advertiseInfo;
        }

        public static class AdvertiseInfoBean {
            /**
             * image : http://127.0.0.1/test.png
             * link : http://www.baidu.com
             */

            private String image;
            private String link;

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }
        }
    }
}
