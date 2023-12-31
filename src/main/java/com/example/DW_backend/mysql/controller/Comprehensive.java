package com.example.DW_backend.mysql.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DW_backend.mysql.mapper.MovieMapper;
import com.example.DW_backend.mysql.pojo.MovieDTO;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:5173","http://localhost:8080"})
@RestController
@RequestMapping("/mysql")
public class Comprehensive {
    @Autowired
    private MovieMapper movieMapper;

    @RequestMapping(value="/ComprehensiveSearch",method=RequestMethod.GET)
    public List<MovieDTO> searchMovies(@RequestParam(required = false) String name,
                                       @RequestParam(required = false) String category,
                                       @RequestParam(required = false) String director,
                                       @RequestParam(required = false) String actor,
                                       @RequestParam(required = false) Double minGrade,
                                       @RequestParam(required = false) Double maxGrade,
                                       @RequestParam(required = false) Integer year,
                                       @RequestParam(required = false) Integer startMonth,
                                       @RequestParam(required = false) Integer endMonth,
                                       @RequestParam(required = false) Integer dayOfWeek) {
                                   

        return movieMapper.findMoviesByMultipleCriteria(name, category, director, actor, 
                                                         minGrade, maxGrade, year, 
                                                         startMonth, endMonth, dayOfWeek);
    }
    
}
