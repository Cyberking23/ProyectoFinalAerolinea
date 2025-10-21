package com.udb.proyectofinalaerolinea.service;

import com.udb.proyectofinalaerolinea.model.Aerolinea;

import java.util.List;

public interface AerolineaService {
    List<Aerolinea> listarTodas();
    Aerolinea obtenerPorId(Long id);
    Aerolinea guardar(Aerolinea aerolinea);
    Aerolinea actualizar(Long id, Aerolinea aerolinea);
    void eliminar(Long id);
}
