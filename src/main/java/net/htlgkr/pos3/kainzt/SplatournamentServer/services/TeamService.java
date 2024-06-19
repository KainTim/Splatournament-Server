package net.htlgkr.pos3.kainzt.SplatournamentServer.services;

import net.htlgkr.pos3.kainzt.SplatournamentServer.dtos.*;
import net.htlgkr.pos3.kainzt.SplatournamentServer.models.SplatUser;
import net.htlgkr.pos3.kainzt.SplatournamentServer.models.Team;
import net.htlgkr.pos3.kainzt.SplatournamentServer.models.Tournament;
import net.htlgkr.pos3.kainzt.SplatournamentServer.repositories.TeamRepository;
import net.htlgkr.pos3.kainzt.SplatournamentServer.repositories.TournamentRepository;
import net.htlgkr.pos3.kainzt.SplatournamentServer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    private TournamentService tournamentService;
    @Autowired
    private TournamentRepository tournamentRepository;

    public TeamCreationIdDTO joinTeam(String username,String password,String teamName){
        if (!userService.verifyLogin(username,password)) return new TeamCreationIdDTO(null,-1L);

        SplatUser user = userRepository.findAll().stream()
                .filter(splatUser -> splatUser.getUsername().equals(username)).
                findFirst().get();
        Optional<Team> optionalTeam = teamRepository.findAll().stream()
                .filter(team -> team.getName().equals(teamName)).findFirst();
        if (optionalTeam.isEmpty()) {
            return new TeamCreationIdDTO(null,-1L);
        }
        optionalTeam.get().getTeamMembers().add(user);
        user.setTeam(optionalTeam.get());
        return new TeamCreationIdDTO(username,teamRepository.findAll().stream()
                .filter(team -> optionalTeam.get().getName().equals(team.getName()))
                .findFirst().get().getId());
    }

    public List<TeamDTO> getAllTeams() {
        return teamRepository.findAll().stream().map(team -> new TeamDTO(team.getId(),
                    team.getName(),
                    team.getTeamMembers().stream().map(member -> {
                        return new UserDTO(member.getUsername(), member.getPassword(), member.getTeam().getId());
                    }
                ).toList()
            )
        ).toList();
    }

    public TeamCreationIdDTO addTeam(String username, String password, String teamName) {
        if (!userService.verifyLogin(username,password)) return new TeamCreationIdDTO(null,-1L);

        Optional<Team> optionalTeam = teamRepository.findAll().stream()
                .filter(team -> team.getName().equals(teamName)).findFirst();
        if (optionalTeam.isPresent()) {
            return new TeamCreationIdDTO(null,-5L);
        }
        Team team = new Team();
        team.setName(teamName);
        SplatUser user = userRepository.findAll().stream()
                .filter(splatUser -> splatUser.getUsername().equals(username))
                .findFirst().get();
        user.getTeam().getTeamMembers().remove(user);
        user.setTeam(team);
        ArrayList<SplatUser> splatUsers = new ArrayList<>();
        splatUsers.add(user);
        team.setTeamMembers(splatUsers);
        teamRepository.save(team);
        return new TeamCreationIdDTO(username,teamRepository.findAll().stream()
                .filter(team1 -> user.getTeam().getName().equals(team1.getName()))
                .findFirst().get().getId());
    }

    public TournamentDTO joinTournament(Long teamId, Long tournamentId) {
        Optional<Tournament> tournamentToJoinOptional = tournamentRepository.findById(tournamentId);
        if (tournamentToJoinOptional.isEmpty()) return new TournamentDTO(-5L,null,null,null,-1L,-1L);
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        if (optionalTeam.isEmpty()||tournamentToJoinOptional.get().getCurrentPlayers().size()>tournamentToJoinOptional.get().getMaxTeams()) return new TournamentDTO(-1L,null,null,null,-5L,-1L);
        Team team = optionalTeam.get();
        Tournament tournament = tournamentToJoinOptional.get();
        team.setTournament(tournament);
        tournament.getCurrentPlayers().add(team);
        tournamentRepository.findAll().forEach(tournament1 -> System.out.println(tournament1.getCurrentPlayers().size()));
        return new TournamentDTO(tournamentId, team.getTournament().getName(),team.getTournament().getCreatedBy(),team.getTournament().getStyle(),team.getTournament().getCurrentPlayers().stream().count(),team.getTournament().getBestOf());
    }
}
