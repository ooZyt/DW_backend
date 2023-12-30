package com.example.javaee_backend.mapper;

import com.example.javaee_backend.pojo.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CollectMapper {
    @Insert("insert into article_collection_info(article_id, user_id, collect_time) values(#{collect.articleId}, #{collect.userId}, #{collect.collectTime})")
    int createArticleCollect(@Param("collect") CollectDTO collectDTO);
    @Insert("insert into competition_collection_info(competition_id, user_id, collect_time) values(#{collect.articleId}, #{collect.userId}, #{collect.collectTime})")
    int createCompetitionCollect(@Param("collect") CollectDTO collectDTO);
    @Insert("insert into qa_collection_info(qa_id, user_id, collect_time) values(#{collect.qaId}, #{collect.userId}, #{collect.collectTime})")
    int createQaCollect(@Param("collect") QaCollectDTO qaCollectDTO);

    @Select("select count(*) from article_collection_info where article_id=#{articleId} and user_id=#{userId}")
    int checkFavorite(@Param("articleId") int articleId, @Param("userId") int userId);
    @Select("select count(*) from competition_collection_info where competition_id=#{articleId} and user_id=#{userId}")
    int checkCompetitionFavorite(@Param("articleId") int articleId, @Param("userId") int userId);

    @Select("select count(*) from qa_collection_info where qa_id=#{qaId} and user_id=#{userId}")
    int checkQaFavorite(@Param("qaId") int qaId, @Param("userId") int userId);


    @Delete("delete from article_collection_info where article_id=#{articleId} and user_id=#{userId}")
    int deleteArticleCollect(@Param("articleId") int articleId, @Param("userId") int userId);
    @Delete("delete from competition_collection_info where competition_id=#{articleId} and user_id=#{userId}")
    int deleteCompetitionCollect(@Param("articleId") int articleId, @Param("userId") int userId);
    @Delete("delete from qa_collection_info where qa_id=#{qaId} and user_id=#{userId}")
    int deleteQaCollect(@Param("qaId") int qaId, @Param("userId") int userId);

    @Select("select count(*) from article_collection_info where article_id=#{articleId}")
    int getCollectCountByArticleId(@Param("articleId") int articleId);

    @Select("select count(*) from qa_collection_info where qa_id=#{qaId}")
    int getCollectCountByQaId(@Param("qaId") int qaId);


    @Select("select article_id from article_collection_info where user_id=#{userId}")
    List<Integer> getArticleIdsByUserId(@Param("userId") int userId);

    @Select("select competition_id from competition_collection_info where user_id=#{userId}")
    List<Integer> getCompetitionIdsByUserId(@Param("userId") int userId);


    @Select("select qa_id from qa_collection_info where user_id=#{userId}")
    List<Integer> getQaIdsByUserId(@Param("userId") int userId);

    @Select("SELECT ai.article_id as articleId, " +
            "ai.article_name as articleName, " +
            "ai.article_publish_time as articlePublishTime, " +
            "ai.article_info as articleInfo, " +
            "ai.visitor_number as visitorNumber, " +
            "ai.like_number as likeNumber, " +
            "ai.collect_number as collectNumber, " +
            "ai.sponsor_name as sponsorName, " +
            "ai.user_id as userId " +
            "FROM article_info ai " +
            "JOIN article_collection_info ac ON ai.article_id = ac.article_id " +
            "WHERE ac.user_id = #{userId}")
    List<ArticleDTO> getCollectArticleInfoByUserId(@Param("userId") int userId);
    @Select("SELECT ci.competition_id AS competitionId, ci.competition_name AS competitionName, ci.competition_start_time AS competitionStartTime, ci.competition_end_time AS competitionEndTime, ci.registration_start_time AS registrationStartTime, ci.registration_end_time AS registrationEndTime, ci.competition_publish_time AS competitionPublishTime, ci.website_url AS websiteUrl, ci.competition_info AS competitionInfo, ci.competition_type AS competitionType, ci.competition_level AS competitionLevel, ci.visitor_number AS visitorNumber, ci.collect_number AS collectNumber, ci.sponsor_name AS sponsorName, ci.user_id AS userId " +
            "FROM competition_info ci " +
            "JOIN competition_collection_info cc ON ci.competition_id = cc.competition_id " +
            "WHERE cc.user_id = #{userId}")
    List<CompetitionDTO> getCollectCompetitionInfoByUserId(@Param("userId") int userId);
    @Select("SELECT qi.qa_id AS qaId, qi.user_id AS userId, qi.user_name AS userName, qi.qa_title AS qaTitle, qi.qa_content AS qaContent, qi.qa_publish_time AS qaPublishTime, qi.visitor_number AS visitorNumber, qi.like_number AS likeNumber, qi.collect_number AS collectNumber " +
            "FROM qa_info qi " +
            "JOIN qa_collection_info cq ON qi.qa_id = cq.qa_id " +
            "WHERE cq.user_id = #{userId}")
    List<QaDTO> getCollectQaInfoByUserId(@Param("userId") int userId);

}