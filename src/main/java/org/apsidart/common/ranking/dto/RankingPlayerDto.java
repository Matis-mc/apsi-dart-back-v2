package org.apsidart.common.ranking.dto;

import java.util.List;

public record RankingPlayerDto (
    String date,
    List<RankingPlayerElementDto> classements){
}
