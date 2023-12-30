package com.example.javaee_backend.enums;

/**
 * 会话信息是否展示枚举类
 */
public enum ChatShowEnum {

    YES(1,"显示"),

    NO(2,"不显示"),

    ;

    Integer code;

    String desc;

    ChatShowEnum(Integer code, String desc) {
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
