package com.example.DW_backend.mysql.service.impl;

import com.example.DW_backend.mysql.mapper.ReleaseTimeMapper;
import com.example.DW_backend.mysql.pojo.ReleaseTimeDTO;
import com.example.DW_backend.mysql.service.ReleaseTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReleaseTimeImpl implements ReleaseTimeService{
     @Autowired
    private ReleaseTimeMapper releaseTimeMapper;

    @Override
    public ReleaseTimeDTO getReleaseTime(int movieId) {
        return releaseTimeMapper.selectReleaseTimeByMovieId(movieId);
    }
    @Override
    public int getMovieCountByYear(int year) {
        return releaseTimeMapper.countMoviesByYear(year);
    }

    @Override
    public int getMovieCountByYearAndMonth(int year, int month) {
        return releaseTimeMapper.countMoviesByYearAndMonth(year, month);
    }

    @Override
    public int getMovieCountByQuarter(int year, int startMonth, int endMonth) {
        return releaseTimeMapper.countMoviesByQuarter(year, startMonth, endMonth);
    }

    @Override
    public int getMovieCountByDayOfWeek(int dayOfWeek) {
        return releaseTimeMapper.countMoviesByDayOfWeek(dayOfWeek);
    }

    
}
