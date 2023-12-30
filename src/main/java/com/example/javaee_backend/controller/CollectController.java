package com.example.javaee_backend.controller;

import com.example.javaee_backend.pojo.*;
import com.example.javaee_backend.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:8000","http://localhost:8080"})
@RestController
@RequestMapping("/api")
public class CollectController {
    @Autowired
    private CollectService collectService;

    @RequestMapping(value = "/createArticleCollect", method = RequestMethod.POST)
    public String createArticleCollect(@RequestBody CollectDTO collectDTO) {
        return collectService.createArticleCollect(collectDTO);
    }

    @RequestMapping(value = "/createCompetitionCollect", method = RequestMethod.POST)
    public String createCompetitionCollect(@RequestBody CollectDTO collectDTO) {
        return collectService.createCompetitionCollect(collectDTO);
    }

    @RequestMapping(value = "/createQaCollect", method = RequestMethod.POST)
    public String createQaCollect(@RequestBody QaCollectDTO qaCollectDTO) {
        return collectService.createQaCollect(qaCollectDTO);
    }

    @RequestMapping(value = "/checkArticleFavorite/{articleId}/{userId}", method = RequestMethod.GET)
    public boolean checkFavorite(@PathVariable("articleId") int articleId, @PathVariable("userId") int userId) {
        return collectService.checkFavorite(articleId, userId);
    }
    @RequestMapping(value = "/checkCompetitionFavorite/{articleId}/{userId}", method = RequestMethod.GET)
    public boolean checkCompetitionFavorite(@PathVariable("articleId") int articleId, @PathVariable("userId") int userId) {
        return collectService.checkCompetitionFavorite(articleId, userId);
    }
    @RequestMapping(value = "/checkQaFavorite/{qaId}/{userId}", method = RequestMethod.GET)
    public boolean checkQaFavorite(@PathVariable("qaId") int qaId, @PathVariable("userId") int userId) {
        return collectService.checkQaFavorite(qaId, userId);
    }


    @RequestMapping(value = "/deleteArticleCollect/{articleId}/{userId}", method = RequestMethod.DELETE)
    public String deleteArticleCollect(@PathVariable("articleId") int articleId, @PathVariable("userId") int userId) {
        return collectService.deleteArticleCollect(articleId, userId);
    }
    @RequestMapping(value = "/deleteCompetitionCollect/{competitionId}/{userId}", method = RequestMethod.DELETE)
    public String deleteCompetitionCollect(@PathVariable("competitionId") int competitionId, @PathVariable("userId") int userId) {
        return collectService.deleteCompetitionCollect(competitionId, userId);
    }
    @RequestMapping(value = "/deleteQaCollect/{qaId}/{userId}", method = RequestMethod.DELETE)
    public String deleteQaCollect(@PathVariable("qaId") int qaId, @PathVariable("userId") int userId) {
        return collectService.deleteQaCollect(qaId, userId);
    }

    @RequestMapping(value="/getCollectArticleIdsByUserId/{userId}",method = RequestMethod.GET)
    public List<Integer> getArticleIdsByUserId(@PathVariable("userId") int userId){
        return collectService.getArticleIdsByUserId(userId);
    }

    @RequestMapping(value="/getCompetitionIdsByUserId/{userId}",method = RequestMethod.GET)
    public List<Integer> getCompetitionIdsByUserId(@PathVariable("userId") int userId){
        return collectService.getCompetitionIdsByUserId(userId);
    }
    @RequestMapping(value="/getQaIdsByUserId/{userId}",method = RequestMethod.GET)
    public List<Integer> getQaIdsByUserId(@PathVariable("userId") int userId){
        return collectService.getQaIdsByUserId(userId);
    }

    @RequestMapping(value="/getCollectArticleInfoByUserId/{userId}",method = RequestMethod.GET)
    public List<ArticleDTO> getCollectArticleInfoByUserId(@PathVariable("userId") int userId){
        return collectService.getCollectArticleInfoByUserId(userId);
    }
    @RequestMapping(value="/getCollectCompetitionInfoByUserId/{userId}",method = RequestMethod.GET)
    public List<CompetitionDTO> getCollectCompetitionInfoByUserId(@PathVariable("userId") int userId){
        return collectService.getCollectCompetitionInfoByUserId(userId);
    }
    @RequestMapping(value="/getCollectQaInfoByUserId/{userId}",method = RequestMethod.GET)
    public List<QaDTO> getCollectQaInfoByUserId(@PathVariable("userId") int userId){
        return collectService.getCollectQaInfoByUserId(userId);
    }

}