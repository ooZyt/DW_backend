package com.example.DW_backend.mysql.controller;

import com.example.DW_backend.mysql.mapper.DirectorMapper;
import com.example.DW_backend.mysql.mapper.MovieMapper;
import com.example.DW_backend.mysql.pojo.MovieDTO;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("mysql/ByDirector")
public class ByDirector {

    @Autowired
    private DirectorMapper directorMapper;

    @GetMapping("/{directorName}")
    public List<MovieDTO> getMoviesByDirector(@PathVariable String directorName) {
        return directorMapper.findMoviesByDirector(directorName);
    }


}
