package org.apsidart.dart.performance;

import java.util.ArrayList;
import java.util.List;

import org.apsidart.dart.game.dto.PlayerPeformanceDto;
import org.apsidart.dart.performance.dto.DartPerformanceDto;
import org.apsidart.dart.performance.entity.DartPerformanceEntity;
import org.apsidart.dart.performance.mapper.DartPerformanceMapper;
import org.apsidart.player.PlayerService;
import org.apsidart.player.dto.PlayerDto;
import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import static org.apsidart.common.utils.CollectionUtils.replaceLastElement;


@ApplicationScoped
public class DartPerformanceService {

    @Inject
    DartPerformanceRepository repository;

    @Inject
    PlayerService playerService;

    private static final Logger LOG = Logger.getLogger(DartPerformanceService.class);

    @Transactional
    public void createPerformanceForGame(Long idGame, List<PlayerDto> players){
        players.forEach(p -> createPerformanceForPlayer(idGame, p.id(), players.indexOf(p) + 1));
    }

    @Transactional
    public void enregistrePerformanceForPlayer(PlayerPeformanceDto dto, Long idGame){
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
            replaceLastElement(entity.getHistPosition(), dto.getPosition());
            replaceLastElement(entity.getHistScore(), dto.getScore());
            replaceLastElement(entity.getVolleys(), dto.getVolley());
        }
        repository.persistAndFlush(entity);
        LOG.info("[SUCCESS] Tour pris en compte pour le joueur " + dto.getPseudo());
    }

    public void endGameForPlayer(PlayerPeformanceDto dto, Long idGame){
        this.enregistrePerformanceForPlayer(dto, idGame);
        // todo : calculer stat
    }

    private boolean isNewTour(DartPerformanceEntity entity, PlayerPeformanceDto dto){
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
                                                        new ArrayList<Integer>(position),
                                                        new ArrayList<Integer>(0),
                                                        0,
                                                        new ArrayList<>());
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
