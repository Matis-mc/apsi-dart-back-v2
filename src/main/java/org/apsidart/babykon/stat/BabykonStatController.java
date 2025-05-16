package org.apsidart.babykon.stat;

import java.util.List;

import org.apsidart.babykon.stat.dto.BabykonStatDto;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/babykon/stat")
@Valid
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class BabykonStatController {

    @Inject
    BabykonStatService service;

    @GET
    public List<BabykonStatDto> getClassement(){
        return service.getClassement();
    }

}
