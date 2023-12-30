package com.example.javaee_backend.service.impl;

import com.example.javaee_backend.bean.CodeMsg;
import com.example.javaee_backend.dao.ChatMapper;
import com.example.javaee_backend.dao.FriendMapper;
import com.example.javaee_backend.domain.*;
import com.example.javaee_backend.mapper.PersonMapper;
import com.example.javaee_backend.pojo.ChatDTO;
import com.example.javaee_backend.pojo.FriendDTO;
import com.example.javaee_backend.pojo.ResultDTO;
import com.example.javaee_backend.pojo.UserDTO;
import com.example.javaee_backend.enums.ChatTypeEnum;
import com.example.javaee_backend.enums.FriendStateEnum;
import com.example.javaee_backend.service.IChatService;
import com.example.javaee_backend.service.IFriendService;
import com.example.javaee_backend.service.LoginService;
import com.example.javaee_backend.util.CommonUtil;
import com.example.javaee_backend.util.CopyUtil;
import com.example.javaee_backend.util.UuidUtil;
import com.example.javaee_backend.util.ValidateEntityUtil;
import com.example.javaee_backend.ws.ChatEndpoint;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class FriendServiceImpl implements IFriendService {

    @Resource
    private FriendMapper friendMapper;

    @Resource
    private PersonMapper userMapper;

    @Resource
    private ChatMapper chatMapper;

    @Resource
    private IChatService chatService;

    @Resource
    private LoginService loginService;

    @Resource
    private ChatEndpoint chatEndpoint;

    /**
     * 发送好友申请
     * @param friendDTO
     * @return
     */
    @Override
    public ResultDTO<Boolean> applyFriend(FriendDTO friendDTO) {
        if(friendDTO == null) {
            return ResultDTO.errorByMsg(CodeMsg.DATA_ERROR);
        }
        // 判断是否登录
        if(CommonUtil.isEmpty(friendDTO.getApplyUser())) {
            return ResultDTO.errorByMsg(CodeMsg.USER_SESSION_EXPIRED);
        }
        // 进行统一表单验证
        CodeMsg validate = ValidateEntityUtil.validate(friendDTO);
        if (!validate.getCode().equals(CodeMsg.SUCCESS.getCode())) {
            return ResultDTO.errorByMsg(validate);
        }
        // 判断这个用户编号是否存在
        UserDTO user = userMapper.getPersonById(Integer.parseInt(friendDTO.getReceiveUser()));
        if(user == null) {
            return ResultDTO.errorByMsg(CodeMsg.USER_NOT_EXIST);
        }
        // 判断是否自己加自己
        if(friendDTO.getReceiveUser().equals(friendDTO.getApplyUser())) {
            return ResultDTO.errorByMsg(CodeMsg.NO_PERMIT_ADD);
        }
        // 判断用户是否已经发出过申请或者是否已经是好友
        FriendExample friendExample = new FriendExample();
        FriendExample.Criteria c1 = friendExample.createCriteria();
        c1.andStateEqualTo(FriendStateEnum.APPLY.getCode())
                .andApplyUserEqualTo(friendDTO.getApplyUser())
                .andReceiveUserEqualTo(friendDTO.getReceiveUser());
        FriendExample.Criteria c2 = friendExample.createCriteria();
        c2.andStateEqualTo(FriendStateEnum.NORMAL.getCode())
                .andApplyUserEqualTo(friendDTO.getApplyUser())
                .andReceiveUserEqualTo(friendDTO.getReceiveUser());
        friendExample.or(c2);
        List<Friend> friendList = friendMapper.selectByExample(friendExample);
        if(friendList != null && friendList.size() > 0) {
            return ResultDTO.errorByMsg(CodeMsg.FRIEND_APPLY_EXIST);
        }
        // 准备添加数据库
        Friend friend = CopyUtil.copy(friendDTO, Friend.class);
        friend.setId(UuidUtil.getShortUuid());
        friend.setCreateTime(new Date());
        friend.setState(FriendStateEnum.APPLY.getCode());
        if(friendMapper.insertSelective(friend) == 0) {
            return ResultDTO.errorByMsg(CodeMsg.FRIEND_APPLY_ERROR);
        }
        chatEndpoint.sendSystemMessage(friendDTO.getReceiveUser());
        return ResultDTO.successByMsg(true, "发送好友申请成功！");
    }

    /**
     * 获取好友申请列表
     * @param userDTO
     * @return
     */
    @Override
    public ResultDTO<List<FriendDTO>> getApplyFriendList(UserDTO userDTO) {
        if(userDTO == null || CommonUtil.isEmpty(userDTO.getToken())) {
            return ResultDTO.errorByMsg(CodeMsg.DATA_ERROR);
        }
        // 获取当前登录用户信息
        ResultDTO<UserDTO> loginUser = loginService.getLoginUser(userDTO.getToken());
        if(loginUser.getCode() != 0) {
            return ResultDTO.errorByMsg(CodeMsg.USER_SESSION_EXPIRED);
        }
        UserDTO loginUserDTO = loginUser.getData();
        if(loginUserDTO == null) {
            return ResultDTO.errorByMsg(CodeMsg.USER_SESSION_EXPIRED);
        }
        // 查询当前登录用户收到的好友申请
        FriendExample friendExample = new FriendExample();
        friendExample.createCriteria().andStateEqualTo(FriendStateEnum.APPLY.getCode()).andReceiveUserEqualTo(loginUserDTO.getId().toString());
        List<Friend> friendList = friendMapper.selectByExample(friendExample);
        List<FriendDTO> friendDTOList = CopyUtil.copyList(friendList, FriendDTO.class);
        for(FriendDTO friendDTO : friendDTOList) {
            UserDTO user = userMapper.getPersonById(Integer.parseInt(friendDTO.getApplyUser()));
            friendDTO.setApplyUserDTO(user);
        }
        return ResultDTO.success(friendDTOList);
    }

    /**
     * 同意好友申请
     * @param friendDTO
     * @return
     */
    @Override
    public ResultDTO<Boolean> agreeApplyFriend(FriendDTO friendDTO) {
        if(friendDTO == null || CommonUtil.isEmpty(friendDTO.getId())) {
            return ResultDTO.errorByMsg(CodeMsg.DATA_ERROR);
        }
        // 更改记录状态
        Friend friend = friendMapper.selectByPrimaryKey(friendDTO.getId());
        friend.setState(FriendStateEnum.NORMAL.getCode());
        if(friendMapper.updateByPrimaryKeySelective(friend) == 0) {
            return ResultDTO.errorByMsg(CodeMsg.AGREE_FRIEND_ERROR);
        }
        // 建立双向好友关系
        Friend newFriend = new Friend();
        newFriend.setApplyUser(friend.getReceiveUser());
        newFriend.setReceiveUser(friend.getApplyUser());
        newFriend.setId(UuidUtil.getShortUuid());
        newFriend.setCreateTime(new Date());
        newFriend.setState(FriendStateEnum.NORMAL.getCode());
        newFriend.setRemark("同意好友请求！");
        if(friendMapper.insertSelective(newFriend) == 0) {
            return ResultDTO.errorByMsg(CodeMsg.AGREE_FRIEND_ERROR);
        }
        return ResultDTO.successByMsg(true, "操作成功！");
    }

    /**
     * 拒绝好友请求
     * @param friendDTO
     * @return
     */
    @Override
    public ResultDTO<Boolean> refuseApplyFriend(FriendDTO friendDTO) {
        if(friendDTO == null || CommonUtil.isEmpty(friendDTO.getId())) {
            return ResultDTO.errorByMsg(CodeMsg.DATA_ERROR);
        }
        // 删除好友申请记录
        if(friendMapper.deleteByPrimaryKey(friendDTO.getId()) == 0) {
            return ResultDTO.errorByMsg(CodeMsg.REFUSE_FRIEND_ERROR);
        }
        return ResultDTO.successByMsg(true, "操作成功！");
    }

    /**
     * 获取当前登录用户的好友列表
     * @param userDTO
     * @return
     */
    @Override
    public ResultDTO<List<FriendDTO>> selectFriendListByUserId(UserDTO userDTO) {
        if(userDTO == null || CommonUtil.isEmpty(userDTO.getToken())) {
            return ResultDTO.errorByMsg(CodeMsg.DATA_ERROR);
        }
        // 通过用户token获取当前登录用户信息
        ResultDTO<UserDTO> loginUser = loginService.getLoginUser(userDTO.getToken());
        if(loginUser.getCode() != 0) {
            return ResultDTO.errorByMsg(CodeMsg.USER_SESSION_EXPIRED);
        }
        UserDTO loginUserDTO = loginUser.getData();
        if(loginUserDTO == null) {
            return ResultDTO.errorByMsg(CodeMsg.USER_SESSION_EXPIRED);
        }
        FriendExample friendExample = new FriendExample();
        // select * from friend where apply_user = ? and state = ?
        friendExample.createCriteria().andApplyUserEqualTo(loginUserDTO.getId().toString()).andStateEqualTo(FriendStateEnum.NORMAL.getCode());
        List<Friend> friendList = friendMapper.selectByExample(friendExample);
        List<FriendDTO> friendDTOList = CopyUtil.copyList(friendList, FriendDTO.class);
        for(FriendDTO friendDTO : friendDTOList) {
            UserDTO user = userMapper.getPersonById(Integer.parseInt(friendDTO.getReceiveUser()));
            friendDTO.setReceiveUserDTO(user);
        }
        return ResultDTO.success(friendDTOList);
    }

    /**
     * 删除好友操作
     * @param friendDTO
     * @return
     */
    @Override
    public ResultDTO<Boolean> deleteFriend(FriendDTO friendDTO) {
        if(CommonUtil.isEmpty(friendDTO.getReceiveUser()) || CommonUtil.isEmpty(friendDTO.getApplyUser())) {
            return ResultDTO.errorByMsg(CodeMsg.DATA_ERROR);
        }
        // 删除好友数据
        FriendExample friendExample = new FriendExample();
        FriendExample.Criteria c1 = friendExample.createCriteria();
        c1.andApplyUserEqualTo(friendDTO.getReceiveUser()).andReceiveUserEqualTo(friendDTO.getApplyUser());
        FriendExample.Criteria c2 = friendExample.createCriteria();
        c2.andApplyUserEqualTo(friendDTO.getApplyUser()).andReceiveUserEqualTo(friendDTO.getReceiveUser());
        friendExample.or(c2);
        friendMapper.deleteByExample(friendExample);
        // 删除相关聊天数据
        ChatExample chatExample = new ChatExample();
        ChatExample.Criteria c3 = chatExample.createCriteria();
        c3.andSenderEqualTo(friendDTO.getReceiveUser()).andReceiverEqualTo(friendDTO.getApplyUser()).andChatTypeEqualTo(ChatTypeEnum.SINGLE.getCode());
        ChatExample.Criteria c4 = chatExample.createCriteria();
        c4.andSenderEqualTo(friendDTO.getApplyUser()).andReceiverEqualTo(friendDTO.getReceiveUser()).andChatTypeEqualTo(ChatTypeEnum.SINGLE.getCode());
        chatExample.or(c4);
        List<Chat> chatList = chatMapper.selectByExample(chatExample);
        for(Chat chat : chatList) {
            ChatDTO chatDTO = new ChatDTO();
            chatDTO.setId(chat.getId());
            chatService.deleteChat(chatDTO);
        }
        // 发送系统消息
        chatEndpoint.sendSystemMessage(friendDTO.getApplyUser());
        chatEndpoint.sendSystemMessage(friendDTO.getReceiveUser());
        return ResultDTO.successByMsg(true, "删除好友成功！");
    }
}
