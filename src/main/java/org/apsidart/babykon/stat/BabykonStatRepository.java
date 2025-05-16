package org.apsidart.babykon.stat;

import java.util.Optional;

import org.apsidart.babykon.stat.entity.BabykonStatEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BabykonStatRepository implements PanacheRepository<BabykonStatEntity>{

        public Optional<BabykonStatEntity> findStatByIdPlayer(long idPlayer){
        return find("SELECT s FROM BabykonStatEntity s WHERE s.player.id = :idPlayer", 
            Parameters.with("idPlayer", idPlayer))
            .firstResultOptional();
    }
    
}
