package com.example.DW_backend.mysql.controller;

import com.example.DW_backend.mysql.mapper.ScoreMapper;
import com.example.DW_backend.mysql.pojo.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("mysql/ByScore")
public class ByScore {

    @Autowired
    private ScoreMapper scoreMapper;

    @GetMapping("/{minScore}/{maxScore}")
//    public List<MovieDTO> getMoviesByScore(@PathParam("minScore") double minScore, @PathParam("maxScore") double maxScore ) {
    public List<MovieDTO> getMoviesByScore(@PathVariable double minScore, @PathVariable double maxScore ) {
        return scoreMapper.findMoviesByScore(minScore, maxScore);
    }

}
