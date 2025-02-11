package org.apsidart.dart.stat.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DartStatDetailGameDto {

    List<DartStatPlayerDetailDto> players;

    Map<Long, LinkedList<Integer>> evolutionScore;

    Map<Long, LinkedList<Integer>> evolutionPosition;


    public void addPlayer(DartStatPlayerDetailDto dartStatPlayerDetailDto){
        players.add(dartStatPlayerDetailDto);
    }

    public Map<Long, LinkedList<Integer>> getEvolutionScore() {
        return evolutionScore;
    }

    public void setEvolutionScore(Map<Long, LinkedList<Integer>> evolutionScore) {
        this.evolutionScore = evolutionScore;
    }

    public Map<Long, LinkedList<Integer>> getEvolutionPosition() {
        return evolutionPosition;
    }

    public void setEvolutionPosition(Map<Long, LinkedList<Integer>> evolutionPosition) {
        this.evolutionPosition = evolutionPosition;
    }

    public void addScore(Long idPlayer, LinkedList<Integer> scores){
        this.evolutionScore.put(idPlayer, scores);
    }


    public void addPosition(Long idPlayer, LinkedList<Integer> position){
        this.evolutionPosition.put(idPlayer, position);
    }

    public DartStatDetailGameDto() {
        this.players = new ArrayList<>();
        this.evolutionPosition = new HashMap<>();
        this.evolutionScore = new HashMap<>();
    }

    public List<DartStatPlayerDetailDto> getPlayers() {
        return players;
    }

    public void setPlayers(List<DartStatPlayerDetailDto> players) {
        this.players = players;
    }    

    
    
}
