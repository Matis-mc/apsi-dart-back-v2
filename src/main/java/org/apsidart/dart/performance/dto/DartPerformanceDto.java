package org.apsidart.dart.performance.dto;

import java.util.List;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

public class DartPerformanceDto {

    @NotNull
    private Long idPlayer;

    @NotNull
    private Long idGame;

    @Nullable
    private Double elo;

    private List<Integer> historiquePosition;    

    @NotNull
    private List<Integer> score;

    @NotNull
    private Integer nombreTour;

    private List<String> volees;

    public DartPerformanceDto() {
    }

    public DartPerformanceDto(Long idPlayer, Long idGame, List<Integer> historiquePosition, List<Integer> score,
            Integer nombreTour, List<String> volees) {
        this.idPlayer = idPlayer;
        this.idGame = idGame;
        this.historiquePosition = historiquePosition;
        this.score = score;
        this.nombreTour = nombreTour;
        this.volees = volees;
    }

    public Long getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(Long idPlayer) {
        this.idPlayer = idPlayer;
    }

    public Long getIdGame() {
        return idGame;
    }

    public void setIdGame(Long idGame) {
        this.idGame = idGame;
    }

    public List<Integer> getHistoriquePosition() {
        return historiquePosition;
    }

    public Integer getLastPosition(){
        return historiquePosition.get(historiquePosition.size()-1);
    }

    public void setHistoriquePosition(List<Integer> historiquePosition) {
        this.historiquePosition = historiquePosition;
    }

    public List<Integer> getScore() {
        return score;
    }

    public void setScore(List<Integer> score) {
        this.score = score;
    }

    public Integer getNombreTour() {
        return nombreTour;
    }

    public void setNombreTour(Integer nombreTour) {
        this.nombreTour = nombreTour;
    }

    public List<String> getVolees() {
        return volees;
    }

    public void setVolees(List<String> volees) {
        this.volees = volees;
    }

    public Double getElo() {
        return elo;
    }

    public void setElo(Double elo) {
        this.elo = elo;
    }

    @Override
    public String toString() {
        return "DartPerformanceDto [idPlayer=" + idPlayer + ", idGame=" + idGame + ", elo=" + elo
                + ", historiquePosition=" + historiquePosition + ", score=" + score + ", nombreTour=" + nombreTour
                + ", volees=" + volees + "]";
    }
    
}
