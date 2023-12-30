package com.example.DW_backend.mysql.service;

import com.example.DW_backend.mysql.pojo.MovieDTO;
//import com.example.DW_backend.mysql.pojo.MovieAsinDTO;
import java.util.List;


public interface MovieService {
    
    MovieDTO getMovie(int movieId);
    MovieDTO getMovieByName(String name);
    List<MovieDTO> getAllMovies();
    List<String> getAsinsById(int id);
}

