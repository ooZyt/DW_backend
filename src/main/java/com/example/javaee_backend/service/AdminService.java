package com.example.javaee_backend.service;

import com.example.javaee_backend.pojo.ResultDTO;
import com.example.javaee_backend.pojo.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AdminService {
    ResultDTO deletc_selectUser(List<UserDTO> users);
    ResultDTO add_selectUser(List<UserDTO> users);
    ResultDTO changeState(UserDTO requestUser);
}
