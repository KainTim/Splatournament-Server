package net.htlgkr.pos3.kainzt.SplatournamentServer.services;

import net.htlgkr.pos3.kainzt.SplatournamentServer.dtos.TeamDTO;
import net.htlgkr.pos3.kainzt.SplatournamentServer.dtos.UserDTO;
import net.htlgkr.pos3.kainzt.SplatournamentServer.models.SplatUser;
import net.htlgkr.pos3.kainzt.SplatournamentServer.models.Team;
import net.htlgkr.pos3.kainzt.SplatournamentServer.repositories.TeamRepository;
import net.htlgkr.pos3.kainzt.SplatournamentServer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    UserService userService;

    public boolean joinTeam(String username,String password,String teamName){
        if (!userService.verifyLogin(username,password)) return false;

        SplatUser user = userRepository.findAll().stream()
                .filter(splatUser -> splatUser.getUsername().equals(username)).
                findFirst().get();
        Optional<Team> optionalTeam = teamRepository.findAll().stream()
                .filter(team -> team.getName().equals(teamName)).findFirst();
        if (optionalTeam.isEmpty()) {
            return false;
        }
        user.setTeam(optionalTeam.get());
        return true;
    }

    public List<TeamDTO> getAllTeams() {
        return teamRepository.findAll().stream().map(team -> new TeamDTO(team.getId(),
                    team.getName(),
                    team.getTeamMembers().stream().map(member -> {
                        return new UserDTO(member.getUsername(), member.getPassword());
                    }
                ).toList()
            )
        ).toList();
    }

    public boolean addTeam(String username, String password, String teamName) {
        if (!userService.verifyLogin(username,password)) return false;

        Optional<Team> optionalTeam = teamRepository.findAll().stream()
                .filter(team -> team.getName().equals(teamName)).findFirst();
        if (optionalTeam.isPresent()) {
            return false;
        }
        Team team = new Team();
        team.setName(teamName);
        SplatUser user = userRepository.findAll().stream()
                .filter(splatUser -> splatUser.getUsername().equals(username))
                .findFirst().get();
        user.setTeam(team);
        team.setTeamMembers(List.of(user));
        teamRepository.save(team);
        return true;
    }
}
