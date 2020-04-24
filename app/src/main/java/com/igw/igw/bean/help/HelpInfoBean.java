package com.igw.igw.bean.help;

/**
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 * @Describe
 */
public class HelpInfoBean {


    /**
     * code : 200
     * message : 操作成功
     */

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


    }
}
