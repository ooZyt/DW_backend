package com.example.DW_backend.mysql.mapper;

import com.example.DW_backend.mysql.pojo.MovieDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ActorMapper {

    @Select("SELECT m.movie_id AS movieId, m.name AS name, m.time AS time, m.format AS format, m.grade AS grade, m.review_number AS reviewNumber " +
            "FROM movie m " +
            "JOIN act a ON m.movie_id = a.movie_id " +
            "JOIN actor ac ON a.actor_id = ac.actor_id " +
            "WHERE ac.name = #{actorName} " +
            "ORDER BY m.grade DESC")
    List<MovieDTO> findMoviesByActor(String actorName);

}
