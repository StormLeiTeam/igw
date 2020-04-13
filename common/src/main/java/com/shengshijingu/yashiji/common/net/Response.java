package com.shengshijingu.yashiji.common.net;

import java.io.Serializable;

import com.google.gson.JsonElement;

/**
 * Author : 雷雷
 * Date : 2018/5/4
 * Description :
 */

public class Response<T> implements Serializable{

    /**
     * status : SUCCEED
     */

    private String status;

    private String code;

    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String msg;

    private JsonElement data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public JsonElement getResult() {
        return data;
    }

    public void setResult(JsonElement result) {
        this.data = result;
    }

    public boolean isSuccess(){
        return "200".equals(code);
    }
}
