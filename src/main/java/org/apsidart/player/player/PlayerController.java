package org.apsidart.player.player;

import java.util.List;

import org.apsidart.player.player.dto.PlayerDto;

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

    @Inject
    private PlayerService service;
    
    @POST
    public Long createPlayer(@Valid PlayerDto player){
        return service.createPlayer(player);
    }

    @GET
    public List<PlayerDto> getPlayers(){
        return service.getAllplayer();
    }

    @GET
    @Path("/{id}")
    public PlayerDto getPlayerById(@PathParam("id") Long id){
        return service.getPlayerById(id);
    }

    @DELETE
    @Path("/{id}")
    public boolean deletePlayer(@PathParam("id") Long id){
        return service.deletePlayerById(id);
    }
}
