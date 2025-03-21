package org.apsidart.dart.performance;

import java.util.List;

import org.apsidart.dart.game.dto.PlayerPerformanceDto;
import org.apsidart.dart.performance.dto.DartPerformanceDto;
import org.apsidart.dart.performance.entity.DartPerformanceEntity;
import org.apsidart.dart.performance.mapper.DartPerformanceMapper;
import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class DartPerformanceService {

    @Inject
    private DartPerformanceRepository repository;

    private static final Logger LOG = Logger.getLogger(DartPerformanceService.class);

    @Transactional
    public void createPerformanceForGame(Long idGame, List<PlayerPerformanceDto> players){
        players.forEach(p -> createPerformanceForPlayer(idGame, p.getIdPlayer(), p.getPosition()));
    }

    @Transactional
    public void enregistrePerformanceForPlayer(PlayerPerformanceDto dto, Long idGame){
        DartPerformanceEntity entity = repository.findByIdGameAndPlayer(idGame, dto.getIdPlayer())
            .orElseThrow((() -> new NotFoundException("Aucune performance n'a été enregistrée pour ce joueur " + dto.getIdPlayer())));
        if(isNewTour(entity, dto)){
            LOG.info("[DO] Ajout d'un nouveau tour pour le joueur " + dto.getPseudo());
            entity.addPosition(dto.getPosition());
            entity.setNombreTour(entity.getNombreTour() + 1);
            entity.addScore(dto.getScore());
            entity.addVolley(dto.getVolley());
        } else {
            LOG.info("[DO] Modification du précédent tour pour le joueur " + dto.getPseudo());
            entity.getHistPosition().removeLast();
            entity.addPosition(dto.getPosition());
            entity.getHistScore().removeLast();
            entity.addScore(dto.getScore());
            entity.getVolleys().removeLast();
            entity.addVolley(dto.getVolley());
        }
        repository.persistAndFlush(entity);
        LOG.info("[SUCCESS] Tour pris en compte pour le joueur " + dto.getPseudo());
    }

    public void endGameForPlayer(PlayerPerformanceDto dto, Long idGame){
        this.enregistrePerformanceForPlayer(dto, idGame);
        // todo : calculer stat
    }

    private boolean isNewTour(DartPerformanceEntity entity, PlayerPerformanceDto dto){
        if(entity.getNombreTour() == dto.getNumberRound()){
            return false;
        } 
        if(entity.getNombreTour() > dto.getNumberRound()){
            throw new IllegalArgumentException("Le numéro du tour est incohérent");
        }
        return true;
    }

    private void createPerformanceForPlayer(Long idGame, Long idPlayer, Integer position){
        
        DartPerformanceEntity performanceEntity = new DartPerformanceEntity(
                                                        idPlayer,
                                                        idGame,
                                                        List.of(position),
                                                        List.of(),
                                                        0,
                                                        List.of());
        LOG.warn("[DEBUG] Initialisation performance " + performanceEntity.getHistPosition() + position);
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
