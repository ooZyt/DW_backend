package com.example.DW_backend.mysql.mapper;

import com.example.DW_backend.mysql.pojo.MovieDTO;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MovieMapper {
    
    @Select("SELECT movie_id AS movieId, name AS name,time AS time,format AS format,grade AS grade,review_number AS reviewNumber "+
    "FROM movie WHERE movie_id=#{movieId}")
    MovieDTO selectMovieById(int movieId);

    @Select("SELECT movie_id AS movieId, name AS name,time AS time,format AS format,grade AS grade,review_number AS reviewNumber FROM movie WHERE name = #{name}")
    List<MovieDTO> findMovieByName(String name);

    @Select("SELECT asin FROM asin WHERE movie_id = #{movieId}")
    List<String> findAsinsByMovieId(int movieId);

    List<MovieDTO> selectAllMovies();
}
