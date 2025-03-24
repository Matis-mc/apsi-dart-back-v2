package org.apsidart.common.enumeration;

public enum StatutGameEnum {

    CREATION("CREATION"),
    IN_PROGRESS("IN_PROGRESS"),
    COMPLETED("COMPLETED"),
    DELETED("DELETED");


    public final String libelle;

    private StatutGameEnum(String libelle) {
        this.libelle = libelle;
    }
    
}
