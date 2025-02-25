package org.apsidart.dart.performance;

import java.util.List;
import java.util.Optional;

import org.apsidart.dart.performance.entity.DartPerformanceEntity;
import org.jboss.logging.Logger;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DartPerformanceRepository implements PanacheRepository<DartPerformanceEntity> {

    private static final Logger LOG = Logger.getLogger(DartPerformanceRepository.class);
    
    public Optional<DartPerformanceEntity> findByIdGameAndPlayer(Long idGame, Long idPlayer){
        return find("SELECT dp FROM DartPerformanceEntity dp WHERE dp.idPlayer = :idPlayer and dp.idGame = :idGame", Parameters.with("idGame", idGame).and("idPlayer", idPlayer)).firstResultOptional();
    }

    public List<DartPerformanceEntity> findByIdGame(Long idGame){
        return find("SELECT dp FROM DartPerformanceEntity dp WHERE  dp.idGame = :idGame", Parameters.with("idGame", idGame)).list();
    }

    public List<DartPerformanceEntity> findByIdPlayer(Long idPlayer){
        return find("SELECT dp FROM DartPerformanceEntity dp WHERE dp.idPlayer = :idPlayer", Parameters.with("idPlayer", idPlayer)).list();
    }

}
