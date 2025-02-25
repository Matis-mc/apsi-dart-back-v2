package org.apsidart.dart.performance.entity;

import java.util.LinkedList;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*
 * La représentation de la pefromance d'un joueur lors de sa participation à une partie
 */

@Entity
@Table(name = "dart_performance")
public class DartPerformanceEntity {

    @Id @GeneratedValue 
    private Long id; 
    
    private Long idPlayer;

    private Long idGame;

    private LinkedList<Integer> histPosition;    

    private LinkedList<Integer> histScore;

    private Integer nombreTour;

    private LinkedList<String> volleys;

    public DartPerformanceEntity() {
    }

    public DartPerformanceEntity(Long idPlayer, Long idGame, LinkedList<Integer> historiquePosition,
    LinkedList<Integer> histScore, Integer nombreTour, LinkedList<String> volees) {
        this.idPlayer = idPlayer;
        this.idGame = idGame;
        this.histPosition = historiquePosition;
        this.histScore = histScore;
        this.nombreTour = nombreTour;
        this.volleys = volees;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LinkedList<Integer> getHistPosition() {
        return histPosition;
    }

    public void setHistPosition(LinkedList<Integer> historiquePosition) {
        this.histPosition = historiquePosition;
    }

    public void addPosition(Integer position){
        this.histPosition.add(position);
    }

    public LinkedList<Integer> getHistScore() {
        return histScore;
    }

    public void setHistScore(LinkedList<Integer> score) {
        this.histScore = score;
    }

    public void addScore(Integer score){
        this.histScore.add(score);
    }

    public Integer getNombreTour() {
        return nombreTour;
    }

    public void setNombreTour(Integer nombreTour) {
        this.nombreTour = nombreTour;
    }

    public LinkedList<String> getVolleys() {
        return volleys;
    }

    public void setVolleys(LinkedList<String> volleys) {
        this.volleys = volleys;
    }

    public void addVolley(String volley){
        this.volleys.add(volley);
    }
    
}
