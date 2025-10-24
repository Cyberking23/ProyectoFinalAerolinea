package com.udb.proyectofinalaerolinea.controller.api;

import com.udb.proyectofinalaerolinea.api.ApiError;
import com.udb.proyectofinalaerolinea.dto.RegistroClienteRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Clientes", description = "Registro y gestión de clientes")
@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteRestDocController {

    // Este endpoint normalmente sería público, si quieres protégelo quitando permitAll() en Security
    @Operation(summary = "Registro de cliente")
    @ApiResponse(responseCode = "201", description = "Cliente registrado")
    @ApiResponse(responseCode = "400", description = "Datos inválidos",
            content = @Content(schema = @Schema(implementation = ApiError.class)))
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(schema = @Schema(implementation = RegistroClienteRequest.class),
                    examples = @ExampleObject(value = """
            { "nombre":"Ana Rodríguez",
              "correo":"ana.rodriguez@mail.com",
              "contrasena":"MiClave2025!",
              "repetir":"MiClave2025!" }""")))
    @PostMapping("/registro")
    public ResponseEntity<Void> registrar(@Valid @RequestBody RegistroClienteRequest body) {
        return ResponseEntity.status(501).build(); // placeholder
    }
}
