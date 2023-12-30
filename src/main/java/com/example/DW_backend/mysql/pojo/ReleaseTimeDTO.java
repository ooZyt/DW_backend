package com.example.DW_backend.mysql.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReleaseTimeDTO {
    private int movieId;
    private Integer year; // 使用包装类，以支持null值
    private Integer month;
    private Integer day;
    
}
