package com.udb.proyectofinalaerolinea.service;

import com.udb.proyectofinalaerolinea.model.Tripulante;

import java.util.List;

public interface TripulanteService {
    List<Tripulante> listarTodos();
    Tripulante obtenerPorId(Long id);
    Tripulante guardar(Tripulante t, Long aerolineaId);
    Tripulante actualizar(Long id, Tripulante t, Long aerolineaId);
    void eliminar(Long id);
}
