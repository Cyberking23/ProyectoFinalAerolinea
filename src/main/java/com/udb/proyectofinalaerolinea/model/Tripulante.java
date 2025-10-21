package com.udb.proyectofinalaerolinea.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "tripulantes")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Tripulante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100)
    private String nombre;

    @NotBlank(message = "El rol es obligatorio")
    @Size(max = 50)
    private String rol;

    @Column(length = 10) // "Activo" | "Inactivo"
    private String disponibilidad = "Activo";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aerolinea_id")
    private Aerolinea aerolinea;
}
