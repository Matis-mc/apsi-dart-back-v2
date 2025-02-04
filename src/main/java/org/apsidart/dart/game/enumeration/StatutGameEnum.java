package org.apsidart.dart.game.enumeration;

public enum StatutGameEnum {

    CREATION("CREATION"),
    IN_PROGRESS("IN_PROGRESS"),
    COMPLETED("COMPLETED");


    public final String libelle;

    private StatutGameEnum(String libelle) {
        this.libelle = libelle;
    }
    
}
