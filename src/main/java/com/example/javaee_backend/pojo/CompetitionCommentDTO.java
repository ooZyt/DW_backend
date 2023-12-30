package com.example.javaee_backend.pojo;


import java.util.Date;
import java.util.List;

public class CompetitionCommentDTO { // 测试
    private int commentId;
    private int userId;
    private String userName;
    private int competitionId;
    private Integer parentCommentId;
    private String parentCommentUserName;
    private String commentContent;
    private Date commentTime;
    private List<CompetitionCommentDTO> children;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(int competitionId) {
        this.competitionId = competitionId;
    }

    public Integer getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public String getParentCommentUserName() {
        return parentCommentUserName;
    }

    public void setParentCommentUserName(String parentCommentUserName) {
        this.parentCommentUserName = parentCommentUserName;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public List<CompetitionCommentDTO> getChildren() {
        return children;
    }

    public void setChildren(List<CompetitionCommentDTO> children) {
        this.children = children;
    }

}
