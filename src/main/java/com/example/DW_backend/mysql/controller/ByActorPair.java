package com.example.DW_backend.mysql.controller;

import com.example.DW_backend.mysql.mapper.ActorMapper;
import com.example.DW_backend.mysql.mapper.ActorPairMapper;
import com.example.DW_backend.mysql.pojo.ActorPairDTO;
import com.example.DW_backend.mysql.pojo.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("mysql/ByActorPair")
public class ByActorPair {

    @Autowired
    private ActorPairMapper actorPairMapper;

    @GetMapping("")
    List<ActorPairDTO> findActorPairsWithMostCollaborations(){
        return actorPairMapper.findActorPairsWithMostCollaborations();
    }


}
