package com.example.DW_backend.mysql.mapper;

import com.example.DW_backend.mysql.pojo.ActorDirectorPairDTO;
import com.example.DW_backend.mysql.pojo.ActorPairDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ActorDirectorPairMapper {

    @Select("SELECT a.name AS actorName, d.name AS directorName, COUNT(*) AS movieCount " +
            "FROM act ac " +
            "JOIN actor a ON ac.actor_id = a.actor_id " +
            "JOIN direct di ON ac.movie_id = di.movie_id " +
            "JOIN director d ON di.director_id = d.director_id " +
            "GROUP BY ac.actor_id, di.director_id " +
            "ORDER BY COUNT(*) DESC " +
            "LIMIT 10000")
    List<ActorDirectorPairDTO> findActorDirectorPairsWithMostCollaborations();
}
