package org.apsidart.babykon.game;

import org.apsidart.babykon.game.entity.BabykonGameEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BabykonGameRepository implements PanacheRepository<BabykonGameEntity>{
    
}
