// src/main/java/com/udb/proyectofinalaerolinea/service/RegistrationService.java
package com.udb.proyectofinalaerolinea.service;

import com.udb.proyectofinalaerolinea.model.Usuario;

public interface RegistrationService {
    Usuario registerCliente(String nombre, String correo, String rawPassword);
}
