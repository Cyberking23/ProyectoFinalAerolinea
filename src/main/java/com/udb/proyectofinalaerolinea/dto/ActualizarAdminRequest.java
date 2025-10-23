// dto/ActualizarAdminRequest.java
package com.udb.proyectofinalaerolinea.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ActualizarAdminRequest {

    @NotNull
    private Long id;

    @NotBlank @Size(max = 100)
    private String nombre;

    @NotBlank @Email @Size(max = 100)
    private String email;

    // Campo opcional: si viene vacío, no se cambia
    @Size(min = 8, max = 100, message = "La nueva contraseña debe tener al menos 8 caracteres")
    private String nuevoPassword;

    // getters/setters
}
