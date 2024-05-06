package net.htlgkr.pos3.kainzt.SplatournamentServer.controllers;

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
    public TournamentDTO addTournament(@RequestBody Tournament tournament){
        tournamentService.addTournament(tournament);
        return new TournamentDTO(tournament.getId(),
                tournament.getName(),
                tournament.getCreatedBy(),
                tournament.getStyle(),
                tournament.getCurrentPlayers().size());
    }

}
