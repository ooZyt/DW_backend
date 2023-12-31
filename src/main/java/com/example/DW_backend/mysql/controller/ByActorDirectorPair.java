package com.example.DW_backend.mysql.controller;

import com.example.DW_backend.mysql.mapper.ActorDirectorPairMapper;
import com.example.DW_backend.mysql.pojo.ActorDirectorPairDTO;
import com.example.DW_backend.mysql.pojo.ActorPairDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins = {"http://localhost:5173"})
@RestController
@RequestMapping("mysql/ByActorDirectorPair")
public class ByActorDirectorPair {

    @Autowired
    private ActorDirectorPairMapper actorDirectorPairMapper;

    @RequestMapping(value="",method=RequestMethod.GET)
    List<ActorDirectorPairDTO> findActorDirectorPairsWithMostCollaborations(){
        return actorDirectorPairMapper.findActorDirectorPairsWithMostCollaborations();
    }


}
