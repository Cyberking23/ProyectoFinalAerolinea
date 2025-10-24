// PasajerosRepository.java
package com.udb.proyectofinalaerolinea.repository;

import com.udb.proyectofinalaerolinea.model.Pasajeros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasajerosRepository extends JpaRepository<Pasajeros, Long> {
    // Puedes agregar métodos personalizados aquí si los necesitas
    List<Pasajeros> findByRegistradoPorId(Long usuarioId);
}