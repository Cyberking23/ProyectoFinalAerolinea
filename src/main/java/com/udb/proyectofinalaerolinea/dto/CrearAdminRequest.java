// dto/CrearAdminRequest.java
package com.udb.proyectofinalaerolinea.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrearAdminRequest {
    @NotBlank @Size(max = 100)
    private String nombre;

    @NotBlank @Email @Size(max = 100)
    private String email;

    @NotBlank @Size(min = 8, max = 100, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;

    // getters/setters
}
