package com.example.javaee_backend.pojo;

import java.util.List;

public class ListCommentDTO {
    private CommentDTO comment;
    private List<CommentDTO> children;

    public CommentDTO getCommentDTO() {
        return comment;
    }

    public void setCommentDTO(CommentDTO comment) {
        this.comment = comment;
    }

    public List<CommentDTO> getChildren() {
        return children;
    }

    public void setChildren(List<CommentDTO> children) {
        this.children = children;
    }
    // getters and setters
}