package com.example.javaee_backend.pojo;

import java.util.Date;

public class ArticleDTO {
    private int articleId;
    private String articleName;
    private Date articlePublishTime;
    private String articleInfo;
    private int visitorNumber;
    private int likeNumber;
    private int collectNumber;
    private String sponsorName;
    private int userId;
    
    // Getter and setter for articleId
    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    // Getter and setter for articleName
    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    // Getter and setter for articlePublishTime
    public Date getArticlePublishTime() {
        return articlePublishTime;
    }

    public void setArticlePublishTime(Date articlePublishTime) {
        this.articlePublishTime = articlePublishTime;
    }

    // Getter and setter for articleInfo
    public String getArticleInfo() {
        return articleInfo;
    }

    public void setArticleInfo(String articleInfo) {
        this.articleInfo = articleInfo;
    }

    // Getter and setter for visitorNumber
    public int getVisitorNumber() {
        return visitorNumber;
    }

    public void setVisitorNumber(int visitorNumber) {
        this.visitorNumber = visitorNumber;
    }

    // Getter and setter for likeNumber
    public int getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(int likeNumber) {
        this.likeNumber = likeNumber;
    }

    // Getter and setter for collectNumber
    public int getCollectNumber() {
        return collectNumber;
    }

    public void setCollectNumber(int collectNumber) {
        this.collectNumber = collectNumber;
    }

    // Getter and setter for sponsorName
    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

    // Getter and setter for userId
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
