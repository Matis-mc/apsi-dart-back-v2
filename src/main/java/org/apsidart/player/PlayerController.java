package org.apsidart.player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apsidart.common.hal.HalLink;
import org.apsidart.common.hal.HalResource;
import org.apsidart.player.dto.PlayerDto;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.common.util.RestMediaType;

import jakarta.annotation.Nullable;
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

@Path("/players")
@Valid
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PlayerController {

    @Inject
    PlayerService service;

    @Context
    UriInfo uriInfo;
    
    @POST
    @Produces({ MediaType.APPLICATION_JSON, RestMediaType.APPLICATION_HAL_JSON })
    public HalResource<Long> createPlayer(@Valid PlayerDto player){
        Long idPlayer = service.createPlayer(player);
        HalResource<Long> halResource = new HalResource<Long>();
        halResource.setContent(idPlayer);
        halResource.addLink("create", uriInfo.getAbsolutePath().toString());
        halResource.addLink("collection", uriInfo.getAbsolutePath().toString());
        halResource.addLink("self", uriInfo.getAbsolutePath() + "/" + idPlayer);
        halResource.addLink("delete", uriInfo.getAbsolutePath() + "/" + idPlayer);
        return halResource;
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON, RestMediaType.APPLICATION_HAL_JSON })
    public HalResource<PlayerDto> getPlayers(){
        return getHalResourceFromList(service.getAllplayer());
    }

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON, RestMediaType.APPLICATION_HAL_JSON })
    public HalResource<PlayerDto> getPlayerById(@PathParam("id") Long id){
        return getHalResourceFromContent(service.getPlayerById(id), id);
    }

    @DELETE
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON, RestMediaType.APPLICATION_HAL_JSON })
    public HalResource<Boolean> deletePlayer(@PathParam("id") Long id){
        return getHalResourceFromContent(service.deletePlayerById(id), null);
    }

    public Map<String, HalLink> getPublicHalLinks(){
        Map<String, HalLink> links = new HashMap<>();
        links.put("create", new HalLink(uriInfo.getAbsolutePath().toString()));
        links.put("collection", new HalLink(uriInfo.getAbsolutePath().toString()));
        return links;
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

    private HalResource<PlayerDto> getHalResourceFromList(List<PlayerDto> content){
        HalResource<PlayerDto> halResource = new HalResource<PlayerDto>();
        content.forEach(item -> halResource.addEmbedded(getHalResourceFromContent(item, item.id())));
        return halResource;
    }
}
