package com.example.javaee_backend.service;

import com.example.javaee_backend.pojo.LikeDTO;
import com.example.javaee_backend.pojo.QaLikeDTO;

import java.util.List;

public interface LikeService {
    String createLike(LikeDTO like);
    String createQaLike(QaLikeDTO like);

    int getLikeCountByArticleId(int articleId);
    int getLikeCountByQaId(int qaId);

    String deleteLike(int articleId, int userId);
    String deleteQaLike(int qaId, int userId);

    boolean checkLike(int articleId, int userId);
    boolean checkQaLike(int qaId, int userId);

    List<Integer> getArticleIdsByUserId(int userId);
    List<Integer> getQaIdsByUserId(int userId);
}