package org.apsidart.common.exception;

public class InvalidStatutGame extends RuntimeException {

    public InvalidStatutGame(Long idGame, String statut){
        super("La partie " + idGame + "a un statut incorrect : " + statut);
    }
    
}
