package com.udb.proyectofinalaerolinea.controller.api;

import com.udb.proyectofinalaerolinea.api.ApiError;
import com.udb.proyectofinalaerolinea.model.Vuelo;
import com.udb.proyectofinalaerolinea.service.VueloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Tag(name = "Vuelos", description = "Operaciones CRUD para vuelos")
@SecurityRequirement(name = "bearerAuth") // JWT requerido
@RestController
@RequestMapping("/api/v1/vuelos")
public class VueloRestController {

    private final VueloService vueloService;
    public VueloRestController(VueloService vueloService) { this.vueloService = vueloService; }

    // ===== DTOs locales para Swagger (si ya los tienes en otro paquete, úsalos y borra esto) =====
    @Schema(name = "VueloCreateDto", description = "Datos para crear/actualizar un vuelo")
    public record VueloCreateDto(
            @Schema(example = "ACS1010") String identificador,
            @Schema(example = "SAL") String origen,
            @Schema(example = "LAX") String destino,
            @Schema(example = "2025-12-01T07:30:00") LocalDateTime fechaSalida,
            @Schema(example = "2025-12-01T12:15:00") LocalDateTime fechaLlegada,
            @Schema(example = "580.00") BigDecimal tarifa,
            @Schema(example = "1") @NotNull Long avionId,
            @Schema(example = "1") @NotNull Long aerolineaId
    ) {}

    @Schema(name = "VueloResponseDto", description = "Vuelo expuesto por la API")
    public record VueloResponseDto(
            Long id, String identificador, String origen, String destino,
            LocalDateTime fechaSalida, LocalDateTime fechaLlegada,
            BigDecimal tarifa, String avionModelo, String aerolineaNombre
    ) {}

    private VueloResponseDto toDto(Vuelo v) {
        return new VueloResponseDto(
                v.getId(), v.getIdentificador(), v.getOrigen(), v.getDestino(),
                v.getFechaSalida(), v.getFechaLlegada(), v.getTarifa(),
                (v.getAvion()!=null ? v.getAvion().getModelo() : null),
                (v.getAerolinea()!=null ? v.getAerolinea().getNombre() : null)
        );
    }

    // ===== Endpoints =====

    @Operation(summary = "Listar vuelos")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = VueloResponseDto.class))))
    @GetMapping
    public ResponseEntity<List<VueloResponseDto>> listar() {
        return ResponseEntity.ok(vueloService.listarTodos().stream().map(this::toDto).toList());
    }

    @Operation(summary = "Obtener vuelo por ID")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = VueloResponseDto.class)))
    @ApiResponse(responseCode = "404", description = "No encontrado",
            content = @Content(schema = @Schema(implementation = ApiError.class)))
    @GetMapping("/{id}")
    public ResponseEntity<VueloResponseDto> obtener(@PathVariable Long id) {
        var v = vueloService.obtenerPorId(id);
        if (v == null) throw new NoSuchElementException("Vuelo no encontrado");
        return ResponseEntity.ok(toDto(v));
    }

    @Operation(summary = "Crear vuelo")
    @ApiResponse(responseCode = "201", description = "Creado",
            content = @Content(schema = @Schema(implementation = VueloResponseDto.class),
                    examples = @ExampleObject(value = """
                    { "id":10,"identificador":"ACS1010","origen":"SAL","destino":"LAX",
                      "fechaSalida":"2025-12-01T07:30:00","fechaLlegada":"2025-12-01T12:15:00",
                      "tarifa":580.00,"avionModelo":"Boeing 737-800","aerolineaNombre":"AeroCuscatlán"}""")))
    @ApiResponse(responseCode = "400", description = "Datos inválidos",
            content = @Content(schema = @Schema(implementation = ApiError.class)))
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(schema = @Schema(implementation = VueloCreateDto.class),
                    examples = @ExampleObject(value = """
                    { "identificador":"ACS1010","origen":"SAL","destino":"LAX",
                      "fechaSalida":"2025-12-01T07:30:00","fechaLlegada":"2025-12-01T12:15:00",
                      "tarifa":580.0,"avionId":1,"aerolineaId":1 }""")))
    @PostMapping
    public ResponseEntity<VueloResponseDto> crear(@RequestBody VueloCreateDto body) {
        var v = new Vuelo();
        v.setIdentificador(body.identificador());
        v.setOrigen(body.origen());
        v.setDestino(body.destino());
        v.setFechaSalida(body.fechaSalida());
        v.setFechaLlegada(body.fechaLlegada());
        v.setTarifa(body.tarifa());
        var saved = vueloService.guardar(v, body.avionId(), body.aerolineaId());
        return ResponseEntity.status(201).body(toDto(saved));
    }

    @Operation(summary = "Actualizar vuelo")
    @ApiResponse(responseCode = "200", description = "Actualizado",
            content = @Content(schema = @Schema(implementation = VueloResponseDto.class)))
    @ApiResponse(responseCode = "404", description = "No encontrado",
            content = @Content(schema = @Schema(implementation = ApiError.class)))
    @PutMapping("/{id}")
    public ResponseEntity<VueloResponseDto> actualizar(@PathVariable Long id, @RequestBody VueloCreateDto body) {
        var v = new Vuelo();
        v.setIdentificador(body.identificador());
        v.setOrigen(body.origen());
        v.setDestino(body.destino());
        v.setFechaSalida(body.fechaSalida());
        v.setFechaLlegada(body.fechaLlegada());
        v.setTarifa(body.tarifa());
        var updated = vueloService.actualizar(id, v, body.avionId(), body.aerolineaId());
        return ResponseEntity.ok(toDto(updated));
    }

    @Operation(summary = "Eliminar vuelo")
    @ApiResponse(responseCode = "204", description = "Eliminado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        vueloService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
