package org.apsidart.player.organisation.mapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apsidart.player.organisation.dto.CreationOrganisationDto;
import org.apsidart.player.organisation.dto.OrganisationDto;
import org.apsidart.player.organisation.entity.OrganisationEntity;
import org.apsidart.player.player.dto.PlayerDto;
import org.apsidart.player.player.mapper.PlayerMapper;

public class OrganisationMapper{

    public static OrganisationDto entityToDto(OrganisationEntity entity){
        List<PlayerDto> players = entity.getPlayers().stream().map(p -> PlayerMapper.entityToDto(p)).toList();
        return new OrganisationDto(entity.getId(), entity.getLibelle(), entity.getDateCreation(), players);
    }

    public static OrganisationEntity dtoToEntity(CreationOrganisationDto dto){
        return new OrganisationEntity(dto.libelle(), LocalDate.now(), new ArrayList<>());
    }


}