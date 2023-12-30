package com.example.javaee_backend.mapper;

import com.example.javaee_backend.pojo.CommentDTO;
import com.example.javaee_backend.pojo.CompetitionCommentDTO;
import com.example.javaee_backend.pojo.QaCommentDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Insert("insert into competition_discussion_info(user_id, competition_id, parent_comment_id, comment_content, comment_time, parent_comment_user_name, user_name) " +
            "values(#{comment.userId}, #{comment.competitionId}, #{comment.parentCommentId}, #{comment.commentContent}, #{comment.commentTime}, #{comment.parentCommentUserName}, #{comment.userName})")
    int createCompetitionComment(@Param("comment") CompetitionCommentDTO commentDTO);

    @Insert("insert into article_discussion_info(user_id, article_id, parent_comment_id, comment_content, comment_time, parent_comment_user_name, user_name) " +
            "values(#{comment.userId}, #{comment.articleId}, #{comment.parentCommentId}, #{comment.commentContent}, #{comment.commentTime}, #{comment.parentCommentUserName}, #{comment.userName})")
    int createArticleComment(@Param("comment") CommentDTO commentDTO);

    @Insert("insert into qa_discussion_info(user_id, qa_id, parent_comment_id, comment_content, comment_time, parent_comment_user_name, user_name) " +
            "values(#{comment.userId}, #{comment.qaId}, #{comment.parentCommentId}, #{comment.commentContent}, #{comment.commentTime}, #{comment.parentCommentUserName}, #{comment.userName})")
    int createQaComment(@Param("comment") QaCommentDTO qaCommentDTO);


    @Select("select comment_id as commentId, user_id as userId, article_id as articleId, parent_comment_id as parentCommentId, comment_content as commentContent, comment_time as commentTime, parent_comment_user_name as parentCommentUserName, user_name as userName from article_discussion_info where article_id=#{articleId}")
    List<CommentDTO> getCommentsByArticleId(@Param("articleId") int articleId);

    @Select("select comment_id as commentId, user_id as userId, competition_id as competitionId, parent_comment_id as parentCommentId, comment_content as commentContent, comment_time as commentTime, parent_comment_user_name as parentCommentUserName, user_name as userName from competition_discussion_info where competition_id=#{competitionId}")
    List<CompetitionCommentDTO> getCommentsByCompetitionId(@Param("competitionId") int competitionId);

    @Select("select comment_id as commentId, user_id as userId, qa_id as qaId, parent_comment_id as parentCommentId, comment_content as commentContent, comment_time as commentTime, parent_comment_user_name as parentCommentUserName, user_name as userName from qa_discussion_info where qa_id=#{qaId}")
    List<QaCommentDTO> getCommentsByQaId(@Param("qaId") int qaId);

    @Select("select comment_id as commentId, user_id as userId, competition_id as competitionId, parent_comment_id as parentCommentId, comment_content as commentContent, comment_time as commentTime, parent_comment_user_name as parentCommentUserName, user_name as userName from competition_discussion_info")
    List<CompetitionCommentDTO> getAllCompetitionComments();
    @Select("select comment_id as commentId, user_id as userId, article_id as articleId, parent_comment_id as parentCommentId, comment_content as commentContent, comment_time as commentTime, parent_comment_user_name as parentCommentUserName, user_name as userName from article_discussion_info")
    List<CommentDTO> getAllArticleComments();
    @Select("select comment_id as commentId, user_id as userId, qa_id as qaId, parent_comment_id as parentCommentId, comment_content as commentContent, comment_time as commentTime, parent_comment_user_name as parentCommentUserName, user_name as userName from qa_discussion_info")
    List<QaCommentDTO> getAllQaComments();
}