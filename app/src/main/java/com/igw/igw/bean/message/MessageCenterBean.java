package com.igw.igw.bean.message;

import java.util.List;

/**
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 * @Describe
 */
public class MessageCenterBean {
    /**
     * code : 200
     * data : {"total":1,"rows":[{"ctime":"2020-07-21 09:54:16","headImage":"http://106.54.111.24:84/gameword-user-service/upload/system/image/20200721/20200721095224_677_20200721095224.jpg","id":1,"isAgree":1,"isRead":1,"messageContent":"添加您为好友，是否同意？","messageName":"添加好友","messageType":1,"messageUrl":"{\"firstCharPinyin\":\"\",\"friendAgencyName\":\"\",\"friendCityCnName\":\"\",\"friendCityEnName\":\"\",\"friendCountryCnName\":\"\",\"friendCountryEnName\":\"\",\"friendCountryFlag\":\"\",\"friendHeadImage\":\"\",\"friendNickName\":\"\",\"friendSex\":0,\"friendUserDesc\":\"\",\"friendUserId\":2,\"sortWithOutOrderBy\":\"\",\"sort_\":\"\",\"userId\":3}","sortWithOutOrderBy":"","sort_":"","userId":2}]}
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
         * total : 1
         * rows : [{"ctime":"2020-07-21 09:54:16","headImage":"http://106.54.111.24:84/gameword-user-service/upload/system/image/20200721/20200721095224_677_20200721095224.jpg","id":1,"isAgree":1,"isRead":1,"messageContent":"添加您为好友，是否同意？","messageName":"添加好友","messageType":1,"messageUrl":"{\"firstCharPinyin\":\"\",\"friendAgencyName\":\"\",\"friendCityCnName\":\"\",\"friendCityEnName\":\"\",\"friendCountryCnName\":\"\",\"friendCountryEnName\":\"\",\"friendCountryFlag\":\"\",\"friendHeadImage\":\"\",\"friendNickName\":\"\",\"friendSex\":0,\"friendUserDesc\":\"\",\"friendUserId\":2,\"sortWithOutOrderBy\":\"\",\"sort_\":\"\",\"userId\":3}","sortWithOutOrderBy":"","sort_":"","userId":2}]
         */

        private int total;
        private List<RowsBean> rows;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            /**
             * ctime : 2020-07-21 09:54:16
             * headImage : http://106.54.111.24:84/gameword-user-service/upload/system/image/20200721/20200721095224_677_20200721095224.jpg
             * id : 1
             * isAgree : 1
             * isRead : 1
             * messageContent : 添加您为好友，是否同意？
             * messageName : 添加好友
             * messageType : 1
             * messageUrl : {"firstCharPinyin":"","friendAgencyName":"","friendCityCnName":"","friendCityEnName":"","friendCountryCnName":"","friendCountryEnName":"","friendCountryFlag":"","friendHeadImage":"","friendNickName":"","friendSex":0,"friendUserDesc":"","friendUserId":2,"sortWithOutOrderBy":"","sort_":"","userId":3}
             * sortWithOutOrderBy :
             * sort_ :
             * userId : 2
             */

            private String ctime;
            private String headImage;
            private int id;
            private int isAgree;
            private int isRead;
            private String messageContent;
            private String messageName;
            private int messageType;
            private String messageUrl;
            private String sortWithOutOrderBy;
            private String sort_;
            private int userId;

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getHeadImage() {
                return headImage;
            }

            public void setHeadImage(String headImage) {
                this.headImage = headImage;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getIsAgree() {
                return isAgree;
            }

            public void setIsAgree(int isAgree) {
                this.isAgree = isAgree;
            }

            public int getIsRead() {
                return isRead;
            }

            public void setIsRead(int isRead) {
                this.isRead = isRead;
            }

            public String getMessageContent() {
                return messageContent;
            }

            public void setMessageContent(String messageContent) {
                this.messageContent = messageContent;
            }

            public String getMessageName() {
                return messageName;
            }

            public void setMessageName(String messageName) {
                this.messageName = messageName;
            }

            public int getMessageType() {
                return messageType;
            }

            public void setMessageType(int messageType) {
                this.messageType = messageType;
            }

            public String getMessageUrl() {
                return messageUrl;
            }

            public void setMessageUrl(String messageUrl) {
                this.messageUrl = messageUrl;
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
        }
    }


//
//
//    /**
//     * code : 200
//     * data : {"total":4,"rows":[{"ctime":"2020-05-15 15:09:32","id":1,"isAgree":0,"isRead":0,"messageContent":"测试内容","messageName":"测试","messageType":1,"messageUrl":"","sortWithOutOrderBy":"","sort_":"","userId":1},{"ctime":"2020-05-15 15:09:42","id":2,"isAgree":0,"isRead":0,"messageContent":"测试内容1","messageName":"测试1","messageType":2,"messageUrl":"","sortWithOutOrderBy":"","sort_":"","userId":1},{"ctime":"2020-05-15 15:09:48","id":3,"isAgree":0,"isRead":0,"messageContent":"测试内容2","messageName":"测试2","messageType":2,"messageUrl":"","sortWithOutOrderBy":"","sort_":"","userId":1},{"ctime":"2020-05-15 15:09:54","id":4,"isAgree":0,"isRead":0,"messageContent":"测试内容3","messageName":"测试3","messageType":1,"messageUrl":"","sortWithOutOrderBy":"","sort_":"","userId":1}]}
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
//        /**
//         * total : 4
//         * rows : [{"ctime":"2020-05-15 15:09:32","id":1,"isAgree":0,"isRead":0,"messageContent":"测试内容","messageName":"测试","messageType":1,"messageUrl":"","sortWithOutOrderBy":"","sort_":"","userId":1},{"ctime":"2020-05-15 15:09:42","id":2,"isAgree":0,"isRead":0,"messageContent":"测试内容1","messageName":"测试1","messageType":2,"messageUrl":"","sortWithOutOrderBy":"","sort_":"","userId":1},{"ctime":"2020-05-15 15:09:48","id":3,"isAgree":0,"isRead":0,"messageContent":"测试内容2","messageName":"测试2","messageType":2,"messageUrl":"","sortWithOutOrderBy":"","sort_":"","userId":1},{"ctime":"2020-05-15 15:09:54","id":4,"isAgree":0,"isRead":0,"messageContent":"测试内容3","messageName":"测试3","messageType":1,"messageUrl":"","sortWithOutOrderBy":"","sort_":"","userId":1}]
//         */
//
//        private int total;
//        private List<RowsBean> rows;
//
//        public int getTotal() {
//            return total;
//        }
//
//        public void setTotal(int total) {
//            this.total = total;
//        }
//
//        public List<RowsBean> getRows() {
//            return rows;
//        }
//
//        public void setRows(List<RowsBean> rows) {
//            this.rows = rows;
//        }
//
//        public static class RowsBean {
//            /**
//             * ctime : 2020-05-15 15:09:32
//             * id : 1
//             * isAgree : 0
//             * isRead : 0
//             * messageContent : 测试内容
//             * messageName : 测试
//             * messageType : 1
//             * messageUrl :
//             * sortWithOutOrderBy :
//             * sort_ :
//             * userId : 1
//             */
//
//            private String ctime;
//            private int id;
//            private int isAgree;
//            private int isRead;
//            private String messageContent;
//            private String messageName;
//            private int messageType;
//            private String messageUrl;
//            private String sortWithOutOrderBy;
//            private String sort_;
//            private int userId;
//
//            public String getCtime() {
//                return ctime;
//            }
//
//            public void setCtime(String ctime) {
//                this.ctime = ctime;
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
//            public int getIsAgree() {
//                return isAgree;
//            }
//
//            public void setIsAgree(int isAgree) {
//                this.isAgree = isAgree;
//            }
//
//            public int getIsRead() {
//                return isRead;
//            }
//
//            public void setIsRead(int isRead) {
//                this.isRead = isRead;
//            }
//
//            public String getMessageContent() {
//                return messageContent;
//            }
//
//            public void setMessageContent(String messageContent) {
//                this.messageContent = messageContent;
//            }
//
//            public String getMessageName() {
//                return messageName;
//            }
//
//            public void setMessageName(String messageName) {
//                this.messageName = messageName;
//            }
//
//            public int getMessageType() {
//                return messageType;
//            }
//
//            public void setMessageType(int messageType) {
//                this.messageType = messageType;
//            }
//
//            public String getMessageUrl() {
//                return messageUrl;
//            }
//
//            public void setMessageUrl(String messageUrl) {
//                this.messageUrl = messageUrl;
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
//        }
//    }


//    private int code;
//    private String message;
//    private CommonBean.DataBean data;
//
//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
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
//    public CommonBean.DataBean getData() {
//        return data;
//    }
//
//    public void setData(CommonBean.DataBean data) {
//        this.data = data;
//    }
//
//    public static class DataBean {
//
//        private int total;
//        private List<RowsBean> rows;
//
//
//        public int getTotal() {
//            return total;
//        }
//
//        public void setTotal(int total) {
//            this.total = total;
//        }
//
//        public List<RowsBean> getRows() {
//            return rows;
//        }
//
//        public void setRows(List<RowsBean> rows) {
//            this.rows = rows;
//        }
//
//        public static class RowsBean {
//
//            private int id;
//            private String messageName;
//            private String messageContent;
//            private int messageType;
//            private boolean isAgree;
//
//            public boolean isAgree() {
//                return isAgree;
//            }
//
//            public void setAgree(boolean agree) {
//                isAgree = agree;
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
//            public String getMessageName() {
//                return messageName;
//            }
//
//            public void setMessageName(String messageName) {
//                this.messageName = messageName;
//            }
//
//            public String getMessageContent() {
//                return messageContent;
//            }
//
//            public void setMessageContent(String messageContent) {
//                this.messageContent = messageContent;
//            }
//
//            public int getMessageType() {
//                return messageType;
//            }
//
//            public void setMessageType(int messageType) {
//                this.messageType = messageType;
//            }
//
//            //
////            rows": [{
////                    "id": 1, //消息ID
////                    "messageName": "恭喜你来到酷玩街区", //消息名称
////                    "messageContent": "亲爱的好友，恭喜您已注册成功", //消息内容
////                    "messageType": 1,//消息类型（1：活动与邀请，2：通知）
////                    "isAgree": 0,//是否同意（0：默认，1：同意，2：不同意）
////                    "ctimeStr": "2019年9月15日 15:47"//创建时间
////        }],
////                "total": 10
//        }
//    }
}
