package com.example.javaee_backend.dao;

import com.example.javaee_backend.domain.Friend;
import com.example.javaee_backend.domain.FriendExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FriendMapper {
    int countByExample(FriendExample example);

    int deleteByExample(FriendExample example);

    int deleteByPrimaryKey(String id);

    int insert(Friend record);

    int insertSelective(Friend record);

    List<Friend> selectByExample(FriendExample example);

    Friend selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Friend record, @Param("example") FriendExample example);

    int updateByExample(@Param("record") Friend record, @Param("example") FriendExample example);

    int updateByPrimaryKeySelective(Friend record);

    int updateByPrimaryKey(Friend record);
}
