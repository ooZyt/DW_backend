package com.example.javaee_backend.controller;

import com.example.javaee_backend.pojo.ResultDTO;
import com.example.javaee_backend.pojo.UserDTO;
import com.example.javaee_backend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/User/namelogin", method = RequestMethod.POST)
    public ResultDTO login(@RequestBody UserDTO requestUser) throws SQLException {
        return loginService.login(requestUser);
    }

    @RequestMapping(value = "/User/register", method = RequestMethod.POST)
    public ResultDTO register(@RequestBody UserDTO requestUser) throws SQLException {
        return loginService.register(requestUser);
    }

    @RequestMapping(value = "/User/phonelogin", method = RequestMethod.POST)
    public ResultDTO phonelogin(@RequestBody UserDTO requestUser) throws SQLException {
        return loginService.phoneLogin(requestUser);
    }

    @RequestMapping(value = "/User/misspass", method = RequestMethod.POST)
    public ResultDTO missPass(@RequestBody UserDTO requestUser) throws SQLException {
        return loginService.missPass(requestUser);
    }

    @RequestMapping(value = "/User/check_login", method = RequestMethod.POST)
    public ResultDTO<UserDTO> checkLogin(@RequestBody UserDTO userDTO){
        return loginService.checkLogin(userDTO);
    }
}
