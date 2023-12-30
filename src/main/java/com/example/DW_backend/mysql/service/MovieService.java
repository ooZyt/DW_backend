package com.example.DW_backend.mysql.service;

import com.example.DW_backend.mysql.pojo.MovieDTO;
import java.util.List;


public interface MovieService {
    
    MovieDTO getMovie(int movieId);
    List<MovieDTO> getAllMovies();
}

