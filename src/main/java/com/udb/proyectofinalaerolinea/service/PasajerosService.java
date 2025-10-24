// PasajerosService.java
package com.udb.proyectofinalaerolinea.service;

import com.udb.proyectofinalaerolinea.model.Pasajeros;

import java.util.List;
import java.util.Optional;

public interface PasajerosService {
    List<Pasajeros> findAll();
    Optional<Pasajeros> findById(Long id);
    Pasajeros save(Pasajeros pasajero);
    Pasajeros update(Long id, Pasajeros pasajero);
    void deleteById(Long id);
    List<Pasajeros> findByRegistradoPor(Long usuarioId);
}