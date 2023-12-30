package com.example.javaee_backend.service;

import com.example.javaee_backend.pojo.TeamInvitationDTO;
import com.example.javaee_backend.pojo.TeamInvitationDTO;

import java.util.List;


public interface TeamInvitationService {
    String createTeamInvitation(TeamInvitationDTO teamInvitation);

    TeamInvitationDTO getTeamInvitation(int teamInvitationId);

    String modifyTeamInvitation(TeamInvitationDTO teamInvitation);

    String deleteTeamInvitation(int teamInvitationId);

    List<TeamInvitationDTO> getAllTeamInvitations();

    List<TeamInvitationDTO> getTeamInvitationByUserId(int userId);
    List<TeamInvitationDTO> getTeamInvitationBySearchKey(String SearchKey);

}
