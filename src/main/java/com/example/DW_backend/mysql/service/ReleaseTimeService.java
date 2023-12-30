package com.example.DW_backend.mysql.service;

import com.example.DW_backend.mysql.pojo.ReleaseTimeDTO;

public interface ReleaseTimeService {
    ReleaseTimeDTO getReleaseTime(int movieId);
    
}
