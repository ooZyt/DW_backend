
package com.example.DW_backend.mysql.service.impl;
import com.example.DW_backend.mysql.mapper.MovieMapper;
import com.example.DW_backend.mysql.pojo.MovieDTO;
import com.example.DW_backend.mysql.service.MovieService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieMapper movieMapper;

    @Override
    public MovieDTO getMovie(int movieId) {
        return movieMapper.selectMovieById(movieId);
    }

    @Override
    public List<MovieDTO> getAllMovies() {
        return movieMapper.selectAllMovies();
    }
    
}
