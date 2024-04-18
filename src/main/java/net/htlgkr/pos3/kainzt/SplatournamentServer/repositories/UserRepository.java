package net.htlgkr.pos3.kainzt.SplatournamentServer.repositories;

import net.htlgkr.pos3.kainzt.SplatournamentServer.models.SplatUser;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ListCrudRepository<SplatUser, Integer> {
}
