package com.example.javaee_backend.controller;


import com.example.javaee_backend.pojo.CompetitionDTO;
import com.example.javaee_backend.pojo.ResultDTO;
import com.example.javaee_backend.service.PersonService;

import java.util.*;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.example.javaee_backend.pojo.UserDTO;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = {"http://localhost:8000","http://localhost:8080"})
@RestController
@RequestMapping("/api")
public class PersonController {
     @Autowired
    private PersonService personService;

    // 添加个人信息
    //@PostMapping("/addUser")
    //public String addPerson(@RequestBody PersonDTO person) {
    //    return personService.addPerson(person);
    //}

    // 根据 userId 获取个人信息
    @GetMapping("/getUser/{userId}")
    public UserDTO getPerson(@PathVariable(value = "userId") int userId) {
        return personService.getPersonById(userId);
    }

    @RequestMapping(value="/getUserByName",method = RequestMethod.GET)
    public List<UserDTO> getUsersByName(@RequestParam String userName){
        return personService.getPersonsByName(userName);
    }
    @RequestMapping(value="/getUserByPhone",method = RequestMethod.GET)
    public List<UserDTO> getUsersByPhone(@RequestParam String userPhone){
        return personService.getPersonsByPhone(userPhone);
    }

    @RequestMapping(value="/getUserByState",method = RequestMethod.GET)
    public List<UserDTO> getUsersByState(@RequestParam String userState){
        return personService.getPersonsByState(userState);
    }

    @RequestMapping(value="/getUsersByRegistrationDateRange", method = RequestMethod.GET)
    public List<UserDTO> getUsersByRegistrationDateRange(
            @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date startTime,
            @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date endTime) {
        return personService.getUsersByRegistrationDateRange(startTime, endTime);
    }

    @RequestMapping(value="/getUsersByMultipleCriteria", method = RequestMethod.GET)
    public List<UserDTO> getUsersByMultipleCriteria(
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String userPhone,
            @RequestParam(required = false) String userState,
            @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date startTime,
            @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date endTime) {

        List<List<UserDTO>> lists = new ArrayList<>();

        if (userName != null && !userName.isEmpty()) {
            lists.add(personService.getPersonsByName(userName));
        }
        if (userPhone != null && !userPhone.isEmpty()) {
            lists.add(personService.getPersonsByPhone(userPhone));
        }
        if (userState != null && !userState.isEmpty()) {
            lists.add(personService.getPersonsByState(userState));
        }
        if (startTime != null && endTime != null) {
            lists.add(personService.getUsersByRegistrationDateRange(startTime, endTime));
        }

        List<UserDTO> intersection = new ArrayList<>();
        if (!lists.isEmpty()) {
            intersection.addAll(lists.get(0));
            for (List<UserDTO> list : lists) {
                intersection.retainAll(list);
            }
        }

        return intersection;
    }

    // 更新个人信息
    @PutMapping("/updateUser")
    public ResponseEntity<String> updateUserInformation(@RequestParam Integer id,
                                                        @RequestParam String userName, @RequestParam("userBirth") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date userBirth,
                                                        @RequestParam String userGender, @RequestParam String universityName,
                                                        @RequestParam String userIntro) {
        personService.updateUserInformation(id,  userName, userBirth, userGender, universityName, userIntro);
        return ResponseEntity.ok("User information updated successfully.");
    }

    // 根据 userId 删除个人信息
    @DeleteMapping("/deleteUser/{userId}")
    public String deletePerson(@PathVariable(value = "userId") int userId) {
        System.out.println("UserId="+userId);
        return personService.deletePerson(userId);
    }

    // 获取所有个人信息
    @GetMapping("/getAllUsers")
    public List<UserDTO> getAllPersons() {
        return personService.getAllPersons();
    }

    //更新头像
    @PostMapping("/updateAvatar")
    public ResultDTO updateAvatar(@RequestParam MultipartFile avatar, @RequestParam String id ){
        return personService.updateAvatar(avatar, Integer.parseInt(id));
    }
}
