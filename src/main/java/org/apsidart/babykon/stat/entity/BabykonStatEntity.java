package org.apsidart.babykon.stat.entity;

import org.apsidart.player.dto.PlayerDto;
import org.apsidart.player.entity.PlayerEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "babykon_player_stat")
public class BabykonStatEntity {

    @Id @GeneratedValue 
    private Long id;  

    @OneToOne
    PlayerEntity player;

    private double eloScore;

    private int nbGame;

    private int nbVictoire;

    private double maxElo;
    
    private int nbGamelle;

    public BabykonStatEntity(){};

    public BabykonStatEntity(PlayerEntity player, double eloScore, int nbGame, int nbVictoire, double maxElo,
            int nbGamelle) {
        this.player = player;
        this.eloScore = eloScore;
        this.nbGame = nbGame;
        this.nbVictoire = nbVictoire;
        this.maxElo = maxElo;
        this.nbGamelle = nbGamelle;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public void setPlayerDto(PlayerEntity player) {
        this.player = player;
    }

    public double getEloScore() {
        return eloScore;
    }

    public void setEloScore(double eloScore) {
        this.eloScore = eloScore;
    }

    public int getNbGame() {
        return nbGame;
    }

    public void setNbGame(int nbGame) {
        this.nbGame = nbGame;
    }

    public int getNbVictoire() {
        return nbVictoire;
    }

    public void setNbVictoire(int nbVictoire) {
        this.nbVictoire = nbVictoire;
    }

    public double getMaxElo() {
        return maxElo;
    }

    public void setMaxElo(double maxElo) {
        this.maxElo = maxElo;
    }

    public int getNbGamelle() {
        return nbGamelle;
    }

    public void setNbGamelle(int nbGamelle) {
        this.nbGamelle = nbGamelle;
    }

    @Override
    public String toString() {
        return "BabykonStatEntity [player=" + player + ", eloScore=" + eloScore + ", nbGame=" + nbGame + ", nbVictoire="
                + nbVictoire + ", maxElo=" + maxElo + "]";
    }



    
}
