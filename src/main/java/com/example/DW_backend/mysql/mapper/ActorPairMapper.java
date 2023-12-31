package com.example.DW_backend.mysql.mapper;

import com.example.DW_backend.mysql.pojo.ActorPairDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface ActorPairMapper {

    @Select("SELECT DISTINCT a1.name AS actor1, a2.name AS actor2, COUNT(*) AS movieCount " +
            "FROM act_n act1 " +
            "JOIN act_n act2 ON act1.movie_id = act2.movie_id AND act1.actor_id != act2.actor_id " +
            "JOIN actor_n a1 ON act1.actor_id = a1.actor_id " +
            "JOIN actor_n a2 ON act2.actor_id = a2.actor_id " +
            "GROUP BY act1.actor_id, act2.actor_id " +
            "ORDER BY COUNT(*) DESC "
           )
    List<ActorPairDTO> findActorPairsWithMostCollaborations();

}
