package com.example.javaee_backend.controller;//package tongji.article.client.controller;

import com.example.javaee_backend.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.javaee_backend.pojo.ArticleDTO;
//import tongji.article.api.ArticleService;
//import tongji.article.api.pojo.ArticleDTO;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:8000","http://localhost:8080"})
@RestController
@RequestMapping("/api")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/createArticle", method = RequestMethod.POST)
    public int createArticle(@RequestBody ArticleDTO article) {
        return articleService.createArticle(article);
    }

    @RequestMapping(path = "/getArticle/{id}", method = RequestMethod.GET)
    public ArticleDTO getArticle(@PathVariable("id") int articleId) {
        return articleService.getArticle(articleId);
    }
    @RequestMapping(path = "/getArticleByName", method = RequestMethod.GET)
    public List<ArticleDTO> getArticleByName(@RequestParam(value = "keyword") String keyword) {
        return articleService.getArticlesByName(keyword);// 如果没有找到匹配的文章，则返回null或者抛出异常
    }
    @RequestMapping(path = "/getAllArticles", method = RequestMethod.GET)
    public List<ArticleDTO> getAllArticles() {
        return articleService.getAllArticles();
    }

//    @RequestMapping(value = "/modifyArticle", method = RequestMethod.PATCH)
//    public String modifyArticle(@RequestBody ArticleDTO article) {
//        return articleService.modifyArticle(article);
//    }

    @RequestMapping(value = "/deleteArticle", method = RequestMethod.DELETE)
    public String deleteArticle(@RequestParam(value = "articleId") int articleId) {
        System.out.println("articleId="+articleId);
        return articleService.deleteArticle(articleId);
    }

    @RequestMapping(value = "/increaseArticleVisitorNumber/{articleId}", method = RequestMethod.PATCH)
    public String increaseArticleVisitorNumber(@PathVariable int articleId ) {
        System.out.println("articleId="+articleId);
        return articleService.increaseVisitorNumber(articleId);
    }

    @RequestMapping(value = "/increaseArticleLikeNumber/{articleId}", method = RequestMethod.PATCH)
    public String increaseArticleLikeNumber(@PathVariable int articleId) {
        System.out.println("articleId="+articleId);
        return articleService.increaseLikeNumber(articleId);
    }

    @RequestMapping(value = "/decreaseArticleLikeNumber/{articleId}", method = RequestMethod.PATCH)
    public String decreaseArticleLikeNumber(@PathVariable int articleId) {
        System.out.println("articleId="+articleId);
        return articleService.decreaseLikeNumber(articleId);
    }

    @RequestMapping(path = "/getArticlesByUserId/{id}", method = RequestMethod.GET)
    public List<ArticleDTO> getArticlesByUserId(@PathVariable("id") int UserId) {
        return articleService.getArticlesByUserId(UserId);
    }


}
