package org.apsidart.common.tournament.dto;

public record TournamentRankingDto(
    long idPlayer,
    int nbVictory,
    int rank
) {

}
