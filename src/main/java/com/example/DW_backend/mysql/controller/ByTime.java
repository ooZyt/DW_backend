package com.example.DW_backend.mysql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    private ReleaseTimeService releaseTimeService;

    @GetMapping("/{movieId}")
    public ReleaseTimeDTO getReleaseTime(@PathVariable("movieId") int movieId) {
        return releaseTimeService.getReleaseTime(movieId);
    }

    @GetMapping("/year/{year}")
    public int getMovieCountByYear(@PathVariable("year") int year) {
        return releaseTimeService.getMovieCountByYear(year);
    }

    @GetMapping("/year/{year}/month/{month}")
    public int getMovieCountByYearAndMonth(@PathVariable("year") int year, @PathVariable("month") int month) {
        return releaseTimeService.getMovieCountByYearAndMonth(year, month);
    }

    @GetMapping("/quarter/{year}/{startMonth}/{endMonth}")
    public int getMovieCountByQuarter(@PathVariable("year") int year, @PathVariable("startMonth") int startMonth, @PathVariable("endMonth") int endMonth) {
        return releaseTimeService.getMovieCountByQuarter(year, startMonth, endMonth);
    }

    @GetMapping("/dayOfWeek/{dayOfWeek}")
    public int getMovieCountByDayOfWeek(@PathVariable("dayOfWeek") int dayOfWeek) {
        return releaseTimeService.getMovieCountByDayOfWeek(dayOfWeek);
    }


    
}
