package com.example.DW_backend.mysql.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {
    private int movieId;
    private String name;
    private int time;
    private String format;
    private double grade;
    private int reviewNumber;


}