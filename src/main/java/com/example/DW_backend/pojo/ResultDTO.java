package com.example.DW_backend.pojo;

public class ResultDTO <T> {
    //响应码
    private int code;
    private String msg;
    private T data;

    public ResultDTO(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public ResultDTO(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String Msg){
        this.msg = Msg;
    }
    public T getData() {return data;}
    public void setData(T data) {this.data = data;}

    private ResultDTO(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private ResultDTO(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    private ResultDTO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
