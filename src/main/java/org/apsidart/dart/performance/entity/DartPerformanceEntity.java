package org.apsidart.dart.performance.entity;

import java.util.List;

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

    private List<Integer> historiquePosition;    

    private Integer score;

    private Integer nombreTour;

    private List<String> volees;

    public DartPerformanceEntity() {
    }

    public DartPerformanceEntity(Long idPlayer, Long idGame, List<Integer> historiquePosition,
            Integer score, Integer nombreTour, List<String> volees) {
        this.idPlayer = idPlayer;
        this.idGame = idGame;
        this.historiquePosition = historiquePosition;
        this.score = score;
        this.nombreTour = nombreTour;
        this.volees = volees;
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

    public List<Integer> getHistoriquePosition() {
        return historiquePosition;
    }

    public void setHistoriquePosition(List<Integer> historiquePosition) {
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

    public List<String> getVolees() {
        return volees;
    }

    public void setVolees(List<String> volees) {
        this.volees = volees;
    }

    

    
}
