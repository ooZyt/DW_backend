package com.example.javaee_backend.controller.web;

import com.example.javaee_backend.pojo.ChatDTO;
import com.example.javaee_backend.pojo.ResultDTO;
import com.example.javaee_backend.pojo.UserDTO;
import com.example.javaee_backend.service.IChatService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController("WebChatController")
@RequestMapping("/web/chat")
public class ChatController {

    @Resource
    private IChatService chatService;

    /**
     * 发起新会话
     * @param chatDTO
     * @return
     */
    @PostMapping("/start_chat")
    public ResultDTO<String> startNewChat(@RequestBody ChatDTO chatDTO){
        return chatService.startNewChat(chatDTO);
    }

    /**
     * 获取当前登录用户的会话信息
     * @param userDTO
     * @return
     */
    @PostMapping("/get")
    public ResultDTO<List<ChatDTO>> getChatList(@RequestBody UserDTO userDTO){
        return chatService.getChatList(userDTO);
    }

    /**
     * 标记会话为已读
     * @param chatDTO
     * @return
     */
    @PostMapping("/read")
    public ResultDTO<Boolean> markChatRead(@RequestBody ChatDTO chatDTO){
        return chatService.markChatRead(chatDTO);
    }

    /**
     * 删除聊天
     * @param chatDTO
     * @return
     */
    @PostMapping("/delete")
    public ResultDTO<Boolean> deleteChat(@RequestBody ChatDTO chatDTO){
        return chatService.hideChat(chatDTO);
    }
}
