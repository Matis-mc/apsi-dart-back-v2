package org.apsidart.dart.game.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dart_game")
public class DartGameEntity {

    @Id @GeneratedValue 
    private Long id;  
    
    private String type;

    private String statut;

    private LocalDate date;

    public DartGameEntity(String type, LocalDate date) {
        this.type = type;
        this.statut = statut;
        this.date = date;
    }

    public DartGameEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    
    
}
