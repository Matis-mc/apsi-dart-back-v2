package org.apsidart.dart.stat;

import java.util.List;
import java.util.Objects;

import org.apsidart.common.mapper.PlayerMapper;
import org.apsidart.dart.game.dto.PlayerPeformanceDto;
import org.apsidart.dart.performance.DartPerformanceRepository;
import org.apsidart.dart.performance.entity.DartPerformanceEntity;
import org.apsidart.dart.stat.dto.DartStatGameDto;
import org.apsidart.dart.stat.dto.DartStatPlayerDetailDto;
import org.apsidart.dart.stat.dto.DartStatPlayerDto;
import org.apsidart.dart.stat.entity.DartStatPlayerEntity;
import org.apsidart.dart.stat.mapper.DartStatMapper;
import org.apsidart.player.PlayerRepository;
import org.apsidart.player.PlayerService;
import org.apsidart.player.dto.PlayerDto;
import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class DartRechercheStatService {

    @Inject
    DartStatRepository statRepository;

    @Inject
    DartPerformanceRepository performanceRepository;

    @Inject
    PlayerService playerService;

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
            dto.addEloScore(s.getEloScore());
            dto.addAvgPosition(s.getAvgPosition().getValue());
            dto.addAvgPoint(s.getAvgPoints().getValue());
            dto.addPctVictoire(s.getPctVictoire().getValue());
            dto.addAvgNbDartCompleted(s.getAvgNbDartCompleted().getValue());
        });   
        LOG.info("[SUCCESS] Récupération des statistiques détaillées : " + dto.toString());
        return dto;
    }

    public DartStatGameDto getGameStat(Long idGame){
        DartStatGameDto dartStatGameDto = new DartStatGameDto();
        List<DartPerformanceEntity> performanceDtos = performanceRepository.findByIdGame(idGame);
        performanceDtos.forEach(p -> {
            PlayerDto playerDto = playerService.getPlayerById(p.getIdPlayer());
            dartStatGameDto.addPlayer(playerDto);
            dartStatGameDto.addPosition(p.getIdPlayer(), p.getHistPosition());
            dartStatGameDto.addScore(p.getIdPlayer(), p.getHistScore());
        });
        return dartStatGameDto;
    }
}
