package org.apsidart.common.tournament.dto;

import java.util.Date;
import java.util.List;

public record TournamentDto(Date tournamentDate, List<TournamentRankingDto> rankings, int nbGame) {
}
