package org.apsidart.player.player.dto;

import jakarta.validation.constraints.NotBlank;

public record PlayerDto(

    Long id,

    @NotBlank
    String firstName,

    @NotBlank
    String name,

    @NotBlank
    String pseudo) {
    
}
