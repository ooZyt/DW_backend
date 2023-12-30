package com.example.javaee_backend.mapper;

import com.example.javaee_backend.pojo.UserDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LoginMapper {
    @Select("select id as id,user_name as userName,register_time as registerTime,password as password from `test`.`user_info` where user_name = #{userName}")
    List<UserDTO> findByUsername(@Param("userName") String userName);

    @Select("select id as id,user_name as userName,user_phone as userPhone from `test`.`user_info` where user_phone = #{userPhone}")
    List<UserDTO> findByUserphone(@Param("userPhone") String userPhone);

    @Insert("insert into `test`.`user_info`(id,role,password,user_avatar,user_name,user_state,user_gender,user_phone,university_name,user_intro,register_time) values" +
            "(#{id},#{role},#{password},#{userAvatar},#{userName},#{userState},#{userGender},#{userPhone},#{universityName},#{userIntro},#{registerTime})")
    void insert_user(UserDTO UserDTO);
    @Update("update `test`.`user_info` set password = #{password} where user_phone = #{userPhone}")
    void update_password(@Param("password") String password,@Param("userPhone") String userPhone);

    @Update("update `test`.`user_info` set user_state = #{userState} where user_name = #{userName}")
    void update_state(@Param("userState") String userState,@Param("userName") String userName);
}
