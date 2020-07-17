package com.igw.igw.bean.chat;

import java.util.List;

/**
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 * @Describe
 */
public class ChatRoomUsesBean {


    /**
     * code : 200
     * data : {"roomUsers":[{"agencyName":"明咯咙","cityCnName":"北京","cityEnName":"BEIJING","countryCnName":"中国","countryEnName":"CHINA","countryFlag":"","headImage":"/gameword-user-service/upload/18235188642/image/20200429/20200429175403_841_headImage","isAdmin":0,"isBlock":0,"isFriend":1,"nickName":"hah\n哈哈","sex":1,"userDesc":"我哈哈哈哈哈哈哈哈","userId":1}]}
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
        private List<RoomUsersBean> roomUsers;

        public List<RoomUsersBean> getRoomUsers() {
            return roomUsers;
        }

        public void setRoomUsers(List<RoomUsersBean> roomUsers) {
            this.roomUsers = roomUsers;
        }

        public static class RoomUsersBean {
            /**
             * agencyName : 明咯咙
             * cityCnName : 北京
             * cityEnName : BEIJING
             * countryCnName : 中国
             * countryEnName : CHINA
             * countryFlag :
             * headImage : /gameword-user-service/upload/18235188642/image/20200429/20200429175403_841_headImage
             * isAdmin : 0
             * isBlock : 0
             * isFriend : 1
             * nickName : hah
             哈哈
             * sex : 1
             * userDesc : 我哈哈哈哈哈哈哈哈
             * userId : 1
             */

            private String agencyName;
            private String cityCnName;
            private String cityEnName;
            private String countryCnName;
            private String countryEnName;
            private String countryFlag;
            private String headImage;
            private int isAdmin;
            private int isBlock;
            private int isFriend;
            private String nickName;
            private int sex;
            private String userDesc;
            private int userId;

            public String getAgencyName() {
                return agencyName;
            }

            public void setAgencyName(String agencyName) {
                this.agencyName = agencyName;
            }

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

            public String getCountryCnName() {
                return countryCnName;
            }

            public void setCountryCnName(String countryCnName) {
                this.countryCnName = countryCnName;
            }

            public String getCountryEnName() {
                return countryEnName;
            }

            public void setCountryEnName(String countryEnName) {
                this.countryEnName = countryEnName;
            }

            public String getCountryFlag() {
                return countryFlag;
            }

            public void setCountryFlag(String countryFlag) {
                this.countryFlag = countryFlag;
            }

            public String getHeadImage() {
                return headImage;
            }

            public void setHeadImage(String headImage) {
                this.headImage = headImage;
            }

            public int getIsAdmin() {
                return isAdmin;
            }

            public void setIsAdmin(int isAdmin) {
                this.isAdmin = isAdmin;
            }

            public int getIsBlock() {
                return isBlock;
            }

            public void setIsBlock(int isBlock) {
                this.isBlock = isBlock;
            }

            public int getIsFriend() {
                return isFriend;
            }

            public void setIsFriend(int isFriend) {
                this.isFriend = isFriend;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public String getUserDesc() {
                return userDesc;
            }

            public void setUserDesc(String userDesc) {
                this.userDesc = userDesc;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }
        }
    }
}
