package com.example.javaee_backend.dao;

import com.example.javaee_backend.domain.Chat;
import com.example.javaee_backend.domain.ChatExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

public interface ChatMapper {
    long countByExample(ChatExample example);

    int deleteByExample(ChatExample example);

    int deleteByPrimaryKey(String id);

    int insert(Chat record);

    int insertSelective(Chat record);

    List<Chat> selectByExample(ChatExample example);

    Chat selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Chat record, @Param("example") ChatExample example);

    int updateByExample(@Param("record") Chat record, @Param("example") ChatExample example);

    int updateByPrimaryKeySelective(Chat record);

    int updateByPrimaryKey(Chat record);
}
