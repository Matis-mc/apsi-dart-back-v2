package org.apsidart.dart.stat;

import static org.apsidart.common.Constants.Stat.AVG_POINT;
import static org.apsidart.common.Constants.Stat.AVG_POSITION;
import static org.apsidart.common.Constants.Stat.ELO_INITIAL;
import static org.apsidart.common.Constants.Stat.TYPE_ELO;
import static org.apsidart.common.Constants.Stat.NB_GAME;
import static org.apsidart.common.Constants.Stat.NB_VICTOIRE;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apsidart.common.ListUtils;
import org.apsidart.dart.multiElo.MultiEloService;
import org.apsidart.dart.performance.dto.DartPerformanceDto;
import org.apsidart.dart.stat.entity.AvgStat;
import org.apsidart.dart.stat.entity.StatPlayerEntity;
import org.apsidart.dart.stat.entity.SumStat;
import org.apsidart.dart.stat.entity.TmstpStat;
import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;


@ApplicationScoped
public class DartStatEnregistrementService {

    @Inject
    private DartStatRepository dartStatRepository;

    @Inject
    private MultiEloService multiEloService;

    private static final Logger LOG = Logger.getLogger(DartRechercheStatService.class);

    @Transactional
    public void majPlayersStat(List<DartPerformanceDto> performances, String typeGame){

        LOG.info("[START] maj ou creation des statistiques de chaque joueur ");       
        LOG.info("[DO] Récupération / initialisaton des entities ");         
        // on créer de nouvelles entity pour chaque joueur ( à partir de l'ancienne ou de 0)
        List<StatPlayerEntity> statEntitiesWithoutEloUpdated = performances
            .stream()
            .map((DartPerformanceDto p) -> {
                return dartStatRepository.findLastStatByIdPlayerAndTypeJeu(p.getIdPlayer(), typeGame)
                    .map(s -> updateStatPlayerEntity(s, p))
                    .orElse(initNewDartStatPlayer(p, typeGame));})
            .collect(Collectors.toList());

        double[] eloScores = statEntitiesWithoutEloUpdated.stream()
            .map(e -> e.getCurrentEloScore().map(TmstpStat::getValue).orElse(ELO_INITIAL))
            .mapToDouble(Double::doubleValue)
            .toArray();

        LOG.info("[DO] Récupération des scores ELO de chaque joueur : " + statEntitiesWithoutEloUpdated.toString());        

        statEntitiesWithoutEloUpdated.forEach( s -> {
            double eloScore = multiEloService
                .calculateNewEloRating(
                        getPerformanceFromListWithIdPlayer(performances, s.getIdPlayer()),
                        s.getCurrentEloScore().map(TmstpStat::getValue).orElse(ELO_INITIAL), eloScores);
            s.addEloScore(eloScore, typeGame);
            LOG.info("[DO] MAJ score elo et enregistrement de l'entity : " + s.toString());        
            dartStatRepository.persist(s);});
        LOG.info("[SUCCESS] maj ou creation des statistiques de chaque joueur ");       

    }

    private DartPerformanceDto getPerformanceFromListWithIdPlayer(List<DartPerformanceDto> performances, Long idPlayer){
        return performances.stream().filter(p -> p.getIdPlayer().equals(idPlayer)).findFirst().get();
    }
    

    private StatPlayerEntity updateStatPlayerEntity(StatPlayerEntity entity, DartPerformanceDto performanceDto){
    
        boolean isVictoire = isVictoire(performanceDto.getHistoriquePosition().getLast());

        entity.addAvgPoints(ListUtils.getLastElement(performanceDto.getScore()));
        entity.addAvgPosition(ListUtils.getLastElement(performanceDto.getHistoriquePosition()));
        entity.setNbGame(getNewSumStatFromOld(entity.getNbGame(), 1d));
        entity.setNbVictoire(getNewSumStatFromOld(entity.getNbVictoire(), isVictoire ? 1d : 0d));

        return entity;
    }

    private StatPlayerEntity initNewDartStatPlayer(DartPerformanceDto performanceDto, String typeGame){
        boolean isVictoire = isVictoire(performanceDto.getHistoriquePosition().getLast());

        List<TmstpStat> elos = new ArrayList<>();
        elos.add(new TmstpStat(typeGame, TYPE_ELO, ELO_INITIAL, Date.valueOf(LocalDate.now())));

        StatPlayerEntity e =  new StatPlayerEntity(
                typeGame, 
                elos,
                performanceDto.getIdPlayer(), 
                new Timestamp(Date.valueOf(LocalDate.now()).getTime()),
                new ArrayList<AvgStat>(List.of(new AvgStat(ListUtils.getLastElement(performanceDto.getHistoriquePosition()), AVG_POSITION))),
                new ArrayList<AvgStat>(List.of(new AvgStat(ListUtils.getLastElement(performanceDto.getScore()), AVG_POINT))),
                new SumStat(1d, NB_GAME ),
                new SumStat(isVictoire ? 1d : 0d, NB_VICTOIRE));
        return e;
                
    }

    private boolean isVictoire(Integer position){
        return position == 1;
    }

    private SumStat getNewSumStatFromOld(SumStat sumStat, double newValue){
        return new SumStat(sumStat.getValue() + newValue, sumStat.getLabel());
    }
    
}
