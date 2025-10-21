package com.udb.proyectofinalaerolinea.repository;

import com.udb.proyectofinalaerolinea.model.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VueloRepository extends JpaRepository<Vuelo, Long> {
    Optional<Vuelo> findByIdentificador(String identificador);
    boolean existsByIdentificador(String identificador);
}