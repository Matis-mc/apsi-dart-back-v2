package org.apsidart.dart.game.dto;

import java.util.List;

import org.apsidart.dart.game.enumeration.CodeTypGameEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class DartGameCreationDto {

    private CodeTypGameEnum typeGame;

    @NotBlank
    private String creationDate;

    @NotEmpty
    private List<PlayerPerformanceDto> players;

    public DartGameCreationDto(@NotBlank CodeTypGameEnum typeGame, @NotBlank String creationDate,
            @NotEmpty List<PlayerPerformanceDto> players) {
        this.typeGame = typeGame;
        this.creationDate = creationDate;
        this.players = players;
    }

    public CodeTypGameEnum getTypeGame() {
        return typeGame;
    }

    public void setTypeGame(CodeTypGameEnum typeGame) {
        this.typeGame = typeGame;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String dateCreation) {
        this.creationDate = dateCreation;
    }

    public List<PlayerPerformanceDto> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerPerformanceDto> players) {
        this.players = players;
    }
}
