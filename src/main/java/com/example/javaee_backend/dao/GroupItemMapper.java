package com.example.javaee_backend.dao;

import com.example.javaee_backend.domain.GroupItem;
import com.example.javaee_backend.domain.GroupItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupItemMapper {
    long countByExample(GroupItemExample example);

    int deleteByExample(GroupItemExample example);

    int deleteByPrimaryKey(String id);

    int insert(GroupItem record);

    int insertSelective(GroupItem record);

    List<GroupItem> selectByExample(GroupItemExample example);

    GroupItem selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") GroupItem record, @Param("example") GroupItemExample example);

    int updateByExample(@Param("record") GroupItem record, @Param("example") GroupItemExample example);

    int updateByPrimaryKeySelective(GroupItem record);

    int updateByPrimaryKey(GroupItem record);
}