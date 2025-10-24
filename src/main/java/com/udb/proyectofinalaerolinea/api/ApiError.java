package com.udb.proyectofinalaerolinea.api;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.Instant;

@Schema(description = "Estructura estándar de error")
public class ApiError {
    @Schema(example = "/api/v1/vuelos/99") public String path;
    @Schema(example = "404")               public int status;
    @Schema(example = "Not Found")         public String error;
    @Schema(example = "Vuelo no encontrado") public String message;
    @Schema(example = "2025-01-01T12:00:00Z") public Instant timestamp = Instant.now();

    public ApiError() {}
    public ApiError(String path, int status, String error, String message) {
        this.path = path; this.status = status; this.error = error; this.message = message;
    }
}
