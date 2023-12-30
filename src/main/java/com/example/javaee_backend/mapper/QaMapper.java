package com.example.javaee_backend.mapper;

import java.util.List;
import com.example.javaee_backend.pojo.QaDTO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface QaMapper {
    @Select("SELECT qa_id AS qaId, user_id AS userId, user_name AS userName, qa_title AS qaTitle, qa_content AS qaContent, qa_publish_time AS qaPublishTime, visitor_number AS visitorNumber, like_number AS likeNumber, collect_number AS collectNumber " +
            "FROM qa_info")
    List<QaDTO> getAllQas();

    @Insert("INSERT INTO qa_info(qa_id, user_id, user_name, qa_title, qa_content, qa_publish_time, visitor_number, like_number, collect_number) " +
            "VALUES(#{qa.qaId}, #{qa.userId}, #{qa.userName}, #{qa.qaTitle}, #{qa.qaContent}, #{qa.qaPublishTime}, #{qa.visitorNumber}, #{qa.likeNumber}, #{qa.collectNumber})")
    int addQa(@Param("qa") QaDTO qaDTO);

    @Select("SELECT qa_id AS qaId, user_id AS userId, user_name AS userName, qa_title AS qaTitle, qa_content AS qaContent, qa_publish_time AS qaPublishTime, visitor_number AS visitorNumber, like_number AS likeNumber, collect_number AS collectNumber " +
            "FROM qa_info WHERE qa_title = #{title}")
    List<QaDTO> getQaByTitle(@Param("title") String qaTitle);

    @Select("SELECT qa_id AS qaId, user_id AS userId, user_name AS userName, qa_title AS qaTitle, qa_content AS qaContent, qa_publish_time AS qaPublishTime, visitor_number AS visitorNumber, like_number AS likeNumber, collect_number AS collectNumber " +
            "FROM qa_info WHERE user_id = #{userId}")
    List<QaDTO> getQaByUserId(@Param("userId") int userId);

    @Select("SELECT qa_id AS qaId, user_id AS userId, user_name AS userName, qa_title AS qaTitle, qa_content AS qaContent, qa_publish_time AS qaPublishTime, visitor_number AS visitorNumber, like_number AS likeNumber, collect_number AS collectNumber " +
            "FROM qa_info WHERE qa_title LIKE CONCAT('%', #{SearchKey}, '%')")
    List<QaDTO> getQaBySearchKey(@Param("SearchKey") String SearchKey);

    @Select("SELECT qa_id AS qaId, user_id AS userId, user_name AS userName, qa_title AS qaTitle, qa_content AS qaContent, qa_publish_time AS qaPublishTime, visitor_number AS visitorNumber, like_number AS likeNumber, collect_number AS collectNumber " +
            "FROM qa_info WHERE qa_id = #{qaId}")
    QaDTO getQa(@Param("qaId") int qaId);

    @Update("UPDATE qa_info SET user_name = #{qa.userName}, qa_title = #{qa.qaTitle}, qa_content = #{qa.qaContent}, qa_publish_time = #{qa.qaPublishTime}, visitor_number = #{qa.visitorNumber}, like_number = #{qa.likeNumber}, collect_number = #{qa.collectNumber} WHERE qa_id = #{qa.qaId}")
    int updateQa(@Param("qa") QaDTO qaDTO);

    @Delete("DELETE FROM qa_info WHERE qa_id = #{qaId}")
    int deleteQa(@Param("qaId") int qaId);

    @Update("update qa_info set visitor_number = visitor_number + 1 where qa_id = #{qaId}")
    int increaseVisitorNumber(@Param("qaId") int qaId);

    @Update("update qa_info set like_number = like_number + 1 where qa_id = #{qaId}")
    int increaseLikeNumber(@Param("qaId") int qaId);

    @Update("update qa_info set collect_number = collect_number + 1 where qa_id = #{qaId}")
    int increaseCollectNumber(@Param("qaId") int qaId);
}
