package net.htlgkr.pos3.kainzt.SplatournamentServer.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.htlgkr.pos3.kainzt.SplatournamentServer.models.enums.TournamentStyle;

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
    private Long maxTeams;
    private Long bestOf;
    private TournamentStyle style;
    private String createdBy;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Team> currentTeams;


}
