package org.apsidart.dart.game.mapper;

import static org.apsidart.common.Constants.General.DATE_FORMAT_DD_MM_YYYYY;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apsidart.dart.game.dto.DartGameCreationDto;
import org.apsidart.dart.game.dto.DartGameDto;
import org.apsidart.dart.game.dto.DartRoundResumeDto;
import org.apsidart.dart.game.entity.DartGameEntity;
import org.apsidart.dart.performance.dto.DartPerformanceDto;


public class DartGameMapper {

    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT_DD_MM_YYYYY);

    public static DartGameEntity dtoToEntity(DartGameCreationDto dto){
        return new DartGameEntity(dto.getTypeGame().name(), LocalDate.parse(dto.getCreationDate(), dateTimeFormatter));
    }
    
    public static DartGameDto entityTodto(DartGameEntity entity){
        return new DartGameDto(entity.getId(), entity.getStatut(), entity.getType(), entity.getDate());
    }

    /**
     * Cette méthode est exécuté pour chaque tour. 
     * A chaque performance, on ajoute la volée du tour correspondant sur la map roundResume.
     * @param performances
     * @param numberRound
     * @return
     */
    public static DartRoundResumeDto listPerformanceToResumeRound(List<DartPerformanceDto> performances, int numberRound){
        Map<Long, String> roundResume = new HashMap<Long, String>();
        performances.forEach(p -> roundResume.put(p.getIdPlayer(), p.getVolees().get(numberRound)));
        return new DartRoundResumeDto(numberRound, roundResume);
    }

}
