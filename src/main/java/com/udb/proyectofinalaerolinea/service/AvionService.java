// src/main/java/com/udb/proyectofinalaerolinea/service/AvionService.java
package com.udb.proyectofinalaerolinea.service;

import com.udb.proyectofinalaerolinea.model.Avion;

import java.util.List;

public interface AvionService {
    List<Avion> listarTodos();
    Avion obtenerPorId(Long id);
    Avion guardar(Avion avion, Long aerolineaId);
    Avion actualizar(Long id, Avion avion, Long aerolineaId);
    void eliminar(Long id);
    boolean existeMatricula(String matricula);
}
