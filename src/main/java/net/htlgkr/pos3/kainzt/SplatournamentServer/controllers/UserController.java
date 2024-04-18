package net.htlgkr.pos3.kainzt.SplatournamentServer.controllers;

import net.htlgkr.pos3.kainzt.SplatournamentServer.dtos.UserDTO;
import net.htlgkr.pos3.kainzt.SplatournamentServer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController{
    @Autowired
    UserService service;
    @GetMapping("verify-login")
    public boolean verifyLogin(@RequestParam String username, @RequestParam String password){
        return service.verifyLogin(username,password);
    }
    @PostMapping("add-user")
    public boolean addUser(@RequestParam String username, @RequestParam String password){
        return service.addUser(username,password);
    }
}
