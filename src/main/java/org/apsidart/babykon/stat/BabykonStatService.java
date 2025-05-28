package org.apsidart.babykon.stat;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import org.apsidart.babykon.game.dto.BabykonGameDto;
import org.apsidart.babykon.stat.dto.BabykonStatDto;
import org.apsidart.babykon.stat.entity.BabykonStatEntity;
import org.apsidart.common.ranking.dto.RankingPlayerDto;
import org.apsidart.common.ranking.dto.RankingPlayerElementDto;
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

        return List.of(new BabykonStatDto(statWinner.getPlayer().getId(), statWinner.getEloScore(), statWinner.getNbGame(), statWinner.getNbVictoire()),
                    new BabykonStatDto(statLoser.getPlayer().getId(), statLoser.getEloScore(), statLoser.getNbGame(), statLoser.getNbVictoire()));
    }

    public BabykonStatEntity getBabykonStatForPlayer(long idPlayer){
        return statRepository.findStatByIdPlayer(idPlayer).orElseGet(() -> initBabykonStat(idPlayer));
    }

    public RankingPlayerDto getClassement(){
        List<RankingPlayerElementDto> rankings = statRepository.findAll()
            .stream()
            .filter(s -> s.getNbGame() > 3)
            .map(s -> new RankingPlayerElementDto(s.getPlayer().getId(), s.getPlayer().getFirstName(), s.getPlayer().getLastName(),
                s.getPlayer().getPseudo(), (int) s.getEloScore(), s.getNbVictoire(), s.getNbGame()))
            .sorted(Comparator.comparing(RankingPlayerElementDto::elo).reversed())
            .toList();
            return new RankingPlayerDto(LocalDate.now().toString(), rankings);
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
