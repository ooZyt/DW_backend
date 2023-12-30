package com.example.javaee_backend.enums;

public enum MessageTypeEnum {

    TEXT(1,"文字消息"),

    FILE(2,"文件消息"),

    ;

    Integer code;

    String desc;

    MessageTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
