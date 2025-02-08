package org.apsidart.dart.game;

import java.util.List;

import org.apsidart.dart.game.dto.DartGameCreationDto;
import org.apsidart.dart.game.dto.DartGameCreationRetourDto;
import org.apsidart.dart.game.dto.DartGameDto;
import org.apsidart.dart.game.dto.DartGameRoundDto;
import org.apsidart.dart.game.entity.DartGameEntity;

import io.quarkus.resteasy.reactive.links.InjectRestLinks;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
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
    DartGameService service;

    @POST
    @InjectRestLinks
    public DartGameCreationRetourDto createGame(@Valid DartGameCreationDto dto){
        return service.createGame(dto);
    }

    @GET
    @InjectRestLinks
    public List<DartGameDto> getAllGame(){
        return service.getAllGame();
    }

    @GET
    @Path("/{id}")
    @InjectRestLinks
    public DartGameDto getGameById(@PathParam("id") Long id){
        return service.getGameById(id);
    }    

    @POST
    @Path("/round")
    @InjectRestLinks
    public String performOnGame(@Valid DartGameRoundDto dto){
        return service.performOnGame(dto);
    }

    @POST
    @Path("/end")
    @InjectRestLinks
    public String endGame(@Valid DartGameRoundDto dto){
        return service.endGame(dto);
    }
    
}
