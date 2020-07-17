package com.igw.igw.bean;

import com.tuacy.fuzzysearchlibrary.IFuzzySearchItem;
import com.tuacy.fuzzysearchlibrary.PinyinUtil;

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
     * data : {"friends":[{"ctime":"2020-05-06 22:34:52","firstCharPinyin":"L","friendAgencyName":"Kkkkkkkkkk","friendCityCnName":"北京","friendCityEnName":"BEIJING","friendCountryCnName":"中国","friendCountryEnName":"CHINA","friendCountryFlag":"","friendHeadImage":"http://106.54.111.24:84/gameword-user-service/upload/system/image/20200426/20200426102736_331_20200426102735.jpg","friendNickName":"Little king","friendSex":2,"friendUserDesc":"Ok, fine","friendUserId":5,"id":3,"noteName":"","sortWithOutOrderBy":"","sort_":"","userId":1,"utime":"2020-05-06 22:34:52"},{"ctime":"2020-05-06 22:34:57","firstCharPinyin":"K","friendAgencyName":"Gggggg","friendCityCnName":"北京","friendCityEnName":"BEIJING","friendCountryCnName":"中国","friendCountryEnName":"CHINA","friendCountryFlag":"","friendHeadImage":"http://106.54.111.24:84/gameword-user-service/upload/system/image/20200426/20200426101247_153_20200426101246.jpg","friendNickName":"Kk","friendSex":2,"friendUserDesc":"Dddddddddddd","friendUserId":4,"id":4,"noteName":"","sortWithOutOrderBy":"","sort_":"","userId":1,"utime":"2020-05-06 22:34:57"},{"ctime":"2020-07-08 17:31:50","firstCharPinyin":"H","friendAgencyName":"明咯咙","friendCityCnName":"北京","friendCityEnName":"BEIJING","friendCountryCnName":"中国","friendCountryEnName":"CHINA","friendCountryFlag":"","friendHeadImage":"/gameword-user-service/upload/18235188642/image/20200429/20200429175403_841_headImage","friendNickName":"hah\n哈哈","friendSex":1,"friendUserDesc":"我哈哈哈哈哈哈哈哈","friendUserId":1,"id":9,"noteName":"","sortWithOutOrderBy":"","sort_":"","userId":1,"utime":"2020-07-08 17:31:50"},{"ctime":"2020-07-12 01:48:16","firstCharPinyin":"C","friendAgencyName":"","friendCityCnName":"","friendCityEnName":"","friendCountryCnName":"中国","friendCountryEnName":"CHINA","friendCountryFlag":"","friendHeadImage":"/gameword-user-service/upload/15110198355/image/20200711/20200711230529_378_headImage","friendNickName":"测试昵称","friendSex":1,"friendUserDesc":"","friendUserId":16,"id":13,"noteName":"","sortWithOutOrderBy":"","sort_":"","userId":1,"utime":"2020-07-12 01:48:16"}]}
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

        public static class FriendsBean implements IFuzzySearchItem {
            /**
             * ctime : 2020-05-06 22:34:52
             * firstCharPinyin : L
             * friendAgencyName : Kkkkkkkkkk
             * friendCityCnName : 北京
             * friendCityEnName : BEIJING
             * friendCountryCnName : 中国
             * friendCountryEnName : CHINA
             * friendCountryFlag :
             * friendHeadImage : http://106.54.111.24:84/gameword-user-service/upload/system/image/20200426/20200426102736_331_20200426102735.jpg
             * friendNickName : Little king
             * friendSex : 2
             * friendUserDesc : Ok, fine
             * friendUserId : 5
             * id : 3
             * noteName :
             * sortWithOutOrderBy :
             * sort_ :
             * userId : 1
             * utime : 2020-05-06 22:34:52
             */

            private String ctime;
            private String firstCharPinyin;
            private String friendAgencyName;
            private String friendCityCnName;
            private String friendCityEnName;
            private String friendCountryCnName;
            private String friendCountryEnName;
            private String friendCountryFlag;
            private String friendHeadImage;
            private String friendNickName;
            private int friendSex;
            private String friendUserDesc;
            private int friendUserId;
            private int id;
            private String noteName;
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

            public String getFriendAgencyName() {
                return friendAgencyName;
            }

            public void setFriendAgencyName(String friendAgencyName) {
                this.friendAgencyName = friendAgencyName;
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

            public String getFriendCountryFlag() {
                return friendCountryFlag;
            }

            public void setFriendCountryFlag(String friendCountryFlag) {
                this.friendCountryFlag = friendCountryFlag;
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

            public int getFriendSex() {
                return friendSex;
            }

            public void setFriendSex(int friendSex) {
                this.friendSex = friendSex;
            }

            public String getFriendUserDesc() {
                return friendUserDesc;
            }

            public void setFriendUserDesc(String friendUserDesc) {
                this.friendUserDesc = friendUserDesc;
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

            public String getNoteName() {
                return noteName;
            }

            public void setNoteName(String noteName) {
                this.noteName = noteName;
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

            @Override
            public String getSourceKey() {
                return friendNickName;
            }

            @Override
            public List<String> getFuzzyKey() {
                return PinyinUtil.getPinYinList(friendNickName);
            }
        }
    }


//
//    /**
//     * code : 200
//     * data : {"friends":[{"ctime":"2020-05-06 22:34:52","firstCharPinyin":"L","friendCityCnName":"北京","friendCityEnName":"BEIJING","friendCountryCnName":"中国","friendCountryEnName":"CHINA","friendHeadImage":"http://106.54.111.24:84/gameword-user-service/upload/system/image/20200426/20200426102736_331_20200426102735.jpg","friendNickName":"Little king","friendUserId":5,"id":3,"sortWithOutOrderBy":"","sort_":"","userId":1,"utime":"2020-05-06 22:34:52"},{"ctime":"2020-05-06 22:34:57","firstCharPinyin":"K","friendCityCnName":"北京","friendCityEnName":"BEIJING","friendCountryCnName":"中国","friendCountryEnName":"CHINA","friendHeadImage":"http://106.54.111.24:84/gameword-user-service/upload/system/image/20200426/20200426101247_153_20200426101246.jpg","friendNickName":"Kk","friendUserId":4,"id":4,"sortWithOutOrderBy":"","sort_":"","userId":1,"utime":"2020-05-06 22:34:57"}]}
//     * message : 操作成功
//     */
//
//    private int code;
//    private DataBean data;
//    private String message;
//
//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public DataBean getData() {
//        return data;
//    }
//
//    public void setData(DataBean data) {
//        this.data = data;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public static class DataBean {
//        private List<FriendsBean> friends;
//
//        public List<FriendsBean> getFriends() {
//            return friends;
//        }
//
//        public void setFriends(List<FriendsBean> friends) {
//            this.friends = friends;
//        }
//
//        public static class FriendsBean  implements IFuzzySearchItem {
//            /**
//             * ctime : 2020-05-06 22:34:52
//             * firstCharPinyin : L
//             * friendCityCnName : 北京
//             * friendCityEnName : BEIJING
//             * friendCountryCnName : 中国
//             * friendCountryEnName : CHINA
//             * friendHeadImage : http://106.54.111.24:84/gameword-user-service/upload/system/image/20200426/20200426102736_331_20200426102735.jpg
//             * friendNickName : Little king
//             * friendUserId : 5
//             * id : 3
//             * sortWithOutOrderBy :
//             * sort_ :
//             * userId : 1
//             * utime : 2020-05-06 22:34:52
//             */
//
//            private String ctime;
//            private String firstCharPinyin;
//            private String friendCityCnName;
//            private String friendCityEnName;
//            private String friendCountryCnName;
//            private String friendCountryEnName;
//            private String friendHeadImage;
//            private String friendNickName;
//            private int friendUserId;
//            private int id;
//            private String sortWithOutOrderBy;
//            private String sort_;
//            private int userId;
//            private String utime;
//
//            public String getCtime() {
//                return ctime;
//            }
//
//            public void setCtime(String ctime) {
//                this.ctime = ctime;
//            }
//
//            public String getFirstCharPinyin() {
//                return firstCharPinyin;
//            }
//
//            public void setFirstCharPinyin(String firstCharPinyin) {
//                this.firstCharPinyin = firstCharPinyin;
//            }
//
//            public String getFriendCityCnName() {
//                return friendCityCnName;
//            }
//
//            public void setFriendCityCnName(String friendCityCnName) {
//                this.friendCityCnName = friendCityCnName;
//            }
//
//            public String getFriendCityEnName() {
//                return friendCityEnName;
//            }
//
//            public void setFriendCityEnName(String friendCityEnName) {
//                this.friendCityEnName = friendCityEnName;
//            }
//
//            public String getFriendCountryCnName() {
//                return friendCountryCnName;
//            }
//
//            public void setFriendCountryCnName(String friendCountryCnName) {
//                this.friendCountryCnName = friendCountryCnName;
//            }
//
//            public String getFriendCountryEnName() {
//                return friendCountryEnName;
//            }
//
//            public void setFriendCountryEnName(String friendCountryEnName) {
//                this.friendCountryEnName = friendCountryEnName;
//            }
//
//            public String getFriendHeadImage() {
//                return friendHeadImage;
//            }
//
//            public void setFriendHeadImage(String friendHeadImage) {
//                this.friendHeadImage = friendHeadImage;
//            }
//
//            public String getFriendNickName() {
//                return friendNickName;
//            }
//
//            public void setFriendNickName(String friendNickName) {
//                this.friendNickName = friendNickName;
//            }
//
//            public int getFriendUserId() {
//                return friendUserId;
//            }
//
//            public void setFriendUserId(int friendUserId) {
//                this.friendUserId = friendUserId;
//            }
//
//            public int getId() {
//                return id;
//            }
//
//            public void setId(int id) {
//                this.id = id;
//            }
//
//            public String getSortWithOutOrderBy() {
//                return sortWithOutOrderBy;
//            }
//
//            public void setSortWithOutOrderBy(String sortWithOutOrderBy) {
//                this.sortWithOutOrderBy = sortWithOutOrderBy;
//            }
//
//            public String getSort_() {
//                return sort_;
//            }
//
//            public void setSort_(String sort_) {
//                this.sort_ = sort_;
//            }
//
//            public int getUserId() {
//                return userId;
//            }
//
//            public void setUserId(int userId) {
//                this.userId = userId;
//            }
//
//            public String getUtime() {
//                return utime;
//            }
//
//            public void setUtime(String utime) {
//                this.utime = utime;
//            }
//
//
//            // 获取原始字符串
//            @Override
//            public String getSourceKey() {
//
//                return friendNickName;
//            }
//
//
//
//            @Override
//            public List<String> getFuzzyKey() {
//
//                return PinyinUtil.getPinYinList(friendNickName);
//            }
//        }
//    }
}
