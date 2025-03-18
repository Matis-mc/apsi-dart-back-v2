package org.apsidart.dart.stat.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "average_stat")
public class AvgStat {

    @Id @GeneratedValue 
    private Long id;
    private double poid;
    private double value;
    private String label;

    public Long getId() {
        return id;
    }

    public double getPoid() {
        return poid;
    }

    public void setPoid(double poid) {
        this.poid = poid;
    }

    public double getValue() {
        return value;
    }

    public int getValueAsInt() {
        return Double.valueOf(this.value).intValue();
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

    public AvgStat(double value, String label) {
        this.value = value;
        this.label = label;
    }

    public AvgStat(double poid, double value, String label) {
        this.poid = poid;
        this.value = value;
        this.label = label;
    }

    public AvgStat() {
    }
}
