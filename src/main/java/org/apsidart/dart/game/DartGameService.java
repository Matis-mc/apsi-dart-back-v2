package org.apsidart.dart.game;

import static org.apsidart.dart.game.enumeration.StatutGameEnum.CREATION;
import static org.apsidart.dart.game.enumeration.StatutGameEnum.IN_PROGRESS;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.apsidart.common.exception.InvalidStatutGameException;
import org.apsidart.dart.game.dto.CommentDto;
import org.apsidart.dart.game.dto.DartGameCreationDto;
import org.apsidart.dart.game.dto.DartGameCreationRetourDto;
import org.apsidart.dart.game.dto.DartGameDetailDto;
import org.apsidart.dart.game.dto.DartGameDto;
import org.apsidart.dart.game.dto.DartGameResumeDto;
import org.apsidart.dart.game.dto.DartGameRoundDto;
import org.apsidart.dart.game.dto.DartPlayerDto;
import org.apsidart.dart.game.dto.DartRoundResumeDto;
import org.apsidart.dart.game.entity.DartGameEntity;
import org.apsidart.dart.game.enumeration.StatutGameEnum;
import org.apsidart.dart.game.mapper.DartGameMapper;
import org.apsidart.dart.performance.DartPerformanceService;
import org.apsidart.dart.performance.dto.DartPerformanceDto;
import org.apsidart.dart.stat.DartStatEnregistrementService;
import org.apsidart.ia.IAService;
import org.apsidart.player.PlayerService;
import org.apsidart.player.dto.PlayerDto;
import org.apsidart.player.entity.PlayerEntity;
import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class DartGameService {

    @Inject
    private DartGameRepository repository;

    @Inject
    private DartPerformanceService performanceService;

    @Inject
    private IAService iaService;

    @Inject
    private DartStatEnregistrementService dartStatEnregistrementService;

    @Inject
    private PlayerService playerService;

    private static final Logger LOG = Logger.getLogger(DartGameService.class);

    @Transactional
    public DartGameCreationRetourDto createGame(DartGameCreationDto dto){

        LOG.info("[START] Creation d'une partie");
        DartGameEntity gameEntity = DartGameMapper.dtoToEntity(dto);
        gameEntity.setStatut(StatutGameEnum.CREATION.libelle);
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
            .sorted(Comparator.comparing(DartGameDto::getDate))
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

    @Transactional
    public CommentDto performOnGame(DartGameRoundDto dto){
        LOG.info("[START] Enregistrement d'un tour avec payload : " + dto.toString());
        DartGameEntity game = checkStatuGame(dto.getIdGame(), List.of(IN_PROGRESS.libelle, CREATION.libelle));
        game.setStatut(IN_PROGRESS.libelle);
        repository.persist(game);
        dto.getPerformances().forEach(p -> performanceService.enregistrePerformanceForPlayer(p, dto.getIdGame()));
        LOG.info("[SUCCESS] Enregistrement d'un tour");
        return iaService.getDartRoundCommentaire(dto.getPerformances());
    }

    @Transactional
    public CommentDto endGame(DartGameRoundDto dto){
        LOG.info("[START] Enregistrement du dernier tour avec payload : " + dto.toString());
        DartGameEntity gameEntity = checkStatuGame(dto.getIdGame(), IN_PROGRESS.libelle);
        dto.getPerformances().forEach(p -> performanceService.endGameForPlayer(p, dto.getIdGame()));
        gameEntity.setStatut(StatutGameEnum.COMPLETED.libelle);
        repository.persist(gameEntity);
        LOG.info("[SUCCESS] Enregistrement du dernier tour." );

        List<DartPerformanceDto> performanceDtos = performanceService.getPerformanceByIdGame(dto.getIdGame());
        dartStatEnregistrementService.majPlayersStat(performanceDtos, gameEntity.getType());       

        return iaService.getDartEndGameCommentaire(dto.getPerformances());
    }

    /*
     * On recupère l'ensemble des coups joués dans une partie en cours, afin de pouvoir la reprendre.
     * On récupère pour chaque round, une map qui associe le player à sa volée.
     */
    public DartGameResumeDto getResumeGame(Long id){
        checkStatuGame(id, IN_PROGRESS.libelle);
        LOG.info("[START] Récupération de l'état de la partie : " + id);
        List<DartRoundResumeDto> roundsResume = new ArrayList<>();
        List<DartPerformanceDto> performances = performanceService.getPerformanceByIdGame(id);
        LOG.info("performances : " + performances);
        int numberRound = performances.get(0).getNombreTour();
        for(int i = 0; i < numberRound; i++){
            roundsResume.add(DartGameMapper.listPerformanceToResumeRound(performances, i));
        }
        LOG.info("[SUCCESS] Récupération de l'état de la partie." );
        return new DartGameResumeDto(roundsResume);

    }

    @Transactional
    public List<DartGameDetailDto> getAllGamesDetail(){
        LOG.info("[START] Récupération de toute les parties en format detaillé");
        List<DartGameDetailDto> games =  repository.findAll()
        .stream()
        .map(this::getDetail)
        .sorted(Comparator.comparing(DartGameDetailDto::date))
        .toList();
        LOG.info("[SUCCESS] Récupération de toute les parties en format detaillé");
        return games;
        
    }

    private DartGameDetailDto getDetail(DartGameEntity entity){
        LOG.info("[DO] Récupération de la partie " + entity.getId());
        List<DartPlayerDto> players = performanceService.getPerformanceByIdGame(entity.getId())
            .stream()
            .map(p -> {
                    LOG.info("[DO] Récupération du joueur " + p.getIdPlayer());
                    try {
                        PlayerDto pe = playerService.getPlayerById(p.getIdPlayer());
                        return new DartPlayerDto(p.getIdPlayer(),
                            pe.firstName(), pe.name(), pe.pseudo(), p.getElo());
                    } catch ( NotFoundException e){
                        LOG.error("[FAILED] Récupération du joueur " + p.getIdPlayer());
                        return null;
                    }
                })
            .filter(Objects::nonNull)
            .toList();
        return new DartGameDetailDto(entity.getId(), entity.getStatut(), entity.getType(), entity.getDate(), players);
        
    }

    private DartGameEntity checkStatuGame(Long idGame, @NotBlank String statutAuthorized){
        return checkStatuGame(idGame, List.of(statutAuthorized));
    }

    private DartGameEntity checkStatuGame(Long idGame, @NotEmpty List<String> statutAuthorized){
        DartGameEntity game = repository.findById(idGame);
        if(statutAuthorized.stream().anyMatch(s -> s.equals(game.getStatut()))){
            return game;
        }
        throw new InvalidStatutGameException(game.getStatut());
    }
    
}
