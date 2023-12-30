package com.example.javaee_backend.service.impl;

import com.example.javaee_backend.mapper.TeamInvitationMapper;
import com.example.javaee_backend.pojo.TeamInvitationDTO;
import com.example.javaee_backend.pojo.TeamInvitationDTO;
import com.example.javaee_backend.service.TeamInvitationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamInvitationServiceImpl implements TeamInvitationService {
    @Autowired
    private TeamInvitationMapper teamInvitationMapper;

    @Override
    public String createTeamInvitation(TeamInvitationDTO teamInvitation) {
        TeamInvitationDTO existTeamInvitation = teamInvitationMapper.getTeamInvitation(teamInvitation.getTeamInvitationId());
        if (null == existTeamInvitation) {
            teamInvitationMapper.addTeamInvitation(teamInvitation);
            return String.valueOf(teamInvitation.getTeamInvitationId());
        }
        throw new IllegalArgumentException("已存在相同ID的问答");
    }

    //    private static final Logger logger = LoggerFactory.getLogger(TeamInvitationServiceImpl.class);
    @Override
    public TeamInvitationDTO getTeamInvitation(int teamInvitationId) {
        return teamInvitationMapper.getTeamInvitation(teamInvitationId);
    }

    @Override
    public String modifyTeamInvitation(TeamInvitationDTO teamInvitation) {
        TeamInvitationDTO existTeamInvitation = teamInvitationMapper.getTeamInvitation(teamInvitation.getTeamInvitationId());
        if (null == existTeamInvitation) {
            return "不存在该问答";
        }
        teamInvitationMapper.updateTeamInvitation(teamInvitation);
        return String.valueOf(teamInvitation.getTeamInvitationId());
    }

    @Override
    public String deleteTeamInvitation(int teamInvitationId) {
        TeamInvitationDTO existTeamInvitation = teamInvitationMapper.getTeamInvitation(teamInvitationId);
        System.out.println(existTeamInvitation);
        if (null == existTeamInvitation) {
            return "不存在该问答";
        }
        teamInvitationMapper.deleteTeamInvitation(teamInvitationId);
        return "成功删除问答";
    }

    @Override
    public List<TeamInvitationDTO> getAllTeamInvitations() {
        return teamInvitationMapper.getAllTeamInvitations();
    }

    @Override
    public List<TeamInvitationDTO> getTeamInvitationByUserId(int userId){
        return teamInvitationMapper.getTeamInvitationByUserId(userId);
    }
    @Override
    public List<TeamInvitationDTO> getTeamInvitationBySearchKey(String SearchKey){
        return teamInvitationMapper.getTeamInvitationBySearchKey(SearchKey);
    }
}
