package org.apsidart.dart.stat.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * La reprsentation d'une statisqtiue qui somme des valeurs
 */
@Entity
@Table(name = "somme_stat")
public class SumStat {

    @Id @GeneratedValue 
    private Long id;
    private double value;
    private String label;
    public Long getId() {
        return id;
    }

    public double getValue() {
        return value;
    }
    public void setValue(double value) {
        this.value = value;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public SumStat(double value, String label) {
        this.value = value;
        this.label = label;
    }

    public SumStat() {
    }

    
}
