package org.apsidart.player.dto;

import jakarta.validation.constraints.NotBlank;

public record PlayerDto(

    @NotBlank
    String firstName,

    @NotBlank
    String lastName,

    @NotBlank
    String pseudo) {
    
}
