package com.udb.proyectofinalaerolinea.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Schema(name = "CambiarEstadoUsuarioRequest",
        description = "Solicitud para activar o inactivar un usuario")
public class CambiarEstadoUsuarioRequest {

    @NotNull
    @Schema(description = "ID del usuario", example = "42", requiredMode = REQUIRED)
    private Long usuarioId;

    @NotBlank
    @Pattern(regexp = "Activo|Inactivo", message = "Estado inválido")
    @Schema(description = "Nuevo estado del usuario (valores permitidos: Activo, Inactivo)",
            example = "Activo", requiredMode = REQUIRED)
    private String estado;
}
