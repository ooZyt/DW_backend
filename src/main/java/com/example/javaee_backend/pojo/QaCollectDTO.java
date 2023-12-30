package com.example.javaee_backend.pojo;

import java.util.Date;

public class QaCollectDTO {
    private int qaId;
    private int userId;
    private Date collectTime;


    public int getQaId() {
        return qaId;
    }

    public void setQaId(int qaId) {
        this.qaId = qaId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }
}
