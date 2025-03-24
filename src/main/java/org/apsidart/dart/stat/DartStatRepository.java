package org.apsidart.dart.stat;

import java.util.List;
import java.util.Optional;

import org.apsidart.dart.stat.entity.StatPlayerEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DartStatRepository implements PanacheRepository<StatPlayerEntity> {

    public List<StatPlayerEntity> findAllStatByIdPlayer(Long idPlayer){
        return find("SELECT s FROM StatPlayerEntity s WHERE s.idPlayer = :idPlayer ORDER BY s.date DESC", 
            Parameters.with("idPlayer", idPlayer))
            .list();
    }

    public List<StatPlayerEntity> findAllStatByIdPlayerAndTypeJeu(Long idPlayer, String typeJeu){
        return find("SELECT s FROM StatPlayerEntity s WHERE s.idPlayer = :idPlayer AND s.typeJeu = :typeJeu ORDER BY s.date DESC", 
            Parameters.with("idPlayer", idPlayer).and("typeJeu", typeJeu))
            .list();
    }

    public Optional<StatPlayerEntity> findLastStatByIdPlayer(Long idPlayer){
        return find("SELECT s FROM StatPlayerEntity s WHERE s.idPlayer = :idPlayer ORDER BY s.date DESC", 
            Parameters.with("idPlayer", idPlayer))
            .firstResultOptional();
    }

    public Optional<StatPlayerEntity> findLastStatByIdPlayerAndTypeJeu(Long idPlayer, String typeJeu){
        return find("SELECT s FROM StatPlayerEntity s WHERE s.idPlayer = :idPlayer AND s.typeJeu = :typeJeu ORDER BY s.date DESC", 
            Parameters.with("idPlayer", idPlayer).and("typeJeu", typeJeu))
            .firstResultOptional();
    }

}
