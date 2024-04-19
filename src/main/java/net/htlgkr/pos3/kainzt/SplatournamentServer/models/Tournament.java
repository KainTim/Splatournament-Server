package net.htlgkr.pos3.kainzt.SplatournamentServer.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.htlgkr.pos3.kainzt.SplatournamentServer.models.enums.TournamentStyle;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@RequiredArgsConstructor
@Setter
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String createdBy;
    private TournamentStyle style;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Team> currentPlayers;


}
