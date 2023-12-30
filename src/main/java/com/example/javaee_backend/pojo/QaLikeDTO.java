package com.example.javaee_backend.pojo;

import java.util.Date;

public class QaLikeDTO {
    private int qaId;
    private int userId;
    private Date likeTime;


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

    public Date getLikeTime() {
        return likeTime;
    }

    public void setLikeTime(Date likeTime) {
        this.likeTime = likeTime;
    }
}
