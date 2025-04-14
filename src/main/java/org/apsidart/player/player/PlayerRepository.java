package org.apsidart.player.player;

import org.apsidart.player.player.entity.PlayerEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlayerRepository implements PanacheRepository<PlayerEntity>{
    
}
