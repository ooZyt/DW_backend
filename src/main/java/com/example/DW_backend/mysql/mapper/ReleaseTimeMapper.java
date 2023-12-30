package com.example.DW_backend.mysql.mapper;

import com.example.DW_backend.mysql.pojo.ReleaseTimeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ReleaseTimeMapper {
    
    ReleaseTimeDTO selectReleaseTimeByMovieId(int movieId);

    @Select("SELECT COUNT(*) FROM movie m JOIN release_time rt ON m.movie_id = rt.movie_id WHERE rt.year = #{year}")
    int countMoviesByYear(@Param("year") int year);

    @Select("SELECT COUNT(*) FROM movie m JOIN release_time rt ON m.movie_id = rt.movie_id WHERE rt.year = #{year} AND rt.month = #{month}")
    int countMoviesByYearAndMonth(@Param("year") int year, @Param("month") int month);

    @Select("SELECT COUNT(*) FROM movie m JOIN release_time rt ON m.movie_id = rt.movie_id WHERE rt.year = #{year} AND rt.month IN (#{startMonth}, #{endMonth})")
    int countMoviesByQuarter(@Param("year") int year, @Param("startMonth") int startMonth, @Param("endMonth") int endMonth);

    @Select("SELECT COUNT(*) FROM movie m JOIN release_time rt ON m.movie_id = rt.movie_id WHERE DAYOFWEEK(rt.day) = 3")
    int countMoviesReleasedOnTuesday();
    
}
