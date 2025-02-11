package org.apsidart.dart.stat.dto;

import java.util.List;

public record DartRankingPlayerDto (
    String date,
    List<DartRankingPlayerElementDto> classements){
}
