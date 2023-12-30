package com.example.javaee_backend.controller;


import com.example.javaee_backend.pojo.ArticleDTO;
import com.example.javaee_backend.pojo.CompetitionDTO;
import com.example.javaee_backend.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:8000","http://localhost:8080"})
@RestController
@RequestMapping("/api")
public class CompetitionController {
    @Autowired
    private CompetitionService competitionService;

    @RequestMapping(value = "/createCompetition", method = RequestMethod.POST)
    public String createCompetition(@RequestBody CompetitionDTO competitionDTO) {
        return competitionService.createCompetition(competitionDTO);
    }

    @RequestMapping(value="/getAllCompetition",method = RequestMethod.GET)
    public List<CompetitionDTO> getAllCompetition(){
        return competitionService.getAllCompetitions();
    }
    @RequestMapping(value="/getCompetitionById/{id}",method = RequestMethod.GET)
    public CompetitionDTO getCompetitionById(@PathVariable("id") int CompetitionId){
        return competitionService.getCompetitionById(CompetitionId);
    }
    @RequestMapping(value="/getCompetitionByName",method = RequestMethod.GET)
    public List<CompetitionDTO> getCompetitionByName(@RequestParam String keyword){
        return competitionService.getCompetitionsByName(keyword);
    }
    @RequestMapping(value="/getMostPopularCompetition",method = RequestMethod.GET)
    public CompetitionDTO getMostPopularCompetition(){
        return competitionService.getMostPopularCompetition();
    }
    @RequestMapping(value = "/getLatestCompetition", method = RequestMethod.GET)
    public CompetitionDTO getLatestCompetition() {
        return competitionService.getLatestCompetition();
    }

    @RequestMapping(value="/deleteCompetitionById/{id}",method = RequestMethod.DELETE)
    public String deleteCompetitionById(@PathVariable("id") int CompetitionId){
        return competitionService.deleteCompetitionById(CompetitionId);
    }


}
