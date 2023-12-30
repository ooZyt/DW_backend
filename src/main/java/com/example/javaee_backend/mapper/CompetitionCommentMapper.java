package com.example.javaee_backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.javaee_backend.pojo.CompetitionCommentDTO;

@Mapper
public interface CompetitionCommentMapper {
    @Select("SELECT comment_id AS commentId, user_id AS userId, competition_id AS competitionId, parent_comment_id AS parentCommentId, comment_content AS commentContent, comment_time AS commentTime " +
            "FROM competition_discussion_info " +
            "WHERE competition_id = #{competitionId} AND parent_comment_id IS NULL")
    List<CompetitionCommentDTO> getParentCommentsByCompetitionId(@Param("competitionId") int competitionId);

    @Select("SELECT comment_id AS commentId, user_id AS userId, competition_id AS competitionId, parent_comment_id AS parentCommentId, comment_content AS commentContent, comment_time AS commentTime " +
            "FROM competition_discussion_info " +
            "WHERE competition_id = #{competitionId} AND parent_comment_id = #{parentCommentId}")
    List<CompetitionCommentDTO> getChildCommentsByCompetitionIdAndParentId(
            @Param("competitionId") int competitionId,
            @Param("parentCommentId") int parentCommentId
    );
}