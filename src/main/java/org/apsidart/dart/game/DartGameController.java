package org.apsidart.dart.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apsidart.common.hal.HalLink;
import org.apsidart.common.hal.HalResource;
import org.apsidart.dart.game.dto.CommentDto;
import org.apsidart.dart.game.dto.DartGameCreationDto;
import org.apsidart.dart.game.dto.DartGameCreationRetourDto;
import org.apsidart.dart.game.dto.DartGameDto;
import org.apsidart.dart.game.dto.DartGameRoundDto;
import org.jboss.resteasy.reactive.common.util.RestMediaType;

import jakarta.annotation.Nullable;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;

@Path("/dart/game")
@Valid
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class DartGameController {

    @Inject
    DartGameService service;

    @Context
    UriInfo uriInfo;

    @POST
    @Produces({ MediaType.APPLICATION_JSON, RestMediaType.APPLICATION_HAL_JSON })
    public HalResource<DartGameCreationRetourDto> createGame(@Valid DartGameCreationDto dto){

        DartGameCreationRetourDto retour = service.createGame(dto);
        return new HalResource<DartGameCreationRetourDto>()
            .setContent(retour)
            .addLink("round",  uriInfo.getAbsolutePath() + "/round")
            .addLink("self", uriInfo.getAbsolutePath() + "/" + retour.id())
            .addLink("delete", uriInfo.getAbsolutePath() + "/" + retour.id());
    }

    @GET
    public HalResource<DartGameDto> getAllGame(){
        HalResource<DartGameDto> halResource = new HalResource<>();
        service.getAllGame()
            .stream()
            .map(g -> getHalResourceFromDartGame(g))
            .forEach(r -> halResource.addEmbedded(r));
            return halResource;
    }

    @GET
    @Path("/{id}")
    public HalResource<DartGameDto> getGameById(@PathParam("id") Long id){
        DartGameDto dto = service.getGameById(id);
        return getHalResourceFromDartGame(dto);
    }    

    @POST
    @Path("/round")
    public CommentDto performOnGame(@Valid DartGameRoundDto dto){
        return service.performOnGame(dto);
    }

    @POST
    @Path("/end")
    public CommentDto endGame(@Valid DartGameRoundDto dto){
        return service.endGame(dto);
    }

    @DELETE
    @Path("/{id}")
    public void deleteGameById(@PathParam("id") Long id){
        service.deleteGame(id);
    }    


    private <T> HalResource<DartGameDto> getHalResourceFromDartGame(@Nullable DartGameDto dto){
        HalResource<DartGameDto> halResource = new HalResource<DartGameDto>()
            .setContent(dto)
            .addLink("self", uriInfo.getAbsolutePath() + "/" + dto.getId())
            .addLink("all", uriInfo.getAbsolutePath() + "/");
        return switch(dto.getStatut()){
            case "CREATION" -> halResource
                            .addLink("round",  uriInfo.getAbsolutePath() + "/round")
                            .addLink("delete", uriInfo.getAbsolutePath() + "/" + dto.getId());
            case "IN_PROGRESS" -> halResource
                            .addLink("round",  uriInfo.getAbsolutePath() + "/round")
                            .addLink("end",  uriInfo.getAbsolutePath() + "/end")
                            .addLink("delete", uriInfo.getAbsolutePath() + "/" + dto.getId());
            case "COMPLETED" -> halResource;
            case "DELETED" -> halResource;
            default -> halResource;
        };
    }
    
}
