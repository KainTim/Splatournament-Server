package net.htlgkr.pos3.kainzt.SplatournamentServer.repositories;

import net.htlgkr.pos3.kainzt.SplatournamentServer.models.Team;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends ListCrudRepository<Team,Long> {
}
