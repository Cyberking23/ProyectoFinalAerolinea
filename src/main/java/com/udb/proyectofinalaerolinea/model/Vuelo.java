package com.udb.proyectofinalaerolinea.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "vuelos")
@Schema(description = "Vuelo programado con avión y aerolínea")
public class Vuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID interno del vuelo", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    // nombre de columna tal como está en la BD
    @Column(name = "identificador_vuelo", nullable = false, length = 50)
    @Schema(description = "Código/identificador de vuelo", example = "ACS1001", requiredMode = Schema.RequiredMode.REQUIRED)
    private String identificador;

    @Column(name = "origen", length = 100)
    @Schema(description = "Código IATA/ciudad de origen", example = "SAL")
    private String origen;

    @Column(name = "destino", length = 100)
    @Schema(description = "Código IATA/ciudad de destino", example = "MIA")
    private String destino;

    @Column(name = "distancia_km")
    @Schema(description = "Distancia aproximada en kilómetros", example = "1650")
    private Integer distanciaKm;

    @Column(name = "duracion_horas", length = 20)
    @Schema(description = "Duración total (hh:mm)", example = "02:40")
    private String duracionHoras;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) // para formularios MVC
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")      // para JSON (Swagger)
    @Column(name = "fecha_salida")
    @Schema(description = "Fecha/hora de salida", example = "2025-11-06T08:15:00")
    private LocalDateTime fechaSalida;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "fecha_llegada")
    @Schema(description = "Fecha/hora de llegada", example = "2025-11-06T10:45:00")
    private LocalDateTime fechaLlegada;

    @Column(name = "tarifa", precision = 10, scale = 2)
    @Schema(description = "Precio base del vuelo (USD)", example = "299.99")
    private BigDecimal tarifa;

    @Column(name = "puerta", length = 10)
    @Schema(description = "Puerta de embarque", example = "A2")
    private String puerta;

    @Column(name = "check_in", length = 50)
    @Schema(description = "Modo de check-in", example = "Online")
    private String checkIn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avion_id")
    @JsonIgnoreProperties(value = {"vuelos", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    @Schema(description = "Avión asignado")
    private Avion avion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aerolinea_id")
    @JsonIgnoreProperties(value = {"vuelos", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    @Schema(description = "Aerolínea operadora")
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
