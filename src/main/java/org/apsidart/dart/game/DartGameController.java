package org.apsidart.dart.game;

import java.util.List;

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
    public DartGameCreationRetourDto createGame(@Valid DartGameCreationDto dto){
        return service.createGame(dto);
    }

    @GET
    public List<DartGameDto> getAllGame(){
        return service.getAllGame();
    }

    @GET
    @Path("/{id}")
    public DartGameDto getGameById(@PathParam("id") Long id){
        return service.getGameById(id);
    }    

    @POST
    @Path("/round")
    @InjectRestLinks
    public CommentDto performOnGame(@Valid DartGameRoundDto dto){
        return service.performOnGame(dto);
    }

    @POST
    @Path("/end")
    @InjectRestLinks
    public CommentDto endGame(@Valid DartGameRoundDto dto){
        return service.endGame(dto);
    }

    @DELETE
    @Path("/{id}")
    public void deleteGameById(@PathParam("id") Long id){
        service.deleteGame(id);
    }    


    private <T> HalResource<T> getHalResourceFromContent(T content, @Nullable Long idContent){
        HalResource<T> halResource = new HalResource<T>();
        halResource.setContent(content);
        halResource.addLink("create", uriInfo.getAbsolutePath().toString());
        halResource.addLink("collection", uriInfo.getAbsolutePath().toString());
        if(idContent != null ){
            halResource.addLink("self", uriInfo.getAbsolutePath() + "/" + idContent);
            halResource.addLink("delete", uriInfo.getAbsolutePath() + "/" + idContent);
        }
        return halResource;
    }

    public HalLink getLinkStartDartGame(){
        return new HalLink(uriInfo.getAbsolutePath().toString());
    }

    public HalLink getLinkSelfGame(Long id){
        return new HalLink(uriInfo.getAbsolutePath().toString() + "/" + id);
    }

    public HalLink getLinkAllDartGame(){
        return new HalLink(uriInfo.getAbsolutePath().toString());
    }
    
    private HalLink getLinkRoundDartGame(){
        return new HalLink(uriInfo.getAbsolutePath().toString() + "/round");
    }

    private HalLink getLinkEndDartGame(){
        return new HalLink(uriInfo.getAbsolutePath().toString() + "/end");
    }
    
}
