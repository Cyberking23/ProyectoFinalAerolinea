package com.udb.proyectofinalaerolinea.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(name = "QuejaForm", description = "Formulario de reclamo del cliente")
public class QuejaForm {

    @Size(max = 20)
    @Schema(description = "Identificador del vuelo relacionado (opcional)", example = "ACS1001")
    private String identificadorVuelo;

    @NotBlank @Size(max = 150)
    @Schema(description = "Asunto o título de la queja", example = "Retraso en el vuelo ACS1001")
    private String asunto;

    @NotBlank
    @Schema(description = "Descripción detallada de la queja",
            example = "El vuelo se retrasó 3 horas y perdí mi conexión. Solicito compensación.")
    private String descripcion;

    // getters/setters
    public String getIdentificadorVuelo() { return identificadorVuelo; }
    public void setIdentificadorVuelo(String identificadorVuelo) { this.identificadorVuelo = identificadorVuelo; }
    public String getAsunto() { return asunto; }
    public void setAsunto(String asunto) { this.asunto = asunto; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
