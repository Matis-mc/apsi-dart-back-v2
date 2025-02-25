package org.apsidart.dart.stat.entity;

import java.sql.Timestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Cette entité représente les statistiques relatives au jeu de fléchette pour un joueur
 */
@Entity
@Table(name = "dart_stat_player")
public class DartStatPlayerEntity {

    @Id @GeneratedValue 
    private Long id;  

    private String typeJeu;

    private Double eloScore;

    private Long idPlayer;

    private Timestamp date;

    @OneToOne(cascade = CascadeType.ALL)
    private AvgStat avgPosition;

    @OneToOne(cascade = CascadeType.ALL)
    private AvgStat avgPoints;

    @OneToOne(cascade = CascadeType.ALL)
    private PctStat pctVictoire;

    @OneToOne(cascade = CascadeType.ALL)
    private AvgStat avgNbDartCompleted;

    @OneToOne(cascade = CascadeType.ALL)
    private SumStat nbGame;

    @OneToOne(cascade = CascadeType.ALL)
    private SumStat nbVictoire;

    public DartStatPlayerEntity(String typeJeu, Double eloScore, Long idPlayer,
            Timestamp date, AvgStat avgPosition, AvgStat avgPoints, PctStat pctVictoire, AvgStat avgNbDartCompleted,
            SumStat nbGame, SumStat nbVictoire) {
        this.typeJeu = typeJeu;
        this.eloScore = eloScore;
        this.idPlayer = idPlayer;
        this.date = date;
        this.avgPosition = avgPosition;
        this.avgPoints = avgPoints;
        this.pctVictoire = pctVictoire;
        this.avgNbDartCompleted = avgNbDartCompleted;
        this.nbGame = nbGame;
        this.nbVictoire = nbVictoire;
    }

    public DartStatPlayerEntity(Long id, String typeJeu, Double eloScore, Long idPlayer,
            Timestamp date, AvgStat avgPosition, AvgStat avgPoints, PctStat pctVictoire, AvgStat avgNbDartCompleted,
            SumStat nbGame, SumStat nbVictoire) {
        this.id = id;
        this.typeJeu = typeJeu;
        this.eloScore = eloScore;
        this.idPlayer = idPlayer;
        this.date = date;
        this.avgPosition = avgPosition;
        this.avgPoints = avgPoints;
        this.pctVictoire = pctVictoire;
        this.avgNbDartCompleted = avgNbDartCompleted;
        this.nbGame = nbGame;
        this.nbVictoire = nbVictoire;
    }

    public Long getId() {
        return id;
    }

    public String getTypeJeu() {
        return typeJeu;
    }

    public void setTypeJeu(String typeJeu) {
        this.typeJeu = typeJeu;
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

    public AvgStat getAvgPosition() {
        return avgPosition;
    }

    public void setAvgPosition(AvgStat avgPosition) {
        this.avgPosition = avgPosition;
    }

    public AvgStat getAvgPoints() {
        return avgPoints;
    }

    public void setAvgPoints(AvgStat avgPoints) {
        this.avgPoints = avgPoints;
    }

    public PctStat getPctVictoire() {
        return pctVictoire;
    }

    public void setPctVictoire(PctStat pctVictoire) {
        this.pctVictoire = pctVictoire;
    }

    public AvgStat getAvgNbDartCompleted() {
        return avgNbDartCompleted;
    }

    public void setAvgNbDartCompleted(AvgStat avgNbDartCompleted) {
        this.avgNbDartCompleted = avgNbDartCompleted;
    }

    public SumStat getNbGame() {
        return nbGame;
    }

    public void setNbGame(SumStat nbGame) {
        this.nbGame = nbGame;
    }

    public SumStat getNbVictoire() {
        return nbVictoire;
    }

    public void setNbVictoire(SumStat nbVictoire) {
        this.nbVictoire = nbVictoire;
    }

    public DartStatPlayerEntity() {
    }

    @Override
    public String toString() {
        return new StringBuilder("DartStatPlayerEntity [id=").append(id)
        .append(", typeJeu=").append(typeJeu)
        .append(", eloScore=").append(eloScore)
        .append(", idPlayer=").append(idPlayer)
        .append(", date=").append(date)
        .append("]")
        .toString();
    }
}
