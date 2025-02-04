package org.apsidart.dart.game.dto;

import java.util.List;

public class DartGameTourDto {
    private Long idJeu;
    private Integer numeroTour;
    private List<PlayerPeformanceDto> performances;

    public DartGameTourDto(Long idJeu, Integer numeroTour, List<PlayerPeformanceDto> performances) {
        this.idJeu = idJeu;
        this.numeroTour = numeroTour;
        this.performances = performances;
    }
    public Long getIdJeu() {
        return idJeu;
    }
    public void setIdJeu(Long idJeu) {
        this.idJeu = idJeu;
    }
    public Integer getNumeroTour() {
        return numeroTour;
    }
    public void setNumeroTour(Integer numeroTour) {
        this.numeroTour = numeroTour;
    }
    public List<PlayerPeformanceDto> getPerformances() {
        return performances;
    }
    public void setPerformances(List<PlayerPeformanceDto> performances) {
        this.performances = performances;
    }
    @Override
    public String toString() {
        return "DartGameTourDto [idJeu=" + idJeu + ", numeroTour=" + numeroTour + ", performances=" + performances
                + "]";
    }
    
    

    
}
