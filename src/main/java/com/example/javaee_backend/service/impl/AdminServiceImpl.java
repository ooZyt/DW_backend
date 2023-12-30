package com.example.javaee_backend.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.example.javaee_backend.mapper.LoginMapper;
import com.example.javaee_backend.pojo.ResultDTO;
import com.example.javaee_backend.pojo.UserDTO;
import com.example.javaee_backend.service.AdminService;
import com.example.javaee_backend.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private static final Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);

    private final LoginMapper loginMapper;

    private final PersonService personService;

    @Autowired
    public AdminServiceImpl(LoginMapper loginMapper,PersonService personService) {
        this.loginMapper = loginMapper;
        this.personService = personService;
    }

    public ResultDTO deletc_selectUser(List<UserDTO> users){
        //进行遍历
        for(UserDTO user:users){
            //首先获得用户名
            String username = user.getUserName();

            //通过name查id，看看有没有这个人
            List<UserDTO> test_users = loginMapper.findByUsername(username);

            if(test_users.size() == 0){
                log.warn("找不到用户名" + username);
                return new ResultDTO(400, "用户名"+username+"错误");
            }
            else{
                UserDTO test_user = test_users.get(0);//获取用户
                //根据id进行删除
                Integer user_id = test_user.getId();
                personService.deletePerson(user_id);
            }
        }
        return new ResultDTO(200,"删除用户成功");
    }

    public ResultDTO add_selectUser(List<UserDTO> users){
        //进行遍历
        for(UserDTO user:users){
            // 对 html 标签进行转义，防止 XSS 攻击
            String username = user.getUserName();
            username = HtmlUtils.htmlEscape(username);
            String password = user.getPassword();
            password = HtmlUtils.htmlEscape(password);
            String phone = user.getUserPhone();
            phone = HtmlUtils.htmlEscape(phone);
            String university = user.getUniversityName();
            university = HtmlUtils.htmlEscape(university);
            String sex = user.getUserGender();
            sex = HtmlUtils.htmlEscape(sex);

            List<UserDTO> test_users = loginMapper.findByUsername(username);
            if (test_users.size() != 0) {
                log.warn("用户名已存在");
                return new ResultDTO(400, "用户名已存在");
            } else {
                UserDTO new_user = new UserDTO();

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date d = new Date();
                String datetime = dateFormat.format(d);
                new_user.setRegisterTime(d);
                new_user.setUserName(username);
                new_user.setPassword(SecureUtil.md5(SecureUtil.md5(password) + SecureUtil.md5("I love java")));
                new_user.setRole("0");
                new_user.setUserState("0");
                new_user.setUserPhone(phone);
                new_user.setAvatarUrl("https://javaee2023.oss-cn-shanghai.aliyuncs.com/avatar/default-avatar.png");
                new_user.setUserIntro("无");
                new_user.setUniversityName(university);
                new_user.setUserGender(sex);
                loginMapper.insert_user(new_user);
                List<UserDTO> tmps = loginMapper.findByUsername(username);
            }
        }
        return new ResultDTO(200, "添加成功");
    }

    public ResultDTO changeState(UserDTO requestUser){
        // 对 html 标签进行转义，防止 XSS 攻击
        String username = requestUser.getUserName();
        username = HtmlUtils.htmlEscape(username);
        String state = requestUser.getUserState();
        state = HtmlUtils.htmlEscape(state);

        List<UserDTO> users = loginMapper.findByUsername(username);
        if (users.size() == 0) {
            log.warn("找不到用户名" + username);
            return new ResultDTO(400, "用户名"+username+"错误");
        } else {
            UserDTO user = users.get(0);//获取用户

            //进行更新
            loginMapper.update_state(state,username);
        }
        return new ResultDTO(200, "更新状态成功");
    }

}
