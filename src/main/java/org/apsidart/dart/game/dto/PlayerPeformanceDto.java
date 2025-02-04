package org.apsidart.dart.game.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PlayerPeformanceDto {
    @NotNull
    Long idPlayer;
    
    @NotBlank
    String pseudo;

    @NotNull
    Integer Score;

    @NotNull
    Integer position;
    
    @NotBlank
    String volee;
    
    @NotNull
    Integer numeroTour;

    public Long getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(Long idPlayer) {
        this.idPlayer = idPlayer;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public Integer getScore() {
        return Score;
    }

    public void setScore(Integer score) {
        Score = score;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getVolee() {
        return volee;
    }

    public void setVolee(String volee) {
        this.volee = volee;
    }

    public Integer getNumeroTour() {
        return numeroTour;
    }

    public void setNumeroTour(Integer numeroTour) {
        this.numeroTour = numeroTour;
    }

    @Override
    public String toString() {
        return "PlayerPeformanceDto [idPlayer=" + idPlayer + ", pseudo=" + pseudo + ", Score=" + Score + ", position="
                + position + ", volee=" + volee + ", numeroTour=" + numeroTour + "]";
    }

    
    

}
