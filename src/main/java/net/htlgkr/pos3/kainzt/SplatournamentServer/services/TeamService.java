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

    public boolean joinTeam(String username,String password,String teamName){
        if (!userService.verifyLogin(username,password)) return false;

        SplatUser user = userRepository.findAll().stream()
                .filter(splatUser -> splatUser.getUsername().equals(username)).
                findFirst().get();
        Optional<Team> optionalTeam = teamRepository.findAll().stream()
                .filter(team -> team.getName().equals(teamName)).findFirst();
        if (optionalTeam.isEmpty()) {
            return false;
        }
        optionalTeam.get().getTeamMembers().add(user);
        user.setTeam(optionalTeam.get());
        return true;
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

    public TeamCreationDTO addTeam(String username, String password, String teamName) {
        if (!userService.verifyLogin(username,password)){
            System.out.println("Invalid Login");
            System.out.println("u: "+username+"pw: "+password);
            return new TeamCreationDTO(null,null,null);
        }
        Optional<Team> optionalTeam = teamRepository.findAll().stream()
                .filter(team -> team.getName().equals(teamName)).findFirst();
        if (optionalTeam.isPresent()) {
            System.out.println("Team Already Present");
            return new TeamCreationDTO(null,null,null);
        }
        Team team = new Team();
        team.setName(teamName);
        SplatUser user = userRepository.findAll().stream()
                .filter(splatUser -> splatUser.getUsername().equals(username))
                .findFirst().get();
        user.setTeam(team);
        team.setTeamMembers(List.of(user));
        System.out.println(team.getId());
        long ID = teamRepository.findAll().stream().mapToLong(Team::getId).max().getAsLong() + 1L;
        team.setId(ID);

        teamRepository.save(team);
        TeamCreationDTO teamCreationDTO = new TeamCreationDTO(username, password, teamName);
        System.out.println(teamCreationDTO.toString());
        return teamCreationDTO;
    }

    public TournamentDTO joinTournament(Long teamId, Long tournamentId) {
        Optional<Tournament> tournamentToJoinOptional = tournamentRepository.findById(tournamentId);
        if (tournamentToJoinOptional.isEmpty()) return new TournamentDTO(-1L,null,null,null,-1L);
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        if (optionalTeam.isEmpty()) return new TournamentDTO(-1L,null,null,null,-1L);
        Team team = optionalTeam.get();
        team.setTournament(tournamentToJoinOptional.get());
        tournamentToJoinOptional.get().getCurrentTeams().add(team);
        return new TournamentDTO(tournamentId, team.getTournament().getName(),team.getTournament().getCreatedBy(),team.getTournament().getStyle(),team.getTournament().getCurrentTeams().stream().count());
    }
    public SetDTO getAvailableSet(AvailableSetDTO availableSetDTO) {
        Team teamOther = tournamentRepository.findById(availableSetDTO.tournamentID()).get().getCurrentTeams()
                .stream().filter(team -> team.getInSetWithTeamID() != null)
                .findAny().get();
        teamOther.setInSetWithTeamID(availableSetDTO.teamID());
        Team team = teamRepository.findById(availableSetDTO.teamID()).get();
        team.setInSetWithTeamID(teamOther.getId());
        return new SetDTO(availableSetDTO.teamID(), teamOther.getId(),tournamentRepository.findById(availableSetDTO.tournamentID()).get().getBestOf());
    }
}
