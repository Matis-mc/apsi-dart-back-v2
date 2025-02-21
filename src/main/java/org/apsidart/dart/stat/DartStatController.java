package org.apsidart.dart.stat;

import java.util.List;

import org.apsidart.dart.stat.dto.DartRankingPlayerDto;
import org.apsidart.dart.stat.dto.DartStatDetailGameDto;
import org.apsidart.dart.stat.dto.DartStatGameDto;
import org.apsidart.dart.stat.dto.DartStatPlayerDetailDto;
import org.apsidart.dart.stat.dto.DartStatPlayerDto;
import org.jboss.resteasy.reactive.RestQuery;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/dart/stat")
@Valid
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class DartStatController {

    @Inject
    private DartRechercheStatService service;

    @GET
    @Path("/player/ranking")
    public DartRankingPlayerDto getRanking(){
        return service.getClassementPlayers();
    }

    @GET
    @Path("/player/{id}")
    public List<DartStatPlayerDto> getPlayerStat(@PathParam("id") Long idPlayer){
        return service.getAllStatsPlayer(idPlayer);
    }
    
    @GET
    @Path("/player/{id}/last")
    public DartStatPlayerDto getPlayerStat(@PathParam("id") Long idPlayer,
                                           @RestQuery String typeGame){
        return service.getLastStatPlayerByGame(idPlayer, typeGame);
    }

    @GET
    @Path("/player/{id}/detail")
    public DartStatPlayerDetailDto getPlayerDetailStat(@PathParam("id") Long idPlayer,
                                                 @RestQuery String typeGame){
        return service.getHistoriqueStatsPlayer(idPlayer, typeGame);
    }

    @GET
    @Path("/game/{id}")
    public DartStatGameDto getGameStatById(@PathParam("id") Long idGame){
        return service.getGameStat(idGame);
    }

    @GET
    @Path("/game/{id}/detail")
    public DartStatDetailGameDto getGameStatDetailById(@PathParam("id") Long idGame, 
                                                       @NotNull @QueryParam("typeGame") String typeGame){
        return service.getGameStatDetail(idGame, typeGame);
    }

    
}
