package com.igw.igw.bean.chat;

/**
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 * @Describe
 */
public class BannedBean {




    private DataBean dataBean;

    private String message;

    private int code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getDataBean() {
        return dataBean;
    }

    public void setDataBean(DataBean dataBean) {
        this.dataBean = dataBean;
    }

    public static class DataBean {

        public int getBlockType() {
            return blockType;
        }

        public void setBlockType(int blockType) {
            this.blockType = blockType;
        }

        private int blockType;

    }
}
