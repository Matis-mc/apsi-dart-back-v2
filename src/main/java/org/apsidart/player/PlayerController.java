package org.apsidart.player;

import java.util.List;

import org.apsidart.player.dto.PlayerDto;
import org.jboss.logging.Logger;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/players")
@Valid
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PlayerController {

    private static final Logger LOG = Logger.getLogger(PlayerController.class);

    @Inject
    PlayerService ps;
    
    @POST
    public Long createPlayer(@Valid PlayerDto player){
        return ps.createPlayer(player);
    }

    @GET
    public List<PlayerDto> getPlayers(){
        return ps.getAllplayer();
    }

    @GET
    @Path("/{id}")
    public PlayerDto getPlayerById(@PathParam("id") Long id){
        return ps.getPlayerById(id);
    }

    @DELETE
    @Path("/{id}")
    public boolean deletePlayer(@PathParam("id") Long id){
        return ps.deletePlayerById(id);
    }
}
