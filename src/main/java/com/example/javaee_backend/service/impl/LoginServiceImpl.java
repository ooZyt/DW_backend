package com.example.javaee_backend.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.javaee_backend.bean.CodeMsg;
import com.example.javaee_backend.mapper.LoginMapper;
import com.example.javaee_backend.pojo.ResultDTO;
import com.example.javaee_backend.pojo.UserDTO;
import com.example.javaee_backend.service.LoginService;
import com.example.javaee_backend.util.CommonUtil;
import com.example.javaee_backend.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;
import cn.hutool.crypto.SecureUtil;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {
    private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);

    private final LoginMapper loginMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    // 使用构造函数注入 LoginMapper
    @Autowired
    public LoginServiceImpl(LoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }

    public ResultDTO login(UserDTO requestUser){
        // 对 html 标签进行转义，防止 XSS 攻击
        String username = requestUser.getUserName();
        username = HtmlUtils.htmlEscape(username);

        List<UserDTO> users = loginMapper.findByUsername(username);
        if(users.size() == 0){
            log.warn("找不到用户名");
            return new ResultDTO(400, "用户名或密码错误");
        }
        else {
            UserDTO user = users.get(0);//获取用户
//            if(user.getPassword().equals(requestUser.getPassword())){
            // 检查日期是否为 null
            if (user.getRegisterTime() == null) {
                log.error("用户注册时间为空");
                // 返回相应的错误信息或进行其他处理
                return new ResultDTO(500, "服务器内部错误");
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date d = user.getRegisterTime();
            // 创建一个日历对象
            Calendar calendar = Calendar.getInstance();

            // 设置日历的时间为日期对象的时间
            calendar.setTime(d); // 使用add方法将日历的时间增加8小时
            calendar.add(Calendar.HOUR_OF_DAY, 8);

            // 从日历对象中获取增加后的日期时间
            d = calendar.getTime();
            String datetime = dateFormat.format(d);
            String str1 = SecureUtil.md5(SecureUtil.md5(requestUser.getPassword()) + SecureUtil.md5("I love java"));
            String str2 = user.getPassword();
            if(str1.equals(str2)){
                log.warn("登录成功");
                String token = UuidUtil.getShortUuid();
                user.setToken(token);
                //把token存入redis中 有效期1小时
                stringRedisTemplate.opsForValue().set("USER_" + token, JSON.toJSONString(user), 3600, TimeUnit.SECONDS);
                return new ResultDTO<UserDTO>(200, "登录成功", user);
            }else{
                log.warn("密码错误");
                log.warn("str1:" +str1 + "\nstr2: " + str2);
                return new ResultDTO(400, "用户名或密码错误");
            }
        }
    }

    public ResultDTO register(UserDTO requestUser) {
        // 对 html 标签进行转义，防止 XSS 攻击
        String username = requestUser.getUserName();
        username = HtmlUtils.htmlEscape(username);
        String password = requestUser.getPassword();
        password = HtmlUtils.htmlEscape(password);
        String phone = requestUser.getUserPhone();
        phone = HtmlUtils.htmlEscape(phone);

        List<UserDTO> users = loginMapper.findByUsername(username);
        if (users.size() != 0) {
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
            new_user.setUniversityName("黄渡理工");
            new_user.setUserGender("你怎么敢假定我的性别");
            loginMapper.insert_user(new_user);
            log.warn("注册成功");
            List<UserDTO> tmps = loginMapper.findByUsername(username);
            return new ResultDTO<Integer>(200, "注册成功", tmps.get(0).getId());
        }
    }

    public ResultDTO phoneLogin(UserDTO requestUser){
        // 对 html 标签进行转义，防止 XSS 攻击
        String phone = requestUser.getUserPhone();
        phone = HtmlUtils.htmlEscape(phone);

        List<UserDTO> users = loginMapper.findByUserphone(phone);
        if (users.size() == 0) {
            log.warn("手机号不存在");
            return new ResultDTO(400, "手机号不存在");
        } else {
            UserDTO user = users.get(0);//获取用户
            log.warn("登录成功");
            return new ResultDTO<UserDTO>(200, "登录成功", user);
        }
    }

    public ResultDTO missPass(UserDTO requestUser){
        // 对 html 标签进行转义，防止 XSS 攻击
        String phone = requestUser.getUserPhone();
        phone = HtmlUtils.htmlEscape(phone);
        String password = requestUser.getPassword();
        password = HtmlUtils.htmlEscape(password);

        //根据手机号查找用户
        List<UserDTO> users = loginMapper.findByUserphone(phone);
        if (users.size() == 0) {
            log.warn("手机号不存在");
            return new ResultDTO(400, "手机号不存在");
        } else {
            //进行密码修改
            String s = SecureUtil.md5(SecureUtil.md5(password) + SecureUtil.md5("I love java"));
            loginMapper.update_password(s,phone);
            log.warn("密码修改成功");
            return new ResultDTO(200,"密码修改成功");
        }
    }

    /**
     * 检查用户是否登录
     * @param userDTO
     * @return
     */
    public ResultDTO<UserDTO> checkLogin(UserDTO userDTO){
        if(userDTO == null || CommonUtil.isEmpty(userDTO.getToken())){
            return ResultDTO.errorByMsg(CodeMsg.USER_SESSION_EXPIRED);
        }
        ResultDTO<UserDTO> responseDTO = getLoginUser(userDTO.getToken());
        if(responseDTO.getCode() != 0){
            return responseDTO;
        }
        log.info("获取到的登录信息={}", responseDTO.getData());
        return ResultDTO.success(responseDTO.getData());
    }

    /**
     * 获取当前登录用户
     * @return
     */
    public ResultDTO<UserDTO> getLoginUser(String token){
        String value = stringRedisTemplate.opsForValue().get("USER_" + token);
        if(CommonUtil.isEmpty(value)){
            return ResultDTO.errorByMsg(CodeMsg.USER_SESSION_EXPIRED);
        }
        UserDTO selectedUserDTO = JSON.parseObject(value, UserDTO.class);
        return ResultDTO.success(selectedUserDTO);
    }

    /**
     * 退出登录操作
     * @param userDTO
     * @return
     */
    @Override
    public ResultDTO<Boolean> logout(UserDTO userDTO) {
        if(!CommonUtil.isEmpty(userDTO.getToken())){
            // token不为空  清除redis中数据
            stringRedisTemplate.delete("USER_" + userDTO.getToken());
        }
        return ResultDTO.successByMsg(true, "退出登录成功！");
    }
}
