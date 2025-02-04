package org.apsidart.dart.game.dto;

import java.time.LocalDate;
import java.util.List;

import org.apsidart.dart.performance.dto.DartPerformanceDto;

public class DartGameDto {

    private Long id;
    private String statut;
    private String typeGame;
    private LocalDate date;
    private List<DartPerformanceDto> performances;

    public DartGameDto() {
    }

    public DartGameDto( String statut, String typeGame, LocalDate date) {
        this.statut = statut;
        this.typeGame = typeGame;
        this.date = date;
    }

    

    public DartGameDto(Long id, String statut, String typeGame, LocalDate date) {
        this.id = id;
        this.statut = statut;
        this.typeGame = typeGame;
        this.date = date;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getStatut() {
        return statut;
    }
    public void setStatut(String statut) {
        this.statut = statut;
    }
    public String getTypeGame() {
        return typeGame;
    }
    public void setTypeGame(String typeGame) {
        this.typeGame = typeGame;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public List<DartPerformanceDto> getPerformances() {
        return performances;
    }
    public void setPerformances(List<DartPerformanceDto> performances) {
        this.performances = performances;
    }

    @Override
    public String toString() {
        return "DartGameDto [id=" + id + ", statut=" + statut + ", typeGame=" + typeGame + ", date=" + date
                + ", performances=" + performances + "]";
    }

    


    
    
}
