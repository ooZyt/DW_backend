package com.example.javaee_backend.controller.web;

import com.example.javaee_backend.pojo.FriendDTO;
import com.example.javaee_backend.pojo.ResultDTO;
import com.example.javaee_backend.pojo.UserDTO;
import com.example.javaee_backend.service.IFriendService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController("WebFriendController")
@RequestMapping("/web/friend")
public class FriendController {

    @Resource
    private IFriendService friendService;

    /**
     * 发送好友申请
     * @param friendDTO
     * @return
     */
    @PostMapping("/apply")
    public ResultDTO<Boolean> applyFriend(@RequestBody FriendDTO friendDTO){
        return friendService.applyFriend(friendDTO);
    }

    /**
     * 获取好友申请列表
     * @param userDTO
     * @return
     */
    @PostMapping("/get_apply")
    public ResultDTO<List<FriendDTO>> getApplyFriendList(@RequestBody UserDTO userDTO){
        return friendService.getApplyFriendList(userDTO);
    }

    /**
     * 同意好友申请
     * @param friendDTO
     * @return
     */
    @PostMapping("/agree_apply")
    public ResultDTO<Boolean> agreeApplyFriend(@RequestBody FriendDTO friendDTO){
        return friendService.agreeApplyFriend(friendDTO);
    }

    /**
     * 拒绝好友请求
     * @param friendDTO
     * @return
     */
    @PostMapping("/refuse_apply")
    public ResultDTO<Boolean> refuseApplyFriend(@RequestBody FriendDTO friendDTO){
        return friendService.refuseApplyFriend(friendDTO);
    }

    /**
     * 获取当前登录用户的好友列表
     * @param userDTO
     * @return
     */
    @PostMapping("/get_friend")
    public ResultDTO<List<FriendDTO>> selectFriendListByUserId(@RequestBody UserDTO userDTO){
        return friendService.selectFriendListByUserId(userDTO);
    }

    /**
     * 删除好友操作
     * @param friendDTO
     * @return
     */
    @PostMapping("/delete")
    public ResultDTO<Boolean> deleteFriend(@RequestBody FriendDTO friendDTO){
        return friendService.deleteFriend(friendDTO);
    }
}
