package com.example.javaee_backend.controller.web;

import com.example.javaee_backend.pojo.ChatDTO;
import com.example.javaee_backend.pojo.MessageDTO;
import com.example.javaee_backend.pojo.ResultDTO;
import com.example.javaee_backend.service.IMessageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController("WebMessageController")
@RequestMapping("/web/message")
public class MessageController {

    @Resource
    private IMessageService messageService;

    /**
     * 发送消息
     * @param messageDTO
     * @return
     */
    @PostMapping("/send")
    public ResultDTO<Boolean> sendMessage(@RequestBody MessageDTO messageDTO){
        return messageService.sendMessage(messageDTO);
    }

    /**
     * 获取聊天记录
     * @param chatDTO
     * @return
     */
    @PostMapping("/get")
    public ResultDTO<List<MessageDTO>> getMessageList(@RequestBody ChatDTO chatDTO){
        return messageService.getMessageList(chatDTO);
    }
}
