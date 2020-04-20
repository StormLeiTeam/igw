package com.shengshijingu.yashiji.common.net;

import java.io.PipedReader;
import java.io.Serializable;

import com.google.gson.JsonElement;

/**
 * Author : 雷雷
 * Date : 2018/5/4
 * Description :
 */

public class Response<T> implements Serializable {


    private int code;
    private String message;
    private JsonElement data;

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

    public JsonElement getData() {
        return data;
    }

    public void setData(JsonElement data) {
        this.data = data;
    }


    public boolean isSuccess() {

        return 200 == code;
    }


    //    /**
//     * status : SUCCEED
//     */
//
//    private String status;
//
//    private String code;
//
//    private String errorMessage;
//
//    public String" getErrorMessage() {
//        return errorMessage;
//    }
//
//    public void setErrorMessage(String errorMessage) {
//        this.errorMessage = errorMessage;
//    }
//
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    private String msg;
//
//    private JsonElement data;
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public JsonElement getResult() {
//        return data;
//    }
//
//    public void setResult(JsonElement result) {
//        this.data = result;
//    }
//
//    public boolean isSuccess(){
//        return "200".equals(code);
//    }
}
