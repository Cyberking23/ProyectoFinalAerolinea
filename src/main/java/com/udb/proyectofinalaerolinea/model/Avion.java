package com.udb.proyectofinalaerolinea.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "aviones")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Schema(description = "Aeronave asignada a una aerolínea")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" }) // evita errores al serializar LAZY
public class Avion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID interno del avión", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "La matrícula es obligatoria")
    @Column(nullable = false, length = 20, unique = true)
    @Schema(description = "Matrícula única del avión", example = "N123AB")
    private String matricula;

    @NotBlank(message = "El modelo es obligatorio")
    @Column(nullable = false, length = 60)
    @Schema(description = "Modelo del avión", example = "Boeing 737-800")
    private String modelo;

    @Min(value = 1, message = "Capacidad mínima: 1")
    @Column(nullable = false)
    @Schema(description = "Capacidad de pasajeros", example = "189")
    private int capacidad;

    @NotBlank(message = "El estado es obligatorio")
    @Column(nullable = false, length = 20)
    @Schema(description = "Estado operativo del avión", example = "Activo")
    private String estado; // "Activo" | "Inactivo"

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "aerolinea_id", nullable = false)
    @Schema(description = "Aerolínea propietaria/operadora")
    @JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" }, allowSetters = true)
    private Aerolinea aerolinea;

}
