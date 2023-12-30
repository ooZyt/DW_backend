package com.example.javaee_backend.service.impl;
import cn.hutool.core.net.multipart.UploadFile;
import com.example.javaee_backend.mapper.PersonMapper;
import com.example.javaee_backend.pojo.ResultDTO;
import com.example.javaee_backend.pojo.UploadFileDTO;
import com.example.javaee_backend.pojo.UserDTO;
import com.example.javaee_backend.service.PersonService;
import com.example.javaee_backend.service.UploadFileTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Date;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private UploadFileTool uploadFileTool;

    @Override
    public UserDTO getPersonById(int userId) {
        // 实现根据 userId 获取个人信息的逻辑
        return personMapper.getPersonById(userId);

    }

    @Override
    public String updateUserInformation(Integer id, String userName, Date userBirth,
                                      String userGender, String universityName, String userIntro) {
        int result =personMapper.updateUserInfo(id, userName, userBirth, userGender, universityName, userIntro);
        return result > 0 ? "用户信息更新成功" : "用户信息更新失败";
    }


    @Override
    public String deletePerson(int userId) {
        // 实现删除个人信息的逻辑
        UserDTO existPerson = personMapper.getPersonById(userId);
        if(null == existPerson){
            return "不存在该用户";
        }
        int result = personMapper.deletePerson(userId);
        return result > 0 ? "用户注销成功" : "用户注销失败";
    }

    @Override
    public List<UserDTO> getAllPersons() {
        // 实现获取所有个人信息的逻辑
        return personMapper.getAllPersons();
    }

    @Override
    public ResultDTO updateAvatar(MultipartFile file, Integer userid) {
        UserDTO user = personMapper.getPersonById(userid);
        // 调用上传文件服务保存头像
        UploadFileDTO uploadFile = uploadFileTool.upload(user.getUserName(), file.getOriginalFilename(), file);
        // 将头像路径保存到个人信息中
        user.setAvatarUrl(uploadFile.getPath());
        personMapper.updateUserAvatar(user.getId(),user.getAvatarUrl());
        return new ResultDTO<String> (200,"上传头像成功",uploadFile.getPath());
    }

    @Override
    public List<UserDTO> getPersonsByName(String name) {
        return personMapper.getUserByName(name);
    }

    @Override
    public List<UserDTO> getPersonsByPhone(String phone) {
        return personMapper.getUserByPhone(phone);
    }

    @Override
    public List<UserDTO> getPersonsByState(String state) {
        return personMapper.getUserByStatus(state);
    }

    public List<UserDTO> getUsersByRegistrationDateRange(Date startTime, Date endTime) {
        return personMapper.getUsersByRegistrationDateRange(startTime, endTime);
    }
}
