package com.example.javaee_backend.service;

import com.example.javaee_backend.pojo.ChatDTO;
import com.example.javaee_backend.pojo.ResultDTO;
import com.example.javaee_backend.pojo.UserDTO;

import java.util.List;

public interface IChatService {

    // 发起新会话
    ResultDTO<String> startNewChat(ChatDTO chatDTO);

    // 获取当前登录用户的会话信息
    ResultDTO<List<ChatDTO>> getChatList(UserDTO userDTO);

    // 标记会话为已读
    ResultDTO<Boolean> markChatRead(ChatDTO chatDTO);

    // 删除会话信息
    ResultDTO<Boolean> deleteChat(ChatDTO chatDTO);

    // 隐藏会话信息
    ResultDTO<Boolean> hideChat(ChatDTO chatDTO);

}
