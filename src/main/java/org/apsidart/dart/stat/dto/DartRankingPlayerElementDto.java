package org.apsidart.dart.stat.dto;

public record DartRankingPlayerElementDto(
    Long idPlayer,
    String name,
    String lastName,
    String pseudo,
    Integer elo,
    Integer nbVictoty,
    Integer nbGame
) {
    
}
