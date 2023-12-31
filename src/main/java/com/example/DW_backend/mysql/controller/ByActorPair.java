package com.example.DW_backend.mysql.controller;


import com.example.DW_backend.mysql.mapper.ActorPairMapper;
import com.example.DW_backend.mysql.pojo.ActorPairDTO;
import com.example.DW_backend.mysql.pojo.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:5173"})
@RestController
@RequestMapping("mysql/ByActorPair")
public class ByActorPair {

    @Autowired
    private ActorPairMapper actorPairMapper;

    @RequestMapping(value="",method=RequestMethod.GET)
    List<ActorPairDTO> findActorPairsWithMostCollaborations(){
        List<ActorPairDTO> AApair=actorPairMapper.findActorPairsWithMostCollaborations();
       
        return AApair;
    }


}
