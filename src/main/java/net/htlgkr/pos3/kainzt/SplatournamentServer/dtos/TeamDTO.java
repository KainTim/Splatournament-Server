package net.htlgkr.pos3.kainzt.SplatournamentServer.dtos;

import java.util.List;

public record TeamDTO(long id, String name, List<UserDTO> teamMembers) {
}
