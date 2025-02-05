package org.apsidart.dart.game.dto;

import java.util.List;

public class DartGameRoundDto {
    private Long idGame;
    private Integer numberRound;
    private List<PlayerPeformanceDto> performances;

    public DartGameRoundDto(Long idJeu, Integer numeroTour, List<PlayerPeformanceDto> performances) {
        this.idGame = idJeu;
        this.numberRound = numeroTour;
        this.performances = performances;
    }
    public Long getIdGame() {
        return idGame;
    }
    public void setIdGame(Long idJeu) {
        this.idGame = idJeu;
    }
    public Integer getNumberRound() {
        return numberRound;
    }
    public void setNumberRound(Integer numeroTour) {
        this.numberRound = numeroTour;
    }
    public List<PlayerPeformanceDto> getPerformances() {
        return performances;
    }
    public void setPerformances(List<PlayerPeformanceDto> performances) {
        this.performances = performances;
    }
    @Override
    public String toString() {
        return "DartGameTourDto [idJeu=" + idGame + ", numeroTour=" + numberRound + ", performances=" + performances
                + "]";
    }
    
    

    
}
