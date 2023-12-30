package com.example.javaee_backend.service.impl;

import com.example.javaee_backend.mapper.CompetitionMapper;
import com.example.javaee_backend.pojo.*;
import com.example.javaee_backend.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CompetitionServiceImpl implements CompetitionService {
    @Autowired
    private CompetitionMapper competitionMapper;

    @Override
    public String createCompetition(CompetitionDTO competition) {

        CompetitionDTO existCompetition = competitionMapper.getCompetition(competition.getCompetitionId());
        if (null == existCompetition) {
            competitionMapper.addCompetition(competition);
            return String.valueOf(competition.getCompetitionId());
        }
        throw new IllegalArgumentException("已存在相同ID的文章");
    }

    @Override
    public CompetitionDTO getCompetitionById(int competitionId) {
        return competitionMapper.getCompetition(competitionId);

    }
    @Override
    public String modifyCompetition(CompetitionDTO competition) {
        return null;
    }

    @Override
    public List<CompetitionDTO> getAllCompetitions() {
        return competitionMapper.getAllCompetitions();
    }

    @Override
    public List<CompetitionDTO> getCompetitionsByName(String name) {
        return competitionMapper.getCompetitionByName(name);
    }

    @Override
    public CompetitionDTO getMostPopularCompetition() {
        return null;
    }

    @Override
    public CompetitionDTO getLatestCompetition() {
        return null;
    }

    @Override
    public String deleteCompetitionById(int competitionId){
        CompetitionDTO existCompetition = competitionMapper.getCompetition(competitionId);
        System.out.println(existCompetition);
        if (null == existCompetition) {
            return "不存在该竞赛";
        }
        competitionMapper.deleteCompetition(competitionId);
        return "成功删除竞赛";
    }

}
