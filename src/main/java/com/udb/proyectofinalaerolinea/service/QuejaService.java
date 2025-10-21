package com.udb.proyectofinalaerolinea.service;

import com.udb.proyectofinalaerolinea.model.EstadoQueja;
import com.udb.proyectofinalaerolinea.model.Queja;

import java.util.List;

public interface QuejaService {
    Queja crearDesdePublico(String identificadorVuelo, String asunto, String descripcion);
    List<Queja> listarTodas();
    Queja obtenerPorId(Long id);
    void actualizarEstado(Long id, EstadoQueja nuevoEstado);
    void eliminar(Long id);
}
