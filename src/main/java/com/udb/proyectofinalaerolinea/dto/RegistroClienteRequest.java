// dto/RegistroClienteRequest.java
package com.udb.proyectofinalaerolinea.dto;

import com.udb.proyectofinalaerolinea.validation.FieldMatch;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@FieldMatch(first = "contrasena", second = "repetir", message = "Las contraseñas no coinciden")
public class RegistroClienteRequest {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "Máximo 100 caracteres")
    private String nombre;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Formato de correo inválido")
    @Size(max = 100, message = "Máximo 100 caracteres")
    private String correo;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, max = 100, message = "Debe tener entre 6 y 100 caracteres")
    private String contrasena;

    @NotBlank(message = "Repite la contraseña")
    private String repetir;

    // getters/setters
}
