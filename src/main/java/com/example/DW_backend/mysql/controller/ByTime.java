package com.example.DW_backend.mysql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DW_backend.mysql.mapper.ReleaseTimeMapper;
import com.example.DW_backend.mysql.pojo.MovieAsinDTO;
import com.example.DW_backend.mysql.pojo.MovieDTO;
import com.example.DW_backend.mysql.pojo.ReleaseTimeDTO;
import com.example.DW_backend.mysql.service.ReleaseTimeService;

import javax.annotation.Resource;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/mysql/byTime")
public class ByTime {
    @Autowired
    private ReleaseTimeMapper releaseTimeMapper;

    @GetMapping("/{movieId}")
    public ReleaseTimeDTO getReleaseTime(@PathVariable("movieId") int movieId) {
        return releaseTimeMapper.selectReleaseTimeByMovieId(movieId);
    }

    @GetMapping("/year/{year}")
    public List<MovieDTO> getMovieCountByYear(@PathVariable("year") int year) {
        return releaseTimeMapper.countMoviesByYear(year);
    }

    @GetMapping("/year/{year}/month/{month}")
    public List<MovieDTO> getMovieCountByYearAndMonth(@PathVariable("year") int year, @PathVariable("month") int month) {
        return releaseTimeMapper.countMoviesByYearAndMonth(year, month);
    }

    @GetMapping("/quarter/{year}/{startMonth}/{endMonth}")
    public List<MovieDTO> getMovieCountByQuarter(@PathVariable("year") int year, @PathVariable("startMonth") int startMonth, @PathVariable("endMonth") int endMonth) {
        return releaseTimeMapper.countMoviesByQuarter(year, startMonth, endMonth);
    }

    @GetMapping("/dayOfWeek/{dayOfWeek}")
    public List<MovieDTO> getMovieCountByDayOfWeek(@PathVariable("dayOfWeek") int dayOfWeek) {
        return releaseTimeMapper.countMoviesByDayOfWeek(dayOfWeek);
    }


    
}
