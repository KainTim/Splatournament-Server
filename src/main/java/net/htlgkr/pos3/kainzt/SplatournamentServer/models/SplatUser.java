package net.htlgkr.pos3.kainzt.SplatournamentServer.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class SplatUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String username;
    String password;
    @ManyToOne(cascade = CascadeType.ALL)
    private Team team;

}
