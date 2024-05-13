package net.htlgkr.pos3.kainzt.SplatournamentServer.models.enums;

import lombok.NonNull;

public enum TournamentStyle {
    SWISS{
        @NonNull
        @Override
        public String toString() {
            return "Swiss";
        }
    };

}
