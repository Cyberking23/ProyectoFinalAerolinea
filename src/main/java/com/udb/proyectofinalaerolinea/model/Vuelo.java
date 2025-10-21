package com.udb.proyectofinalaerolinea.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "vuelos")
public class Vuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // <-- clave del problema: el nombre correcto en la BD
    @Column(name = "identificador_vuelo", nullable = false, length = 50)
    private String identificador;

    @Column(name = "origen", length = 100)
    private String origen;

    @Column(name = "destino", length = 100)
    private String destino;

    @Column(name = "distancia_km")
    private Integer distanciaKm;

    @Column(name = "duracion_horas", length = 20)
    private String duracionHoras;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "fecha_salida")
    private LocalDateTime fechaSalida;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "fecha_llegada")
    private LocalDateTime fechaLlegada;

    @Column(name = "tarifa", precision = 10, scale = 2)
    private BigDecimal tarifa;

    @Column(name = "puerta", length = 10)
    private String puerta;

    @Column(name = "check_in", length = 50)
    private String checkIn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avion_id")
    private Avion avion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aerolinea_id")
    private Aerolinea aerolinea;

    // Getters y setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getIdentificador() { return identificador; }
    public void setIdentificador(String identificador) { this.identificador = identificador; }

    public String getOrigen() { return origen; }
    public void setOrigen(String origen) { this.origen = origen; }

    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }

    public Integer getDistanciaKm() { return distanciaKm; }
    public void setDistanciaKm(Integer distanciaKm) { this.distanciaKm = distanciaKm; }

    public String getDuracionHoras() { return duracionHoras; }
    public void setDuracionHoras(String duracionHoras) { this.duracionHoras = duracionHoras; }

    public LocalDateTime getFechaSalida() { return fechaSalida; }
    public void setFechaSalida(LocalDateTime fechaSalida) { this.fechaSalida = fechaSalida; }

    public LocalDateTime getFechaLlegada() { return fechaLlegada; }
    public void setFechaLlegada(LocalDateTime fechaLlegada) { this.fechaLlegada = fechaLlegada; }

    public BigDecimal getTarifa() { return tarifa; }
    public void setTarifa(BigDecimal tarifa) { this.tarifa = tarifa; }

    public String getPuerta() { return puerta; }
    public void setPuerta(String puerta) { this.puerta = puerta; }

    public String getCheckIn() { return checkIn; }
    public void setCheckIn(String checkIn) { this.checkIn = checkIn; }

    public Avion getAvion() { return avion; }
    public void setAvion(Avion avion) { this.avion = avion; }

    public Aerolinea getAerolinea() { return aerolinea; }
    public void setAerolinea(Aerolinea aerolinea) { this.aerolinea = aerolinea; }


}
