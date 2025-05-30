package org.apsidart.dart.stat;

import static org.apsidart.common.Constants.General.DATE_FORMAT_DD_MM_YYYYY;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.apsidart.common.ranking.dto.RankingPlayerElementDto;
import org.apsidart.common.ranking.dto.RankingPlayerDto;
import org.apsidart.dart.performance.DartPerformanceService;
import org.apsidart.dart.performance.dto.DartPerformanceDto;
import org.apsidart.dart.stat.dto.DartStatDetailGameDto;
import org.apsidart.dart.stat.dto.DartStatGameDto;
import org.apsidart.dart.stat.dto.DartStatPlayerDetailDto;
import org.apsidart.dart.stat.dto.DartStatPlayerDto;
import org.apsidart.dart.stat.entity.DartStatPlayerEntity;
import org.apsidart.dart.stat.mapper.DartStatMapper;
import org.apsidart.player.PlayerService;
import org.apsidart.player.dto.PlayerDto;
import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class DartRechercheStatService {

    @Inject
    private DartStatRepository statRepository;

    @Inject
    private DartPerformanceService performanceService;

    @Inject
    private PlayerService playerService;

    private static final Logger LOG = Logger.getLogger(DartRechercheStatService.class);

    public DartStatPlayerDto getLastStatPlayerByGame(Long idPlayer, String typeJeu){
        return statRepository.findLastStatByIdPlayerAndTypeJeu(idPlayer, typeJeu)
            .map(s -> DartStatMapper.entityToDto(s))
            .orElseThrow(() -> new NotFoundException("Aucune statistique n'est associé au player : " + idPlayer + " pour le jeu " + typeJeu));
    }

    public List<DartStatPlayerDto> getAllStatsPlayer(Long idPlayer){
        return statRepository.findAllStatByIdPlayer(idPlayer)
            .stream()
            .map(s -> DartStatMapper.entityToDto(s))
            .toList();
    }
    
    public DartStatPlayerDetailDto getHistoriqueStatsPlayer(Long idPlayer, String typeJeu){
        LOG.info("[START] Récupération des statistiques détaillées pour le joueur " + idPlayer + " et le jeu " + typeJeu);
        List<DartStatPlayerEntity> stats = statRepository.findAllStatByIdPlayerAndTypeJeu(idPlayer, typeJeu);
        
        DartStatPlayerDetailDto dto = new DartStatPlayerDetailDto(typeJeu, idPlayer, stats.getFirst().getNbGame().getValue(), stats.getFirst().getNbVictoire().getValue());

        if(Objects.isNull(stats) || stats.isEmpty()){
            LOG.info("[FAILED] Aucune statistique n'est associé au player : " + idPlayer + " pour le jeu " + typeJeu);
            throw new NotFoundException("Aucune statistique n'est associé au player : " + idPlayer);
        }
        stats.forEach( s -> {
            dto.addEloScore(s.getEloScore().intValue());
            dto.addAvgPosition(s.getAvgPosition().getValue());
            dto.addAvgPoint(s.getAvgPoints().getValue());
            dto.addPctVictoire(s.getPctVictoire().getValue());
            dto.addAvgNbDartCompleted(s.getAvgNbDartCompleted().getValue());
        });   
        LOG.info("[SUCCESS] Récupération des statistiques détaillées : " + dto.toString());
        return dto;
    }

    /**
     * On récupère les statistiques d'une partie, et on associe les statistiques complète de chaque joueur
     * @param idGame
     * @return
     */
    public DartStatDetailGameDto getGameStatDetail(Long idGame, String typeJeu){
        DartStatDetailGameDto dartStatGameDto = new DartStatDetailGameDto();
        List<DartPerformanceDto> performanceDtos = performanceService.getPerformanceByIdGame(idGame);
        performanceDtos.forEach(p -> {
            DartStatPlayerDetailDto dartStatPlayerDetailDto = getHistoriqueStatsPlayer(p.getIdPlayer(), typeJeu);
            dartStatGameDto.addPlayer(dartStatPlayerDetailDto);
            dartStatGameDto.addPosition(p.getIdPlayer(), p.getHistoriquePosition());
            dartStatGameDto.addScore(p.getIdPlayer(), p.getScore());
        });
        return dartStatGameDto;
    }

    public DartStatGameDto getGameStat(Long idGame){
        DartStatGameDto dartStatGameDto = new DartStatGameDto();
        List<DartPerformanceDto> performanceDtos = performanceService.getPerformanceByIdGame(idGame);
        performanceDtos.forEach(p -> {
            PlayerDto playerDto = playerService.getPlayerById(p.getIdPlayer());
            dartStatGameDto.addPlayer(playerDto);
            dartStatGameDto.addPosition(p.getIdPlayer(), p.getHistoriquePosition());
            dartStatGameDto.addScore(p.getIdPlayer(), p.getScore());
        });
        return dartStatGameDto;
    }


    public RankingPlayerDto getClassementPlayers(){
        List<RankingPlayerElementDto> classements = playerService.getAllplayer()
            .stream()
            .map( p -> getClassementFromPlayer(p))
            .filter(Objects::nonNull)
            .filter(p -> p.nbGame() > 3)
            .sorted(Comparator.comparing(RankingPlayerElementDto::elo).reversed())
            .toList();
        return new RankingPlayerDto(LocalDate.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT_DD_MM_YYYYY)), classements);
    }

    // pour les joueurs qui n'ont as fait de partie de fléchette, il n'y a pas de performance. On retourne null, et on filtrera dessus.
    public RankingPlayerElementDto getClassementFromPlayer(PlayerDto playerDto){
        return statRepository.findLastStatByIdPlayer(playerDto.id())
            .map(stat -> DartStatMapper.toClassement(playerDto, stat))
            .orElse(null);
    }
}
