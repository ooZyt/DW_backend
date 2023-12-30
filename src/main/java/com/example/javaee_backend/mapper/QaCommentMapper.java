package com.example.javaee_backend.mapper;

import com.example.javaee_backend.pojo.QaCommentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QaCommentMapper {
    @Select("SELECT comment_id AS commentId, user_id AS userId, qa_id AS qaId, parent_comment_id AS parentCommentId, comment_content AS commentContent, comment_time AS commentTime " +
            "FROM qa_discussion_info " +
            "WHERE qa_id = #{qaId} AND parent_comment_id IS NULL")
    List<QaCommentDTO> getParentCommentsByQaId(@Param("qaId") int qaId);

    @Select("SELECT comment_id AS commentId, user_id AS userId, qa_id AS qaId, parent_comment_id AS parentCommentId, comment_content AS commentContent, comment_time AS commentTime " +
            "FROM qa_discussion_info " +
            "WHERE qa_id = #{qaId} AND parent_comment_id = #{parentCommentId}")
    List<QaCommentDTO> getChildCommentsByQaIdAndParentId(
            @Param("qaId") int qaId,
            @Param("parentCommentId") int parentCommentId
    );
}