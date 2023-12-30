package com.example.javaee_backend.pojo;

import com.example.javaee_backend.bean.CodeMsg;

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

    public static <T> ResultDTO<T> success(T data) {
        return new ResultDTO<>(CodeMsg.SUCCESS.getCode(), data);
    }

    public static <T> ResultDTO<T> successByMsg(T data, String msg) {
        return new ResultDTO<>(CodeMsg.SUCCESS.getCode(), msg, data);
    }

    public static <T> ResultDTO<T> errorByMsg(CodeMsg codeMsg) {
        return new ResultDTO<>(codeMsg.getCode(),codeMsg.getMsg());
    }
}
