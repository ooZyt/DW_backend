package com.example.javaee_backend.service;

import com.example.javaee_backend.pojo.CommentDTO;
import com.example.javaee_backend.pojo.CompetitionCommentDTO;
import com.example.javaee_backend.pojo.ListCommentDTO;
import com.example.javaee_backend.pojo.QaCommentDTO;

import java.util.List;

public interface CommentService {

    int createCompetitionComment(CompetitionCommentDTO commentDTO);

    int createArticleComment(CommentDTO commentDTO);
    int createQaComment(QaCommentDTO commentDTO);


    List<CompetitionCommentDTO> getCompetitionCommentById(int competitionId);
    List<CommentDTO> getArticleCommentById(int articleId);
    List<QaCommentDTO> getQaCommentById(int qaId);

    List<CompetitionCommentDTO> getAllCompetitionComments();
    List<CommentDTO> getAllArticleComments();
    List<QaCommentDTO> getAllQaComments();


}