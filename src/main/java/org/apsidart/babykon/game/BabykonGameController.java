package org.apsidart.babykon.game;

import java.util.List;

import org.apsidart.babykon.game.dto.BabykonGameDto;
import org.apsidart.babykon.stat.dto.BabykonStatDto;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("/babykon/game")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class BabykonGameController {

    @Inject
    BabykonGameService service;

    @POST
    public List<BabykonStatDto> saveGame(BabykonGameDto dto){
        return service.saveGame(dto);
    }

}
