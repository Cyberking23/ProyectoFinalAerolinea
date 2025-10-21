package com.udb.proyectofinalaerolinea.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "aerolineas")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Aerolinea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la aerolínea es obligatorio")
    @Size(max = 100)
    private String nombre;

    @NotBlank(message = "El código IATA/ICAO es obligatorio")
    @Column(name = "codigo_iata_icao", unique = true, length = 10)
    private String codigoIataIcao;

    @NotBlank(message = "El país de origen es obligatorio")
    @Column(name = "pais_origen", length = 60)
    private String paisOrigen;

    @Column(length = 15)
    private String estado = "Activo"; // "Activo" | "Inactivo"
}
