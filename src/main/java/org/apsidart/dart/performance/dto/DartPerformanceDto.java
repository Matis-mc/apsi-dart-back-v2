package org.apsidart.dart.performance.dto;

import java.util.LinkedList;

public class DartPerformanceDto {

    private Long idPlayer;

    private Long idGame;

    private LinkedList<Integer> historiquePosition;    

    private Integer score;

    private Integer nombreTour;

    private LinkedList<String> volees;

    public DartPerformanceDto() {
    }

    public DartPerformanceDto(Long idPlayer, Long idGame, LinkedList<Integer> historiquePosition, Integer score,
            Integer nombreTour, LinkedList<String> volees) {
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

    public LinkedList<Integer> getHistoriquePosition() {
        return historiquePosition;
    }

    public void setHistoriquePosition(LinkedList<Integer> historiquePosition) {
        this.historiquePosition = historiquePosition;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getNombreTour() {
        return nombreTour;
    }

    public void setNombreTour(Integer nombreTour) {
        this.nombreTour = nombreTour;
    }

    public LinkedList<String> getVolees() {
        return volees;
    }

    public void setVolees(LinkedList<String> volees) {
        this.volees = volees;
    }

    
    
}
