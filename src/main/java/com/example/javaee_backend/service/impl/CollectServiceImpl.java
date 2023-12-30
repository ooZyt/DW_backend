package com.example.javaee_backend.service.impl;

import com.example.javaee_backend.mapper.CollectMapper;
import com.example.javaee_backend.pojo.*;
import com.example.javaee_backend.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectMapper collectMapper;
    @Override
    public String createArticleCollect(CollectDTO collectDTO) {
        int result = collectMapper.createArticleCollect(collectDTO);
        if (result > 0) {
            return "收藏成功";
        }
        throw new IllegalArgumentException("创建收藏失败");
    }
    @Override
    public String createCompetitionCollect(CollectDTO collectDTO) {
        int result = collectMapper.createCompetitionCollect(collectDTO);
        if (result > 0) {
            return "收藏成功";
        }
        throw new IllegalArgumentException("创建收藏失败");
    }
    @Override
    public String createQaCollect(QaCollectDTO qaCollectDTO) {
        int result = collectMapper.createQaCollect(qaCollectDTO);
        if (result > 0) {
            return "收藏成功";
        }
        throw new IllegalArgumentException("创建收藏失败");
    }
    @Override
    public boolean checkFavorite(int articleId, int userId) {
        return collectMapper.checkFavorite(articleId, userId) > 0;
    }
    @Override
    public boolean checkCompetitionFavorite(int articleId, int userId) {
        return collectMapper.checkCompetitionFavorite(articleId, userId) > 0;
    }
    @Override
    public boolean checkQaFavorite(int qaId, int userId) {
        return collectMapper.checkQaFavorite(qaId, userId) > 0;
    }



    @Override
    public String deleteArticleCollect(int articleId, int userId) {
        int result = collectMapper.deleteArticleCollect(articleId, userId);
        if (result > 0) {
            return "取消收藏成功";
        }
        throw new IllegalArgumentException("取消收藏失败");
    }

    @Override
    public String deleteCompetitionCollect(int competitionId, int userId) {
        int result = collectMapper.deleteCompetitionCollect(competitionId, userId);
        if (result > 0) {
            return "取消收藏成功";
        }
        throw new IllegalArgumentException("取消收藏失败");
    }
    @Override
    public String deleteQaCollect(int qaId, int userId) {
        int result = collectMapper.deleteQaCollect(qaId, userId);
        if (result > 0) {
            return "取消收藏成功";
        }
        throw new IllegalArgumentException("取消收藏失败");
    }

    @Override
    public List<Integer> getArticleIdsByUserId(int userId) {
        return collectMapper.getArticleIdsByUserId(userId);
    }

    @Override
    public List<Integer> getCompetitionIdsByUserId(int userId) {
        return collectMapper.getCompetitionIdsByUserId(userId);
    }
    @Override
    public List<Integer> getQaIdsByUserId(int userId) {
        return collectMapper.getQaIdsByUserId(userId);
    }

    @Override
    public List<ArticleDTO> getCollectArticleInfoByUserId(int userId){
        return collectMapper.getCollectArticleInfoByUserId(userId);
    }
    @Override
    public List<CompetitionDTO> getCollectCompetitionInfoByUserId(int userId){
        return collectMapper.getCollectCompetitionInfoByUserId(userId);
    }
    @Override
    public List<QaDTO> getCollectQaInfoByUserId(int userId){
        return collectMapper.getCollectQaInfoByUserId(userId);
    }
}
