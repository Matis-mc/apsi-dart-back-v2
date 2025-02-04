package org.apsidart.dart.game;

import org.apsidart.dart.game.entity.DartGameEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DartGameRepository implements PanacheRepository<DartGameEntity> {
    
}
