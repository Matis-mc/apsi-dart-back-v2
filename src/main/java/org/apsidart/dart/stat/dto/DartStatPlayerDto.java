package org.apsidart.dart.stat.dto;

import java.sql.Timestamp;

public class DartStatPlayerDto {
    
    private Long id;  

    private String typeGame;

    private Double eloScore;

    private Long idPlayer;

    private Timestamp date;

    private Double avgPosition;

    private Double avgPoints;


    private Double nbGame;

    private Double nbVictory;

    public DartStatPlayerDto(Long id, String typeGame, Double eloScore, Long idPlayer, Timestamp date,
            Double avgPosition, Double avgPoints, Double nbGame, Double nbVictoire) {
        this.id = id;
        this.typeGame = typeGame;
        this.eloScore = eloScore;
        this.idPlayer = idPlayer;
        this.date = date;
        this.avgPosition = avgPosition;
        this.avgPoints = avgPoints;
        this.nbGame = nbGame;
        this.nbVictory = nbVictoire;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeGame() {
        return typeGame;
    }

    public void setTypeGame(String typeJeu) {
        this.typeGame = typeJeu;
    }

    public Double getEloScore() {
        return eloScore;
    }

    public void setEloScore(Double eloScore) {
        this.eloScore = eloScore;
    }

    public Long getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(Long idPlayer) {
        this.idPlayer = idPlayer;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Double getAvgPosition() {
        return avgPosition;
    }

    public void setAvgPosition(Double avgPosition) {
        this.avgPosition = avgPosition;
    }

    public Double getAvgPoints() {
        return avgPoints;
    }

    public void setAvgPoints(Double avgPoints) {
        this.avgPoints = avgPoints;
    }

    public Double getNbGame() {
        return nbGame;
    }

    public void setNbGame(Double nbGame) {
        this.nbGame = nbGame;
    }

    public Double getNbVictory() {
        return nbVictory;
    }

    public void setNbVictory(Double nbVictoire) {
        this.nbVictory = nbVictoire;
    }
}
