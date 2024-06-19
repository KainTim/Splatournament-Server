package net.htlgkr.pos3.kainzt.SplatournamentServer.services;

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
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TeamRepository teamRepository;

    public boolean verifyLogin(String username, String password){
        return userRepository.findAll().stream().filter(splatUser -> splatUser.getUsername().equals(username))
                .anyMatch(splatUser -> splatUser.getPassword().equals(password));
    }

    public boolean addUser(String username, String password) {
        SplatUser splatUser = new SplatUser();
        splatUser.setUsername(username);
        splatUser.setPassword(password);
        if (userRepository.findAll().stream().anyMatch(splatUser1 -> splatUser1.getUsername().equals(username))) {
            return false;
        }
        userRepository.save(splatUser);
        return true;
    }
    public List<UserDTO> getAllUsers(){
        return userRepository.findAll().stream().map(splatUser -> {
            if (splatUser.getTeam()==null){
                Team team = new Team();
                team.setId(-1L);
                splatUser.setTeam(team);
            }
            return splatUser;
        }).map(splatUser -> new UserDTO(splatUser.getUsername(),
                splatUser.getPassword(),splatUser.getTeam().getId())).toList();
    }
}
