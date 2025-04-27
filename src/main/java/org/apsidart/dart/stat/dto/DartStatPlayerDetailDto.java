package org.apsidart.dart.stat.dto;

import java.util.ArrayList;
import java.util.List;

public class DartStatPlayerDetailDto {
    
    private String typeGame;

    private List<Integer> eloScore;

    private Long idPlayer;

    private List<Double> avgPosition;

    private List<Double> avgPoints;

    private Double nbGame;

    private Double nbVictory;

    public DartStatPlayerDetailDto(String typeJeu, List<Integer> eloScore, Long idPlayer,
            List<Double> avgPosition, List<Double> avgPoints, 
                        Double nbGame, Double nbVictoire) {
        this.typeGame = typeJeu;
        this.eloScore = eloScore;
        this.idPlayer = idPlayer;
        this.avgPosition = avgPosition;
        this.avgPoints = avgPoints;
        this.nbGame = nbGame;
        this.nbVictory = nbVictoire;
    }

    public DartStatPlayerDetailDto(String typeJeu, Long idPlayer, Double nbGame, Double nbVictoire) {
        this.typeGame = typeJeu;
        this.idPlayer = idPlayer;
        this.nbGame = nbGame;
        this.nbVictory = nbVictoire;
        this.eloScore = new ArrayList<>();
        this.avgPosition = new ArrayList<>();
        this.avgPoints = new ArrayList<>();
    }

    public DartStatPlayerDetailDto() {
        this.eloScore = new ArrayList<>();
        this.avgPosition = new ArrayList<>();
        this.avgPoints = new ArrayList<>();
    }

    public String getTypeGame() {
        return typeGame;
    }

    public void setTypeGame(String typeJeu) {
        this.typeGame = typeJeu;
    }

    public List<Integer> getEloScore() {
        return eloScore;
    }

    public void setEloScore(List<Integer> eloScore) {
        this.eloScore = eloScore;
    }

    public void addEloScore(Integer eloScore){
        this.eloScore.add(eloScore);
    }

    public Long getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(Long idPlayer) {
        this.idPlayer = idPlayer;

    }

    public List<Double> getAvgPosition() {
        return avgPosition;
    }

    public void setAvgPosition(List<Double> avgPosition) {
        this.avgPosition = avgPosition;
    }

    public void addAvgPosition(Double avgPosition){
        this.avgPosition.add(avgPosition);
    }

    public List<Double> getAvgPoints() {
        return avgPoints;
    }

    public void setAvgPoints(List<Double> avgPoints) {
        this.avgPoints = avgPoints;
    }

    public void addAvgPoint(Double avgPoint){
        this.avgPoints.add(avgPoint);
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

    @Override
    public String toString() {
        return new StringBuilder("DartStatPlayerDetailDto [typeJeu=").append(typeGame)
        .append(", eloScore=").append(eloScore)
        .append(", idPlayer=").append(idPlayer)
        .append(", avgPosition=").append(avgPosition)
        .append(", avgPoints=").append(avgPoints)
        .append(", nbGame=").append(nbGame)
        .append(", nbVictoire=").append(nbVictory)
        .append("]")
        .toString();
    }
}
