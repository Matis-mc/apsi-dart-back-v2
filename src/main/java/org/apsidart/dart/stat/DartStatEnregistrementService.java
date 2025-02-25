package org.apsidart.dart.stat;

import static org.apsidart.common.Constants.Stat.AVG_DART_COMPLETE;
import static org.apsidart.common.Constants.Stat.AVG_POINT;
import static org.apsidart.common.Constants.Stat.AVG_POSITION;
import static org.apsidart.common.Constants.Stat.ELO_INITIAL;
import static org.apsidart.common.Constants.Stat.NB_GAME;
import static org.apsidart.common.Constants.Stat.NB_VICTOIRE;
import static org.apsidart.common.Constants.Stat.PCT_VICTOIRE;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apsidart.dart.multiElo.MultiEloService;
import org.apsidart.dart.performance.dto.DartPerformanceDto;
import org.apsidart.dart.stat.entity.AvgStat;
import org.apsidart.dart.stat.entity.DartStatPlayerEntity;
import org.apsidart.dart.stat.entity.PctStat;
import org.apsidart.dart.stat.entity.SumStat;
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
    public void majPlayersStat(List<DartPerformanceDto> performances, String typeJeu){

        LOG.info("[START] maj ou creation des statistiques de chaque joueur ");       
        LOG.info("[DO] Récupération / initialisaton des entities ");         
        // on créer de nouvelles entity pour chaque joueur ( à partir de l'ancienne ou de 0)
        List<DartStatPlayerEntity> statEntitiesWithoutEloUpdated = performances
            .stream()
            .map((DartPerformanceDto p) -> {
                return dartStatRepository.findLastStatByIdPlayerAndTypeJeu(p.getIdPlayer(), typeJeu)
                    .map(s -> updateDartStatPlayerEntity(s, p))
                    .orElse(initNewDartStatPlayer(p, typeJeu));})
            .collect(Collectors.toList());

        double[] eloScores = statEntitiesWithoutEloUpdated.stream().map(e -> e.getEloScore()).mapToDouble(Double::doubleValue).toArray();

        LOG.info("[DO] Récupération des scores ELO de chaque joueur : " + statEntitiesWithoutEloUpdated.toString());        

        statEntitiesWithoutEloUpdated.forEach( s -> {
            s.setEloScore(multiEloService
                .calculateNewEloRating(
                        getPerformanceFromListWithIdPlayer(performances, s.getIdPlayer()), s.getEloScore(), eloScores));
            LOG.info("[DO] MAJ score elo et enregistrement de l'entity : " + s.toString());        
            dartStatRepository.persist(s);});
        LOG.info("[SUCCESS] maj ou creation des statistiques de chaque joueur ");       

    }

    private DartPerformanceDto getPerformanceFromListWithIdPlayer(List<DartPerformanceDto> performances, Long idPlayer){
        return performances.stream().filter(p -> p.getIdPlayer() == idPlayer).findFirst().get();
    }
    

    private DartStatPlayerEntity updateDartStatPlayerEntity(DartStatPlayerEntity entity, DartPerformanceDto performanceDto){
    
        boolean isVictoire = isVictoire(performanceDto.getHistoriquePosition().getLast());
        
        DartStatPlayerEntity newStatEntity = new DartStatPlayerEntity(
                entity.getTypeJeu(), 
                entity.getEloScore(),
                entity.getIdPlayer(), 
                new Timestamp(new Date().getTime()),
                getNewAvgStatFromOld(entity.getAvgPosition(), performanceDto.getHistoriquePosition().getLast()),
                getNewAvgStatFromOld(entity.getAvgPoints(), performanceDto.getScore().getLast()),
                getNewPctStatFromOld(entity.getPctVictoire(), isVictoire ? 1d : 0d),
                getNewAvgStatFromOld(entity.getAvgNbDartCompleted(), getNbDartCompleted(performanceDto.getVolees())),
                getNewSumStatFromOld(entity.getNbGame(), 1d),
                getNewSumStatFromOld(entity.getNbVictoire(), isVictoire ? 1d : 0d));
            return newStatEntity;
    }

    private DartStatPlayerEntity initNewDartStatPlayer(DartPerformanceDto performanceDto, String typeJeu){
        boolean isVictoire = isVictoire(performanceDto.getHistoriquePosition().getLast());

        return new DartStatPlayerEntity(
                typeJeu, 
                ELO_INITIAL,
                performanceDto.getIdPlayer(), 
                new Timestamp(new Date().getTime()),
                new AvgStat(performanceDto.getHistoriquePosition().getLast(), AVG_POSITION),
                new AvgStat(performanceDto.getScore().getLast(), AVG_POINT),
                new PctStat(isVictoire ? 1d : 0d, PCT_VICTOIRE),
                new AvgStat(getNbDartCompleted(performanceDto.getVolees()), AVG_DART_COMPLETE),
                new SumStat(1d, NB_GAME ),
                new SumStat(isVictoire ? 1d : 0d, NB_VICTOIRE));
    }

    private boolean isVictoire(Integer position){
        return position == 1;
    }

    private Double getNbDartCompleted(List<String> volees){
        Double nbDartCompleted = 0d;
        for(String v : volees){
            String[] darts = v.split("-");
            for(String dart : darts){
                if(!"0".equals(dart)){
                    nbDartCompleted += 1d;
                }
            } 
        }
        return nbDartCompleted;
    }

    private AvgStat getNewAvgStatFromOld(AvgStat avgStat, double newValue){
        double poid = avgStat.getPoid() + 1d;
        return new AvgStat(
            poid,
            ((avgStat.getValue())*(poid) + newValue ) / poid,
                avgStat.getLabel());
    }

    private PctStat getNewPctStatFromOld(PctStat pctStat, double newValue){
        if(newValue > 1){
            newValue = newValue / 100d;
        }
        double poid = pctStat.getPoid() + 1d;
        return new PctStat(
                poid,
                ((pctStat.getValue())*(poid) + newValue ) / (poid),
                pctStat.getLabel());
    }

    private SumStat getNewSumStatFromOld(SumStat sumStat, double newValue){
        return new SumStat(sumStat.getValue() + newValue, sumStat.getLabel());
    }
    
}
