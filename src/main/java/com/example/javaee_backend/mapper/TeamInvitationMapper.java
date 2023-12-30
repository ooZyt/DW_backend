package com.example.javaee_backend.mapper;

import com.example.javaee_backend.pojo.TeamInvitationDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TeamInvitationMapper {
    @Select("SELECT team_invitation_id AS teamInvitationId, user_id AS userId, user_name AS userName, team_invitation_title AS teamInvitationTitle, team_invitation_content AS teamInvitationContent, team_invitation_publish_time AS teamInvitationPublishTime " +
            "FROM team_invitation_info")
    List<TeamInvitationDTO> getAllTeamInvitations();

    @Select("SELECT team_invitation_id AS teamInvitationId, user_id AS userId, user_name AS userName, team_invitation_title AS teamInvitationTitle, team_invitation_content AS teamInvitationContent, team_invitation_publish_time AS teamInvitationPublishTime " +
            "FROM team_invitation_info WHERE user_id = #{userId}")
    List<TeamInvitationDTO> getTeamInvitationByUserId(@Param("userId") int userId);

    @Select("SELECT team_invitation_id AS teamInvitationId, user_id AS userId, user_name AS userName, team_invitation_title AS teamInvitationTitle, team_invitation_content AS teamInvitationContent, team_invitation_publish_time AS teamInvitationPublishTime " +
            "FROM team_invitation_info WHERE team_invitation_title LIKE CONCAT('%', #{SearchKey}, '%')")
    List<TeamInvitationDTO> getTeamInvitationBySearchKey(@Param("SearchKey") String SearchKey);

    @Insert("INSERT INTO team_invitation_info (user_id, user_name, team_invitation_title, team_invitation_content, team_invitation_publish_time) " +
            "VALUES (#{invitation.userId}, #{invitation.userName}, #{invitation.teamInvitationTitle}, #{invitation.teamInvitationContent}, #{invitation.teamInvitationPublishTime})")
    @Options(useGeneratedKeys = true, keyProperty = "invitation.teamInvitationId")
    int addTeamInvitation(@Param("invitation") TeamInvitationDTO invitationDTO);

    @Select("SELECT team_invitation_id AS teamInvitationId, user_id AS userId, user_name AS userName, team_invitation_title AS teamInvitationTitle, team_invitation_content AS teamInvitationContent, team_invitation_publish_time AS teamInvitationPublishTime " +
            "FROM team_invitation_info WHERE team_invitation_id = #{teamInvitationId}")
    TeamInvitationDTO getTeamInvitation(@Param("teamInvitationId") int teamInvitationId);

    @Update("UPDATE team_invitation_info SET user_name = #{invitation.userName}, team_invitation_title = #{invitation.teamInvitationTitle}, team_invitation_content = #{invitation.teamInvitationContent}, team_invitation_publish_time = #{invitation.teamInvitationPublishTime} WHERE team_invitation_id = #{invitation.teamInvitationId}")
    int updateTeamInvitation(@Param("invitation") TeamInvitationDTO invitationDTO);

    @Delete("DELETE FROM team_invitation_info WHERE team_invitation_id = #{teamInvitationId}")
    int deleteTeamInvitation(@Param("teamInvitationId") int teamInvitationId);
}
