package com.udb.proyectofinalaerolinea.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reservaciones")
public class Reservacion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_reservacion", nullable = false)
    private LocalDate fechaReservacion;

    @Column(nullable = false, length = 100)
    private String pasajero;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoReservacion estado = EstadoReservacion.ACTIVA;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vuelo_id")
    private Vuelo vuelo;

    // getters/setters
    public Long getId() { return id; }
    public LocalDate getFechaReservacion() { return fechaReservacion; }
    public void setFechaReservacion(LocalDate fechaReservacion) { this.fechaReservacion = fechaReservacion; }
    public String getPasajero() { return pasajero; }
    public void setPasajero(String pasajero) { this.pasajero = pasajero; }
    public EstadoReservacion getEstado() { return estado; }
    public void setEstado(EstadoReservacion estado) { this.estado = estado; }
    public Vuelo getVuelo() { return vuelo; }
    public void setVuelo(Vuelo vuelo) { this.vuelo = vuelo; }
}
