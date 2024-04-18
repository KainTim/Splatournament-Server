package net.htlgkr.pos3.kainzt.SplatournamentServer.services;

import net.htlgkr.pos3.kainzt.SplatournamentServer.dtos.UserDTO;
import net.htlgkr.pos3.kainzt.SplatournamentServer.models.SplatUser;
import net.htlgkr.pos3.kainzt.SplatournamentServer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository repository;
    public boolean verifyLogin(String username, String password){
        return repository.findAll().stream().filter(splatUser -> splatUser.getUsername().equals(username))
                .anyMatch(splatUser -> splatUser.getPassword().equals(password));
    }

    public void addUser(String username, String password) {
        SplatUser splatUser = new SplatUser();
        splatUser.setUsername(username);
        splatUser.setPassword(password);
        repository.save(splatUser);
    }
}
