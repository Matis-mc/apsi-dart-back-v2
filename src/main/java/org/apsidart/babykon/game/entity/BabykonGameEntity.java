package org.apsidart.babykon.game.entity;

import java.time.LocalDate;

import org.apsidart.player.entity.PlayerEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "babykon_game")
public class BabykonGameEntity {

    @Id @GeneratedValue 
    private Long id;  

    private LocalDate date;

    @ManyToOne
    private PlayerEntity winner;

    @ManyToOne
    private PlayerEntity loser;

    private double[] score;

    public BabykonGameEntity(){};

    public BabykonGameEntity(LocalDate date, PlayerEntity winner, PlayerEntity loser, double[] score) {
        this.date = date;
        this.winner = winner;
        this.loser = loser;
        this.score = score;
    }
    
}
