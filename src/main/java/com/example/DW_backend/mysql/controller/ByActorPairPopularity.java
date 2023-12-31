package com.example.DW_backend.mysql.controller;

import com.example.DW_backend.mysql.mapper.ActorPairByPopularityMapper;
import com.example.DW_backend.mysql.mapper.ActorPairMapper;
import com.example.DW_backend.mysql.pojo.ActorPairDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("mysql/ByActorPairPopularity")
public class ByActorPairPopularity {

    @Autowired
    private ActorPairByPopularityMapper actorPairByPopularityMapper;

    @GetMapping("{category}")
    List<ActorPairDTO> findMostReviewedActorPairInCategory(@PathVariable String category){
        return actorPairByPopularityMapper.findMostReviewedActorPairInCategory(category);
    }


}
