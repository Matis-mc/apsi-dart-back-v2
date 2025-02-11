package org.apsidart.dart.stat.mapper;

import org.apsidart.dart.stat.dto.DartRankingPlayerElementDto;
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

    public static DartRankingPlayerElementDto toClassement(PlayerDto playerDto, DartStatPlayerEntity statEntity){
        return new DartRankingPlayerElementDto(
            playerDto.id(),
            playerDto.firstName(),
            playerDto.name(),
            playerDto.pseudo(),
            statEntity.getEloScore().intValue(),
            (int) statEntity.getNbVictoire().getValue(), 
            (int) statEntity.getNbGame().getValue());
    }
    
}
