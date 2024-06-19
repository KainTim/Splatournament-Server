package net.htlgkr.pos3.kainzt.SplatournamentServer.controllers;

import net.htlgkr.pos3.kainzt.SplatournamentServer.dtos.*;
import net.htlgkr.pos3.kainzt.SplatournamentServer.models.SplatUser;
import net.htlgkr.pos3.kainzt.SplatournamentServer.models.Team;
import net.htlgkr.pos3.kainzt.SplatournamentServer.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;
    @GetMapping("admin/getAllTeams")
    public List<TeamDTO> getAllTeams(){
        return teamService.getAllTeams();
    }
    @PostMapping("join")
    public boolean joinTeam(@RequestParam String username, @RequestParam String password,@RequestParam String teamname){
        return teamService.joinTeam(username,password,teamname);
    }
    @PostMapping("add")
    public TeamCreationDTO addTeam(@RequestBody TeamCreationDTO teamCreationDTO){
        return teamService.addTeam(teamCreationDTO.username(), teamCreationDTO.password(), teamCreationDTO.teamname());
    }
    @PostMapping("joinTournament")
    public TournamentDTO joinTournament(@RequestBody JoinTournamentDTO tournamentDTO){
        return teamService.joinTournament(tournamentDTO.teamId(), tournamentDTO.tournamentId());
    }
    @GetMapping("getAvailableSet")
    public SetDTO getAvailableSet(@RequestBody AvailableSetDTO availableSetDTO){
        return teamService.getAvailableSet(availableSetDTO);
    }
}
