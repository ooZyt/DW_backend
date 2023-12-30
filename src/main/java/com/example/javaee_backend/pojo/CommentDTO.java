package com.example.javaee_backend.pojo;

import java.util.Date;
import java.util.List;

public class CommentDTO { // this pojo is for article. sorry for the bad name.
    private int commentId;
    private int userId;

    private String userName;
    private int articleId;
    private Integer parentCommentId;
    private String commentContent;
    private Date commentTime;
    private String parentCommentUserName;
    private List<CommentDTO> children;
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

    public int getArticleId() {
        return articleId;
    }

    public void setCompetitionId(int articleId) {
        this.articleId = articleId;
    }

    public Integer getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(int parentCommentId) {
        this.parentCommentId = parentCommentId;
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

    public String getParentCommentUserName() {
        return parentCommentUserName;
    }

    public void setParentCommentUserName(String parentCommentUserName) {
        this.parentCommentUserName = parentCommentUserName;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<CommentDTO> getChildren() {
        return children;
    }
    public void setChildren(List<CommentDTO> children) {
        this.children = children;
    }

}
