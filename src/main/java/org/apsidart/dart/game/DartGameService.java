package org.apsidart.dart.game;

import java.util.List;

import org.apsidart.dart.game.dto.DartGameCreationDto;
import org.apsidart.dart.game.dto.DartGameDto;
import org.apsidart.dart.game.dto.DartGameTourDto;
import org.apsidart.dart.game.entity.DartGameEntity;
import org.apsidart.dart.game.enumeration.StatutGameEnum;
import org.apsidart.dart.game.mapper.DartGameMapper;
import org.apsidart.dart.performance.DartPerformanceService;
import org.apsidart.dart.performance.dto.DartPerformanceDto;
import org.apsidart.player.PlayerService;
import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class DartGameService {

    @Inject
    DartGameRepository repository;

    @Inject
    DartPerformanceService performanceService;

    private static final Logger LOG = Logger.getLogger(DartGameService.class);

    @Transactional
    public Long createGame(DartGameCreationDto dto){

        LOG.info("[START] Creation d'une partie");
        DartGameEntity gameEntity = DartGameMapper.dtoToEntity(dto);
        gameEntity.setStatut(StatutGameEnum.IN_PROGRESS.libelle);
        repository.persistAndFlush(gameEntity);
        LOG.info("[SUCCESS] Creation d'une partie");
        LOG.info("[START] Initialisation des performances de chaques joueurs de la partie " + gameEntity.getId());
        performanceService.createPerformanceForGame(gameEntity.getId(), dto.getPlayers());
        LOG.info("[SUCCESS] Initialisation des performances de chaques joueurs");
        return gameEntity.getId();

    }

    /**
     * On récupère la liste des parties. Les performances des joueurs ne sont pas chargés pour chaque partie, il faut rappeler l'endpoint individuel pour les avoirs.
     */
    public List<DartGameDto> getAllGame(){
        return repository.findAll()
            .stream()
            .map(entity -> DartGameMapper.entityTodto(entity))
            .toList();
        
    }

    /*
     * On récupère une partie et les performances associées pour tous les joueurs.
     */
    public DartGameDto getGameById(Long idGame){
        LOG.info("[START] Récupération d'une partie");
        DartGameDto dartGameDto = repository.findByIdOptional(idGame)
            .map(entity -> DartGameMapper.entityTodto(entity))
            .orElseThrow(() -> new NotFoundException());
        LOG.info("[Success] Récupération d'une partie : " + dartGameDto.toString());
        LOG.info("[START] Récupération des performances de chaque joueur");
        List<DartPerformanceDto> performances = performanceService.getPerformanceByIdGame(idGame);
        dartGameDto.setPerformances(performances);
        LOG.info("[SUCCESS] Récupération des performances de chaque joueur");
        return dartGameDto;
        
    }

    public String performOnGame(DartGameTourDto dto){
        LOG.info("[START] Enregistrement d'un tour avec payload : " + dto.toString());
        dto.getPerformances().forEach(p -> performanceService.enregistrePerformanceForPlayer(p, dto.getIdJeu()));
        LOG.info("[SUCCESS] Enregistrement d'un tour");
        return "ça me coupe la chique";
    }
    
}
