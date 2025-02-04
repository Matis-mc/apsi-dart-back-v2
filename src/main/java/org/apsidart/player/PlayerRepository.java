package org.apsidart.player;

import org.apsidart.player.entity.PlayerEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlayerRepository implements PanacheRepository<PlayerEntity>{
    
}
