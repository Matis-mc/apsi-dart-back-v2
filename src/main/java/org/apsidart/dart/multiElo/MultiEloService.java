package org.apsidart.dart.multiElo;

import org.apsidart.dart.performance.dto.DartPerformanceDto;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import static org.apsidart.common.utils.CollectionUtils.getLastIndex;;

@ApplicationScoped
public class MultiEloService {
    
    @Inject
    private ExponentialEloFunction eloFunction; 

    /**
     * On calcule le nouveaux score Elo d'un joueur à partir de son classement, du nombre de joueur présent et de leurs scores elo.
     */
    public double calculateNewEloRating(DartPerformanceDto performance, double scoreElo,  double[] allEloPlayers){
        double nbPlayer = allEloPlayers.length;
        double actualScore = eloFunction.calculateScore(nbPlayer, getLastIndex(performance.getHistoriquePosition()));
        double expectedScore = eloFunction.predicteScore(nbPlayer, scoreElo, allEloPlayers);
        return eloFunction.calculateNewElo(actualScore, expectedScore, nbPlayer, scoreElo);
    }
}