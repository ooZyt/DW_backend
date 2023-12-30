package com.example.javaee_backend.controller;

import com.example.javaee_backend.pojo.TeamInvitationDTO;
import com.example.javaee_backend.pojo.TeamInvitationDTO;
import com.example.javaee_backend.service.TeamInvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.javaee_backend.pojo.TeamInvitationDTO;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:8000", "http://localhost:8080"})
@RestController
@RequestMapping("/api")
public class TeamInvitationController {

    @Autowired
    private TeamInvitationService teamInvitationService;

    @PostMapping("/createTeamInvitation")
    public String createTeamInvitation(@RequestBody TeamInvitationDTO teamInvitation) {
        return teamInvitationService.createTeamInvitation(teamInvitation);
    }
    
    @GetMapping("/getTeamInvitation/{teamInvitationId}")
    public TeamInvitationDTO getTeamInvitation(@PathVariable int teamInvitationId) {
        return teamInvitationService.getTeamInvitation(teamInvitationId);
    }
    @GetMapping("/getTeamInvitationByUserId/{userId}")
    public List<TeamInvitationDTO> getTeamInvitationByUserId(@PathVariable int userId) {
        return teamInvitationService.getTeamInvitationByUserId(userId);
    }
    @GetMapping("/getTeamInvitationBySearchKey")
    public List<TeamInvitationDTO> getTeamInvitationBySearchKey(@RequestParam String keyword) {
        System.out.println("team:keyword="+keyword);
        return teamInvitationService.getTeamInvitationBySearchKey(keyword);
    }

    @PatchMapping("/modifyTeamInvitation")
    public String modifyTeamInvitation(@RequestBody TeamInvitationDTO teamInvitation) {
        return teamInvitationService.modifyTeamInvitation(teamInvitation);
    }

    @DeleteMapping("/deleteTeamInvitation/{teamInvitationId}")
    public String deleteTeamInvitation(@PathVariable int teamInvitationId) {
        System.out.println("teamInvitationId=" + teamInvitationId);
        return teamInvitationService.deleteTeamInvitation(teamInvitationId);
    }

    @GetMapping("/getAllTeamInvitations")
    public List<TeamInvitationDTO> getAllTeamInvitations() {
        return teamInvitationService.getAllTeamInvitations();
    }
}
