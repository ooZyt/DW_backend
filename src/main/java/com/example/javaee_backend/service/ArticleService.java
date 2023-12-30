package com.example.javaee_backend.service;

import com.example.javaee_backend.pojo.ArticleDTO;

import java.util.List;


public interface ArticleService {
    int createArticle(ArticleDTO article);

    ArticleDTO getArticle(int articleId);

    String modifyArticle(ArticleDTO article);

    String deleteArticle(int articleId);

    List<ArticleDTO> getAllArticles();

    List<ArticleDTO> getArticlesByName(String name);

    String increaseVisitorNumber(int articleId);
    String increaseLikeNumber(int articleId);

    String decreaseLikeNumber(int articleId);

    List<ArticleDTO> getArticlesByUserId(int userId);

    // 如果需要，可以根据特定参数获取文章的方法
    // List<ArticleDTO> getArticlesByParams(...);
}
