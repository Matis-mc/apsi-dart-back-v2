package org.apsidart.common.simpleElo;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SimpleEloFunction {

    @ConfigProperty(name = "simpleelo.control")
    private double ctrl;

    @ConfigProperty(name = "simpleelo.volatility")
    private double volatility;


    public double caclulateDeltaElo(double eloJoueurA, double eloJoueurB){
        return 1 / (1 + Math.pow(10, (eloJoueurB - eloJoueurA) / ctrl));  
    }

    public double updateELo(double currentElo, double deltaElo, int score){
        return currentElo + volatility * (score - deltaElo);
    }
    
}
