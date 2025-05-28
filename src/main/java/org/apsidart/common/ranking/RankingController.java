package org.apsidart.common.ranking;


import org.apsidart.babykon.stat.BabykonStatService;
import org.apsidart.common.ranking.dto.RankingPlayerDto;
import org.apsidart.dart.stat.DartRechercheStatService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/ranking")
@Valid
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class RankingController {

    @Inject
    private BabykonStatService babykonservice;

    @Inject
    private DartRechercheStatService dartService;

    @GET
    @Path("/dart")
    public RankingPlayerDto getRanking(){
        return dartService.getClassementPlayers();
    }

    @GET
    @Path("/babykon")
    public RankingPlayerDto getClassement(){
        return babykonservice.getClassement();
    }
}
