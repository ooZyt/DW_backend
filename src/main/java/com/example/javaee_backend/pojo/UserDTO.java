package com.example.javaee_backend.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_info")
public class UserDTO {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String role;
    private String password;

    @Column(name="user_avatar")
    private String userAvatar;
    @Column(name="user_name")
    private String userName;
    @Column(name="user_state")
    private String userState;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Column(name="user_birth")
    private Date userBirth;
    @Column(name="user_gender")
    private String userGender;
    @Column(name="user_grade")
    private String userGrade;
    @Column(name="university_name")
    private String universityName;
    @Column(name="user_phone")
    private String userPhone;
    @Column(name="user_intro")
    private String userIntro;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Column(name = "register_time")
    private Date registerTime;

    private String token;

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getUserName() + "'" +
                ", password='" + getPassword() + "'" +
                ", phone='" + getUserPhone() + "'" +
                ", sex='" + getUserGender() + "'" +
                ", universityName='" + getUniversityName() + "'" +
                "}";
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String username) {
        this.userName = username;
    }

    public String getRole() {
        return this.role;
    }
    public void setRole(String role) { this.role = role; }

    public Date getRegisterTime(){return this.registerTime;}
    public void setRegisterTime(Date registerTime) {this.registerTime = registerTime;}

    public String getAvatarUrl(){return this.userAvatar;}
    public void setAvatarUrl(String avatarUrl){this.userAvatar = avatarUrl;}

    public void setUserGender(String userGender){this.userGender = userGender;}
    public String getUserGender(){return this.userGender;}

    public void setGrade(String grade){this.userGrade = grade;}
    public String getGrade(){return this.userGrade;}

    public void setUserBirth(Date userBirth){this.userBirth = userBirth;}
    public Date getUserBirth(){return this.userBirth;}

    public void setUserState(String userState){this.userState = userState;}
    public String getUserState(){return this.userState;}

    public void setUserIntro(String brief){this.userIntro = brief;}
    public String getUserIntro(){return this.userIntro;}

    public void setUserPhone(String userPhone){this.userPhone = userPhone;}
    public String getUserPhone(){return this.userPhone;}

    public void setUniversityName(String universityName){this.universityName = universityName;}
    public String getUniversityName(){return this.universityName;}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
