package org.apsidart.babykon.stat;

import java.util.Comparator;
import java.util.List;

import org.apsidart.babykon.game.dto.BabykonGameDto;
import org.apsidart.babykon.stat.dto.BabykonStatDto;
import org.apsidart.babykon.stat.entity.BabykonStatEntity;
import org.apsidart.common.simpleElo.SimpleEloFunction;
import org.apsidart.dart.game.DartGameService;
import org.apsidart.player.PlayerRepository;
import org.apsidart.player.entity.PlayerEntity;
import org.jboss.logging.Logger;

import static org.apsidart.common.Constants.Stat.ELO_INITIAL;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BabykonStatService {

    @Inject
    SimpleEloFunction eloFunction;

    @Inject
    BabykonStatRepository statRepository;

    @Inject
    PlayerRepository playerRepository;

    private static final Logger LOG = Logger.getLogger(DartGameService.class);

    public List<BabykonStatDto> uploadStatWithNewGame(BabykonGameDto gameDto){

        LOG.info("[START] Update elo");
        
        BabykonStatEntity statWinner = getBabykonStatForPlayer(gameDto.idWinner());
        BabykonStatEntity statLoser = getBabykonStatForPlayer(gameDto.idLoser());
        LOG.info("[DO] Recuperation entity winner : " + statWinner.toString() + "/n loser : " + statLoser.toString());
        
        double esperanceWinner = eloFunction.caclulateDeltaElo(statWinner.getEloScore(), statLoser.getEloScore());
        double esperanceLoser = eloFunction.caclulateDeltaElo(statLoser.getEloScore(), statWinner.getEloScore());
        LOG.info("[DO] Calcul de l'esperance du gagnant : " + esperanceWinner + "/n du perdant : " + esperanceLoser);

        statRepository.persist(updateStatEntityAfterGame(statWinner, esperanceWinner, true));
        statRepository.persist(updateStatEntityAfterGame(statLoser, esperanceLoser, false));

        LOG.info("[SUCCESS] Update elo");

        return List.of(new BabykonStatDto(statWinner.getEloScore(), statWinner.getNbGame(), statWinner.getNbVictoire()),
                    new BabykonStatDto(statLoser.getEloScore(), statLoser.getNbGame(), statLoser.getNbVictoire()));
    }

    public BabykonStatEntity getBabykonStatForPlayer(long idPlayer){
        return statRepository.findStatByIdPlayer(idPlayer).orElseGet(() -> initBabykonStat(idPlayer));
    }

    public List<BabykonStatDto> getClassement(){

        return statRepository.findAll()
            .stream()
            .filter(s -> s.getNbGame() > 3)
            .map(s -> new BabykonStatDto(s.getEloScore(), s.getNbGame(), s.getNbVictoire()))
            .sorted(Comparator.comparing(BabykonStatDto::elo).reversed())
            .toList();
    }

    private BabykonStatEntity updateStatEntityAfterGame(BabykonStatEntity stat, double deltaElo, boolean isVictory){
        int score = isVictory ? 1 : 0;
        stat.setEloScore(eloFunction.updateELo(stat.getEloScore(), deltaElo, score));
        stat.setNbGame(stat.getNbGame() + 1);
        stat.setNbVictoire(stat.getNbVictoire() + score);
        return stat;
    }

    private BabykonStatEntity initBabykonStat(long idPlayer){
        PlayerEntity playerEntity = playerRepository.findById(idPlayer);
        return new BabykonStatEntity(playerEntity, (double) ELO_INITIAL, 0, 0, (double) ELO_INITIAL, 0);
    }
    
}
