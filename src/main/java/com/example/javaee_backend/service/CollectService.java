package com.example.javaee_backend.service;

import com.example.javaee_backend.pojo.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CollectService {
    String createArticleCollect(CollectDTO collectDTO);
    String createCompetitionCollect(CollectDTO collectDTO);
    String createQaCollect(QaCollectDTO qaCollectDTO);

    boolean checkFavorite(int articleId, int userId);
    boolean checkCompetitionFavorite(int articleId, int userId);
    boolean checkQaFavorite(int qaId, int userId);


    String deleteArticleCollect(int articleId, int userId);
    String deleteCompetitionCollect(int competitionId, int userId);
    String deleteQaCollect(int qaId, int userId);


    List<Integer> getArticleIdsByUserId(int userId);
    List<Integer> getCompetitionIdsByUserId(int userId);
    List<Integer> getQaIdsByUserId(int userId);

    List<ArticleDTO> getCollectArticleInfoByUserId(int userId);
    List<CompetitionDTO> getCollectCompetitionInfoByUserId(int userId);
    List<QaDTO> getCollectQaInfoByUserId(int userId);

}
