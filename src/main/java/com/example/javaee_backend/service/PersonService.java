package com.example.javaee_backend.service;
import java.util.Date;
import java.util.List;


import com.example.javaee_backend.pojo.ResultDTO;
import com.example.javaee_backend.pojo.UserDTO;
import org.springframework.web.multipart.MultipartFile;

public interface PersonService {


    UserDTO getPersonById(int userId);

    String updateUserInformation(Integer id,  String userName, Date userBirth,
                                      String userGender, String universityName, String userIntro);

    String deletePerson(int userId);

    List<UserDTO> getAllPersons();

    ResultDTO updateAvatar(MultipartFile file, Integer userid);

    List<UserDTO> getPersonsByName(String name);
    List<UserDTO> getPersonsByPhone(String phone);

    List<UserDTO> getPersonsByState(String state);
    List<UserDTO> getUsersByRegistrationDateRange(Date startTime, Date endTime);

}
