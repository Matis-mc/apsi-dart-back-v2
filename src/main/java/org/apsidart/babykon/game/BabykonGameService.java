package org.apsidart.babykon.game;

import java.time.LocalDate;
import java.util.List;

import org.apsidart.babykon.game.dto.BabykonGameDto;
import org.apsidart.babykon.game.entity.BabykonGameEntity;
import org.apsidart.babykon.stat.BabykonStatService;
import org.apsidart.babykon.stat.dto.BabykonStatDto;
import org.apsidart.player.PlayerRepository;
import org.apsidart.player.entity.PlayerEntity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class BabykonGameService {

    @Inject
    PlayerRepository playerRepository;

    @Inject
    BabykonGameRepository gameRepository;

    @Inject 
    BabykonStatService statService;

    @Transactional
    public List<BabykonStatDto> saveGame(BabykonGameDto dto){
        
        // Enregistrement de la partie
        PlayerEntity winner = playerRepository.findById(dto.idWinner());
        PlayerEntity loser = playerRepository.findById(dto.idLoser());
        BabykonGameEntity entity = new BabykonGameEntity(LocalDate.now(), winner, loser, dto.score());
        gameRepository.persistAndFlush(entity);

        // calcul nouvel Elo + Mise à jour des statistiques
        return statService.uploadStatWithNewGame(dto);

    }
    
}
