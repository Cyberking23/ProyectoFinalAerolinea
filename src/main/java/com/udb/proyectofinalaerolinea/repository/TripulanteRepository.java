package com.udb.proyectofinalaerolinea.repository;

import com.udb.proyectofinalaerolinea.model.Tripulante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripulanteRepository extends JpaRepository<Tripulante, Long> { }
