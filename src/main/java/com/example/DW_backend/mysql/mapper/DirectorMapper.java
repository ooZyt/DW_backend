package com.example.DW_backend.mysql.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import com.example.DW_backend.mysql.pojo.MovieDTO;

@Mapper
public interface DirectorMapper {

    @Select("SELECT m.movie_id AS movieId, m.name AS name, m.time AS time, m.format AS format, m.grade AS grade, m.review_number AS reviewNumber " +
            "FROM movie m " +
            "JOIN direct d ON m.movie_id = d.movie_id " +
            "JOIN director dr ON d.director_id = dr.director_id " +
            "WHERE dr.name = #{directorName} " +
            "ORDER BY m.grade DESC")
    List<MovieDTO> findMoviesByDirector(String directorName);


}
