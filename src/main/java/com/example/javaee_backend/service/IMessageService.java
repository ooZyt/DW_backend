package com.example.javaee_backend.service;

import com.example.javaee_backend.pojo.ChatDTO;
import com.example.javaee_backend.pojo.MessageDTO;
import com.example.javaee_backend.pojo.ResultDTO;

import java.util.List;

public interface IMessageService {

    // 发送消息
    ResultDTO<Boolean> sendMessage(MessageDTO messageDTO);

    // 获取聊天记录
    ResultDTO<List<MessageDTO>> getMessageList(ChatDTO chatDTO);

}
