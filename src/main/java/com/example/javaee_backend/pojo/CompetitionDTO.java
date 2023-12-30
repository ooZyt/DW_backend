package com.example.javaee_backend.pojo;

import java.util.Date;

public class CompetitionDTO {
    private int competitionId;
    private String competitionName;
    private Date competitionStartTime;
    private Date competitionEndTime;
    private Date registrationStartTime;
    private Date registrationEndTime;
    private Date competitionPublishTime;
    private String websiteUrl;
    private String competitionInfo;
    private String competitionType;
    private String competitionLevel;
    private int visitorNumber;
    private int collectNumber;
    private String sponsorName;
    private Integer userId;  // 使用 Integer 以便处理可能的 null 值

    // Getter and setter for competitionId
    public int getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(int competitionId) {
        this.competitionId = competitionId;
    }

    // Getter and setter for competitionName
    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    // Getter and setter for competitionStartTime
    public Date getCompetitionStartTime() {
        return competitionStartTime;
    }

    public void setCompetitionStartTime(Date competitionStartTime) {
        this.competitionStartTime = competitionStartTime;
    }

    // Getter and setter for competitionEndTime
    public Date getCompetitionEndTime() {
        return competitionEndTime;
    }

    public void setCompetitionEndTime(Date competitionEndTime) {
        this.competitionEndTime = competitionEndTime;
    }

    // Getter and setter for registrationStartTime
    public Date getRegistrationStartTime() {
        return registrationStartTime;
    }

    public void setRegistrationStartTime(Date registrationStartTime) {
        this.registrationStartTime = registrationStartTime;
    }

    // Getter and setter for registrationEndTime
    public Date getRegistrationEndTime() {
        return registrationEndTime;
    }

    public void setRegistrationEndTime(Date registrationEndTime) {
        this.registrationEndTime = registrationEndTime;
    }

    // Getter and setter for competitionPublishTime
    public Date getCompetitionPublishTime() {
        return competitionPublishTime;
    }

    public void setCompetitionPublishTime(Date competitionPublishTime) {
        this.competitionPublishTime = competitionPublishTime;
    }

    // Getter and setter for websiteUrl
    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    // Getter and setter for competitionInfo
    public String getCompetitionInfo() {
        return competitionInfo;
    }

    public void setCompetitionInfo(String competitionInfo) {
        this.competitionInfo = competitionInfo;
    }

    // Getter and setter for competitionType
    public String getCompetitionType() {
        return competitionType;
    }

    public void setCompetitionType(String competitionType) {
        this.competitionType = competitionType;
    }

    // Getter and setter for competitionLevel
    public String getCompetitionLevel() {
        return competitionLevel;
    }

    public void setCompetitionLevel(String competitionLevel) {
        this.competitionLevel = competitionLevel;
    }

    // Getter and setter for visitorNumber
    public int getVisitorNumber() {
        return visitorNumber;
    }

    public void setVisitorNumber(int visitorNumber) {
        this.visitorNumber = visitorNumber;
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
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
