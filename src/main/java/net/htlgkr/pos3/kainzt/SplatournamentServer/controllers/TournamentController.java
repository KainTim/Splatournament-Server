package net.htlgkr.pos3.kainzt.SplatournamentServer.controllers;

import net.htlgkr.pos3.kainzt.SplatournamentServer.dtos.TournamentCreationDTO;
import net.htlgkr.pos3.kainzt.SplatournamentServer.dtos.TournamentDTO;
import net.htlgkr.pos3.kainzt.SplatournamentServer.models.Tournament;
import net.htlgkr.pos3.kainzt.SplatournamentServer.services.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/tournaments")
public class TournamentController {
    @Autowired
    TournamentService tournamentService;

    @GetMapping
    public List<TournamentDTO> getCurrentTournaments(){
        return tournamentService.getCurrentTournaments();
    }
    @PostMapping("add")
    public TournamentCreationDTO addTournament(@RequestBody TournamentCreationDTO tournament){
        Tournament tournament1 = new Tournament();
        tournament1.setName(tournament.getName());
        tournament1.setBestOf(tournament.getBestOf());
        tournament1.setMaxTeams(tournament.getMaxTeams());
        tournament1.setStyle(tournament.getStyle());
        tournament1.setCreatedBy(tournament.getCreatedBy());
        tournamentService.addTournament(tournament1);
        return tournament;
    }
    @DeleteMapping("/admin/delete/all")
    public void deleteAllTournaments(){
        tournamentService.deleteAllTournaments();
    }

}
