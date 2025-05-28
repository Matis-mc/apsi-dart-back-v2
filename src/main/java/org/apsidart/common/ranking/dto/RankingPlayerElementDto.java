package org.apsidart.common.ranking.dto;

public record RankingPlayerElementDto(
    Long idPlayer,
    String name,
    String lastName,
    String pseudo,
    Integer elo,
    Integer nbVictory,
    Integer nbGame
) {
    
}
