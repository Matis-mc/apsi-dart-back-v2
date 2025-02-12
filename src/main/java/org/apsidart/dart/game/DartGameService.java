package org.apsidart.dart.game;

import java.util.List;

import org.apsidart.common.exception.InvalidStatutGame;
import org.apsidart.dart.game.dto.CommentDto;
import org.apsidart.dart.game.dto.DartGameCreationDto;
import org.apsidart.dart.game.dto.DartGameCreationRetourDto;
import org.apsidart.dart.game.dto.DartGameDto;
import org.apsidart.dart.game.dto.DartGameRoundDto;
import org.apsidart.dart.game.entity.DartGameEntity;
import org.apsidart.dart.game.enumeration.StatutGameEnum;
import org.apsidart.dart.game.mapper.DartGameMapper;
import org.apsidart.dart.performance.DartPerformanceService;
import org.apsidart.dart.performance.dto.DartPerformanceDto;
import org.apsidart.dart.stat.DartStatEnregistrementService;
import org.apsidart.ia.IAService;
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

    @Inject
    IAService iaService;

    @Inject
    DartStatEnregistrementService dartStatEnregistrementService;

    private static final Logger LOG = Logger.getLogger(DartGameService.class);

    @Transactional
    public DartGameCreationRetourDto createGame(DartGameCreationDto dto){

        LOG.info("[START] Creation d'une partie");
        DartGameEntity gameEntity = DartGameMapper.dtoToEntity(dto);
        gameEntity.setStatut(StatutGameEnum.IN_PROGRESS.libelle);
        repository.persistAndFlush(gameEntity);
        LOG.info("[SUCCESS] Creation d'une partie");
        LOG.info("[START] Initialisation des performances de chaques joueurs de la partie " + gameEntity.getId());
        performanceService.createPerformanceForGame(gameEntity.getId(), dto.getPlayers());
        LOG.info("[SUCCESS] Initialisation des performances de chaques joueurs");
        String commentaire = iaService.getDartStartGameCommentaire(dto.getPlayers());
        return new DartGameCreationRetourDto(gameEntity.getId(), commentaire);

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

    public CommentDto performOnGame(DartGameRoundDto dto){
        LOG.info("[START] Enregistrement d'un tour avec payload : " + dto.toString());
        DartGameEntity gameEntity = repository.findById(dto.getIdGame());
        checkStatutGame(gameEntity, List.of(StatutGameEnum.IN_PROGRESS, StatutGameEnum.CREATION));
        dto.getPerformances().forEach(p -> performanceService.enregistrePerformanceForPlayer(p, dto.getIdGame()));
        LOG.info("[SUCCESS] Enregistrement d'un tour");
        return iaService.getDartRoundCommentaire(dto.getPerformances());
    }

    @Transactional
    public CommentDto endGame(DartGameRoundDto dto){

        LOG.info("[START] Enregistrement du dernier tour avec payload : " + dto.toString());
        DartGameEntity gameEntity = repository.findById(dto.getIdGame());
        checkStatutGame(gameEntity, List.of(StatutGameEnum.IN_PROGRESS));
        dto.getPerformances().forEach(p -> performanceService.endGameForPlayer(p, dto.getIdGame()));
        gameEntity.setStatut(StatutGameEnum.COMPLETED.libelle);
        repository.persist(gameEntity);
        LOG.info("[SUCCESS] Enregistrement du dernier tour." );

        List<DartPerformanceDto> performanceDtos = performanceService.getPerformanceByIdGame(dto.getIdGame());
        dartStatEnregistrementService.majPlayersStat(performanceDtos, gameEntity.getType());       

        return iaService.getDartEndGameCommentaire(dto.getPerformances());
    }

    @Transactional
    public void deleteGame(Long idGame){
        DartGameEntity gameEntity = repository.findById(idGame);
        gameEntity.setStatut(StatutGameEnum.DELETED.libelle);
        repository.persistAndFlush(gameEntity);

    }

    private void checkStatutGame(DartGameEntity gameEntity, List<StatutGameEnum> validStatut){
        if(validStatut.stream().noneMatch(statut -> statut.equals(gameEntity.getStatut()))){
            throw new InvalidStatutGame(gameEntity.getId(), gameEntity.getStatut());
        }
    }
    
}
