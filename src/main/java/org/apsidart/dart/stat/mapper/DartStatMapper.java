package org.apsidart.dart.stat.mapper;

import org.apsidart.common.ListUtils;
import org.apsidart.dart.stat.dto.DartRankingPlayerElementDto;
import org.apsidart.dart.stat.dto.DartStatPlayerDetailDto;
import org.apsidart.dart.stat.dto.DartStatPlayerDto;
import org.apsidart.dart.stat.entity.AvgStat;
import org.apsidart.dart.stat.entity.StatPlayerEntity;
import org.apsidart.dart.stat.entity.TmstpStat;
import org.apsidart.player.player.dto.PlayerDto;

public class DartStatMapper {

    public static DartStatPlayerDto entityToDto(StatPlayerEntity entity){

        return new DartStatPlayerDto(
            entity.getId(), 
            entity.getTypeJeu(), 
            ListUtils.getLastElement(entity.getEloScores()).getValue(), 
            entity.getIdPlayer(), 
            entity.getDate(), 
            ListUtils.getLastElement(entity.getAvgPosition()).getValue(), 
            ListUtils.getLastElement(entity.getAvgPoints()).getValue(), 
            entity.getNbGame().getValue(), 
            entity.getNbVictoire().getValue());

    }

    public static DartStatPlayerDetailDto mapEntityToDtoDetail(StatPlayerEntity entity){

        return new DartStatPlayerDetailDto(
            entity.getTypeJeu(),
            entity.getEloScores().stream().map(TmstpStat::getValue).map(d -> d.intValue()).toList(),
            entity.getIdPlayer(),
            entity.getAvgPosition().stream().map(AvgStat::getValue).toList(),
            entity.getAvgPoints().stream().map(AvgStat::getValue).toList(),
            entity.getNbGame().getValue(),
            entity.getNbVictoire().getValue());
        }

    public static DartRankingPlayerElementDto toClassement(PlayerDto playerDto, StatPlayerEntity statEntity){
        return new DartRankingPlayerElementDto(
            playerDto.id(),
            playerDto.firstName(),
            playerDto.name(),
            playerDto.pseudo(),
            ListUtils.getLastElement(statEntity.getEloScores()).getValue().intValue(),
            statEntity.getNbVictoire().getValueAsInt(), 
            statEntity.getNbGame().getValueAsInt());
    }
}
