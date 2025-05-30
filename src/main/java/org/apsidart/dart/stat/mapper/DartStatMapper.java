package org.apsidart.dart.stat.mapper;

import org.apsidart.common.ranking.dto.RankingPlayerElementDto;
import org.apsidart.dart.stat.dto.DartStatPlayerDto;
import org.apsidart.dart.stat.entity.DartStatPlayerEntity;
import org.apsidart.player.dto.PlayerDto;

public class DartStatMapper {

    public static DartStatPlayerDto entityToDto(DartStatPlayerEntity entity){

        return new DartStatPlayerDto(
            entity.getId(), 
            entity.getTypeJeu(), 
            entity.getEloScore(), 
            entity.getIdPlayer(), 
            entity.getDate(), 
            entity.getAvgPosition().getValue(), 
            entity.getAvgPoints().getValue(), 
            entity.getPctVictoire().getValue(), 
            entity.getAvgNbDartCompleted().getValue(), 
            entity.getNbGame().getValue(), 
            entity.getNbVictoire().getValue());

    }

    public static RankingPlayerElementDto toClassement(PlayerDto playerDto, DartStatPlayerEntity statEntity){
        return new RankingPlayerElementDto(
            playerDto.id(),
            playerDto.firstName(),
            playerDto.name(),
            playerDto.pseudo(),
            statEntity.getEloScore().intValue(),
            statEntity.getNbVictoire().getValueAsInt(), 
            statEntity.getNbGame().getValueAsInt());
    }
}
