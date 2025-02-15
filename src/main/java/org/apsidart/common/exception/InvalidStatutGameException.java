package org.apsidart.common.exception;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class InvalidStatutGameException extends WebApplicationException {
    public InvalidStatutGameException(String statut) {
        super("Le statut de la partie n'est pas correct : " + statut, Response.Status.BAD_REQUEST);
    }
}
