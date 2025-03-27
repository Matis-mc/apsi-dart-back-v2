package org.apsidart.player.organisation;

import java.util.List;
import java.util.Objects;

import org.apsidart.player.organisation.dto.OrganisationDto;
import org.apsidart.player.organisation.entity.OrganisationEntity;
import org.apsidart.player.organisation.mapper.OrganisationMapper;
import org.apsidart.player.player.PlayerService;
import org.jboss.logging.Logger;

import com.oracle.svm.core.annotate.Inject;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class OrganisationService {

    @Inject
    private OrganisationRepository or;

    private static final Logger LOG = Logger.getLogger(OrganisationService.class);

    @Transactional
    public Long createOrganisaton(OrganisationDto dto){
        LOG.info("[START] Creation d'une nouvelle organisation : " + dto.toString());
        OrganisationEntity entity = OrganisationMapper.dtoToEntity(dto);
        or.persistAndFlush(entity);
        LOG.info("[SUCCESS] Creation d'une nouvelle organisation : " + entity.toString());
        return entity.getId();
    }

    public List<OrganisationDto> getAllOrganisations(){
        return or.listAll()
            .stream()
            .map(e -> OrganisationMapper.entityToDto(e))
            .toList();
    }

    public OrganisationDto getOrganisationById(Long id){
        OrganisationEntity oe = or.findById(id);
        if(Objects.isNull(oe)){
            throw new NotFoundException("Aucune organisation ne correspond à cette id " + id);
        }
        return OrganisationMapper.entityToDto(oe);
    }

    @Transactional
    public boolean deleteOrganisationById(Long id){
        return or.deleteById(id);
    }  
    
    @Transactional
    public boolean addPlayerToOrganisation(Long idPlayer, Long idOrganisation){
        return true

    }
}
