package com.example.DW_backend.mysql.mapper;

import com.example.DW_backend.mysql.pojo.MovieDTO;
import com.example.DW_backend.mysql.pojo.ReleaseTimeDTO;

import java.util.List;

import org.apache.ibatis.annotations.*;

@Mapper
public interface ReleaseTimeMapper {

    @Select("SELECT movie_id AS movieId, year AS year,month AS month,day AS day ,FROM movie m JOIN release_time rt ON m.movie_id = rt.movie_id")
    ReleaseTimeDTO selectReleaseTimeByMovieId(int movieId);

    @Select("SELECT m.movie_id AS movieId, m.name AS name,m.time AS time,m.format AS format,m.grade AS grade,m.review_number AS reviewNumber FROM movie m JOIN release_time rt ON m.movie_id = rt.movie_id WHERE rt.year = #{year}")
    List<MovieDTO> countMoviesByYear(@Param("year") int year);

    @Select("SELECT m.movie_id AS movieId, m.name AS name,m.time AS time,m.format AS format,m.grade AS grade,m.review_number AS reviewNumber FROM movie m JOIN release_time rt ON m.movie_id = rt.movie_id WHERE rt.year = #{year} AND rt.month = #{month}")
    List<MovieDTO> countMoviesByYearAndMonth(@Param("year") int year, @Param("month") int month);

    @Select("SELECT m.movie_id AS movieId, m.name AS name,m.time AS time,m.format AS format,m.grade AS grade,m.review_number AS reviewNumber FROM movie m JOIN release_time rt ON m.movie_id = rt.movie_id WHERE rt.year = #{year} AND rt.month IN (#{startMonth}, #{endMonth})")
    List<MovieDTO>countMoviesByQuarter(@Param("year") int year, @Param("startMonth") int startMonth, @Param("endMonth") int endMonth);

    @Select("SELECT m.movie_id AS movieId, m.name AS name,m.time AS time,m.format AS format,m.grade AS grade,m.review_number AS reviewNumber FROM movie m JOIN release_time rt ON m.movie_id = rt.movie_id WHERE DAYOFWEEK(STR_TO_DATE(CONCAT(rt.year, '-', rt.month, '-', rt.day), '%Y-%c-%e')) = #{dayOfWeek}")
    List<MovieDTO> countMoviesByDayOfWeek(@Param("dayOfWeek") int dayOfWeek);
    
}
