package org.apsidart.dart.stat.entity;

import java.time.LocalDate;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "timestamp_stat")
public class TmstpStat {

    @Id @GeneratedValue
    private Long id; 

    private String typeGame;

    private String type;

    private Double value;

    private LocalDate date;

    public TmstpStat(String typeGame, String type, Double value, LocalDate date) {
        this.typeGame = typeGame;
        this.type = type;
        this.value = value;
        this.date = date;
    }

    public TmstpStat(){}

    public Long getId() {
        return id;
    }

    public String getTypeGame() {
        return typeGame;
    }

    public String getType() {
        return type;
    }

    public Double getValue() {
        return value;
    }

    public LocalDate getDate() {
        return date;
    };

}
