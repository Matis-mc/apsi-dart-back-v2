package org.apsidart.player.player;

import java.util.List;
import java.util.Objects;

import org.apsidart.player.player.dto.PlayerDto;
import org.apsidart.player.player.entity.PlayerEntity;
import org.apsidart.player.player.mapper.PlayerMapper;
import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class PlayerService {

    @Inject
    private PlayerRepository pr;

    private static final Logger LOG = Logger.getLogger(PlayerService.class);

    @Transactional
    public Long createPlayer(PlayerDto dto){
        LOG.info("[START] Creation d'un nouveau joueur : " + dto.toString());
        PlayerEntity playerCreated = PlayerMapper.dtoToEntity(dto);
        pr.persistAndFlush(playerCreated);
        LOG.info("[SUCCESS] Creation d'un nouveau joueur : " + playerCreated.toString());
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
        if(Objects.isNull(pe)){
            throw new NotFoundException("Aucun joueur ne correspond à cette id " + id);
        }
        return PlayerMapper.entityToDto(pe);
    }

    @Transactional
    public boolean deletePlayerById(Long id){
        return pr.deleteById(id);
    }    
}
