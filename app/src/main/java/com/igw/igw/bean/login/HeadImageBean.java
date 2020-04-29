package com.igw.igw.bean.login;

/**
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 * @Describe 上传图片 返回跌bean
 *
 *
 *
 */
public class HeadImageBean {

    /**
     * code : 200
     * data : {"attachmentId":5,"attachmentName":"headImage","attachmentPath":"/opt/gameowrold/gameword.user.service/webapps/gameword-user-service/upload/18235188642/image/20200428/20200428164854_754_headImage","attachmentSize":78022,"attachmentSuffix":"headimage","attachmentType":0,"attachmentUrl":"/gameword-user-service/upload/18235188642/image/20200428/20200428164854_754_headImage","uploadLoginName":"18235188642"}
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
         * attachmentId : 5
         * attachmentName : headImage
         * attachmentPath : /opt/gameowrold/gameword.user.service/webapps/gameword-user-service/upload/18235188642/image/20200428/20200428164854_754_headImage
         * attachmentSize : 78022
         * attachmentSuffix : headimage
         * attachmentType : 0
         * attachmentUrl : /gameword-user-service/upload/18235188642/image/20200428/20200428164854_754_headImage
         * uploadLoginName : 18235188642
         */

        private int attachmentId;
        private String attachmentName;
        private String attachmentPath;
        private int attachmentSize;
        private String attachmentSuffix;
        private int attachmentType;
        private String attachmentUrl;
        private String uploadLoginName;

        public int getAttachmentId() {
            return attachmentId;
        }

        public void setAttachmentId(int attachmentId) {
            this.attachmentId = attachmentId;
        }

        public String getAttachmentName() {
            return attachmentName;
        }

        public void setAttachmentName(String attachmentName) {
            this.attachmentName = attachmentName;
        }

        public String getAttachmentPath() {
            return attachmentPath;
        }

        public void setAttachmentPath(String attachmentPath) {
            this.attachmentPath = attachmentPath;
        }

        public int getAttachmentSize() {
            return attachmentSize;
        }

        public void setAttachmentSize(int attachmentSize) {
            this.attachmentSize = attachmentSize;
        }

        public String getAttachmentSuffix() {
            return attachmentSuffix;
        }

        public void setAttachmentSuffix(String attachmentSuffix) {
            this.attachmentSuffix = attachmentSuffix;
        }

        public int getAttachmentType() {
            return attachmentType;
        }

        public void setAttachmentType(int attachmentType) {
            this.attachmentType = attachmentType;
        }

        public String getAttachmentUrl() {
            return attachmentUrl;
        }

        public void setAttachmentUrl(String attachmentUrl) {
            this.attachmentUrl = attachmentUrl;
        }

        public String getUploadLoginName() {
            return uploadLoginName;
        }

        public void setUploadLoginName(String uploadLoginName) {
            this.uploadLoginName = uploadLoginName;
        }
    }
}
