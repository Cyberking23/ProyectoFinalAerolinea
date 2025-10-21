package com.udb.proyectofinalaerolinea.service;

import com.udb.proyectofinalaerolinea.model.Queja;

import java.util.List;
import java.util.Map;

public interface EstadisticasService {
    Map<String, Long> resumenTotales();         // tarjetas
    List<Queja> ultimasQuejas(int limite);      // tabla
}
