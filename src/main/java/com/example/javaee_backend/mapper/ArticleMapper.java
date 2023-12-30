package com.example.javaee_backend.mapper;

import java.util.List;

import com.example.javaee_backend.pojo.ArticleDTO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ArticleMapper {
    @Select("select article_id as articleId, article_name as articleName, article_publish_time as articlePublishTime, article_info as articleInfo, visitor_number as visitorNumber, like_number as likeNumber, collect_number as collectNumber, sponsor_name as sponsorName, user_id as userId " +
            "from article_info")
    List<ArticleDTO> getAllArticles();
    @Insert("insert into article_info(article_id, article_name, article_publish_time, article_info, visitor_number, like_number, collect_number, sponsor_name, user_id) " +
            "values(#{article.articleId}, #{article.articleName}, #{article.articlePublishTime}, #{article.articleInfo}, #{article.visitorNumber}, #{article.likeNumber}, #{article.collectNumber}, #{article.sponsorName}, #{article.userId})")
    int addArticle(@Param("article") ArticleDTO articleDTO);

    @Select("select article_id as articleId, article_name as articleName, article_publish_time as articlePublishTime, article_info as articleInfo, visitor_number as visitorNumber, like_number as likeNumber, collect_number as collectNumber, sponsor_name as sponsorName, user_id as userId " +
            "from article_info where article_name LIKE CONCAT ('%', #{name}, '%')")
    List<ArticleDTO> getArticleByName(@Param("name") String articleName);

    @Select("select article_id as articleId, article_name as articleName, article_publish_time as articlePublishTime, article_info as articleInfo, visitor_number as visitorNumber, like_number as likeNumber, collect_number as collectNumber, sponsor_name as sponsorName, user_id as userId " +
            "from article_info where user_id=#{userId}")
    List<ArticleDTO> getArticleByUserId(@Param("userId") int userId);

    @Select("select article_id as articleId, article_name as articleName, article_publish_time as articlePublishTime, article_info as articleInfo, visitor_number as visitorNumber, like_number as likeNumber, collect_number as collectNumber, sponsor_name as sponsorName, user_id as userId " +
            "FROM article_info WHERE article_id = #{articleId}")
    ArticleDTO getArticle(@Param("articleId") int articleId);

    @Update("UPDATE article_info SET article_name = #{article.articleName}, article_publish_time = #{article.articlePublishTime}, article_info = #{article.articleInfo}, visitor_number = #{article.visitorNumber}, like_number = #{article.likeNumber}, collect_number = #{article.collectNumber}, sponsor_name = #{article.sponsorName}, user_id = #{article.userId} WHERE article_id = #{article.articleId}")
    int updateArticle(@Param("article") ArticleDTO article);

    @Delete("DELETE FROM article_info WHERE article_id = #{articleId}")
    int deleteArticle(@Param("articleId") int articleId);
    @Update("update article_info set visitor_number = visitor_number + 1 where article_id = #{articleId}")
    int increaseVisitorNumber(@Param("articleId") int articleId);

    @Update("update article_info set like_number = like_number + 1 where article_id = #{articleId}")
    int increaseLikeNumber(@Param("articleId") int articleId);

    @Update("UPDATE article_info SET like_number = CASE WHEN like_number > 0 THEN like_number - 1 ELSE 0 END WHERE article_id = #{articleId}")
    int decreaseLikeNumber(@Param("articleId") int articleId);


}
