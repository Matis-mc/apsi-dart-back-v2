package org.apsidart.dart.stat.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apsidart.player.dto.PlayerDto;


public class DartStatGameDto {

    List<PlayerDto> players;

    Map<Long, List<Integer>> evolutionScore;

    Map<Long, List<Integer>> evolutionPosition;

    public List<PlayerDto> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerDto> players) {
        this.players = players;
    }

    public void addPlayer(PlayerDto playerDto){
        players.add(playerDto);
    }

    public Map<Long, List<Integer>> getEvolutionScore() {
        return evolutionScore;
    }

    public void setEvolutionScore(Map<Long, List<Integer>> evolutionScore) {
        this.evolutionScore = evolutionScore;
    }

    public Map<Long, List<Integer>> getEvolutionPosition() {
        return evolutionPosition;
    }

    public void setEvolutionPosition(Map<Long, List<Integer>> evolutionPosition) {
        this.evolutionPosition = evolutionPosition;
    }

    public void addScore(Long idPlayer, List<Integer> scores){
        this.evolutionScore.put(idPlayer, scores);
    }


    public void addPosition(Long idPlayer, List<Integer> position){
        this.evolutionPosition.put(idPlayer, position);
    }

    public DartStatGameDto() {
        this.players = new ArrayList<>();
        this.evolutionPosition = new HashMap<>();
        this.evolutionScore = new HashMap<>();
    }    

    
    
}
