package com.example.DW_backend.mysql.service;

import com.example.DW_backend.mysql.pojo.ReleaseTimeDTO;

public interface ReleaseTimeService {
    ReleaseTimeDTO getReleaseTime(int movieId);

    int getMovieCountByYear(int year);
    int getMovieCountByYearAndMonth(int year, int month);
    int getMovieCountByQuarter(int year, int startMonth, int endMonth);
    int getMovieCountByDayOfWeek(int dayOfWeek);
    
}
