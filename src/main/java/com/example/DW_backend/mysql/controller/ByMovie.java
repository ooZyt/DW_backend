package com.example.DW_backend.mysql.controller;

import com.example.DW_backend.mysql.pojo.MovieAsinDTO;
import com.example.DW_backend.mysql.pojo.MovieDTO;
import com.example.DW_backend.mysql.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.DW_backend.mysql.mapper.*;

import java.util.List;


@RestController
@RequestMapping("/mysql/movies")
public class ByMovie {
    @Autowired
    private MovieMapper movieMapper;

    @GetMapping("/getMovieById/{id}")
    public MovieDTO getMovie(@PathVariable("id") int movieId) {
        return movieMapper.selectMovieById(movieId);
        
    }

    @GetMapping
    public List<MovieDTO> getAllMovies() {
        return movieMapper.selectAllMovies();
    }
    @GetMapping("/SearchInfoByName")
    public MovieDTO searchMoviesByName(@RequestParam String name) {
        List<MovieDTO> movies=movieMapper.findMovieByName(name);
        // 检查列表是否为空
         if (!movies.isEmpty()) {
             // 返回列表的第一个元素
             return movies.get(0);
         }
 
         // 如果列表为空，则返回null或其他适当的响应
         return null;
    }
    @GetMapping("/SearchAsinsByName")
    public MovieAsinDTO searchAsinsByName(@RequestParam String name){
        List<MovieDTO> movies=movieMapper.findMovieByName(name);
         // 对于输入电影，获取其ASIN号
        List<String> asinNumbers = movieMapper.findAsinsByMovieId(movies.get(0).getMovieId());
        
        MovieAsinDTO movieWithAsins= new MovieAsinDTO(movies.get(0),asinNumbers);
        return movieWithAsins;
    }



    
}
