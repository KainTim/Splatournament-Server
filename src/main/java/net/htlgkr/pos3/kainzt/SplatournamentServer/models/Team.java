package net.htlgkr.pos3.kainzt.SplatournamentServer.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private Tournament tournament;

    @OneToMany(cascade = CascadeType.ALL)
    private List<SplatUser> teamMembers;

    private Long inSetWithTeamID;

}