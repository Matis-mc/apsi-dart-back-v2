package org.apsidart.common.mapper;

import org.apsidart.player.dto.PlayerDto;
import org.apsidart.player.entity.PlayerEntity;

public class PlayerMapper {
    

    public static PlayerEntity dtoToEntity(PlayerDto pcd){

        return new PlayerEntity(pcd.firstName(), pcd.lastName(), pcd.pseudo());

    }

    public static PlayerDto entityToDto(PlayerEntity pe){

        return new PlayerDto(pe.getId(), pe.getFirstName(), pe.getLastName(), pe.getPseudo());

    }

}
