package org.apsidart.player.dto;

import jakarta.validation.constraints.NotBlank;

public record PlayerDto(

    Long id,

    @NotBlank
    String firstName,

    @NotBlank
    String lastName,

    @NotBlank
    String pseudo) {

    
    
}
