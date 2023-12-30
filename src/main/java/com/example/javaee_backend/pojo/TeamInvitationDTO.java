package com.example.javaee_backend.pojo;

import java.util.Date;
//注意：lombok已经在pom.xml中exclude,所以不能用了
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//
//@Getter
//@Setter
//@ToString


public class TeamInvitationDTO {
    private int teamInvitationId;
    private int userId;
    private String userName;
    private String teamInvitationTitle;
    private String teamInvitationContent;
    private Date teamInvitationPublishTime;

    public int getTeamInvitationId() {
        return teamInvitationId;
    }

    public void setTeamInvitationId(int teamInvitationId) {
        this.teamInvitationId = teamInvitationId;
    }

    public String getUserName() {return userName;}

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTeamInvitationTitle() {
        return teamInvitationTitle;
    }

    public void setTeamInvitationTitle(String teamInvitationTitle) {
        this.teamInvitationTitle = teamInvitationTitle;
    }

    public String getTeamInvitationContent() {
        return teamInvitationContent;
    }

    public void setTeamInvitationContent(String teamInvitationContent) {
        this.teamInvitationContent = teamInvitationContent;
    }

    public Date getTeamInvitationPublishTime() {
        return teamInvitationPublishTime;
    }

    public void setTeamInvitationPublishTime(Date teamInvitationPublishTime) {
        this.teamInvitationPublishTime = teamInvitationPublishTime;
    }
}