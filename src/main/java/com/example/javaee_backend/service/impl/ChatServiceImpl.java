package com.example.javaee_backend.service.impl;

import com.example.javaee_backend.bean.CodeMsg;
import com.example.javaee_backend.dao.*;
import com.example.javaee_backend.domain.*;
import com.example.javaee_backend.mapper.PersonMapper;
import com.example.javaee_backend.pojo.*;
import com.example.javaee_backend.enums.ChatTypeEnum;
import com.example.javaee_backend.enums.ChatShowEnum;
import com.example.javaee_backend.enums.MessageTypeEnum;
import com.example.javaee_backend.service.IChatService;
import com.example.javaee_backend.service.IMessageService;
import com.example.javaee_backend.service.LoginService;
import com.example.javaee_backend.service.PersonService;
import com.example.javaee_backend.util.*;
import com.example.javaee_backend.ws.ChatEndpoint;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2022-03-22 10:46
 */
@Service
@Transactional
public class ChatServiceImpl implements IChatService {

    @Resource
    private ChatMapper chatMapper;

    @Resource
    private PersonMapper userMapper;

    @Resource
    private MessageMapper messageMapper;

    @Resource
    private FileMessageMapper fileMessageMapper;

    @Resource
    private GroupItemMapper groupItemMapper;

    @Resource
    private GroupMapper groupMapper;

    @Resource
    private IMessageService messageService;

    @Resource
    private LoginService loginService;

    @Resource
    private ChatEndpoint chatEndpoint;

    /**
     * 发起新会话
     * @param chatDTO
     * @return
     */
    @Override
    public ResultDTO<String> startNewChat(ChatDTO chatDTO) {
        if(chatDTO == null) {
            return ResultDTO.errorByMsg(CodeMsg.DATA_ERROR);
        }
        // 进行统一表单验证
        CodeMsg validate = ValidateEntityUtil.validate(chatDTO);
        if (!validate.getCode().equals(CodeMsg.SUCCESS.getCode())) {
            return ResultDTO.errorByMsg(validate);
        }
        ChatExample chatExample = new ChatExample();
        ChatExample.Criteria c1 = chatExample.createCriteria();
        c1.andSenderEqualTo(chatDTO.getSender()).andReceiverEqualTo(chatDTO.getReceiver()).andChatTypeEqualTo(ChatTypeEnum.SINGLE.getCode());
        ChatExample.Criteria c2 = chatExample.createCriteria();
        c2.andSenderEqualTo(chatDTO.getReceiver()).andReceiverEqualTo(chatDTO.getSender()).andChatTypeEqualTo(ChatTypeEnum.SINGLE.getCode());
        chatExample.or(c2);
        List<Chat> chatList = chatMapper.selectByExample(chatExample);
        if(chatList != null && chatList.size() != 0) {
            // 当前会话已存在 更新
            Chat chat = chatList.get(0);
            if(chat.getSender().equals(chatDTO.getSender())) {
                chat.setSenderShow(ChatShowEnum.YES.getCode());
            } else if(chat.getReceiver().equals(chatDTO.getSender())) {
                chat.setReceiverShow(ChatShowEnum.YES.getCode());
            }
            if(chatMapper.updateByPrimaryKeySelective(chat) == 0) {
                return ResultDTO.errorByMsg(CodeMsg.CHAT_ADD_ERROR);
            }
            return ResultDTO.success(chat.getId());
        } else {
            // 当前会话未存在 新增
            Chat chat = CopyUtil.copy(chatDTO, Chat.class);
            chat.setId(UuidUtil.getShortUuid());
            chat.setLastTime(new Date());
            chat.setSenderShow(ChatShowEnum.YES.getCode());
            if(chatMapper.insertSelective(chat) == 0) {
                return ResultDTO.errorByMsg(CodeMsg.CHAT_ADD_ERROR);
            }
            return ResultDTO.success(chat.getId());
        }
    }

    /**
     * 获取当前登录用户的会话信息
     * @param userDTO
     * @return
     */
    @Override
    public ResultDTO<List<ChatDTO>> getChatList(UserDTO userDTO) {
        if(userDTO == null || CommonUtil.isEmpty(userDTO.getToken())) {
            return ResultDTO.errorByMsg(CodeMsg.DATA_ERROR);
        }
        // 获取当前登录用户信息
        ResultDTO<UserDTO> loginUser = loginService.getLoginUser(userDTO.getToken());
        if(loginUser.getCode() != CodeMsg.SUCCESS.getCode()) {
            return ResultDTO.errorByMsg(CodeMsg.USER_SESSION_EXPIRED);
        }
        UserDTO loginUserDTO = loginUser.getData();
        if(loginUserDTO == null) {
            return ResultDTO.errorByMsg(CodeMsg.USER_SESSION_EXPIRED);
        }
        // 开始获取当前登录用户的会话信息
        // 先获取单聊会话信息  1.先获取当前登录用户发起的会话信息 2.再获取当前登录用户收到的会话信息
        ChatExample chatExample = new ChatExample();
        ChatExample.Criteria c1 = chatExample.createCriteria();
        c1.andSenderEqualTo(loginUserDTO.getId().toString()).andSenderShowEqualTo(ChatShowEnum.YES.getCode()).andChatTypeEqualTo(ChatTypeEnum.SINGLE.getCode());
        ChatExample.Criteria c2 = chatExample.createCriteria();
        c2.andReceiverEqualTo(loginUserDTO.getId().toString()).andReceiverShowEqualTo(ChatShowEnum.YES.getCode()).andChatTypeEqualTo(ChatTypeEnum.SINGLE.getCode());
        chatExample.or(c2);
        chatExample.setOrderByClause("last_time desc");
        List<Chat> chatList = chatMapper.selectByExample(chatExample);
        List<ChatDTO> chatDTOList = CopyUtil.copyList(chatList, ChatDTO.class);
        for(ChatDTO chatDTO : chatDTOList) {
            // 封装接收者信息
            UserDTO receiver = userMapper.getPersonById(Integer.parseInt(chatDTO.getReceiver()));
            chatDTO.setReceiverUserDTO(receiver);
            // 封装发送者信息
            UserDTO sender = userMapper.getPersonById(Integer.parseInt(chatDTO.getSender()));
            chatDTO.setSenderDTO(sender);
            // 封装会话时间
            chatDTO.setShowDate(CommonUtil.getShowDate(chatDTO.getLastTime()));
            //封装每个会话对应的聊天记录
            List<MessageDTO> messageList = messageService.getMessageList(chatDTO).getData();
            chatDTO.setMessageDTOList(messageList);
            if(messageList.size() > 0) {
                chatDTO.setLastMessage(EmojiConverterUtil.emojiRecovery(messageList.get(messageList.size() - 1).getContent()));
            } else {
                chatDTO.setLastMessage("");
            }
        }
        // 再获取群聊会话信息
        List<ChatDTO> groupChatDTOList = new ArrayList<>();
        GroupItemExample groupItemExample = new GroupItemExample();
        groupItemExample.createCriteria().andUserIdEqualTo(loginUserDTO.getId().toString()).andShowChatEqualTo(ChatShowEnum.YES.getCode());
        List<GroupItem> groupItemList = groupItemMapper.selectByExample(groupItemExample);
        for(GroupItem groupItem: groupItemList) {
            Group group = groupMapper.selectByPrimaryKey(groupItem.getGroupId());
            Chat chat = chatMapper.selectByPrimaryKey(group.getChatId());
            ChatDTO chatDTO = CopyUtil.copy(chat, ChatDTO.class);
            // 封装会话时间
            chatDTO.setShowDate(CommonUtil.getShowDate(chatDTO.getLastTime()));
            // 封装群聊信息
            GroupDTO groupDTO = CopyUtil.copy(group, GroupDTO.class);
            groupDTO.setUnreadCount(groupItem.getUnreadCount());
            // 封装群聊详情信息
            GroupItemExample groupItemExample2 = new GroupItemExample();
            groupItemExample2.createCriteria().andGroupIdEqualTo(group.getId());
            groupDTO.setGroupItemDTOList(CopyUtil.copyList(groupItemMapper.selectByExample(groupItemExample2), GroupItemDTO.class));
            // 群聊信息存进会话chat中
            chatDTO.setGroupDTO(groupDTO);
            //封装每个会话对应的聊天记录
            List<MessageDTO> messageList = messageService.getMessageList(chatDTO).getData();
            chatDTO.setMessageDTOList(messageList);
            if(messageList.size() > 0) {
                chatDTO.setLastMessage(EmojiConverterUtil.emojiRecovery(messageList.get(messageList.size() - 1).getContent()));
            } else {
                chatDTO.setLastMessage("");
            }
            groupChatDTOList.add(chatDTO);
        }
        // 单聊和群聊合并
        chatDTOList.addAll(groupChatDTOList);
        return ResultDTO.success(chatDTOList);
    }

    /**
     * 标记会话为已读
     * @param chatDTO
     * @return
     */
    @Override
    public ResultDTO<Boolean> markChatRead(ChatDTO chatDTO) {
        if(chatDTO == null || CommonUtil.isEmpty(chatDTO.getId())) {
            return ResultDTO.errorByMsg(CodeMsg.DATA_ERROR);
        }
        if(CommonUtil.isEmpty(chatDTO.getToken())) {
            return ResultDTO.errorByMsg(CodeMsg.USER_SESSION_EXPIRED);
        }
        // 获取当前登录用户信息
        ResultDTO<UserDTO> loginUser = loginService.getLoginUser(chatDTO.getToken());
        if(loginUser.getCode()!= CodeMsg.SUCCESS.getCode()) {
            return ResultDTO.errorByMsg(CodeMsg.USER_SESSION_EXPIRED);
        }
        UserDTO loginUserDTO = loginUser.getData();
        if(loginUserDTO == null) {
            return ResultDTO.errorByMsg(CodeMsg.USER_SESSION_EXPIRED);
        }
        Chat chat = chatMapper.selectByPrimaryKey(chatDTO.getId());
        if(chat == null) {
            return ResultDTO.errorByMsg(CodeMsg.CHAT_NOT_EXIST);
        }
        if(ChatTypeEnum.SINGLE.getCode().equals(chat.getChatType())) {
            // 单聊
            if(chat.getReceiver().equals(loginUserDTO.getId())) {
                chat.setUnreadReceiver(0);
            } else if (chat.getSender().equals(loginUserDTO.getId())) {
                chat.setUnreadSender(0);
            }
            if(chatMapper.updateByPrimaryKey(chat) == 0) {
                return ResultDTO.errorByMsg(CodeMsg.CHAT_UPDATE_ERROR);
            }
            // 发送系统消息，让接受者发起者刷新聊天页面信息
            chatEndpoint.sendSystemMessage(chat.getReceiver());
            chatEndpoint.sendSystemMessage(chat.getSender());
        } else if(ChatTypeEnum.GROUP.getCode().equals(chat.getChatType())) {
            // 群聊
            GroupExample groupExample = new GroupExample();
            groupExample.createCriteria().andChatIdEqualTo(chat.getId());
            List<Group> groupList = groupMapper.selectByExample(groupExample);
            if(groupList == null || groupList.size() == 0) {
                return ResultDTO.errorByMsg(CodeMsg.CHAT_NOT_EXIST);
            }
            Group group = groupList.get(0);
            GroupItemExample groupItemExample = new GroupItemExample();
            groupItemExample.createCriteria().andGroupIdEqualTo(group.getId()).andUserIdEqualTo(loginUserDTO.getId().toString());
            List<GroupItem> groupItemList = groupItemMapper.selectByExample(groupItemExample);
            if(groupItemList == null || groupItemList.size() == 0) {
                return ResultDTO.errorByMsg(CodeMsg.GROUP_NOT_JOIN);
            }
            GroupItem groupItem = groupItemList.get(0);
            groupItem.setUnreadCount(0);
            if(groupItemMapper.updateByPrimaryKeySelective(groupItem) == 0) {
                return ResultDTO.errorByMsg(CodeMsg.CHAT_UPDATE_ERROR);
            }
            // 发送系统消息，让当前查看消息用户刷新聊天页面信息
            chatEndpoint.sendSystemMessage(groupItem.getUserId());
        }
        return ResultDTO.success(true);
    }

    /**
     * 删除会话信息
     * @param chatDTO
     * @return
     */
    @Override
    public ResultDTO<Boolean> deleteChat(ChatDTO chatDTO) {
        if(CommonUtil.isEmpty(chatDTO.getId())) {
            return ResultDTO.errorByMsg(CodeMsg.DATA_ERROR);
        }
        MessageExample messageExample = new MessageExample();
        messageExample.createCriteria().andChatIdEqualTo(chatDTO.getId());
        List<Message> messageList = messageMapper.selectByExample(messageExample);
        // 先删除文件消息
        for(Message message : messageList) {
            if(MessageTypeEnum.FILE.getCode().equals(message.getMessageType())){
                FileMessageExample fileMessageExample = new FileMessageExample();
                fileMessageExample.createCriteria().andMessageIdEqualTo(message.getId());
                fileMessageMapper.deleteByExample(fileMessageExample);
            }
        }
        // 再删除消息
        messageMapper.deleteByExample(messageExample);
        // 最后删除会话
        if(chatMapper.deleteByPrimaryKey(chatDTO.getId()) == 0) {
            return ResultDTO.errorByMsg(CodeMsg.CHAT_DELETE_ERROR);
        }
        return ResultDTO.successByMsg(true, "删除会话信息成功！");
    }

    /**
     * 隐藏会话信息
     * @param chatDTO
     * @return
     */
    @Override
    public ResultDTO<Boolean> hideChat(ChatDTO chatDTO) {
        if(CommonUtil.isEmpty(chatDTO.getId()) || CommonUtil.isEmpty(chatDTO.getSender())) {
            return ResultDTO.errorByMsg(CodeMsg.DATA_ERROR);
        }
        Chat chat = chatMapper.selectByPrimaryKey(chatDTO.getId());
        if(ChatTypeEnum.SINGLE.getCode().equals(chat.getChatType())) {
            // 单聊
            if(chat.getReceiver().equals(chatDTO.getSender())) {
                chat.setReceiverShow(ChatShowEnum.NO.getCode());
            } else if (chat.getSender().equals(chatDTO.getSender())) {
                chat.setSenderShow(ChatShowEnum.NO.getCode());
            }
            if(chatMapper.updateByPrimaryKeySelective(chat) == 0) {
                return ResultDTO.errorByMsg(CodeMsg.CHAT_DELETE_ERROR);
            }
        } else if(ChatTypeEnum.GROUP.getCode().equals(chat.getChatType())) {
            // 群聊
            GroupExample groupExample = new GroupExample();
            groupExample.createCriteria().andChatIdEqualTo(chatDTO.getId());
            List<Group> groupList = groupMapper.selectByExample(groupExample);
            if(groupList == null || groupList.size() == 0) {
                return ResultDTO.errorByMsg(CodeMsg.CHAT_NOT_EXIST);
            }
            Group group = groupList.get(0);
            GroupItemExample groupItemExample = new GroupItemExample();
            groupItemExample.createCriteria().andGroupIdEqualTo(group.getId()).andUserIdEqualTo(chatDTO.getSender());
            List<GroupItem> groupItemList = groupItemMapper.selectByExample(groupItemExample);
            if(groupItemList == null || groupItemList.size() == 0) {
                return ResultDTO.errorByMsg(CodeMsg.GROUP_NOT_JOIN);
            }
            GroupItem groupItem = groupItemList.get(0);
            groupItem.setShowChat(ChatShowEnum.NO.getCode());
            if(groupItemMapper.updateByPrimaryKeySelective(groupItem) == 0) {
                return ResultDTO.errorByMsg(CodeMsg.CHAT_DELETE_ERROR);
            }
        }
        return ResultDTO.successByMsg(true, "删除聊天成功！");
    }
}
