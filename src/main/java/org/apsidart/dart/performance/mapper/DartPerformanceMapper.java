package org.apsidart.dart.performance.mapper;

import org.apsidart.dart.performance.dto.DartPerformanceDto;
import org.apsidart.dart.performance.entity.DartPerformanceEntity;

public class DartPerformanceMapper {

    public static DartPerformanceDto entityToDto(DartPerformanceEntity entity){
        return new DartPerformanceDto(  entity.getIdPlayer(),
                                        entity.getIdGame(),
                                        entity.getHistoriquePosition(),
                                        entity.getScore(),
                                        entity.getNombreTour(), 
                                        entity.getVolees());}
    
}
