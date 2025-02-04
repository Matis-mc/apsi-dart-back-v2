package org.apsidart.dart.performance;

import java.util.List;

import org.apsidart.dart.performance.dto.DartPerformanceDto;
import org.apsidart.dart.performance.entity.DartPerformanceEntity;
import org.apsidart.dart.performance.mapper.DartPerformanceMapper;
import org.apsidart.player.PlayerService;
import org.apsidart.player.dto.PlayerDto;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DartPerformanceService {

    @Inject
    DartPerformanceRepository repository;

    @Inject
    PlayerService playerService;

    @Transactional
    public void createPerformanceForGame(Long idGame, List<PlayerDto> players){
        players.forEach(p -> createPerformanceForPlayer(idGame, p.id(), players.indexOf(p) + 1));
    }

    private void createPerformanceForPlayer(Long idGame, Long idPlayer, Integer position){
        
        DartPerformanceEntity performanceEntity = new DartPerformanceEntity(
                                                        idPlayer,
                                                        idGame,
                                                        List.of(position),
                                                        0,
                                                        0,
                                                        List.of());
        repository.persistAndFlush(performanceEntity);

    }

    public List<DartPerformanceDto> getPerformanceByIdGame(Long idGame){
        return repository.findByIdGame(idGame)
            .stream()
            .map(entity -> DartPerformanceMapper.entityToDto(entity))
            .toList();
    }

    public List<DartPerformanceDto> getPerformanceByIdplayer(Long idPlayer){
        return repository.findByIdPlayer(idPlayer)
            .stream()
            .map(entity -> DartPerformanceMapper.entityToDto(entity))
            .toList();
    }

    
    
}
