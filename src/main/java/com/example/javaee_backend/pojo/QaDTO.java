package com.example.javaee_backend.pojo;

import java.util.Date;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//
//@Getter
//@Setter
//@ToString
public class QaDTO {
    private int qaId;
    private int userId;
    private String userName;
    private String qaTitle;
    private String qaContent;
    private Date qaPublishTime;
    private int visitorNumber;
    private int likeNumber;
    private int collectNumber;

    // Getters and Setters
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
    public String getUserName() {return userName;}

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getQaTitle() {
        return qaTitle;
    }

    public void setQaTitle(String qaTitle) {
        this.qaTitle = qaTitle;
    }

    public String getQaContent() {
        return qaContent;
    }

    public void setQaContent(String qaContent) {
        this.qaContent = qaContent;
    }

    public Date getQaPublishTime() {
        return qaPublishTime;
    }

    public void setQaPublishTime(Date qaPublishTime) {
        this.qaPublishTime = qaPublishTime;
    }

    public int getVisitorNumber() {
        return visitorNumber;
    }

    public void setVisitorNumber(int visitorNumber) {
        this.visitorNumber = visitorNumber;
    }

    public int getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(int likeNumber) {
        this.likeNumber = likeNumber;
    }

    public int getCollectNumber() {
        return collectNumber;
    }

    public void setCollectNumber(int collectNumber) {
        this.collectNumber = collectNumber;
    }

    // toString method for logging and debugging purposes
    @Override
    public String toString() {
        return "QaDTO{" +
                "qaId=" + qaId +
                ", userId=" + userId +
                ", qaTitle='" + qaTitle + '\'' +
                ", qaContent='" + qaContent + '\'' +
                ", qaPublishTime=" + qaPublishTime +
                ", visitorNumber=" + visitorNumber +
                ", likeNumber=" + likeNumber +
                ", collectNumber=" + collectNumber +
                '}';
    }
}

