package com.example.DW_backend.mysql.mapper;

import com.example.DW_backend.mysql.pojo.ActorPairDTO;
import com.example.DW_backend.mysql.pojo.MovieDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ActorPairByPopularityMapper {

    @Select("SELECT a1.name AS actor1, a2.name AS actor2, SUM(m.review_number) AS totalReviews " +
            "FROM act act1 " +
            "JOIN act act2 ON act1.movie_id = act2.movie_id AND act1.actor_id != act2.actor_id " +
            "JOIN actor a1 ON act1.actor_id = a1.actor_id " +
            "JOIN actor a2 ON act2.actor_id = a2.actor_id " +
            "JOIN movie m ON act1.movie_id = m.movie_id " +
            "JOIN category c ON m.movie_id = c.movie_id " +
            "WHERE c.category_name = #{categoryName} " +
            "GROUP BY act1.actor_id, act2.actor_id " +
            "ORDER BY totalReviews DESC " +
            "LIMIT 10")
    List<ActorPairDTO> findMostReviewedActorPairInCategory(String categoryName);


}
