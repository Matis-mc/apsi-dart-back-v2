package org.apsidart.dart.game.dto;

import java.time.LocalDate;
import java.util.List;

public record DartGameDetailDto(
    Long id,
    String statut,
    String typeGame,
    LocalDate date,
    List<DartPlayerDto> dartPlayers
) {
    
}
