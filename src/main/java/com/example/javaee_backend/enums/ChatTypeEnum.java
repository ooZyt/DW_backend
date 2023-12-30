package com.example.javaee_backend.enums;

/**
 * 聊天类型枚举类
 */
public enum ChatTypeEnum {

    SINGLE(1,"单聊"),

    GROUP(2,"群聊"),

    ;

    Integer code;

    String desc;

    ChatTypeEnum(Integer code, String desc) {
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
