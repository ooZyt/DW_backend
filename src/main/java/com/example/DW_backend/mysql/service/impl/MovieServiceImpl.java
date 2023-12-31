
package com.example.DW_backend.mysql.service.impl;

import com.example.DW_backend.mysql.mapper.MovieMapper;

import com.example.DW_backend.mysql.pojo.MovieDTO;
import com.example.DW_backend.mysql.service.MovieService;

import java.util.ArrayList;
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

    @Override
    public MovieDTO getMovieByName(String name) {

        List<MovieDTO> movies=movieMapper.findMovieByName(name);
       // 检查列表是否为空
        if (!movies.isEmpty()) {
            // 返回列表的第一个元素
            return movies.get(0);
        }

        // 如果列表为空，则返回null或其他适当的响应
        return null;
    }

    @Override
    public List<String> getAsinsById(int id) {

        // 对于输入电影，获取其ASIN号
        List<String> asinNumbers = movieMapper.findAsinsByMovieId(id);

        return asinNumbers;
    }

}
