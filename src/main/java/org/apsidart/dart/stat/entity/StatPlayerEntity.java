package org.apsidart.dart.stat.entity;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import static org.apsidart.common.Constants.Stat.AVG_POINT;
import static org.apsidart.common.Constants.Stat.AVG_POSITION;
import static org.apsidart.common.Constants.Stat.TYPE_ELO;


/**
 * Cette entité représente les statistiques relatives au jeu de fléchette pour un joueur
 */
@Entity
@Table(name = "stat_player")
public class StatPlayerEntity {

    @Id @GeneratedValue 
    private Long id;  

    private String typeJeu;

    @OneToMany(cascade = CascadeType.ALL)
    private List<TmstpStat> eloScores;

    private Long idPlayer;

    private Timestamp date;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "average_stat_id")
    @JoinTable(name = "stat_player_avg_position",
        joinColumns = @JoinColumn(name = "average_stat_id"))
    private List<AvgStat> avgPosition;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "average_stat_id")
    @JoinTable(name = "stat_player_avg_points",
        joinColumns = @JoinColumn(name = "average_stat_id"))
    private List<AvgStat> avgPoints;

    @OneToOne(cascade = CascadeType.ALL)
    private SumStat nbGame;

    @OneToOne(cascade = CascadeType.ALL)
    private SumStat nbVictoire;

    public StatPlayerEntity(String typeJeu, List<TmstpStat> eloScore, Long idPlayer,
            Timestamp date, List<AvgStat> avgPosition, List<AvgStat> avgPoints,
            SumStat nbGame, SumStat nbVictoire) {
        this.typeJeu = typeJeu;
        this.eloScores = eloScore;
        this.idPlayer = idPlayer;
        this.date = date;
        this.avgPosition = avgPosition;
        this.avgPoints = avgPoints;
        this.nbGame = nbGame;
        this.nbVictoire = nbVictoire;
    }

    public StatPlayerEntity(Long id, String typeJeu, List<TmstpStat> eloScore, Long idPlayer,
            Timestamp date, List<AvgStat> avgPosition, List<AvgStat> avgPoints,
            SumStat nbGame, SumStat nbVictoire) {
        this.id = id;
        this.typeJeu = typeJeu;
        this.eloScores = eloScore;
        this.idPlayer = idPlayer;
        this.date = date;
        this.avgPosition = avgPosition;
        this.avgPoints = avgPoints;
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

    public List<TmstpStat> getEloScores() {
        return eloScores;
    }

    public void setEloScores(List<TmstpStat> eloScores) {
        this.eloScores = eloScores;
    }

    public void addEloScore(TmstpStat eloScore){
        this.eloScores.add(eloScore);
    }

    public void addEloScore(double eloScore, String typeGame){
        this.eloScores.add(new TmstpStat(typeGame, TYPE_ELO, eloScore, LocalDate.now()));
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

    public List<AvgStat> getAvgPosition() {
        return avgPosition;
    }

    public void setAvgPosition(List<AvgStat> avgPosition) {
        this.avgPosition = avgPosition;
    }

    public void addAvgPosition(double newValue){
        if( this.avgPosition.isEmpty()){
            this.avgPosition.add(new AvgStat(1d, newValue, AVG_POSITION));
        } else {
            AvgStat lastElement = this.avgPosition.get(this.avgPosition.size());
            double poid = this.avgPosition.size() + 1d;
            this.avgPosition.add(new AvgStat(
                poid,
                ((lastElement.getValue())*(poid) + newValue ) / poid,
                AVG_POSITION));
        }
    }

    public List<AvgStat> getAvgPoints() {
        return avgPoints;
    }

    public void setAvgPoints(List<AvgStat> avgPoints) {
        this.avgPoints = avgPoints;
    }

    public void addAvgPoints(double newValue){
        if( this.avgPoints.isEmpty()){
            this.avgPoints.add(new AvgStat(1d, newValue, AVG_POINT));
        } else {
            AvgStat lastElement = this.avgPoints.get(this.avgPoints.size());
            double poid = this.avgPoints.size() + 1d;
            this.avgPoints.add(new AvgStat(
                poid,
                ((lastElement.getValue())*(poid) + newValue ) / poid,
                AVG_POINT));
        }
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

    public Optional<TmstpStat> getCurrentEloScore(){
        if(Objects.isNull(eloScores) || eloScores.isEmpty()){
            return Optional.empty();
        }
        return this.eloScores.stream()
            .sorted(Comparator.comparing(TmstpStat::getDate))
            .findFirst();
    }

    public StatPlayerEntity() {
    }

    @Override
    public String toString() {
        return new StringBuilder("StatPlayerEntity [id=").append(id)
        .append(", typeJeu=").append(typeJeu)
        .append(", eloScore=").append(eloScores)
        .append(", positions=").append(avgPosition.toString())
        .append(", points=").append(avgPoints.toString())
        .append(", idPlayer=").append(idPlayer)
        .append(", date=").append(date)
        .append("]")
        .toString();
    }
}
