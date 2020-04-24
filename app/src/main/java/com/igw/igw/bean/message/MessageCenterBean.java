package com.igw.igw.bean.message;

import com.igw.igw.bean.help.CommonBean;

import java.util.List;

/**
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 * @Describe
 */
public class MessageCenterBean {


    private int code;
    private String message;
    private CommonBean.DataBean data;

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

    public CommonBean.DataBean getData() {
        return data;
    }

    public void setData(CommonBean.DataBean data) {
        this.data = data;
    }

    public static class DataBean {

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

            private int id;
            private String messageName;
            private String messageContent;
            private int messageType;
            private boolean isAgree;

            public boolean isAgree() {
                return isAgree;
            }

            public void setAgree(boolean agree) {
                isAgree = agree;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getMessageName() {
                return messageName;
            }

            public void setMessageName(String messageName) {
                this.messageName = messageName;
            }

            public String getMessageContent() {
                return messageContent;
            }

            public void setMessageContent(String messageContent) {
                this.messageContent = messageContent;
            }

            public int getMessageType() {
                return messageType;
            }

            public void setMessageType(int messageType) {
                this.messageType = messageType;
            }

            //
//            rows": [{
//                    "id": 1, //消息ID
//                    "messageName": "恭喜你来到酷玩街区", //消息名称
//                    "messageContent": "亲爱的好友，恭喜您已注册成功", //消息内容
//                    "messageType": 1,//消息类型（1：活动与邀请，2：通知）
//                    "isAgree": 0,//是否同意（0：默认，1：同意，2：不同意）
//                    "ctimeStr": "2019年9月15日 15:47"//创建时间
//        }],
//                "total": 10
        }
    }
}
