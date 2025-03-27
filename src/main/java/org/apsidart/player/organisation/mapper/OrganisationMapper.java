package org.apsidart.player.organisation.mapper;

import java.util.List;

import org.apsidart.player.organisation.dto.OrganisationDto;
import org.apsidart.player.organisation.entity.OrganisationEntity;
import org.apsidart.player.player.dto.PlayerDto;
import org.apsidart.player.player.entity.PlayerEntity;
import org.apsidart.player.player.mapper.PlayerMapper;

public class OrganisationMapper{

    public static OrganisationDto entityToDto(OrganisationEntity entity){
        List<PlayerDto> players = entity.getPlayers().stream().map(p -> PlayerMapper.entityToDto(p)).toList();
        return new OrganisationDto(entity.getId(), entity.getLibelle(), entity.getDateCreation(), players);
    }

    public static OrganisationEntity dtoToEntity(OrganisationDto dto){
        List<PlayerEntity> players = dto.players().stream().map(p -> PlayerMapper.dtoToExistingEntity(p)).toList();
        return new OrganisationEntity(dto.libelle(), dto.dateCreation(), players);
    }


}