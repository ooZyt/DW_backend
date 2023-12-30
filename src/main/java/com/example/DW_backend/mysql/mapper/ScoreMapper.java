package com.example.DW_backend.mysql.mapper;

import com.example.DW_backend.mysql.pojo.MovieDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ScoreMapper {

    @Select("SELECT m.movie_id AS movieId, m.name AS name, m.time AS time, m.format AS format, m.grade AS grade, m.review_number AS reviewNumber " +
            "FROM movie m WHERE grade BETWEEN #{minScore} AND #{maxScore}")
    List<MovieDTO> findMoviesByScore(double minScore, double maxScore);


}
