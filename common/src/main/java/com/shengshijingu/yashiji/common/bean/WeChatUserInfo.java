package com.shengshijingu.yashiji.common.bean;

/**
 * 创建时间  2019/5/162:08 PM .
 *
 * 作者  雷雷
 */

public class WeChatUserInfo {


    /**
     * code : 200
     * result : {"user":{"registerTime":"2019-05-21 14:34:26","resource":2,"openid":"oUyXX1E1e1JZcTnlzzPz81vvX8bM","ip":"","sex":0,"updateTime":"2019-05-21 14:34:26","type":1,"regionWx":"--","headImgUrl":"http://thirdwx.qlogo.cn/mmopen/vi_32/gHsVsZEe46NFmDJkZAFrdictTquPDCjHqgWnQWibFeq4yDbDJOSDlac8pZv0xBYWPOnLTOByGrB5LaY7iciacw83MA/132","phone":"17611111111","nickname":"梵不高","id":1000014,"region":"","status":1},"token":"TOKEN:35556bd5-da67-4fd6-ae8f-ed2eb295d9c0"}
     * status : SUCCEED
     */

    private String code;

    private ResultBean result;

    private String status;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class ResultBean {

        /**
         * user : {"registerTime":"2019-05-21 14:34:26","resource":2,"openid":"oUyXX1E1e1JZcTnlzzPz81vvX8bM","ip":"","sex":0,"updateTime":"2019-05-21 14:34:26","type":1,"regionWx":"--","headImgUrl":"http://thirdwx.qlogo.cn/mmopen/vi_32/gHsVsZEe46NFmDJkZAFrdictTquPDCjHqgWnQWibFeq4yDbDJOSDlac8pZv0xBYWPOnLTOByGrB5LaY7iciacw83MA/132","phone":"17611111111","nickname":"梵不高","id":1000014,"region":"","status":1}
         * token : TOKEN:35556bd5-da67-4fd6-ae8f-ed2eb295d9c0
         */

        private UserBean user;

        private String token;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public static class UserBean {

            /**
             * registerTime : 2019-05-21 14:34:26
             * resource : 2
             * openid : oUyXX1E1e1JZcTnlzzPz81vvX8bM
             * ip :
             * sex : 0
             * updateTime : 2019-05-21 14:34:26
             * type : 1
             * regionWx : --
             * headImgUrl : http://thirdwx.qlogo.cn/mmopen/vi_32/gHsVsZEe46NFmDJkZAFrdictTquPDCjHqgWnQWibFeq4yDbDJOSDlac8pZv0xBYWPOnLTOByGrB5LaY7iciacw83MA/132
             * phone : 17611111111
             * nickname : 梵不高
             * id : 1000014
             * region :
             * status : 1
             */

            private String registerTime;

            private int resource;

            private String openid;

            private String ip;

            private int sex;

            private String updateTime;

            private int type;

            private String regionWx;

            private String headImgUrl;

            private String phone;

            private String nickname;

            private int id;

            private String region;

            private int status;

            public String getRegisterTime() {
                return registerTime;
            }

            public void setRegisterTime(String registerTime) {
                this.registerTime = registerTime;
            }

            public int getResource() {
                return resource;
            }

            public void setResource(int resource) {
                this.resource = resource;
            }

            public String getOpenid() {
                return openid;
            }

            public void setOpenid(String openid) {
                this.openid = openid;
            }

            public String getIp() {
                return ip;
            }

            public void setIp(String ip) {
                this.ip = ip;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getRegionWx() {
                return regionWx;
            }

            public void setRegionWx(String regionWx) {
                this.regionWx = regionWx;
            }

            public String getHeadImgUrl() {
                return headImgUrl;
            }

            public void setHeadImgUrl(String headImgUrl) {
                this.headImgUrl = headImgUrl;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
