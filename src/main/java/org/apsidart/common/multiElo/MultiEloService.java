package org.apsidart.common.multiElo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class MultiEloService {
    
    @Inject
    private ExponentialEloFunction eloFunction; 

    /**
     * On calcule le nouveaux score Elo d'un joueur à partir de son classement, du nombre de joueur présent et de leurs scores elo.
     */
    public double calculateNewEloRating(int position, double scoreElo,  double[] allEloPlayers){
        double nbPlayer = allEloPlayers.length;
        double actualScore = eloFunction.calculateScore(nbPlayer, position);
        double expectedScore = eloFunction.predicteScore(nbPlayer, scoreElo, allEloPlayers);
        return eloFunction.calculateNewElo(actualScore, expectedScore, nbPlayer, scoreElo);
    }
}