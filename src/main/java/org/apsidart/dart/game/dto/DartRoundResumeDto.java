package org.apsidart.dart.game.dto;

import java.util.Map;

public class DartRoundResumeDto {

    private Integer numberRound;
    private Map<Long, String> playerVolleys;

    public DartRoundResumeDto(Integer numberRound, Map<Long, String> playerVolleys) {
        this.numberRound = numberRound;
        this.playerVolleys = playerVolleys;
    }

    public Integer getNumberRound() {
        return numberRound;
    }

    public void setNumberRound(Integer numberRound) {
        this.numberRound = numberRound;
    }
    
    public Map<Long, String> getPlayerVolleys() {
        return playerVolleys;
    }

    public void setPlayerVolleys(Map<Long, String> playerVolleys) {
        this.playerVolleys = playerVolleys;
    }
}
