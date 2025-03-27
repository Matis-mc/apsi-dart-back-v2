package org.apsidart.player.organisation.dto;

import java.time.LocalDate;
import java.util.List;

import org.apsidart.player.player.dto.PlayerDto;


public record OrganisationDto (
    Long id,
    String libelle,
    LocalDate dateCreation,
    List<PlayerDto> players
) {
    
}
