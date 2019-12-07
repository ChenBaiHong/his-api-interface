package com.healthan.liugong.apiintranet.common;

public class Results<T> {

    private int code = 0;
    private String message = "";
    private T result;

    public Results() {}
    public Results(int code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public Results(T result) {
        this.result = result;
    }

    public static Results Success(Object data){
        return new Results(200, "成功", data);
    }

    public static Results error(Object data){
        return new Results(400,"失败",data);
    }

    public static Results loginError(Object data){
        return new Results(401,"失败",data);
    }

    public static Results isfail(String data){
        return new Results(402,data,"");
    }

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

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
