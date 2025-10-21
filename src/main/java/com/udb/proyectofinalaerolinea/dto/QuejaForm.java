package com.udb.proyectofinalaerolinea.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class QuejaForm {

    @Size(max = 20)
    private String identificadorVuelo;  // opcional

    @NotBlank @Size(max = 150)
    private String asunto;

    @NotBlank
    private String descripcion;

    // getters/setters
    public String getIdentificadorVuelo() { return identificadorVuelo; }
    public void setIdentificadorVuelo(String identificadorVuelo) { this.identificadorVuelo = identificadorVuelo; }
    public String getAsunto() { return asunto; }
    public void setAsunto(String asunto) { this.asunto = asunto; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}