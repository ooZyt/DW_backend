package com.example.DW_backend.mysql.mapper;

import com.example.DW_backend.mysql.pojo.ActorDirectorPairDTO;
import com.example.DW_backend.mysql.pojo.ActorPairDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ActorDirectorPairMapper {

    @Select("SELECT a.name AS actorName, d.name AS directorName, COUNT(*) AS movieCount " +
            "FROM act_n ac " +
            "JOIN actor_n a ON ac.actor_id = a.actor_id " +
            "JOIN direct_n di ON ac.movie_id = di.movie_id " +
            "JOIN director_n d ON di.director_id = d.director_id " +
            "GROUP BY ac.actor_id, di.director_id " +
            "ORDER BY COUNT(*) DESC " 
            )
    List<ActorDirectorPairDTO> findActorDirectorPairsWithMostCollaborations();
}
