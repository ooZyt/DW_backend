package com.example.javaee_backend.mapper;

import com.example.javaee_backend.pojo.UserDTO;

import java.util.List;
import java.util.Date;
import org.apache.ibatis.annotations.*;

@Mapper
public interface PersonMapper {
    // 获取 user_info 表中所有个人信息
    @Select("SELECT id AS id, role AS role, user_name AS userName, user_birth AS userBirth, user_gender AS userGender,user_grade AS userGrade,university_name AS universityName,user_phone AS userPhone,user_intro AS userIntro,user_avatar AS userAvatar,register_time AS registerTime,user_state AS userState " +
        "FROM user_info")
    List<UserDTO> getAllPersons();

    @Select("SELECT id AS id, " +
            "user_name AS userName, " +
            "user_state AS userState, " +
            "user_birth AS userBirth, " +
            "user_gender AS userGender, " +
            "user_grade AS userGrade, " +
            "university_name AS universityName, " +
            "user_phone AS userPhone, " +
            "user_intro AS userIntro, " +
            "user_avatar AS userAvatar, " +
            "register_time AS registerTime " +
            "FROM user_info " +
            "WHERE user_name LIKE CONCAT('%', #{name}, '%')")
    List<UserDTO> getUserByName(@Param("name") String userName);
    @Select("SELECT id AS id, " +
            "user_name AS userName, " +
            "user_state AS userState, " +
            "user_birth AS userBirth, " +
            "user_gender AS userGender, " +
            "user_grade AS userGrade, " +
            "university_name AS universityName, " +
            "user_phone AS userPhone, " +
            "user_intro AS userIntro, " +
            "user_avatar AS userAvatar, " +
            "register_time AS registerTime " +
            "FROM user_info " +
            "WHERE user_phone LIKE CONCAT('%', #{phone}, '%')")
    List<UserDTO> getUserByPhone(@Param("phone") String userPhone);

    @Select("SELECT id AS id, " +
            "user_name AS userName, " +
            "user_state AS userState, " +
            "user_birth AS userBirth, " +
            "user_gender AS userGender, " +
            "user_grade AS userGrade, " +
            "university_name AS universityName, " +
            "user_phone AS userPhone, " +
            "user_intro AS userIntro, " +
            "user_avatar AS userAvatar, " +
            "register_time AS registerTime " +
            "FROM user_info " +
            "WHERE user_state = #{state}")
    List<UserDTO> getUserByStatus(@Param("state") String userState);

    // Existing mapper methods ...

    @Select("SELECT id AS id, " +
            "user_name AS userName, " +
            "user_state AS userState, " +
            "user_birth AS userBirth, " +
            "user_gender AS userGender, " +
            "user_grade AS userGrade, " +
            "university_name AS universityName, " +
            "user_phone AS userPhone, " +
            "user_intro AS userIntro, " +
            "user_avatar AS userAvatar, " +
            "register_time AS registerTime " +
            "FROM user_info " +
            "WHERE register_time BETWEEN #{startTime} AND #{endTime}")
    List<UserDTO> getUsersByRegistrationDateRange(@Param("startTime") Date startTime,
                                                  @Param("endTime") Date endTime);

    // 从 user_info 表根据 userId 获取个人信息
    @Select("SELECT id AS id,user_name AS userName,user_state AS userState,user_Birth AS userBirth,user_gender AS userGender,user_grade AS userGrade,university_name AS universityName,user_phone AS userPhone,user_intro AS userIntro,user_avatar AS userAvatar FROM user_info WHERE id = #{id}")
    UserDTO getPersonById(int id);

    // 更新 user_info 表中的个人信息
    @Update("UPDATE user_info SET user_name = #{userName}, " +
    "user_birth = #{userBirth}, " +
    "user_gender = #{userGender}, " +
    "university_name = #{universityName}, " +
    "user_intro = #{userIntro} " +
    "WHERE id = #{id}")
    int updateUserInfo(@Param("id") Integer id,  @Param("userName") String userName,
                        @Param("userBirth") Date userBirth, @Param("userGender") String userGender,
                        @Param("universityName") String universityName, @Param("userIntro") String userIntro);

     // 从 user_info 表删除个人信息
     @Delete("DELETE FROM user_info WHERE id = #{id}")
     int deletePerson(int id);

     @Update("UPDATE user_info SET user_avatar = #{userAvatar} WHERE id = #{id}")
     void updateUserAvatar(@Param("id") Integer id,@Param("userAvatar") String userAvatar);
}
