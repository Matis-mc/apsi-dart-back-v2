package org.apsidart.dart.stat.dto;

import java.sql.Timestamp;

public class DartStatPlayerDto {
    
    private Long id;  

    public String typeGame;

    public Double eloScore;

    public Long idPlayer;

    public Timestamp date;

    public Double avgPosition;

    public Double avgPoints;

    public Double pctVictory;

    public Double avgNbDartCompleted;

    public Double nbGame;

    public Double nbVictory;

    public DartStatPlayerDto(Long id, String typeGame, Double eloScore, Long idPlayer, Timestamp date,
            Double avgPosition, Double avgPoints, Double pctVictoire, Double avgNbDartCompleted, Double nbGame,
            Double nbVictoire) {
        this.id = id;
        this.typeGame = typeGame;
        this.eloScore = eloScore;
        this.idPlayer = idPlayer;
        this.date = date;
        this.avgPosition = avgPosition;
        this.avgPoints = avgPoints;
        this.pctVictory = pctVictoire;
        this.avgNbDartCompleted = avgNbDartCompleted;
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

    public Double getPctVictory() {
        return pctVictory;
    }

    public void setPctVictory(Double pctVictoire) {
        this.pctVictory = pctVictoire;
    }

    public Double getAvgNbDartCompleted() {
        return avgNbDartCompleted;
    }

    public void setAvgNbDartCompleted(Double avgNbDartCompleted) {
        this.avgNbDartCompleted = avgNbDartCompleted;
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
