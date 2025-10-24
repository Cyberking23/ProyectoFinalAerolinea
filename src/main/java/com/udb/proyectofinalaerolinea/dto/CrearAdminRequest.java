package com.udb.proyectofinalaerolinea.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Getter @Setter
@Schema(name = "CrearAdminRequest", description = "Datos para registrar un nuevo administrador")
public class CrearAdminRequest {

    @NotBlank @Size(max = 100)
    @Schema(description = "Nombre completo", example = "Carlos Méndez", requiredMode = REQUIRED)
    private String nombre;

    @NotBlank @Email @Size(max = 100)
    @Schema(description = "Correo electrónico", example = "carlos.mendez@empresa.com", requiredMode = REQUIRED)
    private String email;

    @NotBlank @Size(min = 8, max = 100, message = "La contraseña debe tener al menos 8 caracteres")
    @Schema(description = "Contraseña del administrador", example = "ClaveFuerte#2025", requiredMode = REQUIRED)
    private String password;
}
