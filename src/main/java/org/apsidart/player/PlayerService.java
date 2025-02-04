package org.apsidart.player;

import java.util.List;

import org.apsidart.common.mapper.PlayerMapper;
import org.apsidart.player.dto.PlayerDto;
import org.apsidart.player.entity.PlayerEntity;
import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class PlayerService {

    @Inject
    PlayerRepository pr;

    private static final Logger LOG = Logger.getLogger(PlayerService.class);

    @Transactional
    public Long createPlayer(PlayerDto dto){
        PlayerEntity playerCreated = PlayerMapper.dtoToEntity(dto);
        pr.persist(PlayerMapper.dtoToEntity(dto));
        LOG.debug("Creation d'un nouveau joueur : " + playerCreated.toString());
        return playerCreated.getId();
    }

    public List<PlayerDto> getAllplayer(){
        return pr.listAll()
            .stream()
            .map(entity -> PlayerMapper.entityToDto(entity))
            .toList();
    }

    public PlayerDto getPlayerById(Long id){
        PlayerEntity pe = pr.findById(id);
        if(pe == null){
            throw new NotFoundException("Aucun joueur ne correspond à cette id " + id);
        }
        return PlayerMapper.entityToDto(pe);
    }

    @Transactional
    public boolean deletePlayerById(Long id){
        return pr.deleteById(id);
    }

    
}
