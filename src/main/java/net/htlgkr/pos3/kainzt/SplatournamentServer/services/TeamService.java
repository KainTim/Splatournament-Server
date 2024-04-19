package net.htlgkr.pos3.kainzt.SplatournamentServer.services;

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

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }
}
