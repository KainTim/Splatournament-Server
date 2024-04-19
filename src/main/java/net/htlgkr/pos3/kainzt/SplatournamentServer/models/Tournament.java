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
@Setter
public class Tournament {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String createdBy;
    private TournamentStyle style;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Team> currentPlayers;


}
