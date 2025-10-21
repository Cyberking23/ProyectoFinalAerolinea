package com.udb.proyectofinalaerolinea.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "aviones")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Avion {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La matrícula es obligatoria")
    @Column(nullable = false, length = 20, unique = true)
    private String matricula;

    @NotBlank(message = "El modelo es obligatorio")
    @Column(nullable = false, length = 60)
    private String modelo;

    @Min(value = 1, message = "Capacidad mínima: 1")
    @Column(nullable = false)
    private int capacidad;

    @NotBlank(message = "El estado es obligatorio")
    @Column(nullable = false, length = 20)
    private String estado; // "Activo" | "Inactivo"

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "aerolinea_id", nullable = false)
    private Aerolinea aerolinea;

}
