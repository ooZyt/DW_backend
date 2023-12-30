package com.example.javaee_backend.service;

import com.example.javaee_backend.pojo.QaDTO;

import java.util.List;


public interface QaService {
    String createQa(QaDTO qa);

    QaDTO getQa(int qaId);

    String modifyQa(QaDTO qa);

    String deleteQa(int qaId);

    List<QaDTO> getAllQas();
    List<QaDTO> getQaByUserId(int userId);
    List<QaDTO> getQaBySearchKey(String SearchKey);

    String increaseVisitorNumber(int qaId);
    String increaseLikeNumber(int qaId);
    String increaseCollectNumber(int qaId);


    // 如果需要，可以根据特定参数获取文章的方法
    // List<QaDTO> getQasByParams(...);
}
