package com.udb.proyectofinalaerolinea.controller.api;

import com.udb.proyectofinalaerolinea.api.ApiError;
import com.udb.proyectofinalaerolinea.dto.ActualizarAdminRequest;
import com.udb.proyectofinalaerolinea.dto.CambiarEstadoUsuarioRequest;
import com.udb.proyectofinalaerolinea.dto.CrearAdminRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Admins", description = "Gestión de administradores y estado de usuarios")
@SecurityRequirement(name = "bearerAuth") // JWT requerido
@RestController
@RequestMapping("/api/v1")
public class AdminRestDocController {

    @Operation(summary = "Crear administrador")
    @ApiResponse(responseCode = "201", description = "Administrador creado")
    @ApiResponse(responseCode = "400", description = "Datos inválidos",
            content = @Content(schema = @Schema(implementation = ApiError.class)))
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(schema = @Schema(implementation = CrearAdminRequest.class),
                    examples = @ExampleObject(value = """
            { "nombre":"Carlos Méndez",
              "email":"carlos.mendez@empresa.com",
              "password":"ClaveFuerte#2025" }""")))
    @PostMapping("/admins")
    public ResponseEntity<Void> crearAdmin(@Valid @RequestBody CrearAdminRequest body) {
        return ResponseEntity.status(501).build(); // placeholder: solo documentación
    }

    @Operation(summary = "Actualizar datos de un administrador")
    @ApiResponse(responseCode = "200", description = "Actualizado")
    @ApiResponse(responseCode = "400", description = "Datos inválidos",
            content = @Content(schema = @Schema(implementation = ApiError.class)))
    @ApiResponse(responseCode = "404", description = "No encontrado",
            content = @Content(schema = @Schema(implementation = ApiError.class)))
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(schema = @Schema(implementation = ActualizarAdminRequest.class),
                    examples = @ExampleObject(value = """
            { "id":7,
              "nombre":"María López",
              "email":"maria.lopez@empresa.com",
              "nuevoPassword":"NuevaClaveSegura123!" }""")))
    @PutMapping("/admins")
    public ResponseEntity<Void> actualizarAdmin(@Valid @RequestBody ActualizarAdminRequest body) {
        return ResponseEntity.status(501).build();
    }

    @Operation(summary = "Cambiar estado de un usuario")
    @ApiResponse(responseCode = "200", description = "Estado actualizado")
    @ApiResponse(responseCode = "400", description = "Datos inválidos",
            content = @Content(schema = @Schema(implementation = ApiError.class)))
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
            content = @Content(schema = @Schema(implementation = ApiError.class)))
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(schema = @Schema(implementation = CambiarEstadoUsuarioRequest.class),
                    examples = @ExampleObject(value = """
            { "usuarioId":42, "estado":"Activo" }""")))
    @PatchMapping("/usuarios/estado")
    public ResponseEntity<Void> cambiarEstado(@Valid @RequestBody CambiarEstadoUsuarioRequest body) {
        return ResponseEntity.status(501).build();
    }
}
