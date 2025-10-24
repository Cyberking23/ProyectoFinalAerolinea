package com.udb.proyectofinalaerolinea.dto;

import com.udb.proyectofinalaerolinea.validation.FieldMatch;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@AllArgsConstructor
@Getter @Setter
@FieldMatch(first = "contrasena", second = "repetir", message = "Las contraseñas no coinciden")
@Schema(name = "RegistroClienteRequest",
        description = "Datos de alta de cliente. Las contraseñas deben coincidir.")
public class RegistroClienteRequest {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "Máximo 100 caracteres")
    @Schema(description = "Nombre completo del cliente", example = "Ana Rodríguez", requiredMode = REQUIRED)
    private String nombre;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Formato de correo inválido")
    @Size(max = 100, message = "Máximo 100 caracteres")
    @Schema(description = "Correo electrónico del cliente", example = "ana.rodriguez@mail.com", requiredMode = REQUIRED)
    private String correo;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, max = 100, message = "Debe tener entre 6 y 100 caracteres")
    @Schema(description = "Contraseña del cliente", example = "MiClave2025!", requiredMode = REQUIRED)
    private String contrasena;

    @NotBlank(message = "Repite la contraseña")
    @Schema(description = "Repetición de la contraseña", example = "MiClave2025!", requiredMode = REQUIRED)
    private String repetir;
}
