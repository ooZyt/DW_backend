package com.example.DW_backend.mysql.pojo;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor

public class MovieAsinDTO {
    private MovieDTO movie;
    private List<String> Asins;

    public MovieAsinDTO(MovieDTO mo,List<String> As){
        movie=mo;
        Asins=As;

    }
    
}
