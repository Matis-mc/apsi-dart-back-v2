package org.apsidart.dart.stat.mapper;

import org.apsidart.dart.stat.dto.DartStatPlayerDto;
import org.apsidart.dart.stat.entity.DartStatPlayerEntity;

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
    
}
