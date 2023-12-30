package com.example.javaee_backend.service.impl;

import com.example.javaee_backend.bean.CodeMsg;
import com.example.javaee_backend.dao.ChatMapper;
import com.example.javaee_backend.dao.GroupItemMapper;
import com.example.javaee_backend.dao.GroupMapper;
import com.example.javaee_backend.domain.*;
import com.example.javaee_backend.mapper.PersonMapper;
import com.example.javaee_backend.pojo.*;
import com.example.javaee_backend.enums.ChatShowEnum;
import com.example.javaee_backend.enums.ChatTypeEnum;
import com.example.javaee_backend.service.IChatService;
import com.example.javaee_backend.service.IGroupService;
import com.example.javaee_backend.service.LoginService;
import com.example.javaee_backend.util.CommonUtil;
import com.example.javaee_backend.util.CopyUtil;
import com.example.javaee_backend.util.UuidUtil;
import com.example.javaee_backend.util.ValidateEntityUtil;
import com.example.javaee_backend.ws.ChatEndpoint;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class GroupServiceImpl implements IGroupService {

    @Resource
    private GroupMapper groupMapper;

    @Resource
    private GroupItemMapper groupItemMapper;

    @Resource
    private LoginService loginService;

    @Resource
    private ChatMapper chatMapper;

    @Resource
    private PersonMapper userMapper;

    @Resource
    private IChatService chatService;

    @Resource
    private ChatEndpoint chatEndpoint;

    /**
     * 创建群聊操作
     * @param groupDTO
     * @return
     */
    @Override
    public ResultDTO<Boolean> createGroup(GroupDTO groupDTO) {
        if(groupDTO == null) {
            return ResultDTO.errorByMsg(CodeMsg.DATA_ERROR);
        }
        // 进行统一表单验证
        CodeMsg validate = ValidateEntityUtil.validate(groupDTO);
        if (!validate.getCode().equals(CodeMsg.SUCCESS.getCode())) {
            return ResultDTO.errorByMsg(validate);
        }
        // 判断是否选择邀请好友
        List<GroupItemDTO> groupItemDTOList = groupDTO.getGroupItemDTOList();
        if(groupItemDTOList == null || groupItemDTOList.size() == 0) {
            return ResultDTO.errorByMsg(CodeMsg.GROUP_ITEM_EMPTY);
        }
        // 创建群聊会话信息
        Chat chat = new Chat();
        chat.setId(UuidUtil.getShortUuid());
        chat.setLastTime(new Date());
        chat.setChatType(ChatTypeEnum.GROUP.getCode());
        if(chatMapper.insertSelective(chat) == 0) {
            return ResultDTO.errorByMsg(CodeMsg.CHAT_ADD_ERROR);
        }
        // 添加群聊数据
        groupDTO.setId(UuidUtil.getShortUuid());
        groupDTO.setCreateTime(new Date());
        Group group = CopyUtil.copy(groupDTO, Group.class);
        group.setChatId(chat.getId());
        if(groupMapper.insertSelective(group) == 0) {
            return ResultDTO.errorByMsg(CodeMsg.GROUP_ADD_ERROR);
        }
        // 添加群聊详情数据
        for(GroupItemDTO groupItemDTO : groupItemDTOList) {
            GroupItem groupItem = CopyUtil.copy(groupItemDTO, GroupItem.class);
            groupItem.setCreateTime(new Date());
            groupItem.setId(UuidUtil.getShortUuid());
            groupItem.setGroupId(group.getId());
            if(groupItemMapper.insertSelective(groupItem) == 0) {
                throw new RuntimeException("群聊详情数据添加出现异常！");
            }
        }
        return ResultDTO.successByMsg(true, "创建群聊成功！");
    }

    /**
     * 获取当前登录用户的群聊列表
     * @param groupDTO
     * @return
     */
    @Override
    public ResultDTO<List<GroupDTO>> listGroupByToken(GroupDTO groupDTO) {
        if(groupDTO == null || CommonUtil.isEmpty(groupDTO.getToken())) {
            return ResultDTO.errorByMsg(CodeMsg.DATA_ERROR);
        }
        // 获取当前登录用户信息
        ResultDTO<UserDTO> loginUser = loginService.getLoginUser(groupDTO.getToken());
        if(loginUser.getCode()!=(CodeMsg.SUCCESS.getCode())) {
            return ResultDTO.errorByMsg(CodeMsg.USER_SESSION_EXPIRED);
        }
        UserDTO loginUserDTO = loginUser.getData();
        if(loginUserDTO == null) {
            return ResultDTO.errorByMsg(CodeMsg.USER_SESSION_EXPIRED);
        }
        List<GroupDTO> groupDTOList = new ArrayList<>();
        GroupItemExample groupItemExample = new GroupItemExample();
        groupItemExample.createCriteria().andUserIdEqualTo(loginUserDTO.getId().toString());
        List<GroupItem> groupItemList = groupItemMapper.selectByExample(groupItemExample);
        for(GroupItem groupItem : groupItemList) {
            Group group = groupMapper.selectByPrimaryKey(groupItem.getGroupId());
            groupDTOList.add(CopyUtil.copy(group, GroupDTO.class));
        }
        // 获取每个群的详情信息
        for(GroupDTO g : groupDTOList) {
            GroupItemExample gie = new GroupItemExample();
            gie.createCriteria().andGroupIdEqualTo(g.getId());
            g.setGroupItemDTOList(CopyUtil.copyList(groupItemMapper.selectByExample(gie), GroupItemDTO.class));
        }
        return ResultDTO.success(groupDTOList);
    }

    /**
     * 发起群聊会话操作
     * @param groupDTO
     * @return
     */
    @Override
    public ResultDTO<ChatDTO> startGroupChat(GroupDTO groupDTO) {
        if(CommonUtil.isEmpty(groupDTO.getId()) || CommonUtil.isEmpty(groupDTO.getToken())) {
            return ResultDTO.errorByMsg(CodeMsg.DATA_ERROR);
        }
        // 获取当前登录用户信息
        ResultDTO<UserDTO> loginUser = loginService.getLoginUser(groupDTO.getToken());
        if(loginUser.getCode()!=CodeMsg.SUCCESS.getCode()) {
            return ResultDTO.errorByMsg(CodeMsg.USER_SESSION_EXPIRED);
        }
        UserDTO loginUserDTO = loginUser.getData();
        if(loginUserDTO == null) {
            return ResultDTO.errorByMsg(CodeMsg.USER_SESSION_EXPIRED);
        }
        Group group = groupMapper.selectByPrimaryKey(groupDTO.getId());
        GroupItemExample groupItemExample = new GroupItemExample();
        groupItemExample.createCriteria().andGroupIdEqualTo(groupDTO.getId()).andUserIdEqualTo(loginUserDTO.getId().toString());
        List<GroupItem> groupItemList = groupItemMapper.selectByExample(groupItemExample);
        if(groupItemList == null || groupItemList.size() == 0) {
            return ResultDTO.errorByMsg(CodeMsg.GROUP_NOT_JOIN);
        }
        GroupItem groupItem = groupItemList.get(0);
        groupItem.setShowChat(ChatShowEnum.YES.getCode());
        if(groupItemMapper.updateByPrimaryKeySelective(groupItem) == 0) {
            return ResultDTO.errorByMsg(CodeMsg.GROUP_START_CHAT_ERROR);
        }
        Chat chat = chatMapper.selectByPrimaryKey(group.getChatId());
        return ResultDTO.success(CopyUtil.copy(chat, ChatDTO.class));
    }


    /**
     * 根据id获取群聊信息
     * @param groupDTO
     * @return
     */
    @Override
    public ResultDTO<GroupDTO> getGroupById(GroupDTO groupDTO) {
        if(groupDTO == null || CommonUtil.isEmpty(groupDTO.getId())) {
            return ResultDTO.errorByMsg(CodeMsg.DATA_ERROR);
        }
        Group group = groupMapper.selectByPrimaryKey(groupDTO.getId());
        if(group == null) {
            return ResultDTO.success(new GroupDTO());
        }
        UserDTO userDTO = userMapper.getPersonById(Integer.parseInt(group.getUserId()));
        GroupDTO selectGroupDTO = CopyUtil.copy(group, GroupDTO.class);
        selectGroupDTO.setUserDTO(userDTO);
        // 获取群聊详情信息
        GroupItemExample groupItemExample = new GroupItemExample();
        groupItemExample.createCriteria().andGroupIdEqualTo(selectGroupDTO.getId());
        List<GroupItem> groupItemList = groupItemMapper.selectByExample(groupItemExample);
        List<GroupItemDTO> groupItemDTOList = CopyUtil.copyList(groupItemList, GroupItemDTO.class);
        for(GroupItemDTO groupItemDTO : groupItemDTOList) {
             groupItemDTO.setUserDTO(userMapper.getPersonById(Integer.parseInt(groupItemDTO.getUserId())));
        }
        selectGroupDTO.setGroupItemDTOList(groupItemDTOList);
        return ResultDTO.success(selectGroupDTO);
    }

    /**
     * 邀请用户加入群聊
     * @param groupDTO
     * @return
     */
    @Override
    public ResultDTO<GroupDTO> inviteGroupUser(GroupDTO groupDTO) {
        if(CommonUtil.isEmpty(groupDTO.getId()) || CommonUtil.isEmpty(groupDTO.getUserId())) {
            return ResultDTO.errorByMsg(CodeMsg.DATA_ERROR);
        }
        UserDTO userDTO = userMapper.getPersonById(Integer.parseInt(groupDTO.getUserId()));
        if(userDTO == null) {
            return ResultDTO.errorByMsg(CodeMsg.USER_NOT_EXIST);
        }
        // 判断当前用户是否在群里
        GroupItemExample groupItemExample = new GroupItemExample();
        groupItemExample.createCriteria().andGroupIdEqualTo(groupDTO.getId()).andUserIdEqualTo(groupDTO.getUserId());
        List<GroupItem> groupItemList = groupItemMapper.selectByExample(groupItemExample);
        if(groupItemList != null && groupItemList.size() != 0) {
            return ResultDTO.errorByMsg(CodeMsg.GROUP_ITEM_EXIST);
        }
        // 没有，那么就实现邀请
        GroupItem groupItem = new GroupItem();
        groupItem.setShowChat(ChatShowEnum.NO.getCode());
        groupItem.setUnreadCount(0);
        groupItem.setUserId(userDTO.getId().toString());
        groupItem.setGroupId(groupDTO.getId());
        groupItem.setCreateTime(new Date());
        groupItem.setId(UuidUtil.getShortUuid());
        if(groupItemMapper.insertSelective(groupItem) == 0){
            return ResultDTO.errorByMsg(CodeMsg.GROUP_INVITE_ERROR);
        }
        return getGroupById(groupDTO);
    }

    /**
     * 退出或解散群聊操作
     * @param groupDTO
     * @return
     */
    @Override
    public ResultDTO<GroupDTO> exitGroup(GroupDTO groupDTO) {
        if(CommonUtil.isEmpty(groupDTO.getId()) || CommonUtil.isEmpty(groupDTO.getUserId())) {
            return ResultDTO.errorByMsg(CodeMsg.DATA_ERROR);
        }
        Group group = groupMapper.selectByPrimaryKey(groupDTO.getId());
        if(group == null) {
            return ResultDTO.errorByMsg(CodeMsg.GROUP_NOT_EXIST);
        }
        GroupItemExample selectGroupItemExample = new GroupItemExample();
        selectGroupItemExample.createCriteria().andGroupIdEqualTo(group.getId());
        List<GroupItem> groupItemList = groupItemMapper.selectByExample(selectGroupItemExample);
        // 判断是否是群主，若是，则解散群聊
        if(groupDTO.getUserId().equals(group.getUserId())) {
            // 是群主 删除会话 群聊 群聊详情信息
            ChatDTO chatDTO = new ChatDTO();
            chatDTO.setId(group.getChatId());
            chatService.deleteChat(chatDTO);
            // 删除群聊详情
            GroupItemExample groupItemExample = new GroupItemExample();
            groupItemExample.createCriteria().andGroupIdEqualTo(group.getId());
            groupItemMapper.deleteByExample(groupItemExample);
            // 删除群聊
            groupMapper.deleteByPrimaryKey(group.getId());
        } else {
            // 不是群主
            GroupItemExample groupItemExample = new GroupItemExample();
            groupItemExample.createCriteria().andGroupIdEqualTo(groupDTO.getId()).andUserIdEqualTo(groupDTO.getUserId());
            if(groupItemMapper.deleteByExample(groupItemExample) == 0) {
                return ResultDTO.errorByMsg(CodeMsg.GROUP_EXIT_ERROR);
            }
        }
        // 发送系统消息通知
        for(GroupItem groupItem : groupItemList) {
            chatEndpoint.sendSystemMessage(groupItem.getUserId());
        }
        return getGroupById(groupDTO);
    }

    /**
     * 更新群聊信息操作
     * @param groupDTO
     * @return
     */
    @Override
    public ResultDTO<GroupDTO> save(GroupDTO groupDTO) {
        if(CommonUtil.isEmpty(groupDTO.getId())) {
            return ResultDTO.errorByMsg(CodeMsg.DATA_ERROR);
        }
        // 进行统一表单验证
        CodeMsg validate = ValidateEntityUtil.validate(groupDTO);
        if(!validate.getCode().equals(CodeMsg.SUCCESS.getCode())){
            return ResultDTO.errorByMsg(validate);
        }
        Group group = CopyUtil.copy(groupDTO, Group.class);
        // 修改数据库中数据
        if(groupMapper.updateByPrimaryKeySelective(group) == 0){
            return ResultDTO.errorByMsg(CodeMsg.GROUP_EDIT_ERROR);
        }
        GroupItemExample selectGroupItemExample = new GroupItemExample();
        selectGroupItemExample.createCriteria().andGroupIdEqualTo(group.getId());
        List<GroupItem> groupItemList = groupItemMapper.selectByExample(selectGroupItemExample);
        // 发送系统消息通知
        for(GroupItem groupItem : groupItemList) {
            chatEndpoint.sendSystemMessage(groupItem.getUserId());
        }
        return getGroupById(groupDTO);
    }
}
