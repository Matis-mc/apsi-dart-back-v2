package org.apsidart.dart.game.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apsidart.dart.game.dto.DartGameCreationDto;
import org.apsidart.dart.game.dto.DartGameDto;
import org.apsidart.dart.game.entity.DartGameEntity;

public class DartGameMapper {

    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static DartGameEntity dtoToEntity(DartGameCreationDto dto){
        return new DartGameEntity(dto.getTypeGame().name(), LocalDate.parse(dto.getDateCreation(), dateTimeFormatter));
    }
    
    public static DartGameDto entityTodto(DartGameEntity entity){
        return new DartGameDto(entity.getId(), entity.getStatut(), entity.getType(), entity.getDate());
    }
}
