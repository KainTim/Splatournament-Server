package net.htlgkr.pos3.kainzt.SplatournamentServer.repositories;

import net.htlgkr.pos3.kainzt.SplatournamentServer.models.Tournament;
import org.springframework.data.repository.ListCrudRepository;

public interface TournamentRepository extends ListCrudRepository<Tournament, Long> {
}
