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

     @GetMapping("/{id}")
    public ReleaseTimeDTO getReleaseTime(@PathVariable("id") int movieId) {
        return releaseTimeService.getReleaseTime(movieId);
    }


    
}
