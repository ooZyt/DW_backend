package com.example.javaee_backend.service;

import com.example.javaee_backend.pojo.ResultDTO;
import com.example.javaee_backend.pojo.UserDTO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
@Service
public interface LoginService {
    ResultDTO login(UserDTO requestUser) ;
    ResultDTO register(UserDTO requestUser);
    ResultDTO phoneLogin(UserDTO requestUser);
    ResultDTO missPass(UserDTO requestUser);

    // 检查用户是否登录
    ResultDTO<UserDTO> checkLogin(UserDTO userDTO);

    // 获取当前登录用户
    ResultDTO<UserDTO> getLoginUser(String token);

    // 退出登录操作
    ResultDTO<Boolean> logout(UserDTO userDTO);
}
