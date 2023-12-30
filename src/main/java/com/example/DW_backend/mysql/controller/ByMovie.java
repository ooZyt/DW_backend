package com.example.DW_backend.mysql.controller;

import com.example.DW_backend.mysql.pojo.MovieDTO;
import com.example.DW_backend.mysql.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("mysql/movies")
public class ByMovie {
    @Autowired
    private MovieService movieService;

    @GetMapping("/{id}")
    public MovieDTO getMovie(@PathVariable("id") int movieId) {
        return movieService.getMovie(movieId);
    }

    @GetMapping
    public List<MovieDTO> getAllMovies() {
        return movieService.getAllMovies();
    }

    
}
