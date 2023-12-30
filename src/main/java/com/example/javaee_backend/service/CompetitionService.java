package com.example.javaee_backend.service;

import com.example.javaee_backend.pojo.CompetitionDTO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CompetitionService {
    String createCompetition(CompetitionDTO competition);

    CompetitionDTO getCompetitionById(int competitionId);

    String modifyCompetition(CompetitionDTO competition);

    List<CompetitionDTO> getAllCompetitions();

    // 如果需要，可以根据特定参数获取比赛的方法
    List<CompetitionDTO> getCompetitionsByName(String name);

    CompetitionDTO getMostPopularCompetition();
    CompetitionDTO getLatestCompetition();
    String deleteCompetitionById(int competitionId);

}