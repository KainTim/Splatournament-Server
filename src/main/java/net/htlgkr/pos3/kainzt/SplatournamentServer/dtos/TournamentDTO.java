package net.htlgkr.pos3.kainzt.SplatournamentServer.dtos;

import net.htlgkr.pos3.kainzt.SplatournamentServer.models.enums.TournamentStyle;

public record TournamentDTO(
    Long id,
    String name,
    String createdBy,
    TournamentStyle style,
    int teamCount
) {
}
