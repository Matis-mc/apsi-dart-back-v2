package org.apsidart.dart.game.dto;

import java.util.List;

import org.apsidart.dart.game.enumeration.CodeTypGameEnum;
import org.apsidart.player.dto.PlayerDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class DartGameCreationDto {

    CodeTypGameEnum typeGame;

    @NotBlank
    String dateCreation;

    @NotEmpty
    List<PlayerDto> players;

    public DartGameCreationDto(@NotBlank CodeTypGameEnum typeGame, @NotBlank String dateCreation,
            @NotEmpty List<PlayerDto> players) {
        this.typeGame = typeGame;
        this.dateCreation = dateCreation;
        this.players = players;
    }

    public CodeTypGameEnum getTypeGame() {
        return typeGame;
    }

    public void setTypeGame(CodeTypGameEnum typeGame) {
        this.typeGame = typeGame;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public List<PlayerDto> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerDto> players) {
        this.players = players;
    }

    


    
}
