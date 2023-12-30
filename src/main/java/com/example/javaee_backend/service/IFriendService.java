package com.example.javaee_backend.service;

import com.example.javaee_backend.pojo.FriendDTO;
import com.example.javaee_backend.pojo.ResultDTO;
import com.example.javaee_backend.pojo.UserDTO;

import java.util.List;

public interface IFriendService {

    // 发送好友申请
    ResultDTO<Boolean> applyFriend(FriendDTO friendDTO);

    // 获取好友申请列表
    ResultDTO<List<FriendDTO>> getApplyFriendList(UserDTO userDTO);

    // 同意好友申请
    ResultDTO<Boolean> agreeApplyFriend(FriendDTO friendDTO);

    // 拒绝好友请求
    ResultDTO<Boolean> refuseApplyFriend(FriendDTO friendDTO);

    // 根据用户id查询好友列表
    ResultDTO<List<FriendDTO>> selectFriendListByUserId(UserDTO userDTO);

    // 删除好友操作
    ResultDTO<Boolean> deleteFriend(FriendDTO friendDTO);
}
