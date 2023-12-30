package com.example.DW_backend.mysql.controller;

import com.example.DW_backend.mysql.mapper.ActorMapper;
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
@RequestMapping("mysql/ByActor")
public class ByActor {

    @Autowired
    private ActorMapper actorMapper;

    @GetMapping("/{ActorName}")
    public List<MovieDTO> getMoviesByActor(@PathVariable String ActorName) {
        return actorMapper.findMoviesByActor(ActorName);
    }


}
