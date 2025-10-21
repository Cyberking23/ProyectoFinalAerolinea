// src/main/java/com/udb/proyectofinalaerolinea/repository/AvionRepository.java
package com.udb.proyectofinalaerolinea.repository;

import com.udb.proyectofinalaerolinea.model.Avion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvionRepository extends JpaRepository<Avion, Long> {
    boolean existsByMatricula(String matricula);
}
