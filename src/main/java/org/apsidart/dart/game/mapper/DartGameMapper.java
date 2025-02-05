package org.apsidart.dart.game.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apsidart.dart.game.dto.DartGameCreationDto;
import org.apsidart.dart.game.dto.DartGameDto;
import org.apsidart.dart.game.entity.DartGameEntity;

import static org.apsidart.common.Constants.General.DATE_FORMAT;


public class DartGameMapper {

    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public static DartGameEntity dtoToEntity(DartGameCreationDto dto){
        return new DartGameEntity(dto.getTypeGame().name(), LocalDate.parse(dto.getCreationDate(), dateTimeFormatter));
    }
    
    public static DartGameDto entityTodto(DartGameEntity entity){
        return new DartGameDto(entity.getId(), entity.getStatut(), entity.getType(), entity.getDate());
    }
}
