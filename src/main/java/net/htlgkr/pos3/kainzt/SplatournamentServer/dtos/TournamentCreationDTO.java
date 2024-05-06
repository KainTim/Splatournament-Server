package net.htlgkr.pos3.kainzt.SplatournamentServer.dtos;

import lombok.Getter;
import lombok.Setter;
import net.htlgkr.pos3.kainzt.SplatournamentServer.models.enums.TournamentStyle;

@Getter
@Setter
public class TournamentCreationDTO {


    private final String name;
    private final long maxTeams;
    private final long bestOf;
    private final TournamentStyle style;
    private final String createdBy;

    public TournamentCreationDTO(String name, long maxTeams, long bestOf, TournamentStyle style, String createdBy) {

        this.name = name;
        this.maxTeams = maxTeams;
        this.bestOf = bestOf;
        this.style = style;
        this.createdBy = createdBy;
    }

}
