package com.example.javaee_backend.controller;

import com.example.javaee_backend.pojo.ResultDTO;
import com.example.javaee_backend.pojo.UserDTO;
import com.example.javaee_backend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/Admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/deleteusers", method = RequestMethod.POST)
    public ResultDTO deleteUsers(@RequestBody List<UserDTO> requestUsers) throws SQLException {
        return adminService.deletc_selectUser(requestUsers);
    }

    @RequestMapping(value = "/addusers",method = RequestMethod.POST)
    public ResultDTO addUsers(@RequestBody List<UserDTO> requestUsers) throws SQLException{
        return adminService.add_selectUser(requestUsers);
    }

    @RequestMapping(value = "/changestate",method = RequestMethod.POST)
    public ResultDTO changeState(@RequestBody UserDTO requestUser) throws SQLException{
        return adminService.changeState(requestUser);
    }

}
