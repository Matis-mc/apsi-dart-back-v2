package org.apsidart.dart.game.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PlayerPeformanceDto {
    @NotNull
    Long idPlayer;
    
    @NotBlank
    String pseudo;

    @NotNull
    Integer score;

    @NotNull
    Integer position;
    
    @NotBlank
    String volley;
    
    @NotNull
    Integer numberRound;

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
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getVolley() {
        return volley;
    }

    public void setVolley(String volee) {
        this.volley = volee;
    }

    public Integer getNumberRound() {
        return numberRound;
    }

    public void setNumberRound(Integer numeroTour) {
        this.numberRound = numeroTour;
    }

    @Override
    public String toString() {
        return "PlayerPeformanceDto [idPlayer=" + idPlayer + ", pseudo=" + pseudo + ", Score=" + score + ", position="
                + position + ", volee=" + volley + ", numeroTour=" + numberRound + "]";
    }

    
    

}
