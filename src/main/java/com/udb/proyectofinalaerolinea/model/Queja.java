package com.udb.proyectofinalaerolinea.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "quejas")
public class Queja {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_reclamo", nullable = false)
    private LocalDate fechaReclamo;

    @NotBlank @Size(max = 150)
    private String asunto;

    @Lob
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoQueja estado = EstadoQueja.PENDIENTE;

    // Opcional: enlazar a un vuelo existente
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vuelo_id")
    private Vuelo vuelo;

    // Getters & setters
    public Long getId() { return id; }
    public LocalDate getFechaReclamo() { return fechaReclamo; }
    public void setFechaReclamo(LocalDate fechaReclamo) { this.fechaReclamo = fechaReclamo; }
    public String getAsunto() { return asunto; }
    public void setAsunto(String asunto) { this.asunto = asunto; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public EstadoQueja getEstado() { return estado; }
    public void setEstado(EstadoQueja estado) { this.estado = estado; }
    public Vuelo getVuelo() { return vuelo; }
    public void setVuelo(Vuelo vuelo) { this.vuelo = vuelo; }
}
