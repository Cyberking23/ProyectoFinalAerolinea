// dto/CambiarEstadoUsuarioRequest.java
package com.udb.proyectofinalaerolinea.dto;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CambiarEstadoUsuarioRequest {
    @NotNull
    private Long usuarioId;

    @NotBlank
    @Pattern(regexp = "Activo|Inactivo", message = "Estado inválido")
    private String estado;

    // getters/setters
}
