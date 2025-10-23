package com.udb.proyectofinalaerolinea.service;

import com.udb.proyectofinalaerolinea.model.Administrador;

// service/AdministradorService.java
public interface AdministradorService {
    Administrador crearAdmin(String nombre, String email, String rawPassword);
    Administrador actualizarAdmin(Long adminId, String nombre, String email, String nuevoPasswordNullable);
    void eliminarAdmin(Long adminId);
}