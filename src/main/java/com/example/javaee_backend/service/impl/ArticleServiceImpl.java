package com.example.javaee_backend.service.impl;

import com.example.javaee_backend.mapper.ArticleMapper;
import com.example.javaee_backend.pojo.ArticleDTO;
import com.example.javaee_backend.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
//import tongji.article.api.ArticleService;
//import tongji.article.api.pojo.ArticleDTO;
//import tongji.article.server.mapper.ArticleMapper;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public int createArticle(ArticleDTO article) {
        ArticleDTO existArticle = articleMapper.getArticle(article.getArticleId());
        if (null == existArticle) {
            articleMapper.addArticle(article);
            return article.getArticleId();
        }
        throw new IllegalArgumentException("已存在相同ID的文章");
    }

//    private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
    @Override
    public ArticleDTO getArticle(int articleId) {
//        Assert.hasLength(articleId, "缺少查询的文章ID");
//        因为articleId改成int了所以这句话不对就先注释掉了
        return articleMapper.getArticle(articleId);
    }

    @Override
    public String modifyArticle(ArticleDTO article) {
        ArticleDTO existArticle = articleMapper.getArticle(article.getArticleId());
        if (null == existArticle) {
            return "不存在该文章";
        }
        articleMapper.updateArticle(article);
        return String.valueOf(article.getArticleId());
    }

    @Override
    public String deleteArticle(int articleId) {
        ArticleDTO existArticle = articleMapper.getArticle(articleId);
        System.out.println(existArticle);
        if (null == existArticle) {
            return "不存在该文章";
        }
        articleMapper.deleteArticle(articleId);
        return "成功删除文章";
    }

    @Override
    public List<ArticleDTO> getAllArticles() {
        return articleMapper.getAllArticles();
    }

    @Override
    public List<ArticleDTO> getArticlesByName(String name) {
        return articleMapper.getArticleByName(name);
    }

    @Override
    public String increaseVisitorNumber(int articleId) {
        ArticleDTO existArticle = articleMapper.getArticle(articleId);
        System.out.println(existArticle);
        if (null == existArticle) {
            return "error:访问人数+1";
        }
        articleMapper.increaseVisitorNumber(articleId);
        return "succeed:访问人数+1";

    }

    public String increaseLikeNumber(int articleId) {
        ArticleDTO existArticle = articleMapper.getArticle(articleId);
        // System.out.println(existArticle);
        if (null == existArticle) {
            return "error:点赞人数+1";
        }
        articleMapper.increaseLikeNumber(articleId);
        return "succeed:点赞人数+1";

    }

    @Override
    public String decreaseLikeNumber(int articleId) {
        ArticleDTO existArticle = articleMapper.getArticle(articleId);
        // System.out.println(existArticle);
        if (null == existArticle) {
            return "error:点赞人数-1";
        }
        articleMapper.decreaseLikeNumber(articleId);
        return "succeed:点赞人数-1";
    }

    @Override
    public List<ArticleDTO> getArticlesByUserId(int userId) {
        return articleMapper.getArticleByUserId(userId);
    }
}
