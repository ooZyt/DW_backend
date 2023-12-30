package com.example.DW_backend.mysql.mapper;

import com.example.DW_backend.mysql.pojo.MovieDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Select("SELECT m.movie_id AS movieId, m.name AS name, m.time AS time, m.format AS format, m.grade AS grade, m.review_number AS reviewNumber " +
            "FROM movie m" +
            "JOIN category c ON m.movie_id = c.movie_id" +
            "GROUP BY c.category_name" +
            "ORDER BY m.grade DESC")
    List<MovieDTO> findMoviesByCategory(String categoryName);

}
