package net.htlgkr.pos3.kainzt.SplatournamentServer.services;

import net.htlgkr.pos3.kainzt.SplatournamentServer.dtos.TournamentDTO;
import net.htlgkr.pos3.kainzt.SplatournamentServer.models.Tournament;
import net.htlgkr.pos3.kainzt.SplatournamentServer.repositories.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentService {
    @Autowired
    TournamentRepository repository;

    public List<TournamentDTO> getCurrentTournaments() {
        return repository.findAll().stream()
                .map(tournament -> new TournamentDTO(tournament.getId(),
                        tournament.getName(),
                        tournament.getCreatedBy(),
                        tournament.getStyle(),
                        (long) tournament.getCurrentPlayers().size(),
                        tournament.getBestOf()))
                .peek(System.out::println).toList();
    }

    public void addTournament(Tournament tournament) {
        repository.save(tournament);
    }

    public void deleteAllTournaments() {
        repository.deleteAll();
    }
}
