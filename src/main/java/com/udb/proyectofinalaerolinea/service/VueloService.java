// src/main/java/com/udb/proyectofinalaerolinea/service/VueloService.java
package com.udb.proyectofinalaerolinea.service;

import com.udb.proyectofinalaerolinea.model.Vuelo;
import java.util.List;

public interface VueloService {
    List<Vuelo> listarTodos();
    Vuelo obtenerPorId(Long id);
    Vuelo guardar(Vuelo vuelo, Long avionId, Long aerolineaId);
    Vuelo actualizar(Long id, Vuelo vuelo, Long avionId, Long aerolineaId);
    void eliminar(Long id);
}
