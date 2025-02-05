package org.apsidart.dart.stat;

import java.util.List;
import java.util.Optional;

import org.apsidart.dart.stat.entity.DartStatPlayerEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DartStatRepository implements PanacheRepository<DartStatPlayerEntity> {

    public List<DartStatPlayerEntity> findAllStatByIdPlayer(Long idPlayer){
        return find("SELECT s FROM DartStatPlayerEntity s WHERE s.idPlayer = :idPlayer ORDER BY s.date DESC", 
            Parameters.with("idPlayer", idPlayer))
            .list();
    }

    public List<DartStatPlayerEntity> findAllStatByIdPlayerAndTypeJeu(Long idPlayer, String typeJeu){
        return find("SELECT s FROM DartStatPlayerEntity s WHERE s.idPlayer = :idPlayer AND s.typeJeu = :typeJeu ORDER BY s.date DESC", 
            Parameters.with("idPlayer", idPlayer).and("typeJeu", typeJeu))
            .list();
    }

    public Optional<DartStatPlayerEntity> findLastStatByIdPlayer(Long idPlayer){
        return find("SELECT s FROM DartStatPlayerEntity s WHERE s.idPlayer = :idPlayer ORDER BY s.date DESC", 
            Parameters.with("idPlayer", idPlayer))
            .firstResultOptional();
    }

    public Optional<DartStatPlayerEntity> findLastStatByIdPlayerAndTypeJeu(Long idPlayer, String typeJeu){
        return find("SELECT s FROM DartStatPlayerEntity s WHERE s.idPlayer = :idPlayer AND s.typeJeu = :typeJeu ORDER BY s.date DESC", 
            Parameters.with("idPlayer", idPlayer).and("typeJeu", typeJeu))
            .firstResultOptional();
    }

}
