package com.udb.proyectofinalaerolinea.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "pasajeros")
public class Pasajeros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellido;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "numero_pasaporte", nullable = false, length = 50) // Agregado nullable = false
    private String numeroPasaporte;

    @Column(name = "correo_electronico", nullable = false, length = 150) // Agregado name y length
    private String correoElectronico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registrado_por") // Quitamos nullable = false
    private Usuario registradoPor;

    @CreationTimestamp
    @Column(name = "creado_en", nullable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    public Pasajeros(String nombre, String apellido, LocalDate fechaNacimiento,
                     String numeroPasaporte, String correoElectronico, Usuario registradoPor) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.numeroPasaporte = numeroPasaporte;
        this.correoElectronico = correoElectronico;
        this.registradoPor = registradoPor;
    }
}