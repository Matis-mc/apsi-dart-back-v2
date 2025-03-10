package org.apsidart.dart.game;

import java.util.List;

import org.apsidart.dart.game.dto.DartGameCreationDto;
import org.apsidart.dart.game.dto.DartGameDto;
import org.apsidart.dart.game.dto.DartGameResumeDto;
import org.apsidart.dart.game.dto.DartGameRoundDto;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/dart/game")
@Valid
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class DartGameController {

    @Inject
    private DartGameService service;

    @POST
    public Long createGame(@Valid DartGameCreationDto dto){
        return service.createGame(dto);
    }

    @GET
    public List<DartGameDto> getAllGame(){
        return service.getAllGame();
    }

    @GET
    @Path("/{id}")
    public DartGameDto getGameById(@NotNull @PathParam("id") Long id){
        return service.getGameById(id);
    }  
    
    @GET
    @Path("/resume/{id}")
    public DartGameResumeDto getResumeGameById(@NotNull @PathParam("id") Long id){
        return service.getResumeGame(id);
    } 

    @POST
    @Path("/round")
    public void performOnGame(@Valid DartGameRoundDto dto){
        service.performOnGame(dto);
    }

    @POST
    @Path("/end")
    public void endGame(@Valid DartGameRoundDto dto){
        service.endGame(dto);
    }
}
