package com.udb.proyectofinalaerolinea.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "aerolineas")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Schema(description = "Operador de transporte aéreo")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Aerolinea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID interno de la aerolínea", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "El nombre de la aerolínea es obligatorio")
    @Size(max = 100)
    @Schema(description = "Nombre comercial de la aerolínea", example = "AeroCuscatlán")
    private String nombre;

    @NotBlank(message = "El código IATA/ICAO es obligatorio")
    @Column(name = "codigo_iata_icao", unique = true, length = 10)
    @Schema(description = "Código IATA/ICAO", example = "AC / ACS")
    private String codigoIataIcao;

    @NotBlank(message = "El país de origen es obligatorio")
    @Column(name = "pais_origen", length = 60)
    @Schema(description = "País de origen", example = "El Salvador")
    private String paisOrigen;

    @Column(length = 15)
    @Schema(description = "Estado operativo", example = "Activo")
    private String estado = "Activo"; // "Activo" | "Inactivo"

    // Si declaras relaciones inversas, por ejemplo:
    // @OneToMany(mappedBy = "aerolinea", fetch = FetchType.LAZY)
    // @com.fasterxml.jackson.annotation.JsonIgnore
    // @Schema(hidden = true)
    // private java.util.List<Avion> aviones;
}
