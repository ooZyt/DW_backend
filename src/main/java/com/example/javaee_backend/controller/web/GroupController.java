package com.example.javaee_backend.controller.web;

import com.example.javaee_backend.pojo.ChatDTO;
import com.example.javaee_backend.pojo.GroupDTO;
import com.example.javaee_backend.pojo.ResultDTO;
import com.example.javaee_backend.service.IGroupService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController("WebGroupController")
@RequestMapping("/web/group")
public class GroupController {

    @Resource
    private IGroupService groupService;

    /**
     * 创建群聊操作
     * @param groupDTO
     * @return
     */
    @PostMapping("/create")
    public ResultDTO<Boolean> createGroup(@RequestBody GroupDTO groupDTO) {
        return groupService.createGroup(groupDTO);
    }

    /**
     * 发起群聊会话操作
     * @param groupDTO
     * @return
     */
    @PostMapping("/start")
    public ResultDTO<ChatDTO> startGroupChat(@RequestBody GroupDTO groupDTO) {
        return groupService.startGroupChat(groupDTO);
    }

    /**
     * 获取当前登录用户的群聊列表
     * @param groupDTO
     * @return
     */
    @PostMapping("/list")
    public ResultDTO<List<GroupDTO>> listGroupByToken(@RequestBody GroupDTO groupDTO) {
        return groupService.listGroupByToken(groupDTO);
    }

    /**
     * 根据id获取群聊信息
     * @param groupDTO
     * @return
     */
    @PostMapping("/get")
    public ResultDTO<GroupDTO> getGroupById(@RequestBody GroupDTO groupDTO) {
        return groupService.getGroupById(groupDTO);
    }

    /**
     * 邀请用户加入群聊
     * @param groupDTO
     * @return
     */
    @PostMapping("/invite")
    public ResultDTO<GroupDTO> inviteGroupUser(@RequestBody GroupDTO groupDTO) {
        return groupService.inviteGroupUser(groupDTO);
    }

    /**
     * 退出或解散群聊操作
     * @param groupDTO
     * @return
     */
    @PostMapping("/exit")
    public ResultDTO<GroupDTO> exitGroup(@RequestBody GroupDTO groupDTO) {
        return groupService.exitGroup(groupDTO);
    }

    /**
     * 更新群聊信息操作
     * @param groupDTO
     * @return
     */
    @PostMapping("/save")
    public ResultDTO<GroupDTO> saveGroup(@RequestBody GroupDTO groupDTO) {
        return groupService.save(groupDTO);
    }
}
