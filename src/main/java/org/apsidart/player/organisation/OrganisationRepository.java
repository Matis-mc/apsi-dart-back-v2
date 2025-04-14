package org.apsidart.player.organisation;


import org.apsidart.player.organisation.entity.OrganisationEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrganisationRepository implements PanacheRepository<OrganisationEntity> {

}
