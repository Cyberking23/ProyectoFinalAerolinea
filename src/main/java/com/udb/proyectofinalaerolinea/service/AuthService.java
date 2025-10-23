package com.udb.proyectofinalaerolinea.service;

public interface AuthService {
    void registrarCliente(String nombre, String email, String rawPassword);
    String emitirTokenPorEmail(String email);       // si ya conoces el email
    String emitirTokenAutenticado(String email);    // desde Authentication
}
