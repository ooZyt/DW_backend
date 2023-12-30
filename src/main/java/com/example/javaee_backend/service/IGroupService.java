package com.example.javaee_backend.service;

import com.example.javaee_backend.pojo.ChatDTO;
import com.example.javaee_backend.pojo.GroupDTO;
import com.example.javaee_backend.pojo.ResultDTO;

import java.util.List;

public interface IGroupService {

    // 创建群聊操作
    ResultDTO<Boolean> createGroup(GroupDTO groupDTO);

    // 获取当前登录用户的群聊列表
    ResultDTO<List<GroupDTO>> listGroupByToken(GroupDTO groupDTO);

    // 发起群聊会话操作
    ResultDTO<ChatDTO> startGroupChat(GroupDTO groupDTO);

    // 根据id获取群聊信息
    ResultDTO<GroupDTO> getGroupById(GroupDTO groupDTO);

    // 邀请用户加入群聊
    ResultDTO<GroupDTO> inviteGroupUser(GroupDTO groupDTO);

    // 退出或解散群聊操作
    ResultDTO<GroupDTO> exitGroup(GroupDTO groupDTO);

    // 更新群聊信息
    ResultDTO<GroupDTO> save(GroupDTO groupDTO);

}
