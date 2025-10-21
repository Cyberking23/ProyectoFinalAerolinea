package com.udb.proyectofinalaerolinea.repository;

import com.udb.proyectofinalaerolinea.model.Aerolinea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface AerolineaRepository extends JpaRepository<Aerolinea, Long> {
    boolean existsByCodigoIataIcao(String codigoIataIcao);
    Optional<Aerolinea> findByCodigoIataIcao(String codigoIataIcao);
}

