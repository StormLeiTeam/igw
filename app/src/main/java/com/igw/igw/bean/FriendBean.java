package com.igw.igw.bean;

import java.util.List;

/**
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 * @Describe
 */
public class FriendBean {


    /**
     * code : 200
     * data : {"friends":[{"ctime":"2020-05-06 22:34:52","firstCharPinyin":"L","friendCityCnName":"北京","friendCityEnName":"BEIJING","friendCountryCnName":"中国","friendCountryEnName":"CHINA","friendHeadImage":"http://106.54.111.24:84/gameword-user-service/upload/system/image/20200426/20200426102736_331_20200426102735.jpg","friendNickName":"Little king","friendUserId":5,"id":3,"sortWithOutOrderBy":"","sort_":"","userId":1,"utime":"2020-05-06 22:34:52"},{"ctime":"2020-05-06 22:34:57","firstCharPinyin":"K","friendCityCnName":"北京","friendCityEnName":"BEIJING","friendCountryCnName":"中国","friendCountryEnName":"CHINA","friendHeadImage":"http://106.54.111.24:84/gameword-user-service/upload/system/image/20200426/20200426101247_153_20200426101246.jpg","friendNickName":"Kk","friendUserId":4,"id":4,"sortWithOutOrderBy":"","sort_":"","userId":1,"utime":"2020-05-06 22:34:57"}]}
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
        private List<FriendsBean> friends;

        public List<FriendsBean> getFriends() {
            return friends;
        }

        public void setFriends(List<FriendsBean> friends) {
            this.friends = friends;
        }

        public static class FriendsBean {
            /**
             * ctime : 2020-05-06 22:34:52
             * firstCharPinyin : L
             * friendCityCnName : 北京
             * friendCityEnName : BEIJING
             * friendCountryCnName : 中国
             * friendCountryEnName : CHINA
             * friendHeadImage : http://106.54.111.24:84/gameword-user-service/upload/system/image/20200426/20200426102736_331_20200426102735.jpg
             * friendNickName : Little king
             * friendUserId : 5
             * id : 3
             * sortWithOutOrderBy :
             * sort_ :
             * userId : 1
             * utime : 2020-05-06 22:34:52
             */

            private String ctime;
            private String firstCharPinyin;
            private String friendCityCnName;
            private String friendCityEnName;
            private String friendCountryCnName;
            private String friendCountryEnName;
            private String friendHeadImage;
            private String friendNickName;
            private int friendUserId;
            private int id;
            private String sortWithOutOrderBy;
            private String sort_;
            private int userId;
            private String utime;

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getFirstCharPinyin() {
                return firstCharPinyin;
            }

            public void setFirstCharPinyin(String firstCharPinyin) {
                this.firstCharPinyin = firstCharPinyin;
            }

            public String getFriendCityCnName() {
                return friendCityCnName;
            }

            public void setFriendCityCnName(String friendCityCnName) {
                this.friendCityCnName = friendCityCnName;
            }

            public String getFriendCityEnName() {
                return friendCityEnName;
            }

            public void setFriendCityEnName(String friendCityEnName) {
                this.friendCityEnName = friendCityEnName;
            }

            public String getFriendCountryCnName() {
                return friendCountryCnName;
            }

            public void setFriendCountryCnName(String friendCountryCnName) {
                this.friendCountryCnName = friendCountryCnName;
            }

            public String getFriendCountryEnName() {
                return friendCountryEnName;
            }

            public void setFriendCountryEnName(String friendCountryEnName) {
                this.friendCountryEnName = friendCountryEnName;
            }

            public String getFriendHeadImage() {
                return friendHeadImage;
            }

            public void setFriendHeadImage(String friendHeadImage) {
                this.friendHeadImage = friendHeadImage;
            }

            public String getFriendNickName() {
                return friendNickName;
            }

            public void setFriendNickName(String friendNickName) {
                this.friendNickName = friendNickName;
            }

            public int getFriendUserId() {
                return friendUserId;
            }

            public void setFriendUserId(int friendUserId) {
                this.friendUserId = friendUserId;
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

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
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
