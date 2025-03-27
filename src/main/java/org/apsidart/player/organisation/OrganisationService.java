package org.apsidart.player.organisation;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apsidart.player.organisation.dto.CreationOrganisationDto;
import org.apsidart.player.organisation.dto.OrganisationDto;
import org.apsidart.player.organisation.entity.OrganisationEntity;
import org.apsidart.player.organisation.mapper.OrganisationMapper;
import org.apsidart.player.player.PlayerRepository;
import org.apsidart.player.player.entity.PlayerEntity;
import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class OrganisationService {

    @Inject
    private OrganisationRepository or;

    @Inject
    private PlayerRepository pr;

    private static final Logger LOG = Logger.getLogger(OrganisationService.class);

    @Transactional
    public Long createOrganisaton(CreationOrganisationDto dto){
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
    public void addPlayersToOrganisation(List<Long> idPlayers, Long idOrganisation){
        LOG.info("[DO] Ajout players sur organisation.");
        OrganisationEntity organisation = or.findById(idOrganisation);
        List<PlayerEntity> player = idPlayers.stream().map(id -> pr.findById(id)).collect(Collectors.toList());
        organisation.addPlayers(player);
        or.persistAndFlush(organisation);
        LOG.info("[SUCCESS] Ajout players sur organisation : " + organisation.toString());

    }
}
