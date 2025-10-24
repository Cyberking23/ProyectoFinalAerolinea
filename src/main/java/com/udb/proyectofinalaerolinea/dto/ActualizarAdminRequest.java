package com.udb.proyectofinalaerolinea.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Schema(name = "ActualizarAdminRequest",
        description = "Datos para actualizar un administrador existente. Si 'nuevoPassword' viene vacío, la contraseña no cambia.")
public class ActualizarAdminRequest {

    @NotNull
    @Schema(description = "ID del administrador", example = "7", requiredMode = REQUIRED)
    private Long id;

    @NotBlank @Size(max = 100)
    @Schema(description = "Nombre completo", example = "María López", requiredMode = REQUIRED)
    private String nombre;

    @NotBlank @Email @Size(max = 100)
    @Schema(description = "Correo electrónico", example = "maria.lopez@empresa.com", requiredMode = REQUIRED)
    private String email;

    @Size(min = 8, max = 100, message = "La nueva contraseña debe tener al menos 8 caracteres")
    @Schema(description = "Nueva contraseña (opcional). Si se omite o es vacía, no se modifica.",
            example = "NuevaClaveSegura123!")
    private String nuevoPassword;
}
