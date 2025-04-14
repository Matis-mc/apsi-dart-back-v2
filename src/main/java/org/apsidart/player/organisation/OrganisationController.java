package org.apsidart.player.organisation;

import java.util.List;

import org.apsidart.player.organisation.dto.AjoutPlayerDto;
import org.apsidart.player.organisation.dto.CreationOrganisationDto;
import org.apsidart.player.organisation.dto.OrganisationDto;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/organisation")
@Valid
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrganisationController {

    @Inject
    private OrganisationService service;

    @GET
    public List<OrganisationDto> getAllOrganisations(){
        return service.getAllOrganisations();
    }

    @GET
    @Path("/{id}")
    public OrganisationDto getOrganisationById(@PathParam("id") Long idOrganisation){
        return service.getOrganisationById(idOrganisation);
    }

    @PUT
    @Path("/{id}/player")
    public void addPlayersToOrganisation(@PathParam("id") Long idOrganisation,
                                          AjoutPlayerDto ajoutPlayerDto){
        service.addPlayersToOrganisation(ajoutPlayerDto.idPlayers(), idOrganisation);
    }

    @POST
    public Long createOrganisation(CreationOrganisationDto organisationDto){
        return service.createOrganisaton(organisationDto);
    }

}
