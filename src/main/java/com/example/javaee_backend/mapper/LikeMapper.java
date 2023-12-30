package com.example.javaee_backend.mapper;

import com.example.javaee_backend.pojo.LikeDTO;
import com.example.javaee_backend.pojo.QaLikeDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LikeMapper {
    @Insert("insert into article_like_info(article_id, user_id, like_time) values(#{like.articleId}, #{like.userId}, #{like.likeTime})")
    int createLike(@Param("like") LikeDTO likeDTO);

    @Insert("insert into qa_like_info(qa_id, user_id, like_time) values(#{like.qaId}, #{like.userId}, #{like.likeTime})")
    int createQaLike(@Param("like") QaLikeDTO QaLikeDTO);


    @Select("select count(*) from article_like_info where article_id=#{articleId}")
    int getLikeCountByArticleId(@Param("articleId") int articleId);

    @Select("select count(*) from qa_like_info where qa_id=#{qaId}")
    int getLikeCountByQaId(@Param("qaId") int qaId);


    @Delete("delete from article_like_info where article_id=#{articleId} and user_id=#{userId}")
    int deleteLike(@Param("articleId") int articleId, @Param("userId") int userId);

    @Delete("delete from qa_like_info where qa_id=#{qaId} and user_id=#{userId}")
    int deleteQaLike(@Param("qaId") int qaId, @Param("userId") int userId);

    @Select("select article_id from article_like_info where user_id=#{userId}")
    List<Integer> getArticleIdsByUserId(@Param("userId") int userId);

    @Select("select qa_id from qa_like_info where user_id=#{userId}")
    List<Integer> getQaIdsByUserId(@Param("userId") int userId);

    @Select("select count(*) from article_like_info where article_id=#{articleId} and user_id=#{userId}")
    int checkLike(@Param("articleId") int articleId, @Param("userId") int userId);

    @Select("select count(*) from qa_like_info where qa_id=#{qaId} and user_id=#{userId}")
    int checkQaLike(@Param("qaId") int qaId, @Param("userId") int userId);


}